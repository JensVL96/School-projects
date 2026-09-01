# INF-3200 — Distributed Systems (UiT)

Coursework for UiT's Distributed Systems course, building a **Chord distributed
hash table** deployed on the university compute cluster. Covers consistent
hashing, ring topology, finger tables, and performance measurement. Work is in
Python; includes lecture and exam notes.

## Assignment 1 — Distributed Hash Table (Chord)

**Part A** builds up the fundamentals: HTTP-based key-value server nodes deployed
across cluster compute nodes, with scripts to launch and test a small network.

**Part B** implements the full **Chord** protocol — nodes arranged in a hash
ring with successor/predecessor links and finger tables for O(log n) lookups,
plus a graceful-shutdown mechanism. Includes a tester that verifies key
storage/retrieval across the ring and throughput/scaling experiments (e.g.
100-value runs and throughput benchmarks), with finger tables toggleable via
config.

*Chord DHT · consistent hashing · finger tables · distributed key-value store ·
cluster deployment · throughput benchmarking · Python*
