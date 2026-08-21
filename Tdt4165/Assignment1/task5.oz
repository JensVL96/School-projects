/* Write a procedure {Circle R} that calculates area, diameter, and circumference of a circle with radius R,
stores the three results in three variables, and then prints the results. Use the expressions A = π ∗ R2,
D = 2R, and C = π ∗ D. (Hint: You may want to bind π to 355/113 ) */
declare
proc {Circle R} A B C D PI in
    PI = 355.0 / 113.0
    D = 2.0 * R
    C = PI * D
    A = PI * R * R
    {System.showInfo A}
    {System.showInfo C}
    {System.showInfo D}
end

{Circle 30.0}

