write a single program execute dby all threads, each working with different data
threads are grouped into blocks, and each block is part of a grid.
blocks are scheduled on any streaming multiprocessor without a guaranteed order, and shared memory within a streaming multiprocessor can lead to race conditions but also improves performance. memory is allocated globally, and data is transferred to the GPU
the GPU is instructed via API to launch the kernel, and operations on the CPU can continue asynchronously

[[Parallel master doc 1#CUDA]]