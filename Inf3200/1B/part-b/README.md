# README

## Setting up the Chord Network

To setup the chord network, go to `src` and use the command
```bash
./run.sh <number of nodes>
```

The `./run.sh` script will initialize the node servers with multiple threads as long as the script is not run on the frontend node on the cluster. This is to reduce the load of the frontend node if it were to be run on the frontend node, which is not recommended. Instead, it is recommended to ssh into any compute node on the cluster before starting up the chord network. A list of compute nodes can be found by running `/share/ifi/available-nodes.sh`.

## Testing the network

To test the chord network, you can run ``python3 chord-tester.py <entry node>``
or ``./tests.sh``.

We usually utilized the `tests.sh` file to make it easier to run tests. An entry node in the network is needed before any tests can be run. When the `./run.sh` script is finished, it writes an access node to a file called `node.txt` which is used in `tests.sh` to automate testing.

The entry node needs to contain both the host name and port of the host. The `chord-tester.py` is by default uncommented inside `tests.sh`.


## Config
Debug printing can be turned on or off in the config file `config.py`.
There is also an option to use finger tables or no finger tables.

## Group
[group.txt](./doc/group.txt)