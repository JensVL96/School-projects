#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "crackme.h"

#define MASTER 0
#define RESULT_TAG 1
#define TRANSFER_TAG 2
#define FOUND_TAG 3
#define STEAL_TAG 4
#define CHUNK_SIZE 100000  // Interval to check for synchronization
#define MAX_WAIT_TIME 0.1 // Maximum wait time for stealing work in seconds

// Converts an integer to a password representation in byte format
void int_to_pass(unsigned long long num, unsigned char *pass, int sizePass)
{
    for (int i = sizePass - 1; i >= 0; i--)
    {
        pass[i] = num % 256;
        num >>= 8;  // Shift 8 bits
    }
}

int main(int argc, char *argv[])
{
    int rank, size, flag;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int sizePass = strtol(argv[1], NULL, 10);

    // Allocate memory for pass and stringToTest with sizePass + 1 to include null terminator
    unsigned char *stringToTest = (unsigned char *)malloc(sizePass + 1);
    stringToTest[sizePass] = '\0';  // Ensure null terminator is in place

    int found = 0;
    int counter = 0;
    int steal_from_rank = rank;
    int continue_stealing = 0;

     // Total range for password space
    unsigned long long start;// = rank * rangePerWorker;
    unsigned long long end = 0;// = start + rangePerWorker;
    unsigned long long totalCombinations = 1ULL << (8 * sizePass);
    unsigned long long rangePerWorker = totalCombinations / (size - 1);

    // Define the range of passwords each worker will handle
    if (rank != 0) {
        start = (rank - 1) * rangePerWorker;
        end = (rank == size - 1) ? totalCombinations : start + rangePerWorker;
    }

    double startTime = MPI_Wtime();
    MPI_Request send_request, recv_request;
    MPI_Status status;

    // Main password cracking loop
    while (!found) {
        if (rank == MASTER) {
            // Check if any worker has found the password
            MPI_Iprobe(MPI_ANY_SOURCE, RESULT_TAG, MPI_COMM_WORLD, &flag, &status);
            if (flag) {
                // Receive the found password and set the found flag
                MPI_Recv(stringToTest, sizePass, MPI_UNSIGNED_CHAR, MPI_ANY_SOURCE, RESULT_TAG, MPI_COMM_WORLD, &status);
                found = 1;

                // Notify all other processes to stop
                for (int i = 0; i < size; i++) {
                    if (i != MASTER && i != status.MPI_SOURCE) {
                        MPI_Send(&found, 1, MPI_INT, i, FOUND_TAG, MPI_COMM_WORLD);
                    }
                }
                break;
            }
        } else { // worker processes
            // Check if the master has sent a termination signal (password found)
            MPI_Iprobe(MASTER, FOUND_TAG, MPI_COMM_WORLD, &flag, &status);
            if (flag) {
                // Receive the signal from the master and exit the loop
                MPI_Recv(&found, 1, MPI_INT, MASTER, FOUND_TAG, MPI_COMM_WORLD, &status);
                break;
            }

            // Search for the password in the assigned range
            unsigned long long i;
            for (i = start; i < end && !found; i++) {
                stringToTest[sizePass] = '\0';  // Ensure null terminator before passing to p()
                counter++;
                int_to_pass(i, stringToTest, sizePass);

                if (!p(sizePass, stringToTest)) {
                    printf("Process %d: Found the password. It is: %s\n", rank, stringToTest);
                    found = 1;

                    // Send the found password to the master, including the null terminator
                    MPI_Send(stringToTest, sizePass, MPI_UNSIGNED_CHAR, MASTER, RESULT_TAG, MPI_COMM_WORLD);
                    break;
                }

                // Periodically check for synchronization or termination
                if (counter % CHUNK_SIZE == 0) {
                    MPI_Iprobe(MASTER, FOUND_TAG, MPI_COMM_WORLD, &flag, &status);
                    if (flag) {
                        MPI_Recv(&found, 1, MPI_INT, MASTER, FOUND_TAG, MPI_COMM_WORLD, &status);
                        break;
                    }

                    // Check if any process is requesting to steal work
                    MPI_Iprobe(MPI_ANY_TAG, STEAL_TAG, MPI_COMM_WORLD, &flag, &status);
                    if (flag) {
                        // NOt really necessary to receive rank, but it's halpful to avoid warnings
                        int stealing_rank = 0;
                        MPI_Recv(&stealing_rank, 1, MPI_INT, MPI_ANY_TAG, STEAL_TAG, MPI_COMM_WORLD, &status);

                        unsigned long long steal_end = end;
                        unsigned long long steal_start = steal_end - (steal_end - i) / 2;

                        // Either send ignore rank condition, or send the stolen range
                        if (steal_end - steal_start < CHUNK_SIZE) {
                            unsigned long long empty = -1;
                            MPI_Send(&steal_start, 1, MPI_UNSIGNED_LONG_LONG, stealing_rank, TRANSFER_TAG, MPI_COMM_WORLD);
                            MPI_Send(&empty, 1, MPI_UNSIGNED_LONG_LONG, stealing_rank, TRANSFER_TAG, MPI_COMM_WORLD);
                        } else {
                            end = steal_start;

                            MPI_Send(&steal_start, 1, MPI_UNSIGNED_LONG_LONG, stealing_rank, TRANSFER_TAG, MPI_COMM_WORLD);
                            MPI_Send(&steal_end, 1, MPI_UNSIGNED_LONG_LONG, stealing_rank, TRANSFER_TAG, MPI_COMM_WORLD);
                        }
                    }
                }
            }

            start = i;  // Update the start position for the next iteration

            // Update the rank from which to steal work
            if (!continue_stealing) {
                steal_from_rank = (steal_from_rank % (size - 1)) + 1;
            }

            // Attempt to steal work from the next rank
            if (steal_from_rank == rank && !continue_stealing) {
                break; // tried every process
            } else {
                MPI_Isend(&rank, 1, MPI_INT, steal_from_rank, STEAL_TAG, MPI_COMM_WORLD, &send_request);

                double timeOut = MPI_Wtime(); // Record the start time for timeout
                double currentTime;

                // Check for a response from the rank being stolen from
                while(1) {
                    MPI_Iprobe(steal_from_rank, TRANSFER_TAG, MPI_COMM_WORLD, &flag, &status);
                    if (flag) {
                        unsigned long long newstart, newend;
                        MPI_Recv(&newstart, 1, MPI_UNSIGNED_LONG_LONG, steal_from_rank, TRANSFER_TAG, MPI_COMM_WORLD, &status);
                        MPI_Recv(&newend, 1, MPI_UNSIGNED_LONG_LONG, steal_from_rank, TRANSFER_TAG, MPI_COMM_WORLD, &status);

                        if (newend == -1) { // Received a signal that there's no work
                            continue_stealing = 0;
                            break;
                        } else {            // Update start and end ranges
                            start = newstart;
                            end = newend;
                            continue_stealing = 1;
                            break;
                        }
                    }
                    // timeout condition
                    currentTime = MPI_Wtime();
                    if ((currentTime - timeOut) > MAX_WAIT_TIME) { // Process took to long to respond
                        continue_stealing = 0;
                        break;
                    }
                }
                // mainly to avoid warnings
                MPI_Wait(&send_request, MPI_STATUS_IGNORE);
            }
        }
    }

    double endTime = MPI_Wtime();
    printf("Process %d total time: %f seconds\n", rank, endTime - startTime);

    // MASTER writes the solution to file
    if (rank == MASTER && found) {
        FILE *file = fopen("solution.txt", "w");
        if (file != NULL) {
            fprintf(file, "Found password: ");
            for (int i = 0; i < sizePass; i++) {
                fprintf(file, "%c", stringToTest[i]);
            }
            fprintf(file, "\n");
            fclose(file);
        } else {
            perror("Error opening solution.txt for writing");
        }
    }

    free(stringToTest);
    MPI_Finalize();
    return 0;
}
