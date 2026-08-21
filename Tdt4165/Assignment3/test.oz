% Task 5
declare
fun {LazyNumberGenerator StartValue}
    StartValue|fun {$} {LazyNumberGenerator StartValue + 1} end
    % StartValue|{LazyNumberGenerator StartValue + 1}
end
{Show {LazyNumberGenerator 0}.1}
{Show {{LazyNumberGenerator 0}.2}.1}
{Show {{{{{{LazyNumberGenerator 0}.2}.2}.2}.2}.2}.1}