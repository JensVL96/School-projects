-- write settings and queries here and run
-- sqlite3 inf2700_orders.sqlite3 < assignment1_queries.sql
-- to perform the queries
-- for example:

.mode column
.header on

SELECT DISTINCT productName, productVendor
FROM   Products
LIMIT  6;

SELECT customerName, contactLastName, contactFirstName
FROM
Customers;

SELECT *
FROM Orders
WHERE shippedDate IS NULL;

SELECT C.customerName AS Customer, SUM(OD.quantityOrdered) AS Total
FROM Orders O, Customers C, OrderDetails OD
WHERE O.customerNumber = C.customerNumber
AND O.orderNumber = OD.orderNumber
GROUP BY O.customerNumber
ORDER BY Total DESC;

SELECT P.productName, T.totalQuantityOrdered
FROM Products P NATURAL JOIN
    (SELECT productCode, SUM(quantityOrdered) AS totalQuantityOrdered
    FROM OrderDetails GROUP BY productCode) AS T
WHERE T.totalQuantityOrdered >= 1000;
 
-- Retrieves all customers in Norway
SELECT customerName, country
FROM Customers C
WHERE country LIKE '%Norway%';


-- Retrieves all classic car products and their scale
SELECT productCode, productName, productScale
FROM Products
WHERE productLine LIKE '%Classic Cars%';


-- Create a list of incomplete orders (order status is "In process"). 
-- The list must contain: orderNumber, requiredDate, productName, 
-- quantityOrdered and quantityInStock.
SELECT O.orderNumber, O.requiredDate, P.productName, OD.quantityOrdered, P.quantityInStock, O.status
FROM Orders O
JOIN OrderDetails OD ON OD.productCode = P.productCode
JOIN Products P ON O.orderNumber = OD.orderNumber
WHERE O.status LIKE '%In Process%'
GROUP BY O.orderNumber;


-- Create a list of customers where the difference between the total 
-- price of all ordered products and the total amount of all payments 
-- exceeds the credit limit. The list must contain the customername, 
-- credit limit, total price, total payment and the difference between 
-- the two sums.
SELECT  C.customerName, 
        C.CreditLimit, 
        SUM(OD.quantityOrdered * OD.priceEach) AS totalPrice,
        SUM(P.amount) AS TotalPayment,
        SUM(P.amount) - SUM(OD.quantityOrdered * OD.priceEach) AS balanceDifference                                -- difference between the two
FROM Customers C
JOIN Orders O ON C.customerNumber = O.customerNumber
JOIN OrderDetails OD ON O.orderNumber = OD.orderNumber
JOIN Payments P ON C.customerNumber = P.customerNumber
GROUP BY C.customerName
HAVING SUM(P.amount) - SUM(OD.quantityOrdered * OD.priceEach) > C.creditLimit;


-- Create a list of customers who have ordered all products that 
-- customer 219 has ordered.
SELECT DISTINCT C.customerNumber, C.customerName
FROM Customers C
JOIN Orders O ON C.customerNumber = O.customerNumber
JOIN OrderDetails OD ON O.orderNumber = OD.orderNumber
-- Filters customers who have ordered atleast one of the same products as 219
WHERE OD.productCode IN (
    SELECT DISTINCT OD.productCode
    FROM Orders O
    JOIN OrderDetails OD ON O.orderNumber = OD.orderNumber
    WHERE O.customerNumber = 219
)
GROUP BY C.customerNumber
-- Filters customers who have ordered all the same products as customer 219
HAVING COUNT(DISTINCT OD.productCode) = (
    SELECT COUNT(DISTINCT OD.productCode)
    FROM Orders O
    JOIN OrderDetails OD ON O.orderNumber = OD.orderNumber
    WHERE O.customerNumber = 219
);
