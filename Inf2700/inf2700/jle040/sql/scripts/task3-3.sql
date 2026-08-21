/*  Create a list of incomplete orders (order status is "In process"). The list must contain orderNumber,
requiredDate, productName, quantityOrdered, and quantityInStock.    */

.print "\n\n\t\033[1;3;4;32;1mCreate a list of incomplete orders.\n\033[m"

SELECT  O.orderNumber, O.requiredDate, P.productName, OD.quantityordered, P.quantityInStock, O.status
FROM    Orders O, Products P, OrderDetails OD
WHERE   O.status LIKE "%In process%"
        AND OD.orderNumber = O.orderNumber
        AND OD.productCode = P.productCode;