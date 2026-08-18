import sys
from urllib.parse import urlparse
import urllib.request
import http.server
import socketserver
import signal
import socket
import logging
import time

logging.basicConfig(level=logging.DEBUG)

try:
    output_id, address, nodes_list = sys.argv[1], sys.argv[2], sys.argv[3:]

except IndexError:
    print("Usage: log-server.py <host:port>")
    sys.exit(1)

crashed = False
local_log = []

class Paxos:
    def __init__(self, server_id, nodes):
        self.server_id = server_id  # Unique identifier for this server
        self.nodes = nodes  # List of all server addresses
        self.proposed_value = None  # Value being proposed
        self.accepted_value = None  # Value accepted by this server
        self.promised_proposal_id = None  # Highest proposal number this server has promised to ignore
        self.highest_accepted_proposal = None  # Highest proposal number this server has accepted
        # self.current_proposal_id = 0
        self.crashed = False
        self.current_proposal_id = int(time.time() * 1000)
        logging.info(f"Paxos instance created with server_id: {self.server_id}, nodes: {self.nodes}")

    def generate_proposal_id(self):
        self.current_proposal_id += 1
        proposal_id = f"{self.server_id}-{self.current_proposal_id}"
        logging.debug(f"Generated proposal_id: {proposal_id}")
        return proposal_id
    
    def send_request_with_retries(url, data, retries=3, timeout=5):
        for i in range(retries):
            try:
                req = urllib.request.Request(url, data=data, method='POST')
                with urllib.request.urlopen(req, timeout=timeout) as response:
                    return response.read().decode('utf-8')
            except Exception as e:
                logging.warning(f"Attempt {i + 1} failed for {url}: {e}")
                time.sleep(2 ** i)  # Exponential backoff
        return None

    def prepare(self, proposal_id):
        """
        Phase 1: Prepare Phase
        - Send a prepare request to all acceptors.
        - Wait for promises from a majority of acceptors.
        """
        if self.crashed:
            logging.warning("Node is crashed, ignoring prepare request")

        proposal_id = self.generate_proposal_id()
        promises = []
        for node in self.nodes:
            if node == self.server_id:
                # Handle self locally
                response = self.promise(proposal_id)
                if response:
                    promises.append(response)
            else:
                url = f"http://{node}/prepare"
                data = f"proposal_id={proposal_id}".encode('utf-8')
                response = self.send_request_with_retries(url, data)
                if response:
                    promises.append(response)

        # Check if we have a majority of promises
        if len(promises) >= (len(self.nodes) // 2) + 1:
            highest_proposal = None
            for promise in promises:
                pid, value = promise.split(':')
                if highest_proposal is None or int(pid.split('-')[1]) > int(highest_proposal.split('-')[1]):
                    highest_proposal = promise

            if highest_proposal:
                self.proposed_value = highest_proposal.split(':')[1]
                logging.info(f"Proposed value set to: {self.proposed_value}")
            else:
                self.proposed_value = self.proposed_value
                logging.info("No previous proposals, using own value")

            return True
        logging.warning("Failed to get a majority of promises")
        return False

    def promise(self, proposal_id):
        """
        Respond to a prepare request.
        - If the proposal number is higher than any we've seen, promise not to accept lower proposals.
        - Return the highest-numbered proposal we've accepted (if any).
        """
        logging.debug(f"Received prepare request with proposal_id: {proposal_id}")
        if self.promised_proposal_id is None or proposal_id > self.promised_proposal_id:
            self.promised_proposal_id = proposal_id
            if self.highest_accepted_proposal:
                response = f"{self.highest_accepted_proposal[0]}:{self.highest_accepted_proposal[1]}"
                logging.debug(f"Returning promise with highest accepted proposal: {response}")
                return response
            else:
                response = f"{proposal_id}:None"
                logging.debug(f"Returning promise with no previous proposals: {response}")
                return response
        logging.debug(f"Ignoring prepare request with proposal_id: {proposal_id}")
        return None

    def accept(self, proposal_id, value):
        """
        Phase 2: Accept Phase
        - Send accept requests to all acceptors with the chosen value.
        - Wait for a majority of acceptors to accept the proposal.
        """
        acceptances = []
        for node in self.nodes:
            if node == self.server_id:
                # Handle self locally
                response = self.accepted(proposal_id, value)
                if response:
                    acceptances.append(response)
            else:
                # Send accept request to other nodes
                try:
                    url = f"http://{node}/accept"
                    data = f"proposal_id={proposal_id}&value={value}".encode('utf-8')
                    req = urllib.request.Request(url, data=data, method='POST')
                    with urllib.request.urlopen(req) as response:
                        response_data = response.read().decode('utf-8')
                        if response_data:
                            acceptances.append(response_data)
                except Exception as e:
                    print(f"Failed to send accept request to {node}: {e}")

        # Check if we have a majority of acceptances
        if len(acceptances) >= (len(self.nodes) // 2) + 1:
            # The value is now chosen
            self.learn(value)
            return True
        return False


    def accepted(self, proposal_id, value):
        """
        Respond to an accept request.
        - Accept the proposal if we haven't promised to ignore it.
        - Return the accepted proposal.
        """
        if self.promised_proposal_id is None or proposal_id >= self.promised_proposal_id:
            self.highest_accepted_proposal = (proposal_id, value)
            return f"{proposal_id}:{value}"
        return None

    def learn(self, value):
        """
        Learn the agreed-upon value.
        - Add the value to the local log.
        """
        global local_log
        local_log.append(value)
        print(f"{self.server_id} learned value: {value}")

class LogRequestHandler(http.server.SimpleHTTPRequestHandler):

    def do_PUT(self):
        global crashed, local_log
        logging.debug(f"Received PUT request from {self.client_address}")
        
        if crashed:
            print(f"\n{self.server.server_address} Received PUT request while crashed, ignoring\n")
            return
        
        content_length = int(self.headers['Content-Length'])
        data = self.rfile.read(content_length).decode('utf-8')
        print(f"{self.server.server_address} Received PUT request with data: {data}")

        # Use Paxos to agree on the log entry
        paxos = Paxos(self.server.server_address, nodes_list)
        paxos.proposed_value = data
        proposal_id = paxos.generate_proposal_id()
        if paxos.prepare(proposal_id):
            if paxos.accept(proposal_id, paxos.proposed_value):
                paxos.learn(paxos.proposed_value)

        # Current logging logic is simple: it just appends the data to a list.
        local_log.append(data)

        self.send_response(200)
        self.end_headers()

    def do_POST(self):
        global crashed, local_log
        url = urlparse(self.path).path
        logging.debug(f"Received POST request from {self.client_address}")

        # If POST is extended, this case should be kept intact and overrule other URLs.
        if crashed and url != "/crash" and url != "/recover" and url != "/exit":
            print(f"\n{self.server.server_address} Received POST request while crashed, ignoring\n")
            return

        if url == "/crash":
            print(f"{self.server.server_address} Simulating crash...")
            logging.info(f"{self.server.server_address} Simulating crash...") # Log the crash
            crashed = True
            self.send_response(200)
            self.end_headers()
            
        elif url == "/recover":
            print(f"{self.server.server_address} Simulating recovery...")
            logging.info(f"{self.server.server_address} Simulating recovery...") # Log the recovery
            crashed = False
            self.send_response(200)
            self.end_headers()

        elif url == "/exit":
            print(f"{self.server.server_address} Exiting...")
            self.send_response(200)
            self.end_headers()
            print(f"{self.server.server_address}: {local_log}")
            with open(f"output/{output_id}-server-{self.server.server_address[0]}{self.server.server_address[1]}.csv", 'w') as f:
                for entry in local_log:
                    f.write(f"{entry}\n")

        elif url == "/prepare":
            try:
                # Handle prepare requests
                content_length = int(self.headers['Content-Length'])
                data = self.rfile.read(content_length).decode('utf-8')
                proposal_id = int(data.split('=')[1])

                paxos = Paxos(self.server.server_address, nodes_list)
                response = paxos.promise(proposal_id)

                self.send_response(200)
                self.send_header('Content-Type', 'text/plain')
                self.end_headers()
                if response:
                    try:
                        self.wfile.write(response.encode('utf-8'))
                    except BrokenPipeError:
                        logging.warning("Client disconnected before response could be sent")
            except Exception as e:
                logging.error(f"Error handling POST request: {e}")
                self.send_response(500)
                self.end_headers()

        elif url == "/accept":
            try:
                # Handle accept requests
                content_length = int(self.headers['Content-Length'])
                data = self.rfile.read(content_length).decode('utf-8')
                proposal_id = int(data.split('&')[0].split('=')[1])
                value = data.split('&')[1].split('=')[1]

                paxos = Paxos(self.server.server_address, nodes_list)
                response = paxos.accepted(proposal_id, value)

                self.send_response(200)
                self.send_header('Content-Type', 'text/plain')
                self.end_headers()
                if response:
                    try:
                        self.wfile.write(response.encode('utf-8'))
                    except BrokenPipeError:
                        logging.warning("Client disconnected before response could be sent")
            except Exception as e:
                logging.error(f"Error handling POST request: {e}")
                self.send_response(500)
                self.end_headers()
        
def start_server(address):
    host, port = address.split(':')
    with socketserver.TCPServer((host, int(port)), LogRequestHandler) as server:
        print(f"Serving HTTP on {host} port {port}...")
        server.socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server.serve_forever()

if __name__ == "__main__":
    start_server(address)