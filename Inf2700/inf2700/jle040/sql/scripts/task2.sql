.print "\n\n\t\033[1;3;4;32;1mThis is A\n\033[m"
SELECT  C.customerName, C.contactLastName, C.contactFirstName
FROM    Customers C;

.print "\n\n\t\033[1;3;4;32;1mThis is B\n\033[m"
SELECT  *
FROM    Orders O
WHERE   O.shippedDate IS NULL;

.print "\n\n\t\033[1;3;4;32;1mThis is C\n\033[m"
SELECT  C.customerName AS Customer, SUM(OD.quantityOrdered) AS Total
FROM    Orders O, Customers C, OrderDetails OD
WHERE   O.customerNumber = C.customerNumber
        AND     O.orderNumber = OD.orderNumber
GROUP BY    O.customerNumber
ORDER BY    Total DESC;

.print "\n\n\t\033[1;3;4;32;1mThis is D\n\033[m"
SELECT  P.productName, T.totalQuantityOrdered
FROM    Products P NATURAL JOIN
        (SELECT productCode, SUM(quantityOrdered) AS totalQuantityOrdered
        FROM    OrderDetails GROUP BY productCode) AS T
WHERE   T.totalQuantityOrdered >= 1000;