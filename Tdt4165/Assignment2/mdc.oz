% \insert '../Assignment1/task7.oz'

% 1a
declare
fun {Lex Input}
    {String.tokens Input & }
end
% {Show {Lex "1 2 + 3 *"}}

% 1b
fun {Tokenize Lexemes} Token in
    case Lexemes of nil then nil
        [] HEAD|TAIL then
        if HEAD == "+" then
            Token = operator(type:plus)
        elseif HEAD == "-" then
            Token = operator(type:minus)
        elseif HEAD == "*" then
            Token = operator(type:multiply)
        elseif HEAD == "/" then
            Token = operator(type:divide)
        elseif {String.isInt HEAD} then
            Token = number({String.toInt HEAD})
        elseif HEAD == "p" then
            Token = command(print)
        elseif HEAD == "d" then
            Token = command(duplicate)
        elseif HEAD == "i" then
            Token = command(invert)
        elseif HEAD == "c" then
            Token = command(clear)
        end
        Token|{Tokenize TAIL}
    end
end
% {Show {Tokenize {Lex "1 2 + 3 *"}}}

% 1c
fun {Interpret Tokens}
    local 
        fun {Recurs Stack Tokens}
            case Tokens of nil then Stack
            [] Token|TokenTail then
                case Token of nil then nil
                [] operator(type:plus) then
                    case Stack of N1|N2|StackTail then
                        {Recurs N1+N2|StackTail TokenTail}
                    end
                [] operator(type:minus) then
                    case Stack of N1|N2|StackTail then
                        {Recurs N1-N2|StackTail TokenTail}
                    end
                [] operator(type:multiply) then
                    case Stack of N1|N2|StackTail then
                        {Recurs N1*N2|StackTail TokenTail}
                    end
                [] operator(type:divide) then
                    case Stack of N1|N2|StackTail then
                        {Recurs N1/N2|StackTail TokenTail}
                    end
                [] number(N) then
                    {Recurs N|Stack TokenTail}
                [] command(print) then  % 1d
                    {Show Stack}
                    {Recurs Stack TokenTail}
                [] command(duplicate) then  % 1e
                    case Stack of N|StackTail then
                        {Recurs N|Stack TokenTail}
                    end
                [] command(invert) then  % 1f
                    case Stack of N|StackTail then
                        {Recurs 0-N|StackTail TokenTail}
                    end
                [] command(clear) then  % 1g
                    {Recurs nil TokenTail}
                end
            end
        end
    in
        {Recurs nil Tokens}
    end
end
% {Show {Interpret [number(1) number(2) number(3) operator(type:plus)]}} 1c
{Show {Interpret {Tokenize {Lex "1 c 2 p i 3 + d"}}}} % 1cdefg

% 2a
fun {ExpressionTree Tokens}
    local
        fun {ExpressionTreeInternal Tokens ExpressionStack} % 2b
            case Tokens of nil then ExpressionStack.1
            [] Token|TokenTail then
                case Token of nil then nil
                [] number(N) then
                    {ExpressionTreeInternal TokenTail N|ExpressionStack}
                [] operator(type:Name) then
                    case ExpressionStack of N1|N2|StackTail then
                        {ExpressionTreeInternal TokenTail Name(N1 N2)|StackTail}
                    end
                end
            end
        end
    in
        {ExpressionTreeInternal Tokens nil}
    end
end
{Show {ExpressionTree {Tokenize {Lex "3 10 9 * - 7 +"}}}}


/* Task 3a
lexemes := number | operator | command ;
number := digit exluding zero, digit | digit ;
digit := "0" | digit exluding zero ;
digit exluding zero := "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
operator := "+" | "-" | "*" | "/" ;
command := "p" | "d" | "i" | "c" ;
*/

/* Task 3b
expression tree := operator token ;
operator token := operator( expression, expression ) ;
expression := type( number ) | type( operator) ;
type := "number" | "operator" ;
number := digit exluding zero, digit | digit ;
digit := "0" | digit exluding zero ;
digit exluding zero := "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
operator := plus | minus | multiply | divide ;
*/

/* Task 3c
The grammar is context free in both cases, since neither one is left or right linear. 
Both grammars shows cases of non-terminals used in a non linear method.
*/
