node=$(cat node.txt)
echo Running tests ...

python3 chord-tester.py $node

# python3 experiment-throughput.py $node

# echo -e "\nTesting network:\n"
# curl http://"$node"/network

# echo -e "\nTesting findsuccessor\n"
# curl http://"$node"/find_successor/cat

# echo -e "\nTesting find_successor_finger\n"
# curl http://"$node"/find_successor_finger/car

# echo -e "\nTesting storage:\n"
# curl -X PUT http://"$node"/storage/TestStorageKey1 -H "Content-Type: text/plain" -d "Amazing!"
# curl http://"$node"/storage/TestStorageKey1