/* 5. Create a list of customers who have ordered all products that customer 219 has ordered. */

.print  "\n\n\t\033[1;3;4;32;1mCreate a list of customers who have ordered all products that customer 219 has ordered.\n\033[m"

SELECT  C.customerName, O.customerNumber, OD.productCode
FROM    Customers C, OrderDetails OD, Orders O

INNER JOIN (
    SELECT  O.customerNumber, OD.productCode
    FROM    Orders O, OrderDetails OD
    WHERE   O.customerNumber = 219
            AND O.orderNumber = OD.orderNumber
            ) AS PRO

WHERE   OD.productCode = PRO.productCode
        AND O.orderNumber = OD.orderNumber
        AND C.customerNumber = O.customerNumber
GROUP BY    O.customerNumber

;