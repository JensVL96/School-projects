Mandatory assignment 1

made by Jens Christian Valen Leynse


Instructions for part 1 task 3:

write: 
    sqlite3 ../inf2700_orders.sqlite3 < task3-4.sql
    
or:
    sqlite3 ../inf2700_orders.sqlite3    and:
    .read task3-4.sql

where:
    sqlite3 (program) 
    ../inf2700_orders.sqlite3 (database)
    < task3-4.sql (pipelining to specific file, extra)   

or:
    sqlite3 (program)
    ../inf2700_orders.sqlite3 (database)     
and after: 
    .read task3-4.sql (sqlite3 command to read from file)

the pipeline is not necessary as the file can be specified inside the Sqlite program, but it's faster


Instructions for part 2:

write:
    make run
    
then either of the four commands:
.help
.exit
.open
.quit

if open then specify the database path 
in this assignment: ../inf2700_orders.sqlite3

then write for the sql query you the user wants

example (given in assignment):
SELECT orderNumber, orderDate FROM Orders WHERE status = 'Cancelled';