# Distributed Log Server with Paxos Consensus

## Project Structure

    src/
    ├── output/ # Stores log output files\
    ├── clean-output.sh # Cleans the output directory\
    ├── kill-local.sh # Kills all local Python processes\
    ├── log-client.py # Client for sending log entries\
    ├── log-comparer.py # Verifies log consistency\
    ├── log-server.py # Paxos-based log server\
    ├── paxos.py # Paxos algorithm implementation\
    └── run.sh # Main execution script

## Quick Start
### Basic Usage
```bash
cd src
chmod +x *.sh       # Make all scripts executable
./run.sh            # Start the system (5 servers + 1 client)
```

## Verify Consistency
After the run completes, check log consistency:
```bash
python3 log-comparer.py <output_id>
```


## Scripts
```bash
./clean-output.sh	Cleans all log files from output directory
./kill-local.sh	Stops all running server/client processes
./run.sh	Starts the system with auto-generated timestamp ID
```

## key files
log-server.py

    Implements Paxos consensus protocol

    Handles PUT/POST requests for log entries

    Manages crashed/recovered states


paxos.py

    Core Paxos algorithm implementation:

        Proposer, Acceptor, Learner roles

        Prepare/Promise and Accept/Accepted phases

## Debugging
    All debugging files will be generated in the debug_paxos_logs folder
    The trace follows the node communications
    The debug follows the nodes contents during each paxos run

## notes
    Output files are saved in output/ with timestamp IDs

    Server logs are compared for consistency using log-comparer.py

    System requires Python 3 and standard libraries