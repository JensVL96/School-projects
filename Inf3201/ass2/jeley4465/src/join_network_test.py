import sys
import requests
import ast



if __name__ == "__main__":
    if len(sys.argv) != 3 and len(sys.argv) != 1:
        print("Usage: join_network_test.py <node_in_network> <joining_node> (or no arguments)")
        sys.exit(1)
    if len(sys.argv) == 1:
        # read_file_and_find_nodes
        with open('nodes.txt', 'r') as file:
            nodes = file.read()

        # Convert string to list
        node_list = ast.literal_eval(nodes)

        # Port is the same for all nodes 
        port = node_list[0].split(":")[1]

        # Remove port from node_list
        node_list = [node.split(':')[0] for node in node_list]
        # Read the first two nodes. each node is on a new line
        # If there are less than two lines, return
        if len(node_list) < 2:
            print("Not enough nodes to join")
            sys.exit(1)
        else:
            node_in_network = node_list[0]
            joining_node = node_list[1]
    else:
        # Get arguments from shell script
        node_in_network = sys.argv[1]
        joining_node = sys.argv[2]
    
    if node_in_network == joining_node:
        print("Node_in_network and joining_node cannot be the same.")
        sys.exit(1)


    # Make joining_node join node_in_network
    print(f"Node {joining_node} joining network at node {node_in_network}")
    requests.post(f"http://{joining_node}:{port}/join?nprime={node_in_network}:{port}")

    