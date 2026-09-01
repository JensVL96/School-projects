import ast
import requests
import http
import json
import time
import os
from helper import write_debug_file
from config import STABILIZE_INTERVAL
from datetime import datetime

cur_dir = os.getcwd()

def get_neighbours(node):
    conn = http.client.HTTPConnection(node)
    conn.request("GET", "/network")
    resp = conn.getresponse()
    if resp.status != 200:
        neighbors = []
    else:
        body = resp.read()
        neighbors = json.loads(body)
    conn.close()
    return neighbors

def walk_neighbours(start_nodes):
    to_visit = start_nodes
    visited = set()
    while to_visit:
        next_node = to_visit.pop()
        visited.add(next_node)
        neighbors = get_neighbours(next_node)
        for neighbor in neighbors:
            if neighbor not in visited:
                to_visit.append(neighbor)
    return visited

def two_single_nodes(node_list):
    assert(len(node_list) == 3)

    node1 = node_list[0]
    node2 = node_list[1]
    node3 = node_list[2]

    requests.post(f"http://{node2}:{port}/join?nprime={node1}:{port}")

    time.sleep(5)

    node1_predecessor = requests.get(f"http://{node1}:{port}/predecessor").text
    node1_successor = requests.get(f"http://{node1}:{port}/successor").text
    node2_predecessor = requests.get(f"http://{node2}:{port}/predecessor").text
    node2_successor = requests.get(f"http://{node2}:{port}/successor").text
    node3_predecessor = requests.get(f"http://{node3}:{port}/predecessor").text
    node3_successor = requests.get(f"http://{node3}:{port}/successor").text

    print(f"\nAfter joining {node2} to {node1}")

    print(f"Node1: {node1}")
    print(f"  Predecessor: {node1_predecessor}")
    print(f"  Successor: {node1_successor}")
    print(f"Node2: {node2}")
    print(f"  Predecessor: {node2_predecessor}")
    print(f"  Successor: {node2_successor}")
    print(f"Node3: {node3}")
    print(f"  Predecessor: {node3_predecessor}")
    print(f"  Successor: {node3_successor}")

    time.sleep(10)

    requests.post(f"http://{node3}:{port}/join?nprime={node1}:{port}")

    node1_predecessor = requests.get(f"http://{node1}:{port}/predecessor").text
    node1_successor = requests.get(f"http://{node1}:{port}/successor").text
    node2_predecessor = requests.get(f"http://{node2}:{port}/predecessor").text
    node2_successor = requests.get(f"http://{node2}:{port}/successor").text
    node3_predecessor = requests.get(f"http://{node3}:{port}/predecessor").text
    node3_successor = requests.get(f"http://{node3}:{port}/successor").text

    print(f"\nAfter joining {node3} to {node1}")

    print(f"Node1: {node1}")
    print(f"  Predecessor: {node1_predecessor}")
    print(f"  Successor: {node1_successor}")
    print(f"Node2: {node2}")
    print(f"  Predecessor: {node2_predecessor}")
    print(f"  Successor: {node2_successor}")
    print(f"Node3: {node3}")
    print(f"  Predecessor: {node3_predecessor}")
    print(f"  Successor: {node3_successor}")

    
def ping_all(node_list):
    for node in node_list:
        x = requests.get(f"http://{node}:{port}/helloworld")
        print(f"{node}: {x.status_code}")


# Function to calculate the time since the last log entry
def time_to_stabilize(file_path, start_time):
    with open(file_path, 'r') as file:
        # Read all lines and get the last one
        lines = file.readlines()
        if not lines:
            print("Log file is empty.")
            return

        # Get the timestamp from the last line
        last_line = lines[-1]
        timestamp_str = last_line[1:20]  # Extracts the timestamp portion
        last_timestamp = datetime.strptime(timestamp_str, '%Y-%m-%d %H:%M:%S')

        # Calculate the time difference
        time_difference = last_timestamp - start_time

        return time_difference

def time_to_grow(node_list):
    global cur_dir

    first_node = node_list[0]

    # Make all nodes join the first node

    for node in node_list:
        if node == first_node:
            continue

        requests.post(f"http://{node}:{port}/join?nprime={first_node}:{port}")
    return

    # time.sleep(3)
    
    successor_edges = []
    predecessor_edges = []

    for node in node_list:
        predecessor = requests.get(f"http://{node}:{port}/predecessor")
        successor = requests.get(f"http://{node}:{port}/successor")

        if predecessor.status_code != 200:
            predecessor = "None"
        else:
            predecessor = predecessor.text

        if successor.status_code != 200:
            successor = "None"
        else:
            successor = successor.text

        print(f"Node: {node}")
        print(f"  Predecessor: {predecessor}")
        print(f"  Successor: {successor}")

        successor_edges.append((node, successor))
        predecessor_edges.append((node, predecessor))

    write_debug_file(f"nodes:{node_list}", cur_dir, 'graph.txt', 'a', f=True)
    write_debug_file(f"successor_edges:{successor_edges}", cur_dir, 'graph.txt', 'a', f=True)
    write_debug_file(f"predecessor_edges:{predecessor_edges}", cur_dir, 'graph.txt', 'a', f=True)


def burst_kill_nodes(node_list, count):
    import random

    count = min(count, len(node_list))

    random.shuffle(node_list)

    nodes_to_crash = node_list[:count]

    for node in nodes_to_crash:
        r = requests.post(f"http://{node}:{port}/sim-crash")

        if r.status_code == 200:
            print(f"Killed node {node}")
            

def kill_random_node(node_list):
    import random

    for node in node_list:
        node = random.choice(node_list)
        r = requests.post(f"http://{node}:{port}/sim-crash")

        if r.status_code == 200:
            print(f"Killed node {node}")
            break

def init_create_network(node_list):
    global cur_dir

    first_node = node_list[0]

    # Make all nodes join the first node

    for node in node_list:
        if node == first_node:
            continue

        requests.post(f"http://{node}:{port}/join?nprime={first_node}:{port}")
    return


if __name__ == "__main__":
    with open('nodes.txt', 'r') as file:
        nodes = file.read()

    write_debug_file("", cur_dir, 'graph.txt', 'w', f=True)

    # Convert string to list
    node_list = ast.literal_eval(nodes)

    # Port is the same for all nodes 
    port = node_list[0].split(":")[1]

    # Remove port from node_list
    node_list = [node.split(':')[0] for node in node_list]

    current_time = datetime.now()
    write_debug_file(f"Starting time: {current_time}", cur_dir, 'debug.log', 'w', f=True)

    # init_create_network(node_list)
    # time_to_grow(node_list)
    burst_kill_nodes(node_list, 12)

    # kill_random_node(node_list)
    # time_to_grow(node_list)
    # ping_all(node_list)
    # two_single_nodes(node_list)

    last_time = current_time
    while True:
        t = time_to_stabilize("debug.log", current_time)
        print(f"Time to stabilize: {t.seconds}")

        time.sleep(1)
    time_to_grow(node_list)
