from flask import Flask
import sys

app = Flask(__name__)

# Initialize hostname and port from command line arguments
hostname = sys.argv[1] if len(sys.argv) > 1 else "unknown-host"
port = int(sys.argv[2]) if len(sys.argv) > 2 else 5000

@app.route('/helloworld')
def get_helloworld():
    response = f"{hostname}:{port}"
    return response

if __name__ == '__main__':
    # Run the Flask server on the specified port
    app.run(host='0.0.0.0', port=port)  # '0.0.0.0' to accept connections from any IP
