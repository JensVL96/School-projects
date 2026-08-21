import logging
import requests
from flask import Flask, request, jsonify
from paxos import Proposer, Acceptor, Learner, ProposalID
import sys

logging.basicConfig(level=logging.INFO)
logging.getLogger('werkzeug').setLevel(logging.WARNING)  # Disable Flask request logs
logging.getLogger('urllib3').setLevel(logging.WARNING)

app = Flask(__name__)

try:
    output_id, address, nodes_list = sys.argv[1], sys.argv[2], sys.argv[3:]

except IndexError:
    print("Usage: log-server.py <host:port>")
    sys.exit(1)

crashed = False
local_log = []

class Messenger:
    def __init__(self, node_uid, nodes_list):
        self.node_uid = node_uid
        self.nodes_list = nodes_list
        # Find our index based on the numeric part of the UID
        self.node_index = int(self.node_uid[4:]) - 1  # Extract number from "nodeX"
        
        # Validate the index
        if self.node_index < 0 or self.node_index >= len(self.nodes_list):
            raise ValueError(f"Invalid node index {self.node_index} for nodes list")

    def send_prepare(self, proposal_id):
        for i, address in enumerate(self.nodes_list):
            node_uid = f"node{i+1}"
            if node_uid != self.node_uid:  # Don't send to ourselves
                url = f"http://{address}/prepare"
                data = {"proposal_id": str(proposal_id), "from_uid": self.node_uid}
                try:
                    requests.post(url, json=data, timeout=0.5)
                except (requests.exceptions.ConnectionError, requests.exceptions.Timeout) as e:
                    pass

    def send_promise(self, to_uid, proposal_id, prev_accepted_id, prev_accepted_value):
        node_num = int(to_uid[4:]) - 1
        address = self.nodes_list[node_num]
        
        url = f"http://{address}/promise"
        data = {
            "proposal_id": str(proposal_id),
            "prev_accepted_id": str(prev_accepted_id) if prev_accepted_id else None,
            "prev_accepted_value": prev_accepted_value,
            "from_uid": self.node_uid,
        }
        try:
            requests.post(url, json=data)
        except requests.exceptions.ConnectionError as e:
            pass

    def send_accept(self, proposal_id, value):
        for i, address in enumerate(self.nodes_list):
            node_uid = f"node{i+1}"
            if node_uid != self.node_uid:
                url = f"http://{address}/accept"
                data = {"proposal_id": str(proposal_id), "value": value}
                try:
                    requests.post(url, json=data)
                except requests.exceptions.ConnectionError as e:
                    pass

    def send_accepted(self, proposal_id, value):
        for i, address in enumerate(self.nodes_list):
            node_uid = f"node{i+1}"
            if node_uid != self.node_uid:
                url = f"http://{address}/accepted"
                data = {"proposal_id": str(proposal_id), "value": value}
                try:
                    requests.post(url, json=data)
                except requests.exceptions.ConnectionError as e:
                    pass

    def on_resolution(self, value):
        self.resolved_value = value
        if value not in local_log:
            local_log.append(value)

class Node(Proposer, Acceptor, Learner):
    def __init__(self, node_uid, nodes_list, quorum_size):
        self.node_index = nodes_list.index(address)
        self.consistent_uid = f"node{self.node_index + 1}"
        self.node_uid = self.consistent_uid

        messenger = Messenger(self.consistent_uid, nodes_list)
        Proposer.__init__(self, messenger, self.consistent_uid, quorum_size)
        Acceptor.__init__(self, messenger)
        Learner.__init__(self, messenger, quorum_size)

        self.proposed_value = None
        self.accepted_value = None
        self.resolved_value = None

    def on_resolution(self, value):
        self.resolved_value = value
        local_log.append(value)

# Global node instance
node = None


# Fix 1: Add route for root path PUT requests
@app.route("/", methods=["PUT"])
def handle_root_put():
    # Simply redirect to the log endpoint
    return handle_put()

@app.route("/log", methods=["PUT"])
def handle_put():
    global crashed, local_log

    # Check if the node is crashed
    if crashed:
        logging.debug(f"Received PUT request while crashed, ignoring")
        return "", 503

    # Read the request data
    data = request.data.decode('utf-8')

    # Use Paxos to agree on the log entry
    if node and hasattr(node, 'set_proposal'):
        node.set_proposal(data)
    else:
        logging.error("Node not initialized!")
        return jsonify({"status": "error", "message": "Node not initialized"}), 500

    # Respond with success
    return jsonify({"status": "success", "message": "Log entry proposed"}), 200

@app.route("/prepare", methods=["POST"])
def handle_prepare():
    global crashed
    url = request.url
    
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        logging.debug(f"Received request while crashed, ignoring")
        return "", 503
        
    data = request.json
    proposal_id_str = data["proposal_id"]
    
    # Parse the ProposalID from the string
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)

    # Handle prepare request
    if node:
        node.recv_prepare(from_uid, proposal_id)
    else:
        logging.error("Node not initialized!")
    
    return jsonify({"status": "ok"})

@app.route("/promise", methods=["POST"])
def handle_promise():
    global crashed
    
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        logging.debug(f"Received request while crashed, ignoring")
        return "", 503

    data = request.json
    proposal_id_str = data["proposal_id"]
    prev_accepted_id_str = data.get("prev_accepted_id")
    prev_accepted_value = data.get("prev_accepted_value")
    from_uid = data.get("from_uid")  # Extract the sender's UID from the message

    # Parse the ProposalID from the string
    number, uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), uid)

    # Parse the previous accepted ID (if it exists)
    prev_accepted_id = None
    if prev_accepted_id_str:
        prev_number, prev_uid = prev_accepted_id_str.split("-")
        prev_accepted_id = ProposalID(int(prev_number), prev_uid)
    
    # Handle the PROMISE request
    node.recv_promise(from_uid, proposal_id, prev_accepted_id, prev_accepted_value)

    return jsonify({"status": "ok"})

@app.route("/accept", methods=["POST"])
def handle_accept():
    global crashed
    
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        logging.debug(f"Received request while crashed, ignoring")
        return "", 503
        
    data = request.json
    proposal_id_str = data["proposal_id"]
    value = data["value"]

    # Parse the ProposalID from the string
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)

    node.recv_accept_request(from_uid, proposal_id, value)
    
    return jsonify({"status": "ok"})

@app.route("/crash", methods=["POST"])
def handle_crash():
    global crashed

    # Simulate a crash
    logging.info("Simulating crash...")
    crashed = True
    return jsonify({"status": "success", "message": "Node crashed"}), 200

@app.route("/recover", methods=["POST"])
def handle_recover():
    global crashed

    # Simulate a recovery
    logging.info("Simulating recovery...")
    crashed = False
    return jsonify({"status": "success", "message": "Node recovered"}), 200


@app.route("/exit", methods=["POST"])
def handle_exit():
    global local_log

    # Log the local log and exit
    logging.info("Exiting...")
    print(f"Local log: {local_log}")

    # Write the log to a file
    output_file = f"output/{output_id}-server-{request.host}.csv"
    with open(output_file, 'w') as f:
        for entry in local_log:
            f.write(f"{entry}\n")

    return jsonify({"status": "success", "message": "Node exiting"}), 200

@app.route("/accepted", methods=["POST"])
def handle_accepted():
    global crashed
    
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        # logging.debug(f"Received request while crashed, ignoring")
        return jsonify({"status": "error", "message": "Node is crashed"}), 503
        
    data = request.json
    proposal_id_str = data["proposal_id"]
    value = data["value"]

    # Parse the ProposalID from the string
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)
    
    node.recv_accepted(request.remote_addr, proposal_id, value)
    return jsonify({"status": "ok"})

if __name__ == "__main__":
    # Set up the node
    host, port = address.split(':')
    
    # Create a dictionary mapping node IDs to addresses
    nodes_dict = {f"node{i+1}": addr for i, addr in enumerate(nodes_list)}
    node_uid = f"node{nodes_list.index(address) + 1}"
    quorum_size = (len(nodes_list) // 2) + 1  # Calculate quorum size
    
    node = Node(address, nodes_list, quorum_size)

    # Start the Flask server
    app.run(host=host, port=int(port), debug=False)