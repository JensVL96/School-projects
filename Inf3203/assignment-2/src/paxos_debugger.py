import time
import os
import requests
from glob import glob

last_known_logs = {}

def write_debug_output(nodes_list, debug_log_file, new_value=None):
    global last_known_logs
    
    output_lines = []
    
    if new_value:
        output_lines.append(f"new put value: {new_value}")

    time.sleep(1)
    
    # Initialize last_known_logs if empty
    # if not last_known_logs:
    #     last_known_logs = {f"node{i+1}": [] for i in range(len(nodes_list))}
    
    # Check each node's status
    for i, address in enumerate(nodes_list):
        node_id = f"node{i+1}"
        try:
            # First check if node is reachable
            status_response = requests.get(f"http://{address}/status", timeout=0.5)
            if status_response.status_code != 200:
                raise ConnectionError("Status check failed")
            
            is_crashed = status_response.json().get("status") == "crashed"
            
            # Only fetch logs if node is not crashed
            current_log = []
            if not is_crashed:
                log_response = requests.get(f"http://{address}/get_log", timeout=0.5)
                if log_response.status_code == 200:
                    current_log = log_response.json().get("get_log", [])
            
            # Format output line
            line = f"{node_id}: {current_log}"
            if is_crashed:
                line += " [crashed]"
            
            output_lines.append(line)
            last_known_logs[node_id] = current_log.copy()
            
        except Exception:
            # Node is completely unreachable - use last known log
            current_log = last_known_logs.get(node_id, [])
            output_lines.append(f"{node_id}: {current_log} [unreachable]")
    
    # Write to debug file
    with open(debug_log_file, "a") as f:
        f.write("\n".join(output_lines) + "\n\n")

class PaxosDebugger:
    def __init__(self, output_id):
        self.trace_file = f"paxos_debug_logs/paxos_trace_{output_id}.log"
        self._clear_file()
        self.proposal_history = {}
        
    def _clear_file(self):
        with open(self.trace_file, 'w') as f:
            f.write("Paxos Proposal Trace\n")
            f.write("="*50 + "\n")
            
    def trace_proposal(self, proposal_id, value, stage, **info):
        """Track a proposal through all stages"""
        timestamp = time.strftime("%H:%M:%S.%f")
        
        if proposal_id not in self.proposal_history:
            self.proposal_history[proposal_id] = {
                'value': value,
                'start': timestamp,
                'stages': []
            }
            
        self.proposal_history[proposal_id]['stages'].append(
            (timestamp, stage, info))
            
        self._write_trace(proposal_id)
        
    def _write_trace(self, proposal_id):
        entry = self.proposal_history[proposal_id]
        with open(self.trace_file, 'a') as f:
            f.write(f"\nProposal {proposal_id}\n")
            f.write(f"Value: {entry['value']}\n")
            f.write(f"Start: {entry['start']}\n")
            f.write("-"*50 + "\n")
            for time, stage, info in entry['stages']:
                f.write(f"{time} | {stage.upper()}\n")
                for k, v in info.items():
                    f.write(f"  {k}: {v}\n")
            f.write("="*50 + "\n")

    def clean_old_files(self):
        self.debug_dir = os.path.dirname(self.trace_file)
    
        # Get all files in the debug directory
        files = glob(f"{self.debug_dir}/*")
        
        # Remove all files in the directory
        for file in files:
            if os.path.exists(file):
                os.remove(file)
            
    def record_state(self, node_id, role, state_data):
        """Appends state with visual separation"""
        with open(self.debug_file, 'a') as f:
            f.write(f"\n{'='*40}\n")
            f.write(f"Time: {time.ctime()}\n")
            f.write(f"Node: {node_id} | Role: {role}\n")
            for k, v in state_data.items():
                f.write(f"{k:>20}: {v}\n")
            f.write(f"{'='*40}\n\n")
        
    def _maybe_flush(self):
        """Flush to file periodically"""
        if time.time() - self.last_update > 0.5:  # Every 500ms
            self.flush_to_file()
            self.last_update = time.time()
    
    def flush_to_file(self):
        """Write current state to debug file"""
        with open(self.debug_file, 'w') as f:
            for node_id, roles in self.states.items():
                f.write(f"=== {node_id} ===\n")
                for role, state in roles.items():
                    f.write(f"{role.upper()}:\n")
                    for k, v in state.items():
                        f.write(f"  {k}: {v}\n")
                f.write("\n")