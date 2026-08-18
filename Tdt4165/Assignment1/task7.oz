%7a
/* Implement {Length List}. It should return the element count of List. */
declare
fun {Length LIST}
    case LIST of HEAD|TAIL then
        1 + {Length TAIL}
    else
        0
    end
end


%7b
/* Implement {Take List Count}. It should return a list of the first Count elements. If Count is bigger
than the amount of elements in the list, it should return the entire List. */
fun {Take LIST COUNT}
    if COUNT==0 then
        nil
    else
        case LIST of HEAD|TAIL then
            HEAD | {Take TAIL COUNT-1}
        else
            LIST
        end
    end
end


%7c
/* Implement {Drop List Count}. It should return a list without the first Count values. If Count is greater
than the length of the list, the function should return nil. */
fun {Drop LIST COUNT}
    if COUNT==0 then
        LIST
    else
        case LIST of HEAD|TAIL then
            {Drop TAIL COUNT-1}
        else
            nil
        end
    end
end


%7d
/* Implement {Append List1 List2}. It should return a list of all the elements in List1 followed by all
the elements in List2. */
fun {Append LIST1 LIST2}
    case LIST1 of nil then 
        LIST2 [] HEAD|TAIL then
        HEAD|{Append TAIL LIST2}
    end
end


%7e
/* Implement {Member List Element}. It should return true if Element is present in List, false otherwise. */
fun {Member LIST ELEMENT}
    case LIST of nil then false
        [] HEAD|TAIL then
        ELEMENT==HEAD
    orelse
        {Member TAIL ELEMENT}
    end
end


%7f
/* Implement {Position List Element} It should return the position of Element in List. You can in this
case assume that the element is present in the list. */
fun {Position LIST ELEMENT}
    case LIST of HEAD|TAIL then
        if ELEMENT==HEAD then
            1
        else
            {Position TAIL ELEMENT} + 1
        end
    end
end