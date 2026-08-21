import hashlib
import requests
import os
import time
from config import DEBUG_MODE

"""
The hash function outputs a number with 160 bits (20 bytes). This means that the maximum ring size is 2^160. 
"""
def hash_mod(name, circle_size):
    assert isinstance(circle_size, int), "circle_size must be an integer"
    hash_value = int(hashlib.sha1(name.encode()).hexdigest(), 16)
    return hash_value % circle_size

"""
The key is in range if the key exists between the id of the current node and the id of the predecessor node
In the find_successor algorithm, the lower_bound is the current node and the upper_bound is the successor
since the node checks if the key exists in the successor node. In the /storage PUT and GET requests, the
lower_bound is the predecessor node and the upper_bound is the current node since the node checks if the key
exists in the current node.
"""
def is_in_range(key, lower_bound, upper_bound, ring_size):
    if upper_bound < lower_bound:
        # print(f"{lower_bound} < {key} <= {ring_size} or 0 <= {key} <= {upper_bound}")
        return lower_bound < key and key <= ring_size or 0 <= key and key <= upper_bound
    else:
        return lower_bound < key <= upper_bound
    
def write_debug_file(message, cur_dir, filname='debug.log', write_mode='a'):
    if not DEBUG_MODE:
        return
    try:
        # Create the full file path by joining the current directory with the filename
        file_path = os.path.join(cur_dir, filname)
        
        # Open the file in write mode using the full file path
        with open(file_path, write_mode) as file:
            file.write(message + '\n')  # Overwrite the file with the message
    except Exception as e:
        print(f"Error writing to debug file: {e}")


if __name__ == "__main__":
    if is_in_range(41, 192, 39, 256):
        print("Success")
    else:
        print("Failure")