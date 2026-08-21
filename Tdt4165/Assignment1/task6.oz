/* Write a function {Factorial N} that computes the factorial of any natural number using recursion.
Note that 0! = 1 (i.e., the factorial of zero is one). */
declare
fun {IncUntil Start Stop} A in
    {System.showInfo "Pushing Start: "#Start}
    if Start < Stop then
        A = {IncUntil Start+1 Stop}
    else
        A = Stop
    end
    {System.showInfo "Popping Start: "#Start}
    A
end
{System.showInfo {IncUntil 10 15}}

fun {Factorial N}
    if N==0 then 1 else
        N * {Factorial N-1}
    end
end
{System.showInfo {Factorial 4}}