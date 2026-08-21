import sys
import http.server
import socketserver
import os
import requests
import json
import threading
import time
from helper import hash_mod, is_in_range, write_debug_file, flush_log
from config import DEBUG_MODE, USE_FINGER_TABLE, STABILIZE_INTERVAL

# Global variables
host = None
port = None
predecessor = None
successor = None
ring_pow = None
ring_size = None
cur_dir = None
shutdown_timer = None
httpd = None
idx = None
next_finger = 0
fingerTable = []
crashed = False

items = {}

SHUTDOWN_TIME = 1000

def closest_preceding_node(id):
    for i in range(ring_pow - 1, 1, -1):
        write_debug_file(f"Checking if id {fingerTable[i][0]} is in range ({idx}, {id}]", cur_dir)
        if is_in_range(fingerTable[i][0], idx, id-1, ring_size):
            # write_debug_file(f"Found closest preceding node {fingerTable[i][1]} for key {id} at finger {fingerTable[i][0]} in ({idx}, {id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)
            # write_debug_file(f"Found closest preceding node {fingerTable[i][1]} for key {id} at finger {fingerTable[i][0]}", cur_dir, f=True)
            return fingerTable[i][1]
        
    return host


"""
    Called periodically. Verifies n's immediate successor, and tells the successor about n.
"""
def stabilize():
    global successor
    
    if successor == host:
        return

    try:
        # successor = requests.get(f"http://{host}:{port}/find_successor_finger/{x}").text

        x = requests.get(f"http://{successor}:{port}/predecessor").text

        if x is None or x == "None" or successor == x:
            pass
        else:
            if is_in_range(hash_mod(x, ring_size), idx+1, hash_mod(successor, ring_size)-1, ring_size):
                successor = x
                write_debug_file(f"[Stabilize] Node {host} updated its successor to {successor}", cur_dir, f=True)
    
    except Exception as e:
        write_debug_file(f"Error in stabilize /predecessor : {e}", cur_dir, f=True)
        flush_log(cur_dir)
        # May need more time to recover from a crash
        pass

    try:
        requests.post(f"http://{successor}:{port}/notify/{host}")
    except Exception as e:
        write_debug_file(f"Error in stabilize /notify: {e}", cur_dir, f=True)
        # May need more time to recover from a crash
        pass
    


"""
    Called periodically, refreshes finger table entries.
    Next stores the index of the next finger to fix.
"""
def fix_fingers():
    global next_finger

    next_finger = next_finger + 1

    if next_finger > ring_pow - 1:
        next_finger = 0

    if successor == host:
        return

    try:
        if USE_FINGER_TABLE:
            fingerTable[next_finger][1] = requests.get(f"http://{successor}:{port}/find_successor_finger/{fingerTable[next_finger-1][0]}").text
        else:
            fingerTable[next_finger][1] = requests.get(f"http://{successor}:{port}/find_successor/{fingerTable[next_finger-1][0]}").text
    except Exception as e:
        # May need more time to recover from a crash
        pass


def check_predecessor():
    global predecessor
    
    if predecessor == host:
        return

    try:
        if not requests.get(f"http://{predecessor}:{port}/helloworld").ok:
            predecessor = None
    except Exception as e:
        predecessor = None


class SimpleHTTPRequestHandler(http.server.BaseHTTPRequestHandler):
    global idx

    def do_GET(self):
        global predecessor
        global successor
        global crashed

        if crashed:
            self.send_error(404)
            return

        if self.path == '/helloworld':
            address = address = f"{host}:{port}"

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()
            self.wfile.write(bytes(address, "utf8"))
        elif self.path.startswith('/storage'):
            """ 
                url: /storage/<key>
                Returns HTTP code 200, with value if <key> exists in DHT
                Returns HTTP code 404, if <key> does not exist in DHT
            """

            message = ''
            key = self.path[len('/storage/'):]

            # The node need to take ownership of the key
            if predecessor is None:
                if key in items:
                    self.send_response(200)
                    message = items[key]
                else:
                    self.send_response(404)

                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(message, "utf8"))
                return

            predecessor_id = hash_mod(predecessor, ring_size)
            id = hash_mod(key, ring_size)

            # Check if the key is stored in the current node
            if is_in_range(id, predecessor_id, idx, ring_size) or host == successor:
                write_debug_file(f"Retrieving key {key} with id {id} at ({predecessor_id}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

                # Check if the key exists in the items dictionary
                if key in items:
                    self.send_response(200)
                    message = items[key]
                else:
                    self.send_response(404)
                    write_debug_file(f"Key {key} does not contain a value", cur_dir) 

                # Send the response back to the client 
                # (The client could be a /storage GET request from another server node acting as a middleman between the client and the DHT)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(message, "utf8"))
            else:
                # Use the find_successor algorithm to find the node that contains the key

                if USE_FINGER_TABLE:
                    r = requests.get(f"http://{successor}:{port}/find_successor_finger/{key}")
                else:
                    r = requests.get(f"http://{successor}:{port}/find_successor/{key}")

                key_owner_node = r.text # The node that contains the key

                # Get the value of the key from the key owner node
                r = requests.get(f"http://{key_owner_node}:{port}/storage/{key}")
                if r.text == '':
                    self.send_response(404)
                else:
                    self.send_response(200)

                # Send the response back to the client
                # (The client could be a /storage GET request from another server node acting as a middleman between the client and the DHT)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(r.text, "utf8"))
        
        elif self.path == '/predecessor':
            """
                url: /predecessor
                Returns HTTP code 200, with the predecessor node of the current node
            """

            if predecessor is None:
                message = "None"
            else:
                message = predecessor

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()
            self.wfile.write(bytes(message, "utf8"))

        elif self.path == '/successor':
            """
                url: /successor
                Returns HTTP code 200, with the successor node of the current node
            """

            if successor is None:
                message = "None"
            else:
                message = successor

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()
            self.wfile.write(bytes(message, "utf8"))

        elif self.path == '/network':
            """ 
                url: /network
                Returns HTTP code 200, with list of known nodes, as JSON
            """

            if predecessor is None:
                message = [f"{host}:{port}"]
            else:
                message = [f"{predecessor}:{port}", f"{successor}:{port}"]

            self.send_response(200)
            self.send_header("Content-type", "application/json")
            self.end_headers()
            self.wfile.write(bytes(json.dumps(message), "utf8"))
        
        elif self.path == '/get_next_node':
            message = successor
            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()
            self.wfile.write(bytes(message, "utf8"))

        elif self.path == '/node-info':
            """
                url: /node-info
                Returns HTTP code 200, with information about the current node, as JSON
            """

            others = []
            for entry in fingerTable:
                others.append(entry[1] + ":" + str(port))

            message = {
                "node_hash": idx,
                "successor": successor + ":" + str(port),
                "others": others
            }

            self.send_response(200)
            self.send_header("Content-type", "application/json")
            self.end_headers()
            self.wfile.write(bytes(json.dumps(message), "utf8"))
        
        elif self.path.startswith('/find_successor_finger'):
            # closest = closest_preceding_node_fingertable()
            key = self.path[len('/find_successor_finger/'):]
            id = hash_mod(key, ring_size)

            if predecessor is not None:
                predecessor_id = hash_mod(predecessor, ring_size)
            else:
                predecessor_id = -999

            successor_id = hash_mod(successor, ring_size)

            # write_debug_file(f"Current Node: {host}, Predecessor is {predecessor} type={type(predecessor)}, successor is {successor}", cur_dir, f=True)
            # write_debug_file(f"Looking for key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir, f=True)

            # Check if the key is stored in the current node
            if (predecessor is None or predecessor == 'None') or (is_in_range(id, predecessor_id, idx, ring_size)):
                # write_debug_file(f"Found key {key} with id {id} at ({predecessor_id if predecessor is not None else -999}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir, f=True)

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(host, "utf8"))
                self.wfile.flush()
            # Check if the key is stored in the successor node
            elif is_in_range(id, idx, successor_id, ring_size):
                # write_debug_file(f"Found key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir, f=True)

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(successor, "utf8"))
                self.wfile.flush()
            else:
                # Forward the request to the successor node
                forward_node = closest_preceding_node(id)
                # write_debug_file(f"Forwarding request to find key {key} to closest preceding node {forward_node}", cur_dir, f=True)

                message = host
                if forward_node == None or forward_node == host or forward_node == "":
                    message = host
                else:
                    try:
                        r = requests.get(f"http://{forward_node}:{port}/find_successor_finger/{key}")
                        message = r.text
                    except Exception as e:
                        write_debug_file(f"Error in node {host}, forward_node: {forward_node}, find_successor_finger: {e}", cur_dir, f=True)
                        # May need more time to recover from a crash
                        pass

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(message, "utf8"))
                self.wfile.flush()

            

        elif self.path.startswith('/find_successor'):
            """
            url: /find_successor
            Returns HTTP code 200, with the successor node of an id
            """

            key = self.path[len('/find_successor/'):]
            id = hash_mod(key, ring_size)
            successor_id = hash_mod(successor, ring_size)

            write_debug_file(f"Looking for key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

            # Check if the key is stored in the successor node
            if is_in_range(id, idx, successor_id, ring_size):
                write_debug_file(f"Found key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(successor, "utf8"))
                self.wfile.flush()
            else:
                # Forward the request to the successor node

                write_debug_file(f"Sent request to successor {successor} to find key {key}", cur_dir)
                r = requests.get(f"http://{successor}:{port}/find_successor/{key}")
                if r is None:
                    self.send_error(404)
                    self.end_headers()
                else:
                    self.send_response(200)
                    self.send_header("Content-type", "text/plain")
                    self.end_headers()
                    self.wfile.write(bytes(r.text, "utf8"))
                    self.wfile.flush()
        else:
            self.send_error(404)
            self.end_headers()


    def do_PUT(self):
        global crashed

        if crashed:
            self.send_error(404)
            return
        
        if self.path.startswith('/storage'):
            """ 
                url: /storage/<key>
                Returns HTTP code 200. Assumed that <value> is persisted.
            """

            key = self.path[len('/storage/'):]
            data = self.rfile.read(int(self.headers['Content-Length'])).decode("utf-8")

            message = ''

            # The node need to take ownership of the key
            if predecessor is None:
                items[key] = data
                message = f"Stored key {key} at {host}"

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.send_header("Content-Length", len(message))
                self.end_headers()
                self.wfile.write(bytes(message, "utf8"))
                self.wfile.flush()
                return
            
            predecessor_id = hash_mod(predecessor, ring_size)
            id = hash_mod(key, ring_size)

            # Check if the key is stored in the current node
            if is_in_range(id, predecessor_id, idx, ring_size) or host == successor:
                write_debug_file(f"Storing key {key} with id {id} at ({predecessor_id}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)
                
                # Check if the key already exists
                if key in items:
                    write_debug_file(f"Key {key} already exists with value {items[key]}", cur_dir)

                # Store the value
                items[key] = data
                
                message = f"Stored key {key} at {host}"

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.send_header("Content-Length", len(message))
                self.end_headers()
                self.wfile.write(bytes(message, "utf8"))
                self.wfile.flush()
            else:
                # Use the find_successor algorithm to find the node that contains the key

                if USE_FINGER_TABLE:
                    r = requests.get(f"http://{successor}:{port}/find_successor_finger/{key}")
                else:
                    r = requests.get(f"http://{successor}:{port}/find_successor/{key}")

                key_owner_node = r.text # The node that contains the key

                # Forward the PUT request to the key owner node
                r = requests.put(f"http://{key_owner_node}:{port}/storage/{key}", data)
                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(r.text, "utf8"))
                self.wfile.flush()

    def do_POST(self):
        global predecessor
        global successor
        global crashed

        if self.path.startswith('/join?nprime='):
            """
                url: /join?nprime=<node>
                Returns HTTP code 200, with the successor node of the joining node
            """

            if crashed:
                self.send_error(404)
                return

            node_prime = self.path[len('/join?nprime='):]
            node_prime = node_prime.split(':')[0]

            if successor == node_prime:
                # The node is already joined
                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                return

            predecessor = None

            if USE_FINGER_TABLE:
                successor = requests.get(f"http://{node_prime}:{port}/find_successor_finger/{host}").text
            else:
                successor = requests.get(f"http://{node_prime}:{port}/find_successor/{host}").text

            write_debug_file(f"[Join] Node {host} joined {node_prime}. Successor updated to {successor}", cur_dir, f=True)

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()

        elif self.path == "/leave":
            """
                url: /leave
                Returns HTTP code 200, with the successor node of the leaving node
            """

            if crashed:
                self.send_error(404)
                return

            # Notify successor to update its predecessor
            if successor:
                try:
                    write_debug_file(f"node {host} updating its successor {successor} with predecessor {predecessor}", cur_dir, f=True)
                    requests.post(f"http://{successor}:{port}/notify/{predecessor}")
                except Exception as e:
                    write_debug_file(f"Error notifying successor on leave: {e}", cur_dir, f=True)

            # Notify predecessor to update its successor
            if predecessor:
                try:
                    write_debug_file(f"node {host} updating its predecessor {predecessor} with successor {successor}", cur_dir, f=True)
                    requests.post(f"http://{predecessor}:{port}/notify/{successor}")
                except Exception as e:
                    write_debug_file(f"Error notifying predecessor on leave: {e}", cur_dir, f=True)


            predecessor = None
            successor = host

            write_debug_file(f"Node {host} is leaving the network", cur_dir, f=True)

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()

        elif self.path == "/sim-crash":
            """
                url: /sim-crash
                Simulates a crash by disallowing requests except for /sim-recover
            """

            crashed = True

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()

        elif self.path == "/sim-recover":
            """
                url: /sim-recover
                Simulates a recover by accepting requests again
            """

            crashed = False

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()

        elif self.path.startswith('/notify/'):
            """
                n' thinks it might be our predecessor
                url: /notify/<node>
                Returns HTTP code 200
            """

            if crashed:
                self.send_error(404)
                return

            node_prime = self.path[len('/notify/'):]

            node_prime_id = hash_mod(node_prime, ring_size)
            successor_id = hash_mod(successor, ring_size)

            if predecessor is None or is_in_range(node_prime_id, hash_mod(predecessor, ring_size)+1, idx-1, ring_size):
                write_debug_file(f"[Notify] Node {host} updated its predecessor to {node_prime}", cur_dir, f=True)
                if node_prime == "None":
                    predecessor = None
                else:
                    predecessor = node_prime

            # Handle edge case where the successor is the current node
            if successor == host:
                write_debug_file(f"[Notify] Node {host} updated its successor to {node_prime}", cur_dir, f=True)
                successor = node_prime

            self.send_response(200)
            self.send_header("Content-type", "text/plain")
            self.end_headers()


    def log_message(self, format, *args):
        if DEBUG_MODE:
            super().log_message(format, *args)


def stop_server():
    httpd.shutdown()


# Shutdown timer can be reset by simply calling this function again
def start_shutdown_timer(timeout):
    global shutdown_timer
    if shutdown_timer is not None:
        shutdown_timer.cancel()  # Cancel the existing timer
    shutdown_timer = threading.Timer(timeout, stop_server)
    shutdown_timer.start()

def start_server(timeout=10):
    global server_thread, httpd

    handler = SimpleHTTPRequestHandler
    httpd = socketserver.ThreadingTCPServer((host, port), handler)
    
    # Run the server in a separate thread
    server_thread = threading.Thread(target=httpd.serve_forever)
    server_thread.start()
    
    # Start the shutdown timer
    start_shutdown_timer(timeout)

def start_periodic_function():
    while True:
        time.sleep(STABILIZE_INTERVAL)
        if crashed:
            continue

        # write_debug_file(f"Stabilizing node {host}", cur_dir, f=True)

        stabilize()
        fix_fingers()
        check_predecessor()

def main():
    global host
    global port
    global predecessor
    global successor
    global ring_size
    global cur_dir
    global httpd
    global idx
    global fingerTable
    global ring_pow

    host = sys.argv[1]
    port = int(sys.argv[2])
    predecessor = sys.argv[3]
    successor = sys.argv[4]
    ring_pow = int(sys.argv[5])
    ring_size = 2**ring_pow
    cur_dir = sys.argv[6]
    fingerTable_in = sys.argv[7]

    fingerTable_clean = fingerTable_in.rstrip(',')
    fingerTable_list = fingerTable_clean.split(',')
    
    idx = hash_mod(host, ring_size)

    i = 0
    for elem in fingerTable_list:
        pow_idx = (2**i)
        key_idx = ((2**i)+idx)%ring_size
        finger_entry = [key_idx, elem]
        fingerTable.append(finger_entry)
        i += 1


    debug_msg = f"Host: {host}\nPort: {port}\n Predecessor: {predecessor}\nSuccessor: {successor}\nRing size: {ring_size}\nCurrent directory: {cur_dir}"
    write_debug_file(debug_msg, cur_dir)

    timer_thread = threading.Thread(target=start_periodic_function)
    timer_thread.daemon = True  # This ensures the thread will exit when the main program exits
    timer_thread.start()

    start_server(timeout=SHUTDOWN_TIME)

if __name__ == "__main__":
    main()