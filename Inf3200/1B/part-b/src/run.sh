#!/bin/bash

using_threads=false

if [[ $(hostname) == "ificluster.ifi.uit.no"* ]]; then
  using_threads=true
  echo "Running on the cluster frontend (Not using threads for starting nodes)"
else
  using_threads=true
  echo "Running on a cluster node (Using threads for starting nodes)"
fi

# Check if it is a argument
if [ -z "$1" ]; then
  echo "please provide the number of nodes"
  exit 1
fi

# Number of nodes retrieved from the argument
N_NODES=$1

PWD=$(pwd)

# Finds available nodes, but times out after 5 seconds
NODES_TMP=$(timeout 2s /share/ifi/available-nodes.sh)

# Creates array from the output
NODES=($NODES_TMP)

# Check if nodes were found
if [ ${#NODES[@]} -eq 0 ]; then
    echo "No nodes found!"
    exit 1
fi

# Path to the Python script
CIRCLE_INIT="init_circle.py"

# Pass the current directory and the nodes as individual arguments
python3 "$CIRCLE_INIT" "$PWD" "$N_NODES" "$using_threads" "${NODES[@]}"