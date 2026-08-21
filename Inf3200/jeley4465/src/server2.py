from flask import Flask, jsonify, request
import socket
import json

app = Flask(__name__)

# Get the host and port dynamically
host = socket.gethostname()
port = None  # We'll set this when we start the server

@app.route('/helloworld', methods=['GET'])
def hello_world():
    # Return the host-port combo as the response
    return f"{host}:{port}", 200

if __name__ == '__main__':
    import argparse
    import random
    
    # Argument parser to accept a port number from the command line or randomly assign one
    parser = argparse.ArgumentParser(description='Start the HTTP server.')
    parser.add_argument('--port', type=int, default=random.randint(49152, 65535), help='Port number to run the server on')
    args = parser.parse_args()

    port = args.port

    # Start the Flask server
    app.run(host='0.0.0.0', port=port)
