# INF-3201 — Parallel Programming (UiT)

Coursework for UiT's Parallel Programming course: models of parallel
computation, message passing (MPI) and shared memory (OpenMP/Pthreads), data
partitioning, and load balancing. Includes lecture and revision notes.

## Assignment — MPI Password Cracker ("CrackMe")

A distributed brute-force password search in C using **MPI**: the search space
is partitioned across worker processes, workers dynamically steal work from each
other when they finish their range early, and the master collects the result
and signals the others to stop once the password is found.

*MPI · message passing · work stealing · load balancing · parallel search · C*

## Notes

`parallelVault/` is an Obsidian-style notes vault covering parallel-programming
concepts (concurrency vs. parallelism, deadlocks, memory models, GPU alignment,
SYCL/OpenCL, pragmas, Dask), plus separate lecture `.txt` notes.
