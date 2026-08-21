import requests
from helper import hash_mod, is_in_range

class Node():
    def __init__(self, ip, port, ring_size_pow):
        self.ip = ip
        self.port = port
        self.ring_size_pow = ring_size_pow
        self.ring_size = 2**ring_size_pow
        self.idx = hash_mod(ip, self.ring_size)
        self.files = []
        self.predecessor = None
        self.successor = None
        self.fingerTable = {}

    def print_fingertable(self):
        print(f"Mod Size: {self.ring_size}")
        for i in range(0, self.ring_size_pow):
            pow_idx = (2**i)
            key_idx = ((2**i)+self.idx)%self.ring_size
            print(f"{self.idx} + {pow_idx} -> {hash_mod(self.fingerTable[key_idx], self.ring_size)}")

        for elem in self.fingerTable:
            print(f"Table:{elem} -> Node:{self.fingerTable[elem]}, Node idx: {hash_mod(self.fingerTable[elem], self.ring_size)}")
    
    def ping(self):
        url = f"http://{self.ip}:{self.port}/helloworld"
        try:
            response = requests.get(url)
            return response.status_code == 200
        except Exception:
            return False


        
    def init_finger_table(self):
        if self.successor == self:
            for i in range(0, self.ring_size_pow):
                key_idx = ((2**i)+self.idx)%self.ring_size
                self.fingerTable[key_idx] = self.ip
            return


        node_iter = self
        for i in range(0, self.ring_size_pow):
            key_idx = ((2**i)+self.idx)%self.ring_size
            while not is_in_range(key_idx, node_iter.idx, node_iter.successor.idx, self.ring_size):
                node_iter = node_iter.successor
            self.fingerTable[key_idx] = node_iter.successor.ip



  