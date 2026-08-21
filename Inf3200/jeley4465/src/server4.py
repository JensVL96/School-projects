import http.server
import socket
from http.server import BaseHTTPRequestHandler, HTTPServer

class SimpleHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path == "/helloworld":
            # Get the server's hostname and port
            host_port = f"{self.server.server_name}:{self.server.server_port}"
            # Respond with the hostname:port combo
            self._respond(200, host_port)
        else:
            self._respond(404, "Not Found")

    def _respond(self, status_code, content):
        self.send_response(status_code)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write(content.encode('utf-8'))

def run_server(port=0):
    hostname = socket.gethostname()
    server_address = (hostname, port)
    httpd = HTTPServer(server_address, SimpleHandler)
    print(f"Starting server at {hostname}:{httpd.server_port}")
    httpd.serve_forever()

if __name__ == "__main__":
    run_server()
