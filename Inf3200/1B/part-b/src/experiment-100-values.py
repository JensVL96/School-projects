import argparse
import http.client
import json
import random
import textwrap
import uuid
import time
import os
import statistics  # For calculating standard deviation
import matplotlib.pyplot as plt  # For plotting
import numpy as np  # For numerical operations

def write_debug_file(message, cur_dir, filname='debug.log', write_mode='a'):
    try:
        # Create the full file path by joining the current directory with the filename
        file_path = os.path.join(cur_dir, filname)
        
        # Open the file in write mode using the full file path
        with open(file_path, write_mode) as file:
            file.write(message + '\n')  # Overwrite the file with the message
    except Exception as e:
        print(f"Error writing to debug file: {e}")


def arg_parser():
    parser = argparse.ArgumentParser(prog="client", description="DHT client")

    parser.add_argument("nodes", type=str, nargs="+",
            help="addresses (host:port) of nodes to test")

    return parser  


class Lorem(object):
    """ Generates lorem ipsum placeholder text"""

    sample = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
        velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
        cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
        est laborum.
        """

    def __init__(self):
        # Lowercase words and strip leading/trailing whitespace
        s = self.sample.lower().strip()
        # Filter out punctuation and other non-alpha non-space characters
        s = filter(lambda c: c.isalpha() or c.isspace(), s)
        # Collect filtered letters back into a string, then split into words
        s = ''.join(s).split()
        # Collapse into a set to dedupe words, then turn back into a list
        self.word_list = sorted(list(set(s)))

        self.min_words = 5
        self.max_words = 20

        self.min_sentences = 3
        self.max_sentences = 6

        self.min_paras = 1
        self.max_paras = 5

    def sentence(self):
        nwords = random.randrange(self.min_words, self.max_words)
        rand_words = [random.choice(self.word_list) for _ in range(0, nwords)]
        rand_words[0] = rand_words[0].capitalize()
        return " ".join(rand_words) + "."

    def paragraph(self):
        nsens = random.randrange(self.min_sentences, self.max_sentences)
        rand_sens = [self.sentence() for _ in range(0, nsens)]
        return textwrap.fill(" ".join(rand_sens))

    def text(self):
        nparas = random.randrange(self.min_paras, self.max_paras)
        rand_paras = [self.paragraph() for _ in range(0, nparas)]
        return "\n\n".join(rand_paras)


lorem = Lorem()


def generate_pairs(count):
    pairs = {}
    for x in range(0, count):
        key = str(uuid.uuid4())
        value = lorem.text()
        pairs[key] = value
    return pairs


def put_value(node, key, value):
    conn = None
    try:
        conn = http.client.HTTPConnection(node)
        conn.request("PUT", "/storage/"+key, value)
        conn.getresponse()
    finally:
        if conn:
            conn.close()


def get_value_raw(node, key):
    conn = None
    try:
        # Make request
        conn = http.client.HTTPConnection(node)
        conn.request("GET", "/storage/"+key)
        resp = conn.getresponse()
        status = resp.status
        headers = resp.getheaders()
        value = resp.read()

        # Extract headers
        contenttype = "text/plain"
        for h, hv in headers:
            if h.lower() == "Content-type".lower():
                contenttype = hv

        # Decode value, if text
        if contenttype == "text/plain":
            value = value.decode("utf-8")
        elif contenttype.startswith("text/plain"):
            value = value.decode("utf-8")

        return status, value, contenttype
    finally:
        if conn:
            conn.close()


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


def retrieve_metrics(nodes, tries=100, N=5):
    """
    Perform N runs of PUT and GET operations and collect metrics.
    Returns lists of PUT times, GET times, and success counts.
    """
    pairs = generate_pairs(tries)

    success_counts = []
    put_times = []
    get_times = []

    for run in range(1, N+1):
        print(f"Run {run}/{N}")

        # Timing PUT operations
        put_start_time = time.time()
        for key, value in pairs.items():
            put_node = random.choice(nodes)
            try:
                put_value(put_node, key, value)
            except Exception as e:
                print(f"PUT to {put_node}: EXCEPTION DURING PUT: {e}")
                continue
        put_end_time = time.time()
        elapsed_put = put_end_time - put_start_time
        put_times.append(elapsed_put)

        # Timing GET operations
        get_start_time = time.time()
        successes = 0
        for key, value in pairs.items():
            get_node = random.choice(nodes)
            try:
                status, returned, contenttype = get_value_raw(get_node, key)
            except Exception as e:
                print(f"GET to {get_node}: EXCEPTION DURING GET: {e}")
                continue

            if status in range(200, 300) and returned == value:
                successes += 1
        get_end_time = time.time()
        elapsed_get = get_end_time - get_start_time
        get_times.append(elapsed_get)
        success_counts.append(successes)

    return put_times, get_times, success_counts


def retrieve_from_different_nodes(nodes, tries=100, N=5):
    """
    Retrieve metrics for a given set of nodes.
    Returns average and standard deviation for PUT time, GET time, and success rate.
    """
    print("Retrieving from different nodes ...")

    put_times, get_times, success_counts = retrieve_metrics(nodes, tries, N)

    # Calculate statistics
    avg_put_time = statistics.mean(put_times)
    std_put_time = statistics.stdev(put_times) if N > 1 else 0

    avg_get_time = statistics.mean(get_times)
    std_get_time = statistics.stdev(get_times) if N > 1 else 0

    total_successes = sum(success_counts)
    total_operations = tries * N
    success_rate = (total_successes / total_operations) * 100  # Percentage

    cur_dir = os.getcwd()

    write_debug_file(f"Success rate: {success_rate:.2f}%", cur_dir, "time.log")
    write_debug_file(f"Average PUT time: {avg_put_time:.4f} s ± {std_put_time:.4f} s", cur_dir, "time.log")
    write_debug_file(f"Average GET time: {avg_get_time:.4f} s ± {std_get_time:.4f} s", cur_dir, "time.log")

    return {
        "put_mean": avg_put_time,
        "put_std": std_put_time,
        "get_mean": avg_get_time,
        "get_std": std_get_time,
        "success_mean": success_rate
    }

def main(args):
    # Prepare to store results
    results = {
        "nodes": [],
        "put_mean": [],
        "put_std": [],
        "get_mean": [],
        "get_std": [],
        "success_mean": [],
        "success_std": []
    }

    nodes = set(args.nodes)
    nodes |= walk_neighbours(args.nodes)
    nodes = list(nodes)

    print(f"{len(nodes)} nodes registered: {', '.join(nodes)}")
    write_debug_file(f"Nodes: {len(nodes)}", os.getcwd(), "time.log")

    if len(nodes) == 0:
        raise RuntimeError("No nodes registered to connect to")

    print()

    # Retrieve metrics
    metrics = retrieve_from_different_nodes(nodes, tries=100, N=5)


    # Store the results
    results["nodes"].append(len(nodes))
    results["put_mean"].append(metrics["put_mean"])
    results["put_std"].append(metrics["put_std"])
    results["get_mean"].append(metrics["get_mean"])
    results["get_std"].append(metrics["get_std"])
    results["success_mean"].append(metrics["success_mean"])

    print(f"Results for {len(nodes)} node(s):")
    print(f"  Success Rate: {metrics['success_mean']:.2f}")
    print(f"  Average PUT Time: {metrics['put_mean']:.4f} s ± {metrics['put_std']:.4f} s")
    print(f"  Average GET Time: {metrics['get_mean']:.4f} s ± {metrics['get_std']:.4f} s")
    print()


if __name__ == "__main__":
    # parser = arg_parser()
    # args = parser.parse_args()
    # main(args)

    p
