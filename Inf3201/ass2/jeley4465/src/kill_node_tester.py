import ast
import requests
import time
import sys


def crash_some_nodes(node_list):
    for node in node_list:
        try:
            requests.post(f"http://{node}:{port}/sim-crash", timeout=5)
        except Exception as e:
            print(f"Error simulating crash on node {node}: {e}")

def node_crash(node):
    print(f"Node {node} has crashed")
    try:
        requests.post(f"http://{node}:{port}/sim-crash", timeout=5)
    except Exception as e:
        print(f"Error simulating crash on node {node}: {e}")

def is_valid_hostname(hostname):
    # Simple check to ensure hostname is not empty and doesn't contain invalid characters
    if not hostname or '<' in hostname or '>' in hostname or '\n' in hostname:
        return False
    return True

def check_if_loop_amended(crashed_pred, crashed_succ):
    try:
        # Validate crashed_pred
        if not is_valid_hostname(crashed_pred) or crashed_pred == crash_this_node:
            print(f"Cannot query crashed or invalid predecessor '{crashed_pred}'")
            return False

        # Get the successor of the crashed predecessor
        crashed_pred_succ_response = requests.get(f"http://{crashed_pred}:{port}/successor", timeout=5)
        if crashed_pred_succ_response.status_code != 200:
            print(f"Failed to get successor from crashed predecessor {crashed_pred}")
            return False
        crashed_pred_succ = crashed_pred_succ_response.text.strip()

        # Validate crashed_succ
        if not is_valid_hostname(crashed_succ) or crashed_succ == crash_this_node:
            print(f"Cannot query crashed or invalid successor '{crashed_succ}'")
            return False

        # Get the predecessor of the crashed successor
        crashed_succ_pred_response = requests.get(f"http://{crashed_succ}:{port}/predecessor", timeout=5)
        if crashed_succ_pred_response.status_code != 200:
            print(f"Failed to get predecessor from crashed successor {crashed_succ}")
            return False
        crashed_succ_pred = crashed_succ_pred_response.text.strip()

        # Validate that the responses are valid hostnames
        if not is_valid_hostname(crashed_pred_succ) or not is_valid_hostname(crashed_succ_pred):
            print("Received invalid data from nodes")
            return False

        score = 0
        out_msg = ""

        if crashed_pred_succ == crashed_succ:
            score += 1
            out_msg += "Successor of crashed predecessor is now the crashed node's successor.\n"

        if crashed_succ_pred == crashed_pred:
            score += 1
            out_msg += "Predecessor of crashed successor is now the crashed node's predecessor.\n"

        if score == 2:
            print("Loop amended")
            print(out_msg)
            return True
        else:
            print("Loop not amended yet.")
            print(out_msg)
            return False
    except Exception as e:
        print(f"Exception occurred during loop amendment check: {e}")
        return False

if __name__ == "__main__":
    with open('nodes.txt', 'r') as file:
        nodes = file.read()

    # Convert string to list
    node_list = ast.literal_eval(nodes)

    # Port is the same for all nodes
    port = node_list[0].split(":")[1]

    # Remove port from node_list
    node_list = [node.split(':')[0] for node in node_list]

    crash_these = []
    n_crash_nodes = 3
    for i in range(n_crash_nodes):
        crash_these.append(node_list.pop())
    crash_some_nodes(crash_these)


    crash_this_node = node_list[1]

    # Get the predecessor and successor of the node to be crashed
    try:
        crashed_pred_response = requests.get(f"http://{crash_this_node}:{port}/predecessor", timeout=5)
        if crashed_pred_response.status_code != 200:
            print(f"Failed to get predecessor of node {crash_this_node}")
            sys.exit(1)
        crashed_pred = crashed_pred_response.text.strip()

        crashed_succ_response = requests.get(f"http://{crash_this_node}:{port}/successor", timeout=5)
        if crashed_succ_response.status_code != 200:
            print(f"Failed to get successor of node {crash_this_node}")
            sys.exit(1)
        crashed_succ = crashed_succ_response.text.strip()

        # Validate predecessor and successor
        if not is_valid_hostname(crashed_pred):
            print(f"Invalid predecessor '{crashed_pred}' received from node {crash_this_node}")
            sys.exit(1)
        if not is_valid_hostname(crashed_succ):
            print(f"Invalid successor '{crashed_succ}' received from node {crash_this_node}")
            sys.exit(1)

    except Exception as e:
        print(f"Failed to get predecessor or successor of node {crash_this_node}: {e}")
        sys.exit(1)

    # Remove the node that is going to crash from the list
    node_list.remove(crash_this_node)

    node_crash(crash_this_node)

    # Wait for the network to stabilize
    while not check_if_loop_amended(crashed_pred, crashed_succ):
        wait_time = 1
        time.sleep(wait_time)
