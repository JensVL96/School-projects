#!/bin/bash

python3 -m pip install -r requirements.txt

# Ensure the script exits on error
set -e

# Requested server amount (input)
NUM_SERVERS=$1

# Check if the number of servers is provided and is a valid number
if [ -z "$NUM_SERVERS" ] || ! [[ "$NUM_SERVERS" =~ ^[0-9]+$ ]]; then
  echo "Usage: $0 <number_of_servers>"
  exit 1
fi

# Retrieve the list of available cluster nodes
NODES=($(/share/ifi/available-nodes.sh))

# Check if any nodes are available
if [ ${#NODES[@]} -eq 0 ]; then
  echo "No available nodes found."
  exit 1
fi

# Calculate the number of nodes available
NUM_NODES=${#NODES[@]}
# echo "number of nodes: $NUM_NODES"

# Function to find an available port
find_available_port() {
  while true; do
    PORT=$(shuf -i 49152-65535 -n1)
    if ! ss -tuln | grep ":$PORT " > /dev/null; then
      echo "$PORT"
      return
    fi
  done
}

# Path to the server
SERVER_SCRIPT="$(realpath server.py)"

# Start servers and collect URLs
SERVER_URLS=()
for (( i=0; i<NUM_SERVERS; i++ ))
do
  NODE=${NODES[$((i % NUM_NODES))]}
  
  # Find an available port
  PORT=$(find_available_port)
  # echo "Port $PORT is available."
  
  # Deploy the server (use SSH to run the server in the background)
  # echo "Starting server on $NODE:$PORT..."
  ssh -f "$NODE" "FLASK_APP=$SERVER_SCRIPT HOSTNAME=$NODE python3 $SERVER_SCRIPT $NODE $PORT > output.txt 2>&1 &"

  # Collect the server's host-port combo
  SERVER_URL="$NODE:$PORT"
  SERVER_URLS+=("$SERVER_URL")
done

# Format the URLs as a single-line JSON list and print 
# R = convert to raw string
# s = combine lines to array
# c = output single line
SERVER_URLS_JSON=$(printf '%s\n' "${SERVER_URLS[@]}" | jq -R . | jq -s . | jq -c .)
echo "$SERVER_URLS_JSON"

# To give the server enough time to set up (error without)
sleep 1
# Optionally run the test script
#python3 testscript.py "$SERVER_URLS_JSON"

#./clean.sh
