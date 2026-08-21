from node_tests import run_pre_tests, run_post_tests
from node import Node
import sys
import subprocess
import random
import os
from concurrent.futures import ThreadPoolExecutor
from config import RING_SIZE_POW, DEBUG_MODE

RING_SIZE = 2**RING_SIZE_POW


def init_nodes_from_ips(ip_list, cur_dir):
    # Generate the nodes
    nodes = []
    port_num = random.randint(49152, 65535)

    for ip in ip_list:
        nodes.append(Node(ip, port_num, RING_SIZE_POW))
        
    # Open the file in write mode using the full file path
    with open(os.path.join(cur_dir, 'node.txt'), 'w') as file:
        file.write(f"{ip_list[0]}:{port_num}\n")

    # Sort the nodes based on the circle idx
    nodes.sort(key=lambda item: item.idx)
    for i in range(len(nodes)):
        nodes[i].predecessor = nodes[i-1]
        nodes[i].successor = nodes[(i+1) % len(nodes)]
        if DEBUG_MODE:
            print(f"idx={nodes[i].idx} Key-range=({nodes[i].predecessor.idx}, {nodes[i].idx}] {nodes[i].ip}:{nodes[i].port}")
    
    for i in range(len(nodes)):
        nodes[i].init_finger_table()
    return nodes

def deploy_node(item, cur_dir):
    print(f"Deploying node (idx={item.idx}): {item.ip}:{item.port}")

    server = "server_node.py"
    predecessor = item.predecessor.ip
    successor = item.successor.ip
    finger_nodes = ",".join([str(item.fingerTable[key]) for key in item.fingerTable])

    command = f"ssh -f {item.ip} 'python3 {cur_dir}/{server} {item.ip} {item.port} {predecessor} {successor} {RING_SIZE_POW} {cur_dir} {finger_nodes}'"
    subprocess.run(command, shell=True)

def deploy_nodes(nodes, using_threads, cur_dir):
    print("Starting node deployment...")
    if using_threads == True:
        with ThreadPoolExecutor(max_workers=12) as executor:
            print(f"Using {executor._max_workers} threads.")
            executor.map(deploy_node, nodes, [cur_dir]*len(nodes))
    else:
        for node in nodes:
            deploy_node(node, cur_dir) 

    print("Node deployment completed.")

if __name__ == "__main__":
    if DEBUG_MODE: print("\n\n------Entered python script-----\n")

    # Get arguments from shell script
    cur_dir = sys.argv[1]
    n_nodes = int(sys.argv[2])
    using_threads = bool(sys.argv[3])
    node_list = sys.argv[4:]  

    # Set to correct number of nodes
    node_list = node_list[:n_nodes]
    assert len(node_list) == n_nodes, "Not correct number of nodes!"
    assert(n_nodes > 0)

    nodes = init_nodes_from_ips(node_list, cur_dir)

    # Run tests
    assert run_pre_tests(nodes), "Tests did not pass!"

    if DEBUG_MODE:
        for node in nodes:
            print(f"\nFingertable of {node.ip}:{node.port}")
            node.print_fingertable()

    # Deploy nodes
    deploy_nodes(nodes, using_threads, cur_dir)

    nodes_down = run_post_tests(nodes)
    if nodes_down != []:
        print("Nodes down:", nodes_down)
        sys.exit(1)
