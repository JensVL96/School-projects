# INF-3203 — Distributed Systems

This folder contains the material for INF-3203, including notes and assignment work on distributed systems, parallel processing, and consensus.

## What is here
- `assignment-1/` — MapReduce and PageRank
- `assignment-2/` — Paxos-based replicated log service
- lecture notes and other supporting material

## Assignment 1: MapReduce and PageRank
Assignment 1 implements a PageRank solution using a MapReduce-style pipeline. The code splits the graph processing into mapper and reducer stages and computes rank contributions across pages.

## Assignment 2: Replicated log with Paxos
Assignment 2 builds a distributed log service where several nodes keep their state consistent through Paxos consensus, including crash and recovery handling.

## Course focus
The course material covers:
- distributed computation
- fault tolerance and replication
- consensus and coordination
- scalability and performance trade-offs
- parallel and distributed programming patterns

## Notes
- Each assignment has its own folder with code and documentation.
