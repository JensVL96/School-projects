INF-2200 Computer Architecture and Organization

Mandatory Assignment 3: Memory simulator

from:   jle040, Jens Christian Valen Leynse
		ran081, Rasmus Andreassen


commands (must be performed in /src/):

compile binaries:
    $ make / make clean

generate input file for cachesim binary:
    $ valgrind --log-file=logfile --tool=lackey --trace-mem=yes ./test mergesort.c
    $ python3 traceconverter.py 

run program:
    $ ./cachesim trace.tr 
