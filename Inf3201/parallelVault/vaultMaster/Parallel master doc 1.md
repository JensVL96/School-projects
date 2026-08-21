


### Most important!
* Make this code parallel
* 




### OpenMP:
* Race Conditions
- Fork-Join Model
- Built-in Load Balance
- [[Pragmas]]
- Critical Section
- Reduction
- Atomic Operations
- Work-Stealing
- task parallelization
- compiler directives
- scheduling parallel tasks
- thread management
- locks and synchronization
- Setting Number of Threads
- Private and Shared Variables
- Reduction Operations
- Critical Sections
- Dynamic vs. Static Scheduling
- Accuracy in Computations
- Thread Independence
- Exporting Environment Variables (`OMP_NUM_THREADS`)

### MPI:
* communication
	* point-to-point communication (send & recv)
	* collective communication (scatter, bcast, gather, reduce)
	* synchronizity (blocking and non-blocking)
* scalability
	* load balancing
	* cluster, multicore processors
	* minimal overhead


### CUDA:
* Data Parallelism
- Memory Management
- Debugging (cudaGetErrorString)
- Block Dimensions
- Interconnect
- Reduction Operations
- Atomic Operations
- CUDA Warp
- Warp Shuffle
- Conditional Branching (advantages and disadvantages)
- Numerical Integration (Trapezoid Rule)
- Bank Conflict
- Importance of Alignment
- [[SPMD]]
#### architecture
- [[Synchronization]]
- [[Workflow]]
- [[Deadlocks]]
- [[steaming multiprocessor]]
%%the host sends instructions and receives results from the device
CUDA programs are designed to follow device CUDA abstractions%%

### Generelt:
- [[Global Memory]]
- [[Shared Memory]]
- [[Local Memory]]
- Embarrassingly Parallel
- Threads Shuffle
- Trapezium Rule
- kernel (different meanings?)
-  emoginous like trapasoid (hva faen er det?)
* Dissemination sum (sum merge. to avoid divergence by conditional branching)
* traperule4.c
* [[Importance of GPU (allignment)]]
* process states
* code segment
* data segment
* memory speed
* CPU cache
* Pipelining
* Parallelism Libraries
- Device and Memory Abstractions
- Kernel Execution
- Synchronization and Memory Management
- Data Structures and Task Management


### Pthreads (POSIX Threads)

- Thread Creation
- Thread Management
- Thread Termination
- Synchronization
- Critical Regions
- Shared Virtual Memory


### Parallel Programming Frameworks and DSLs

- [[OpenCL]]
- [[SYCL]]
- [[Python Dask]]
- [[Microsoft PPL]]
- [[Musket DSL]]

### Challenges and debugging in parallel environments
- [[Concurrency vs. Parallelism]]
- Livelock (life lock?)
- Contention
- Valgrind
- Thread Thrashing

## Flynn's taxonomy

* SISD (single instruction, single data) - sequential, classic CPU
* SIMD (single instruction, multiple data) - GPU / data parallelism
* MIMD (multiple instruction, multiple data) - modern multi-core CPUs
* MISD (multiple instruction, single data) - debugging


### Tidligere eksamener stikkord
* Høst 2022
	* Types of paralellism
	* Static vs dynamic load balancing (types of programs that benefit from which one)
	* Memory bound vs compute bound (examples)
	* worker / master 
	* Scaling challenges with MPI
* Høst 2021
* Høst 2020
	* 
