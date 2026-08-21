# INF-3200 — Parallel Programming

This folder contains course notes, assignments, and example source code for INF-3200, with a focus on parallel and distributed programming concepts.

## What is here
- `parallel.c` — an MPI-based password-cracking example that splits work across processes and can share or steal work dynamically
- `parallelVault/` — additional code and materials
- `ass2/` — assignment 2
- lecture notes such as `30.08.24.txt`, `15.10.24 - DISTRIBUTED SYSTEMS.txt`, and `exam note 1.txt`

## Project focus
The INF-3200 material covers:
- parallel execution with multiple processes or threads
- work distribution and load balancing
- message passing and synchronization
- fault tolerance, redundancy, and distributed-systems concepts
- GPU, CUDA, and performance-related notes

## Example program
`parallel.c` demonstrates a distributed search over a password space using MPI:
- each worker gets a portion of the search range
- processes can request extra work from others when they finish early
- the master process collects the result and stops the rest when the password is found

## Notes
- The code is an educational example, so it prioritizes learning goals over production-ready robustness.
- Compile the C sources according to the assignment requirements and the libraries available on your system.
