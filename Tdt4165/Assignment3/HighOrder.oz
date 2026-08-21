% Task 1a - Implement the procedure
declare QuadraticEquation Sum RightFold Length_fold Sum_fold Quadratic LazyNumberGenerator SumTail in

proc {QuadraticEquation A B C ?RealSol ?X1 ?X2}
    Discriminant = B*B - 4.0*A*C
in
    if Discriminant >= 0.0 then
        RealSol = true
        X1 = (~B + {Sqrt Discriminant}) / (2.0*A)
        X2 = (~B - {Sqrt Discriminant}) / (2.0*A)
    else
        RealSol = false
        %X1 = ~B / (2.0*A)
        %X2 = {Sqrt ~Discriminant} / (2.0*A)
    end
end

declare RS X1 X2 in
    {QuadraticEquation 2.0 1.0 2.0 RS X1 X2}
    {System.show RS#X1#X2}

/* Task 1b
    What are the values of X1, X2, and RealSol, when A = 2, B = 1 and C = -1
        They are: X1 = 0.5, X2 = -1 and RealSol = true
    What are the values of X1, X2, and RealSol, when A = 2, B = 1 and C = 2
        They are: X1 = empty, X2 = empty and RealSol = false.
        The reason that both equation solutions are empty is 
        because the function doesn't handle imaginary numbers 
        (square root of negative discriminants).

Task 1c
    Why are procedural abstractions useful?
        It is useful because the ability to convert any statement 
        into a procedure value means that for instance the execution 
        can be delayed and arguments can be influenced by the call. 
        It is also extremly useful for building abstractions.

Task 1d
    What is the difference between a procedure and a function?
        Procedures are some times more appropriate than functions because they 
        do not necessarily define entities that behave like mathematical functions.
        Procedures are flexible because they do not make any assumptions 
        about the number of inputs and outputs, it can even be zero. Also 
        functions differs in that it return an output while a procedure doesn't.
*/

% Task 2 - Implement the function
fun {Sum List}
    case List of nil then 0
    [] Value|TAIL then Value + {Sum TAIL}
    end
end
{Show {Sum [1 2 3 4 5]}}

% Task 3a - Implement the function
fun {RightFold List Op U}
    case List of nil then U
    [] Value|Tail then {Op Value {RightFold Tail Op U}}
    end
end

/* Task 3b
    Explain each line of code in RightFold in your own words.
        First the Right fold function is declared with the variables provided in the assignment text.
        Next the function makes a case statement to the list where zero is the neutral element when the list is empty.
        Then another case statement seperates the head and tail of the list.
            The case then results in a new operation function with two variables. 
            The first variable is the value at the head position of the list.
            The second variable is the recursive result of going back to the 
            function with the tail of the list (so excluding the first element).

Task 3e
    What is an appropriate value for U when using RightFold to implement the product of list elements?
        THe appropriate value is 1 because then the results do not change. U is the neutral element.
*/

% Task 3c - Implement both functions
fun {Length_fold List} 
    {RightFold List fun {$ X Y} 1+Y end 0}
end
fun {Sum_fold List} 
    {RightFold List fun {$ X Y} X+Y end 0}
end
{Show {Length_fold [1 2 3 4 5]}}
{Show {Sum_fold [1 2 3 4 5]}}

/* Task 3d
    For the Sum and Lenght operations, would LeftFold (a left-associative fold) and RightFold give different
    results? Can you provide an example of an operation for which the two folds do not produce the same result?
        The different fold directions would give different results when subtracting or dividing as the order matters.
        As an example the list [1 2 3 4 5] when subtracting would give:
            Right fold: (1 - (2 - (3 - (4 - 5)))) = 3
            Left fold:  ((((1 - 2) - 3) - 4) - 5) = -13
*/

% Task 4 - Implement the function
fun {Quadratic A B C}
    fun {$ X} A*X*X + B*X + C end
end
{System.show {{Quadratic 3 2 1} 2}} 

% Task 5a - Implement the function
fun {LazyNumberGenerator StartValue}
    StartValue|fun {$} {LazyNumberGenerator StartValue + 1} end
end
{Show {LazyNumberGenerator 0}.1}
{Show {{LazyNumberGenerator 0}.2}.1}
{Show {{{{{{LazyNumberGenerator 0}.2}.2}.2}.2}.2}.1}

/* Task 5b
    Give a high-level desciption of your solution and point out any limitations you find relevant.
        As the infix dot operator selects a field in the record component, the function
        is set up in such a way that it returns a list of both the value and a function handling the next
        element in the infinite list of incrementing integers. The next time the function is called with
        another dot operator it will only iterate through with the tail function until a value is set to
        be returned. The only limitations that I know of are that it's never truly infinite unless the inputs
        are as well.
*/


/* Task 6a
    Is your Sum function from Task 2 tail recursive? If yes, explain why. If not, implement a tail-recursive
    version and explain which changes you needed to introduce to make it tail recursive.
        To make the Sum function tail recursive It needed to have the recursive function call as the last 
        element statement. So by introducing a local function to loop through the count of the iterator
        the function works in the same way only it handles the resulting sum inside of the recursion call.
*/
declare
fun {SumTail List}
    local
        fun {Internal List Count}
            case List of nil then Count
            [] Value|TAIL then {Internal TAIL Value + Count}
            end
        end
    in
        {Internal List 0}
    end
end
{Show {SumTail [1 2 3 4 5]}}

/* Task 6b
    What is the benefit of tail recursion in Oz?
        The main benefit of using tail recursion in Oz is that 
        it is great for space optimization as the stack size is constant.

Task 6c
    Do all programming languages that allow recursion benefit from tail recursion? Why/why not?
        If the programming language fails to recognize the tail recursion it may lose out on
        the benefits it provides. So when the language doesn't implement it the optimalization 
        loses it's effect.
        
*/