/*  Retrieve all customers in Norway.   */

.print "\n\n\t\033[1;3;4;32;1m1. Retrieve all customers in Norway.\n\033[m"

SELECT  C.customerName, C.country
FROM    Customers C
WHERE   C.country LIKE '%Norway%'