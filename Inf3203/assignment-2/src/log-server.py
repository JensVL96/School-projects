import logging
import requests
from flask import Flask, request, jsonify
from paxos import Proposer, Acceptor, Learner, ProposalID
import sys
import time
from paxos_debugger import PaxosDebugger, write_debug_output

logging.basicConfig(level=logging.INFO)
logging.getLogger('werkzeug').setLevel(logging.WARNING)  # Disable Flask request logs
logging.getLogger('urllib3').setLevel(logging.WARNING)

app = Flask(__name__)

try:
    output_id, address, nodes_list = sys.argv[1], sys.argv[2], sys.argv[3:]

except IndexError:
    print("Usage: log-server.py <host:port>")
    sys.exit(1)

# Global state variables
crashed = False
local_log = []          # Stores the committed log entries
failed_proposals = []   # Tracks proposals that need retrying
node = None
paxos_debugger = None

REQUEST_TIMEOUT = 0.5
PREPARE_ATTEMPTS = 10

debug_log_file = f"paxos_debug_logs/paxos_debug_{output_id}.log"

def retry_failed_proposals():
    for proposal in failed_proposals[:]:  # Iterate over a copy to allow removal
        if node.set_proposal(proposal):
            failed_proposals.remove(proposal)

class Messenger:
    """Handles communication between Paxos nodes"""
    def __init__(self, node_uid, nodes_list, address):
        self.nodes_list = nodes_list
        self.node_uid = node_uid
        self.address = address
        self.debugger = None    

    def send_prepare(self, proposal_id, promise_callback=None):
        """Send PREPARE messages to all other nodes"""
        for i, address in enumerate(self.nodes_list):
            node_uid = f"node{i+1}"
            if node_uid != self.node_uid:  # Don't send to ourselves
                url = f"http://{address}/prepare"
                data = {"proposal_id": str(proposal_id), "from_uid": self.node_uid}
                for attempt in range(PREPARE_ATTEMPTS):
                    try:
                        response = requests.post(url, json=data, timeout=REQUEST_TIMEOUT)
                        if response.status_code == 200:
                            resp_data = response.json()
                            if resp_data.get("status") == "promised" and promise_callback:
                                # Parse prev_accepted_id if it exists
                                prev_accepted_id = None
                                if resp_data.get("prev_accepted_id"):
                                    num, uid = resp_data["prev_accepted_id"].split("-")
                                    prev_accepted_id = ProposalID(int(num), uid)
                                
                                # Directly invoke the proposer's recv_promise
                                promise_callback(
                                    node_uid,
                                    proposal_id,
                                    prev_accepted_id,
                                    resp_data.get("prev_accepted_value")
                                )
                                break  # Exit retry loop on successful request
                        
                    except (requests.exceptions.ConnectionError, requests.exceptions.Timeout) as e:
                        time.sleep(0.1 * (2 ** attempt))  # Exponential backoff
                        continue
                        
                    except Exception as e:
                        logging.error(f"Unexpected error preparing to {node_uid}: {str(e)}")
                        break  # Exit retry loop on unexpected errors

    def send_accept_request(self, proposal_id, value):
        """Broadcast ACCEPT requests to all nodes"""
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
        """Called when consensus is reached on a value"""
        self.resolved_value = value
        if value not in local_log:
            local_log.append(value)

        self.proposer.proposal_in_progress = False
        self.sync_with_peers()

    def sync_with_peers(self):
        """Reconcile logs with other nodes through bi-directional diff"""
        for peer in self.nodes_list:
            if peer != self.address:
                try:
                    response = requests.get(f"http://{peer}/get_log", timeout=REQUEST_TIMEOUT)
                    if response.status_code == 200:
                        peer_log = response.json().get("get_log", [])

                        # Determine missing entries in both directions
                        missing_from_local = [entry for entry in peer_log if entry not in local_log]
                        missing_from_peer = [entry for entry in local_log if entry not in peer_log]

                        # Apply changes
                        if missing_from_local:
                            local_log.extend(missing_from_local)
                            logging.info(f"Pulled {len(missing_from_local)} missing entries from {peer}: {missing_from_local}")

                        if missing_from_peer:
                            try:
                                requests.post(f"http://{peer}/merge_log", json={"entries": missing_from_peer}, timeout=0.5)
                            except Exception as send_err:
                                logging.debug(f"Could not send missing entries to {peer}: {str(send_err)}")
                    else:
                        logging.debug(f"Failed to fetch log from {peer} (status code {response.status_code})")
                except Exception as e:
                    logging.debug(f"Error during sync with {peer}: {str(e)}")

    def debug_state(self, role, **state_data):
        if self.debugger:
            self.debugger.record_state(self.node_uid, role, state_data)


class Node(Proposer, Acceptor, Learner):
    """Combines all Paxos roles into single node"""
    def __init__(self, node_uid, nodes_list, quorum_size):
        self.node_index = nodes_list.index(address)
        self.consistent_uid = f"node{self.node_index + 1}"
        self.node_uid = self.consistent_uid
        self.address = node_uid

        # Initialize Paxos roles
        messenger = Messenger(self.consistent_uid, nodes_list, self.address)
        Proposer.__init__(self, messenger, self.consistent_uid, quorum_size)
        Acceptor.__init__(self, messenger)
        Learner.__init__(self, messenger, quorum_size)

        # State tracking
        self.proposed_value = None
        self.accepted_value = None
        self.resolved_value = None

        # Cross-component references
        self.messenger.debugger = paxos_debugger
        self.messenger.proposer = self

    def set_proposal(self, value):
        """Attempt to propose a value to the cluster"""
        try:
            super().set_proposal(value)
            return True
        except Exception as e:
            failed_proposals.append(value)  # Queue for retry
            # logging.error(f"Failed to set proposal: {str(e)}")
            return False



# Flask route handlers
# Client endpoints (/log, /)
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

    retry_failed_proposals()   # First retry any failures
    data = request.data.decode('utf-8')

    # Wait for current proposal to complete with timeout
    # start_time = time.time()
    # while node.proposal_in_progress:
    #     if time.time() - start_time > 5:  # Timeout after 5 seconds
    #         failed_proposals.append(data)
    #         return jsonify({
    #             "status": "error", 
    #             "message": "Timeout waiting for consensus",
    #             "queued_for_retry": True
    #         }), 503
    #     time.sleep(REQUEST_TIMEOUT)

    # Use Paxos to agree on the log entry
    if node and hasattr(node, 'set_proposal'):
        # Attempt to propose the new value
        if not node.set_proposal(data):
            failed_proposals.append(data)     # Queue if timeout
    else:
        logging.error("Node not initialized!")
        return jsonify({"status": "error", "message": "Node not initialized"}), 500

    # Add this line to trigger debug output
    write_debug_output(nodes_list, debug_log_file, data)

    # Respond with success
    return jsonify({"status": "success", "message": "Log entry proposed"}), 200


# Cluster operations (/prepare, /accept, /accepted)
@app.route("/prepare", methods=["POST"])
def handle_prepare():
    """Handle PREPARE messages from other proposers"""

    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        # logging.debug(f"Received request while crashed, ignoring")
        return "", 503

    data = request.json
    proposal_id_str = data["proposal_id"]
    
    # Parse proposal ID and check with acceptor
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)

    # Get promise response from acceptor
    if node:
        accept, prev_id, prev_value = node.recv_prepare(from_uid, proposal_id)

        if accept:
            return jsonify({
                "status": "promised",
                "prev_accepted_id": str(prev_id) if prev_id else None,
                "prev_accepted_value": prev_value
            })

    return jsonify({"status": "rejected"}), 409

@app.route("/accept", methods=["POST"])
def handle_accept():
    """Handle ACCEPT messages from other Acceptors"""
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        return "", 503

    data = request.json
    proposal_id_str = data["proposal_id"]
    value = data["value"]

    # Parse the ProposalID from the string
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)

    node.recv_accept_request(from_uid, proposal_id, value)
    
    return jsonify({"status": "ok"})

@app.route("/accepted", methods=["POST"])
def handle_accepted():
    """Handle ACCEPTED messages in Learners"""
    if crashed and request.path not in ["/crash", "/recover", "/exit"]:
        return jsonify({"status": "error", "message": "Node is crashed"}), 503
        
    data = request.json
    proposal_id_str = data["proposal_id"]
    value = data["value"]

    # Parse the ProposalID from the string
    number, from_uid = proposal_id_str.split("-")
    proposal_id = ProposalID(int(number), from_uid)
    
    node.recv_accepted(from_uid, proposal_id, value)
    return jsonify({"status": "ok"})


# Node management endpoints
@app.route("/crash", methods=["POST"])
def handle_crash():
    global crashed

    # Simulate a crash
    # logging.info("Simulating crash...")
    crashed = True
    return jsonify({"status": "success", "message": "Node crashed"}), 200

@app.route("/recover", methods=["POST"])
def handle_recover():
    global crashed

    # Simulate a recovery
    # logging.info("Simulating recovery...")
    crashed = False
    write_debug_output(nodes_list, debug_log_file)
    return jsonify({"status": "success", "message": "Node recovered"}), 200

@app.route("/exit", methods=["POST"])
def handle_exit():
    # Log the local log and exit
    logging.info("Exiting...")
    print(f"Local log: {local_log}")

    # Write the log to a file
    output_file = f"output/{output_id}-server-{request.host}.csv"
    with open(output_file, 'w') as f:
        for entry in local_log:
            f.write(f"{entry}\n")

    return jsonify({"status": "success", "message": "Node exiting"}), 200


# Node Sync endpoints
@app.route("/get_log", methods=["GET"])
def get_log_endpoint():
    """Endpoint specifically for synchronization"""
    return jsonify({"get_log": local_log}), 200

@app.route("/merge_log", methods=["POST"])
def merge_log():
    """Merge logs from other nodes"""
    entries = request.json.get("entries", [])
    new_entries = [entry for entry in entries if entry not in local_log]

    if new_entries:
        local_log.extend(new_entries)

    return jsonify({"status": "ok", "merged": len(new_entries)}), 200

@app.route("/status", methods=["GET"])
def handle_status():
    """Check the status of the node"""
    return jsonify({"status": "crashed" if crashed else "running"}), 200

# Main execution
if __name__ == "__main__":
    # Initialize debugger and node
    host, port = address.split(':')
    paxos_debugger = PaxosDebugger(output_id)
    paxos_debugger.clean_old_files()
    
    quorum_size = (len(nodes_list) // 2) + 1  # Calculate quorum size
    
    node = Node(address, nodes_list, quorum_size)

    # Start the Flask server
    app.run(host=host, port=int(port), debug=False)