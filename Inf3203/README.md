# INF-3203 — Distributed Systems (UiT)

Coursework for UiT's Distributed Systems course, co-authored with Bjørn Helge
Kværnmo. Covers distributed computation, fault tolerance and replication,
consensus, and performance trade-offs. Work is in Python.

## Assignment 1 — MapReduce & PageRank

A PageRank implementation built on a from-scratch MapReduce pipeline: input is
split across configurable mapper and reducer stages that compute rank
contributions across pages, with a word-count job as a sanity check and
benchmarking across dataset sizes and mapper/reducer counts.

*MapReduce · PageRank · parallel processing · Python · performance benchmarking*

## Assignment 2 — Paxos Replicated Log

A distributed log service kept consistent across nodes via Paxos consensus:
proposer/acceptor/learner roles, prepare/promise and accept/accepted phases, and
crash/recovery handling. Runs 5 servers + a client, with a comparer tool that
verifies log consistency across nodes after a run.

*Paxos · consensus · replication · fault tolerance · distributed logs · Python*
