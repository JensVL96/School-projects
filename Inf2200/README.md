# INF-2200 — Computer Architecture and Organization (UiT)

Coursework for UiT's Computer Architecture course, working from the assembly
level up to CPU and memory-system simulation. Three assignments in x86 assembly,
Python, and C.

## Assignment 1 — Assembly Optimization

Hand-optimizing a program in x86 assembly (`asm.S`) and profiling it: comparing
the compiler-generated assembly against a hand-tuned version and measuring the
speedup with `gprof`.

*x86 assembly · optimization · profiling (gprof) · C · Makefiles*

## Assignment 2 — MIPS CPU Simulator

A cycle-level simulator for a subset of the MIPS architecture in Python:
implementing the datapath and control elements (ALU, control unit, register
file, muxes, sign-extend, shifters) as `CPUElement` subclasses, running sample
programs (Fibonacci, selection sort) from memory images.

*computer architecture · MIPS · CPU datapath · control logic · simulation ·
Python*

## Assignment 3 — Cache Simulator

A memory-hierarchy simulator in C: an L1 read-only instruction cache, an L1 data
cache, and a unified L2 cache, used to measure hit/miss ratios and experiment
with cache size, associativity, and replacement policy to find the best design
for a benchmark.

*caches · memory hierarchy · associativity · LRU · performance measurement · C*
