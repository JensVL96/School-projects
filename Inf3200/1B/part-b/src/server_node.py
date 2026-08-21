import sys
import http.server
import socketserver
import os
import requests
import json
import threading
import time
from helper import hash_mod, is_in_range, write_debug_file
from config import DEBUG_MODE, USE_FINGER_TABLE

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
fingerTable = []


items = {}

SHUTDOWN_TIME = 1000

def closest_preceding_node(id):
    for i in range(ring_pow - 1, 1, -1):
        write_debug_file(f"Checking if id {fingerTable[i][0]} is in range ({idx}, {id}]", cur_dir)
        if is_in_range(fingerTable[i][0], idx, id-1, ring_size):
            write_debug_file(f"Found closest preceding node {fingerTable[i][1]} for key {id} at finger {fingerTable[i][0]} in ({idx}, {id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)
            return fingerTable[i][1]
        
    return host

class SimpleHTTPRequestHandler(http.server.BaseHTTPRequestHandler):
    global idx

    def do_GET(self):
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

            key = self.path[len('/storage/'):]
            predecessor_id = hash_mod(predecessor, ring_size)
            id = hash_mod(key, ring_size)

            # Check if the key is stored in the current node
            if is_in_range(id, predecessor_id, idx, ring_size) or host == successor:
                write_debug_file(f"Retrieving key {key} with id {id} at ({predecessor_id}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

                message = ''

                # Check if the key exists in the items dictionary
                if(key in items):
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
                    r = requests.get(f"http://{successor}:{port}/find_successor/{key}")
                else:
                    r = requests.get(f"http://{successor}:{port}/find_successor_finger/{key}")

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

        elif self.path == '/network':
            """ 
                url: /network
                Returns HTTP code 200, with list of known nodes, as JSON
            """

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
        
        elif self.path.startswith('/find_successor_finger'):
            # closest = closest_preceding_node_fingertable()
            key = self.path[len('/find_successor_finger/'):]
            id = hash_mod(key, ring_size)
            predecessor_id = hash_mod(predecessor, ring_size)
            successor_id = hash_mod(successor, ring_size)

            write_debug_file(f"Looking for key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

            # Check if the key is stored in the current node
            if is_in_range(id, predecessor_id, idx, ring_size):
                write_debug_file(f"Found key {key} with id {id} at ({predecessor_id}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(host, "utf8"))
                self.wfile.flush()
            # Check if the key is stored in the successor node
            elif is_in_range(id, idx, successor_id, ring_size):
                write_debug_file(f"Found key {key} with id {id} at ({idx}, {successor_id}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)

                self.send_response(200)
                self.send_header("Content-type", "text/plain")
                self.end_headers()
                self.wfile.write(bytes(successor, "utf8"))
                self.wfile.flush()
            else:
                # Forward the request to the successor node
                forward_node = closest_preceding_node(id)
                message = None
                if forward_node == host:
                    message = host
                else:
                    r = requests.get(f"http://{forward_node}:{port}/find_successor_finger/{key}")
                    message = r.text

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
        if self.path.startswith('/storage'):
            """ 
                url: /storage/<key>
                Returns HTTP code 200. Assumed that <value> is persisted.
            """

            key = self.path[len('/storage/'):]
            data = self.rfile.read(int(self.headers['Content-Length'])).decode("utf-8")
            predecessor_id = hash_mod(predecessor, ring_size)
            id = hash_mod(key, ring_size)

            # Check if the key is stored in the current node
            if is_in_range(id, predecessor_id, idx, ring_size) or host == successor:
                write_debug_file(f"Storing key {key} with id {id} at ({predecessor_id}, {idx}]. Current node is {host}, successor is {successor}, ring_size={ring_size}", cur_dir)
                
                # Check if the key already exists
                if(key in items):
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

    start_server(timeout=SHUTDOWN_TIME)

if __name__ == "__main__":
    main()