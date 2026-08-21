from flask import Flask, jsonify, request
import socket

app = Flask(__name__)

@app.route('/helloworld', methods=['GET'])
def get_helloworld():
    # Get the server's hostname and port
    hostname = socket.gethostname()
    port = request.environ.get('SERVER_PORT')
    host_port = f"{hostname}:{port}"
    return host_port

if __name__ == '__main__':
    # Run the Flask server on a random available port
    app.run(host=socket.gethostname(), port=0)
