	.file	"main.c"
	.text
	.section	.rodata
.LC0:
	.string	"%d "
	.text
	.globl	printArray
	.type	printArray, @function
printArray:
.LFB5:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$20, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
1:	call	*mcount@GOT(%ebx)
	movl	$0, -12(%ebp)
	jmp	.L2
.L3:
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC0@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	addl	$1, -12(%ebp)
.L2:
	movl	-12(%ebp), %eax
	cmpl	12(%ebp), %eax
	jl	.L3
	subl	$12, %esp
	pushl	$10
	call	putchar@PLT
	addl	$16, %esp
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE5:
	.size	printArray, .-printArray
	.globl	merge
	.type	merge, @function
merge:
.LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$68, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
1:	call	*mcount@GOT(%ebx)
	movl	%ebx, %eax
	movl	8(%ebp), %eax
	movl	%eax, -60(%ebp)
	movl	%gs:20, %eax
	movl	%eax, -12(%ebp)
	xorl	%eax, %eax
	movl	%esp, %eax
	movl	%eax, %ebx
	movl	16(%ebp), %eax
	subl	12(%ebp), %eax
	addl	$1, %eax
	movl	%eax, -36(%ebp)
	movl	20(%ebp), %eax
	subl	16(%ebp), %eax
	movl	%eax, -32(%ebp)
	movl	-36(%ebp), %eax
	leal	-1(%eax), %edx
	movl	%edx, -28(%ebp)
	sall	$2, %eax
	leal	3(%eax), %edx
	movl	$16, %eax
	subl	$1, %eax
	addl	%edx, %eax
	movl	$16, %ecx
	movl	$0, %edx
	divl	%ecx
	imull	$16, %eax, %eax
	subl	%eax, %esp
	movl	%esp, %eax
	addl	$3, %eax
	shrl	$2, %eax
	sall	$2, %eax
	movl	%eax, -24(%ebp)
	movl	-32(%ebp), %eax
	leal	-1(%eax), %edx
	movl	%edx, -20(%ebp)
	sall	$2, %eax
	leal	3(%eax), %edx
	movl	$16, %eax
	subl	$1, %eax
	addl	%edx, %eax
	movl	$16, %ecx
	movl	$0, %edx
	divl	%ecx
	imull	$16, %eax, %eax
	subl	%eax, %esp
	movl	%esp, %eax
	addl	$3, %eax
	shrl	$2, %eax
	sall	$2, %eax
	movl	%eax, -16(%ebp)
	movl	$0, -40(%ebp)
	jmp	.L5
.L6:
	movl	12(%ebp), %edx
	movl	-40(%ebp), %eax
	addl	%edx, %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %ecx
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	%ecx, (%eax,%edx,4)
	addl	$1, -40(%ebp)
.L5:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jl	.L6
	movl	$0, -44(%ebp)
	jmp	.L7
.L8:
	movl	16(%ebp), %eax
	leal	1(%eax), %edx
	movl	-44(%ebp), %eax
	addl	%edx, %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	%ecx, (%eax,%edx,4)
	addl	$1, -44(%ebp)
.L7:
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L8
	movl	$0, -40(%ebp)
	movl	$0, -44(%ebp)
	movl	12(%ebp), %eax
	movl	%eax, -48(%ebp)
	jmp	.L9
.L13:
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	(%eax,%edx,4), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	cmpl	%eax, %ecx
	jg	.L10
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -40(%ebp)
	jmp	.L11
.L10:
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -44(%ebp)
.L11:
	addl	$1, -48(%ebp)
.L9:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jge	.L14
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L13
	jmp	.L14
.L15:
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -40(%ebp)
	addl	$1, -48(%ebp)
.L14:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jl	.L15
	jmp	.L16
.L17:
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -44(%ebp)
	addl	$1, -48(%ebp)
.L16:
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L17
	movl	%ebx, %esp
	nop
	movl	-12(%ebp), %ebx
	xorl	%gs:20, %ebx
	je	.L18
	call	__stack_chk_fail_local
.L18:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE6:
	.size	merge, .-merge
	.globl	mergeSort
	.type	mergeSort, @function
mergeSort:
.LFB7:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$20, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
1:	call	*mcount@GOT(%ebx)
	movl	%ebx, %eax
	movl	12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jge	.L20
	movl	16(%ebp), %eax
	subl	12(%ebp), %eax
	movl	%eax, %edx
	shrl	$31, %edx
	addl	%edx, %eax
	sarl	%eax
	movl	%eax, %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movl	%eax, -12(%ebp)
	subl	$4, %esp
	pushl	-12(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	call	mergeSort
	addl	$16, %esp
	movl	-12(%ebp), %eax
	addl	$1, %eax
	subl	$4, %esp
	pushl	16(%ebp)
	pushl	%eax
	pushl	8(%ebp)
	call	mergeSort
	addl	$16, %esp
	pushl	16(%ebp)
	pushl	-12(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	call	merge
	addl	$16, %esp
.L20:
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE7:
	.size	mergeSort, .-mergeSort
	.globl	mergeSortasm
	.type	mergeSortasm, @function
mergeSortasm:
.LFB8:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$20, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
1:	call	*mcount@GOT(%ebx)
	movl	12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jge	.L22
	movl	16(%ebp), %eax
	subl	12(%ebp), %eax
	movl	%eax, %edx
	shrl	$31, %edx
	addl	%edx, %eax
	sarl	%eax
	movl	%eax, %edx
	movl	12(%ebp), %eax
	addl	%edx, %eax
	movl	%eax, -12(%ebp)
	subl	$4, %esp
	pushl	-12(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	call	mergeSort
	addl	$16, %esp
	movl	-12(%ebp), %eax
	addl	$1, %eax
	subl	$4, %esp
	pushl	16(%ebp)
	pushl	%eax
	pushl	8(%ebp)
	call	mergeSort
	addl	$16, %esp
	subl	$12, %esp
	pushl	8(%ebp)
	call	asm_function@PLT
	addl	$16, %esp
.L22:
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE8:
	.size	mergeSortasm, .-mergeSortasm
	.section	.rodata
.LC1:
	.string	"Given array is "
.LC3:
	.string	"\nc-code time spent: %f\n\n"
.LC4:
	.string	"\nc-code sorted array is "
.LC5:
	.string	"\nassembly time spent: %f\n\n"
.LC6:
	.string	"\nasm sorted array is "
	.text
	.globl	main
	.type	main, @function
main:
.LFB9:
	.cfi_startproc
	leal	4(%esp), %ecx
	.cfi_def_cfa 1, 0
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	movl	%esp, %ebp
	pushl	%esi
	pushl	%ebx
	pushl	%ecx
	.cfi_escape 0xf,0x3,0x75,0x74,0x6
	.cfi_escape 0x10,0x6,0x2,0x75,0x7c
	.cfi_escape 0x10,0x3,0x2,0x75,0x78
	subl	$76, %esp
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
1:	call	*mcount@GOT(%ebx)
	subl	$12, %esp
	pushl	$60
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, -64(%ebp)
	subl	$12, %esp
	pushl	$15
	call	srand@PLT
	addl	$16, %esp
	movl	$0, -68(%ebp)
	jmp	.L24
.L25:
	call	rand@PLT
	movl	%eax, %ecx
	movl	-68(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-64(%ebp), %eax
	leal	(%edx,%eax), %esi
	movl	$1374389535, %edx
	movl	%ecx, %eax
	imull	%edx
	sarl	$5, %edx
	movl	%ecx, %eax
	sarl	$31, %eax
	subl	%eax, %edx
	movl	%edx, %eax
	imull	$100, %eax, %eax
	subl	%eax, %ecx
	movl	%ecx, %eax
	movl	%eax, (%esi)
	addl	$1, -68(%ebp)
.L24:
	cmpl	$14, -68(%ebp)
	jle	.L25
	movl	-64(%ebp), %eax
	movl	%eax, -60(%ebp)
	subl	$12, %esp
	leal	.LC1@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$8, %esp
	pushl	$15
	pushl	-64(%ebp)
	call	printArray
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -56(%ebp)
	subl	$4, %esp
	pushl	$14
	pushl	$0
	pushl	-60(%ebp)
	call	mergeSort
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -52(%ebp)
	movl	-52(%ebp), %eax
	subl	-56(%ebp), %eax
	movl	%eax, -76(%ebp)
	fildl	-76(%ebp)
	fldl	.LC2@GOTOFF(%ebx)
	fdivrp	%st, %st(1)
	fstpl	-40(%ebp)
	subl	$4, %esp
	pushl	-36(%ebp)
	pushl	-40(%ebp)
	leal	.LC3@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC4@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$8, %esp
	pushl	$15
	pushl	-64(%ebp)
	call	printArray
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -48(%ebp)
	subl	$4, %esp
	pushl	$14
	pushl	$0
	pushl	-64(%ebp)
	call	mergeSortasm
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -44(%ebp)
	movl	-44(%ebp), %eax
	subl	-48(%ebp), %eax
	movl	%eax, -76(%ebp)
	fildl	-76(%ebp)
	fldl	.LC2@GOTOFF(%ebx)
	fdivrp	%st, %st(1)
	fstpl	-32(%ebp)
	subl	$4, %esp
	pushl	-28(%ebp)
	pushl	-32(%ebp)
	leal	.LC5@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC6@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$8, %esp
	pushl	$15
	pushl	-64(%ebp)
	call	printArray
	addl	$16, %esp
	subl	$12, %esp
	pushl	-64(%ebp)
	call	free@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	-60(%ebp)
	call	free@PLT
	addl	$16, %esp
	movl	$0, %eax
	leal	-12(%ebp), %esp
	popl	%ecx
	.cfi_restore 1
	.cfi_def_cfa 1, 0
	popl	%ebx
	.cfi_restore 3
	popl	%esi
	.cfi_restore 6
	popl	%ebp
	.cfi_restore 5
	leal	-4(%ecx), %esp
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE9:
	.size	main, .-main
	.section	.rodata
	.align 8
.LC2:
	.long	0
	.long	1093567616
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB10:
	.cfi_startproc
	movl	(%esp), %ebx
	ret
	.cfi_endproc
.LFE10:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (Ubuntu 7.3.0-16ubuntu3) 7.3.0"
	.section	.note.GNU-stack,"",@progbits
