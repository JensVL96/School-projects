%3a
/* Rewrite the following code so that instead of calculating X directly it creates two other variables, Y and
Z, assigns the values to them, and calculates X from these. */
local X Y=300 Z=30 in
   X = Y * Z
   {Show X}
end

local X Y in
   X = "This is a string"
   thread {System.showInfo Y} end
   Y = X
end

%3b
/* Why do you think showInfo can print Y before it is assigned? Why is this behaviour useful? What does the
statement Y = X do? */
/* showInfo can print Y because Y is declared at the start as a local variable. When the print function tries to print Y
 it goes through the inner function to see if it has been declared. 
 It is useful as the outer value can be referenced at any part before the function end. 
 THe statement Y = X  gives the string declared for X the Y variable name as well.*/