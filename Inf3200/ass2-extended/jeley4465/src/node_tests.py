import time
from config import DEBUG_MODE

def TEST_node_circle(nodes):
    start_node = nodes[0]
    iter_node = nodes[0]

    c = 0
    while(1):
        c += 1

        # Check for too large loop
        if c > len(nodes)+1:
            print("TEST FAILED: looped larger than number of nodes")
            break

        # Check for too small loop
        if iter_node.successor == start_node:
            if c < len(nodes):
                print("TEST FAILED: looped smaller than number of nodes")
                return False
                break
            else:
                if DEBUG_MODE: print("TEST PASSED: Circle loop")
                return True
        iter_node = iter_node.successor

def TEST_hash_collision(nodes):
    hash_list = []
    for node in nodes:
        hash_list.append(node.idx)
    if len(hash_list) == len(set(hash_list)):
        if DEBUG_MODE: print("TEST PASSED: Hash collision")
        return True
    else:
        print("TEST FAILED: Hash collision")
        return False

def TEST_ping_all_nodes(nodes):
    MAX_RETRIES = 10
    SLEEP_TIME = 0.2
    

    nodes_down = []

    for node in nodes:
        retries = 0
        while not node.ping() and retries <= MAX_RETRIES:
            time.sleep(SLEEP_TIME)
            retries += 1

        if retries >= MAX_RETRIES:
            print(f"TEST FAILED: Node {node.ip} did not respond after {MAX_RETRIES * SLEEP_TIME} seconds")
            nodes_down.append(node)
            return nodes_down

    if DEBUG_MODE: print("TEST PASSED: All nodes are up")
    return nodes_down

def run_pre_tests(nodes):
    if TEST_node_circle(nodes) and TEST_hash_collision(nodes):
        return True
    else:
        return False

def run_post_tests(nodes):
    nodes_down = TEST_ping_all_nodes(nodes)
    return nodes_down
