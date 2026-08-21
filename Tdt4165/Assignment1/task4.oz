% 4a
/* Write a function {Max Number1 Number2} that returns the maximum of Number1 and Number2. */
declare
fun {Min X Y}
    if X < Y then
        X
    else
        Y
    end
end

fun {Max X Y}
    if X > Y then
        X
    else
        Y
    end
end

{System.showInfo {Max 10 15}}

% 4b
/* Write a procedure {PrintGreater Number1 Number2} that prints the maximum value of the arguments. */
declare
proc {PrintGreater X Y}
    if X > Y then
        {System.showInfo X}
    else
        {System.showInfo Y}
    end
end

{PrintGreater 10 15}