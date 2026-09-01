# INF-3701 — Advanced Database Systems (UiT)

Coursework for UiT's Advanced Database Systems course. No code deliverable —
the work is a written research report.

## Report — Distributed Database Benchmarking (YugabyteDB vs Cassandra)

A benchmarking study co-authored with Gunnar Antoni Solli Olsen, comparing the
distributed SQL database YugabyteDB against Apache Cassandra using the Yahoo!
Cloud Serving Benchmark (YCSB) across cluster sizes from 1 to 16 nodes. The
report analyses throughput and consistency trade-offs, and argues that YCSB's
centralized-client design becomes a bottleneck past a 4:1 node ratio — making
the case for distributed benchmarking tooling for modern scalable databases.

*Distributed databases · benchmarking · YugabyteDB · Cassandra · YCSB ·
scalability · consistency models*
