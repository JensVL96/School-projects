import requests
import ast


# for node in nodes do GET /node-info
def print_all_nodes_infos(nodes):
    print("------- NODES: ------")
    for node in nodes:
        try:
            # request with timeout
            predecessor = requests.get(f"http://{node}:{port}/predecessor", timeout=3)
            successor = requests.get(f"http://{node}:{port}/successor", timeout=3)
            if predecessor.status_code != 200 or successor.status_code != 200:
                print(f"Node {node} did not respond")
                continue
            else:
                predecessor = predecessor.text
                successor = successor.text
        except Exception as e:
            print(f"Node {node} did not respond")
            continue

        # predecessor = requests.get(f"http://{node}:{port}/predecessor")
        # if predecessor.status_code != 200:
        #     print(f"Node {node} did not respond")
        #     continue
        # else:
        #     predecessor = predecessor.text
        # successor = requests.get(f"http://{node}:{port}/successor")
        # if successor.status_code != 200:
        #     print(f"Node {node} did not respond")
        #     continue
        # else:
        #     successor = successor.text
        print(f"Node: {node} Predecessor: {predecessor} Successor: {successor}")

    print("---------------------")



if __name__ == "__main__":
    with open('nodes.txt', 'r') as file:
        nodes = file.read()

    # Convert string to list
    node_list = ast.literal_eval(nodes)

    # Port is the same for all nodes 
    port = node_list[0].split(":")[1]

    # Remove port from node_list
    node_list = [node.split(':')[0] for node in node_list]

    print_all_nodes_infos(node_list)