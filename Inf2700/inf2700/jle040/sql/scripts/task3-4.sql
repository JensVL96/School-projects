/*  4. Create a list of customers where the difference between the total price of all ordered products and
the total amount of all payments exceeds the credit limit. The list must contain the customer name,
credit limit, total price, total payment and the difference between the two sums.    */

.print "\n\n\t\033[1;3;4;32;1mCreate a list of customers where the total amount of all payments exceeds the credit limit.\n\033[m"

SELECT  C.customerName, 
        C.creditLimit, 
        Cost.total as TotalPrice, 
        sum(PM.amount) AS totalPayment,
        Cost.total - sum(PM.amount) AS Diff
        
FROM    Customers C, Payments PM

NATURAL JOIN (
        SELECT O.customerNumber, sum(OD.priceEach * OD.quantityOrdered) AS total
        FROM    OrderDetails OD, Orders O
        WHERE O.orderNumber = OD.orderNumber
        GROUP BY O.customerNumber
        ) AS Cost

WHERE   C.customerNumber = PM.customerNumber

GROUP BY C.customerNumber

HAVING Diff > C.creditLimit;