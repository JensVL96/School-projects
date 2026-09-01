# INF-2700 — Database Systems (UiT)

Coursework for UiT's Database Systems course, covering SQL and the internals of
a database management system. Assignment 1 is SQL; assignments 2–3 implement
DBMS internals in C.

## Assignment 1 — SQL

Schema description and querying against a SQLite orders database: documenting
the eight-table schema (keys, foreign keys, self-references) and writing SQL
queries, with a small C-based SQL tester harness.

*SQL · relational schemas · SQLite · query writing*

## Assignments 2–3 — DBMS Internals in C

Implementing core pieces of a database engine in C on a provided pager/schema
framework: extending the query interpreter to handle all comparison operators
(=, <, <=, >, >=, !=), generating arbitrary-size test tables, replacing linear
search with binary search and benchmarking the two, comparing against a B+-tree,
and implementing join operations.

*DBMS internals · C · paging & storage · binary search · B+-trees · join
algorithms · query interpretation · performance analysis*

**Note:** several `db2700/` and `dbms/` folders are kept as development stages
(from the handout starter code through successive versions); the most complete
is `dbms/`.
