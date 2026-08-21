# README

## Setting up the Chord Network

To setup the chord network, go to `src` and use the command
```bash
./run.sh <number of nodes>
```

The `./run.sh` script will initialize the node servers with multiple threads as long as the script is not run on the frontend node on the cluster. This is to reduce the load of the frontend node if it were to be run on the frontend node, which is not recommended. Instead, it is recommended to ssh into any compute node on the cluster before starting up the chord network. A list of compute nodes can be found by running `/share/ifi/available-nodes.sh`.


## Config
when running: SINGLE_NODE = True
all the nodes start alone, and not in a network cluter

when running: SINGLE_NODE = False
all the nodes start in the same network, with fingertables and neighburs.

## Group
[group.txt](./doc/group.txt)
