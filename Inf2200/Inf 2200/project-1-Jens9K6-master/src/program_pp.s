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


 # ################################################################################
# name: asm_function
# action: Merge sort
# in: An unsorted array

# out: A sorted array
# modifies: The input array
# notes: Sorts the array in a specific way, the merge sort way
# ################################################################################
asm_function:

# merge(int arr[], int l, int m, int r)

pushl %ebp # Saving ebp (base pointer)
movl %esp, %ebp # Creating stack frame

movl 20(%ebp), %eax # Copy %eax into %ebp + 20

pushl %ebx # Using ebx register
pushl %ecx # Using ecx register
pushl %edi # Using edi register
pushl %edx # Using edx register

movl 8(%ebp), %ebx # ebx = arr
movl 12(%ebp), %ecx # ecx = l
movl 16(%ebp), %edi # edi = m
movl 20(%ebp), %edx # edx = r

subl %ecx, %edi # edi - ecx (m - l)
incl %edi # edi = n1 = edi = m - l + 1

subl %ecx, %edx # edx = n2 = edx - ecx (r - m)

movl 4(%ebp), edi # edi = L[n1]
movl 0(%ebp), edx # edx = R[n2]

xorl %esi, %esi # esi = i = 0
xorl %ecx, %ecx # ecx = j = 0

# comments down below

.loop_one:
    cmpl (%edi, %esi, 4), (%edx, %ecx, 4) # edi[esi] cmp edx[ecx]
    jl .loop_two
    movl (%edi, %esi, 4), %ebx
    incl %esi

.loop_two:
    cmpl (%edi, %esi, 4), (%edx, %ecx, 4)
    jg .loop_one
    movl (%edx, %ecx, 4), %ebx
    incl %ecx

.loop_three:
    cmpl (%edi, %esi, 4), (%edx, %ecx, 4)
    jg .loop_one
    cmpl (%edi, %esi, 4), (%edx, %ecx, 4)
    jl .loop_two

popl %ebx
popl %ecx
popl %edi
popl %edx
popl %esi
ret
# loop_one compares the given array element in edi with the given element
# in ecx. If ecx is the smallest on it jumps to loop_two. If not, the
# given element is added to the ebx array and then it increments esi.
# loop_two does the opposite and will jump back to loop_one if
# ecx is the greater one. If not, it will add ecx to ebx and increment
# ecx.
# It will then go into loop_three where it will be returned back to
# loop_one or loop_two
