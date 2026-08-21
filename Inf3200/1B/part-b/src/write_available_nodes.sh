#!/bin/bash


# Run the command on the frontend node and store in the hostfile
NODES_TMP=$(timeout 3s ssh ificluster "bash -s" < /share/ifi/available-nodes.sh > nodes.txt)

# remove unresponsive node
sed -i '/[cC]3-15/d' nodes.txt