% 8a
/* Implement {Push List Element}. It should return an updated version of List, in which Element has
    been added in the first position. */
declare
fun {Push LIST ELEMENT}
    ELEMENT|LIST
end
{Show {Push [1 2 3] 4}}

% 8b
/* Implement {Peek List}. It should return the element in the first position of List, or nil if the stack
    (list) is empty. */
fun {Peek LIST}
    case LIST of HEAD|TAIL then
        HEAD
    else
        nil
    end
end
{Show {Peek [0 1 2 3]}}

% 8c
/* Implement {Pop List}. It should return an updated version of List, in which the first element has been
    removed. */
fun {Pop LIST}
    case LIST of HEAD|TAIL then
        TAIL
    else
        nil
    end
end
{Show {Pop [0 1 2 3]}}