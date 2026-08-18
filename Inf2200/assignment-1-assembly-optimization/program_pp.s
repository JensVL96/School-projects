# 1 "asm.S"
# 1 "<built-in>"
# 1 "<command-line>"
# 31 "<command-line>"
# 1 "/usr/include/stdc-predef.h" 1 3 4
# 32 "<command-line>" 2
# 1 "asm.S"
# 1 "asmdef.h" 1
# 2 "asm.S" 2

.globl asm_function; .type asm_function, @function


################################################################################
# name: asm_function
# action:
# in:

# out:
# modifies:
# notes:
################################################################################
asm_function:

# Do something here...

pushl %ebp # Saving ebp (base pointer)
movl %esp, %ebp # Creating stack frame

movl 24(%ebp), %eax # Copy %eax into %ebp + 24

pushl %ebx # Using ebx register
pushl %esi # Using esi register as j
pushl %edi # Using esi register as n

movl 8(%ebp), %ebx # Creating an array in ebx
movl 12(%ebp), %esi # esi is set to be the first number in the array(min(j))
movl 16(%ebp), %edi # edi is set to be the middle number in the array (mid)
addl $1, %edi # add 1 to edi (it now points at middle + 1(n))
movl 16(%ebp), %ecx # ecx is set as the middle number in the array (mid)
movl 20(%ebp), %edx # edx is set as the last number in the array (max)

loop_one:
if_one:
    cmpl %edx, %esi # compare esi(j) to ecx(mid+1)
    jg if_two # jump if (j) is greater than (mid+1)

if_two:
    cmpl %edx, %esi # compare esi(j) to edx(max)
    jg loop_one_end # jump if (j) is greater than (max)

if_three:
    pushl %ecx # pushing ecx(mid) on the stack
    pushl %edx # pushing edx(max) on the stack
    movl (%ebx, %esi, 4), %ecx # setting ecx(mid) to now be esi(j)
    cmpl (%ebx, %edi, 4), %ecx # compare ecx(esi(j)) to edi(n)
    jg else_one # jump if ecx(esi(j)) is greater than edi(n)

    movl %ecx, (%edx, %eax, 4) # setting temp eax(i) to be ecx(mid)
    incl %esi # incrementing esi(j) by 1(one)

    popl %edx # popping edx from the stack. edx = max
    popl %ecx # popping ecx from the stack. ecx = mid
    incl %eax # incrementing eax(i) by one

    jmp loop_one # unconditional jump to loop_one

else_one:
    pushl %edx # pushing edx(max) onto the stack
    pushl %ecx # pushing ecx(mid) onto the stack
    movl (%ebx, %edi, 4), %ecx # setting ecx(mid) to be edi(n)
    movl %ecx, (%edx, %eax, 4) # setting eax(i) to be ecx(edi(n))
    incl %edi # incrementing edi(n) by 1(one)

    popl %ecx # popping ecx from the stack. ecx = mid
    popl %edx # popping edx from the stack. edx = max
    incl %eax # incrementing i by 1(one)

    jmp loop_one # unconditional jump to loop_one

loop_one_end:
    jmp if_four # unconditional jump to if_four

if_four:
    cmpl %ecx, %esi # compares ecx(mid) to esi(j)
    jg loop_two # jump to loop_two if ecx(mid) is greater than esi(j)

    jmp else_two # unconditional jump to else_two

loop_two:
    pushl %esi # pushing esi(j) onto the stack
    pushl %ecx # pushing ecx(mid) onto the stack

    movl %edi, %ecx # setting ecx(mid) to be edi(n)

end_one:
    popl %ecx # popping ecx from the stack. ecx = mid
    popl %esi # popping esi from the stack. esi = j
    jmp endloop # unconditional jump to endloop

else_two:
    loop_three:
        pushl %edi # pushing edi(n) to the stack
        pushl %edx # pushing edx(max) to the stack

        movl %esi, %edi # setting edi(n) to be esi(j)

    end_two:
        popl %edx # popping edx from the stack. edx = max
        popl %edi # popping edi from the stack. edi = n
        jmp endloop # unconditional jump to endloop

endloop:
    pushl %esi # pushing esi(j) to the stack
    pushl %eax # pushing eax(i) to the stack

    movl 12(%ebp), %esi # setting esi(j) to be the smallest number in the array

end_three:
    popl %eax # popping eax from the stack. eax = i
    popl %esi # popping esi from the stack. esi = j
    jmp end_final # unconditional jump to final

end_final:
    popl %edi # popping from edi from the stack
    popl %esi # popping from esi from the stack
    popl %ebx # popping from edx from the stack

    popl %ebp # popping from ebp from the stack
    ret
