/*  Retrieve all classic car products and their scale.   */

.print "\n\n\t\033[1;3;4;32;1m2. Retrieve all classic car products and their scale.\n\033[m"

SELECT  P.productName, P.productScale, P.productLine
FROM    Products P
WHERE   P.productLine LIKE "%Classic Car%"
ORDER BY    P.productScale DESC;
