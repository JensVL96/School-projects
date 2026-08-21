Task 2:
To run the code. Enter the db2700 folder in the assignment directory
To initialize type in: make run 

Execution:
./run_front < queries.txt > /dev/null

or:
./run_front < queries.sql > /dev/null

The first one is a smaller table with easy to read values. 
The second one is larger to test a more challenging case. 
(The “/dev/null is only to remove the echo from the sqlite output)

Task 3:
Follow the same steps as the previous task.

Input this instead for execution:
./run_test

(Swap the comments on "schema.c" schema creation to test other method)