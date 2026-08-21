


### Most important!
* Make this code parallel
* 




### OpenMP:
* Race conditions in openMP?

### MPI:
* communication
* scalability


### CUDA:
* Data parallelism
* memory management

### Generelt:
* [[Shared memory]]







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


### OpenMP
* Fork-join
* built in load balance
* pragma
* critical section
* pragma omp reduction (assembly, try again. cmpexchange?)
* pragma omp atomic
* work-stealing in openMP?


### Annet
* Work stealing
* emberassingsly parallel
* emoginous like trapasoid (hva faen er det?)
* CUDA debugging (cudaGetErrorString)
* block dimentions
* interconnect 
* reduction operatis
* atomic operations
* Cuda warp
* warp shuffle
* Dissemination sum (sum merge. to avoid divergence by conditional branching)
* conditional branching (and disadvantages)
* threads shuffle
* numerical integration
* traperule4.c
* trapezium
* bank conflict / bank size / bank memory alignment
* Importance of GPU (allignment)
* DSL
* OpenCL
* SYCL
* kernel (different meanings?)
* python flask
* Microsoft PPL
* Musket DSL
* streaming multiprocessor
* 


### Concurrency and paralellism
* Concurrency: concurrency can be without parallelism. But not really the other way around.
* paralellism 
* life lock
* contention
* valgrind
* thread trashing


## parallel

* process states
* code segment
* data segment
* memory speed
* concurrency
* CPU cache
* Pipelining

%%
* heap <-> stack (unstable -> overflow)
* parallel processing vs concurrent execution
* cache closer to cpu = faster
* register fastest memory
* L1, L2 and L3 goes from smallest to larger and fast to slower
* cache is between CPU and RAM
* Pipelining -/ losing in potential of computation
%%

## Flynn's taxonomy

* SISD (single instruction, single data) - sequential, classic CPU
* SIMD (single instruction, multiple data) - GPU / data parallelism
* MIMD (multiple instruction, multiple data) - modern multi-core CPUs
* MISD (multiple instruction, single data) - debugging

## GPU vs CPU:
* parallelistic
* specialization/ purpose
* complexity

%%
	GPU has more processing units than CPUs
	GPU is designed for highly parallel tasks
	CPU is general purpose computation with access to I/O devices
	CPUs have fewer more complex cores than those in GPUs
	%%%%
	GPUs are highly effective for tasks involving algebra due to their parallel processing capabilities. They are extensibly used in 3d video games, neural networks, and matrix multiplications.
	GPUs contain many simple cores designed for highly parallel and specialized computations, particularly suited for simple arithmetic operations.
%%

## CUDA spesifics
* Device SM(streaming multiprocessor)
* architecture

%%
	contains several streaming processors
	the host sends instructions and receives results from the device
	CUDA programs are designed to follow device CUDA abstractions
%%

## memory hierarchy: GPU
* Global memory: %%accessible by all grids and used for sharing data across blocks%%
* shared memory: %%shared within a block and faster than accessing global memory%%
* local memory: %%local to each thread, includes their own memory and registers%%

## CUDA programming model
Single program multiple data (SPMD):
	%%write a single program execute dby all threads, each working with different data
	threads are grouped into blocks, and each block is part of a grid.
	blocks are scheduled on any streaming multiprocessor without a guaranteed order, and shared memory within a streaming multiprocessor can lead to race conditions but also improves performance. memory is allocated globally, and data is transferred to the GPU
	the GPU is instructed via API to launch the kernel, and operations on the CPU can continue asynchronously%%
	
Synchronization %%is required to fetch results, and memory must be deallocated after completion to avoid 	conflicts.%%

workflow
	%%A typical workflow involves allocating a memory region, transferring data to the GPU, launching the kernel, 	and performing asynchronous operations on the CPU. 
	After computations, synchronization is necessary to retrieve results, followed by deallocating memory to 	prevent conflicts.%%

deadlocks
	%%Best practices: avoid synchronization mechanisms on the GPU, due to the complexities and potential for deadlocks, synchronization should be managed carefully and generally avoided within GPU operations.%%

### Pthreads (POSIX Threads)

- Thread Creation
- Thread Management
- Thread Termination
- Synchronization
- Critical Regions
- Shared Virtual Memory

### OpenMP Concepts

- Fork-Join Model
- Task Parallelization
- Compiler Directives
- Scheduling Parallel Tasks
- Thread Management
- Locks and Synchronization

%% ### OpenMP Directives and Clauses

- `#pragma omp parallel`
- `#pragma omp parallel sections`
- `#pragma omp section`
- `#pragma omp for`
- `#pragma omp critical`
- `#pragma omp parallel for schedule(static)`
- `#pragma omp parallel for schedule(dynamic)` 
- `omp_set_num_threads()`
- `omp_get_thread_num()`
- `omp_get_num_threads()`%%

### OpenMP Programming Techniques

- Setting Number of Threads
- Private and Shared Variables
- Reduction Operations
- Critical Sections
- Dynamic vs. Static Scheduling

%% ### Compiler-Specific Directives

- `#pragma GCC poison`
- `#pragma warning` %%

### Practical Examples

- Numerical Integration (Trapezoid Rule)
- Handling Thread Execution on Multiple Cores
- Balancing Workload Among Threads

### Annet

- Accuracy in Computations
- Thread Independence
- Exporting Environment Variables (`OMP_NUM_THREADS`)

### Parallel Programming Frameworks and DSLs

- OpenCL
- SYCL
- Python Dask
- Microsoft PPL
- Musket DSL

### OpenCL

- Heterogeneous Computing
- Device Abstraction
- Command Queues
- Kernel Implementation
- Memory Management
- Performance Optimization
- C99 Specification
- Runtime Device Management
- Workflow Management

### SYCL

- Abstraction over OpenCL
- Simplified Programming Model

### Python Dask

- Task Graphs
- Numpy Integration
- Single-Machine Schedulers
- Hardware Task Assignment

### Microsoft PPL

- Imperative Programming Model
- Concurrent Containers
- Task-Level Parallelism
- Parallel Algorithms (reduce, sort)
- Container Management

### Musket DSL

- Domain-Specific Language for Parallel Programming
- Algorithmic Skeletons
- Data Structure Abstraction
- Meta Information Management
- Kernel Implementation

### General Concepts

- Parallelism Libraries
- Device and Memory Abstractions
- Kernel Execution
- Synchronization and Memory Management
- Data Structures and Task Management