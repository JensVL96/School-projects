# Distributed Systems Fundamentals: Assignment 1 Part A

## How to Set Up the Environment

### Create a Virtual Environment
    Before running the program. Run the following commands:

    ```bash
    python3 -m venv my-env
    source my-env/bin/activate
    ```

### To deactivate the environment when you are finished:

    ```bash
    deactivate
    ```

### Install Dependencies
The shell script will automatically install all required dependencies when you run it. There is no need to install them separately.


## How to Run the Program

1. **Start on the Front-End Node**: Ensure you are logged into the front-end node of the cluster. This node has access to all available compute nodes.
    ```bash
    ssh "your-username"@ificluster.ifi.uit.no
    ```

2. **Deploy the Servers**  
   To deploy the servers across available nodes, first navigate to the src folder, then run the following command on the cluster:

   ```bash
   ./run.sh <number_of_servers>

3. **Testing the Servers**
    To manually test the HTTP GET requests:

    * Use quotation marks when sending the server list to testscript.py:

    ```bash
    python3 testscript.py '["<host>:<port>","<host>:<port>","<host>:<port>"]'
    ```

    * To check servers using curl, send requests to each server's /helloworld endpoint:

    ```bash
    curl http://<host>:<port>/helloworld
    ```

    To automatically test the script:

    * uncomment this line in the shell script
    'python3 testscript.py "$SERVER_URLS_JSON"'

    'you can also uncomment ./clean.sh or do it manually after'

4. **Clean Up**
    After testing, make sure to run the cleanup script to terminate all running processes:

    ```bash
    ./clean.sh