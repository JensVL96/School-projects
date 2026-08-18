	.file	"main.c"
	.text
	.section	.rodata
.LC0:
	.string	"%d "
	.text
	.globl	printArray
	.type	printArray, @function
printArray:
.LFB6:
	.cfi_startproc
	endbr32
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
.LFE6:
	.size	printArray, .-printArray
	.globl	merge
	.type	merge, @function
merge:
.LFB7:
	.cfi_startproc
	endbr32
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$68, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
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
	leal	0(,%eax,4), %edx
	movl	$16, %eax
	subl	$1, %eax
	addl	%edx, %eax
	movl	$16, %ecx
	movl	$0, %edx
	divl	%ecx
	imull	$16, %eax, %eax
	movl	%eax, %edx
	andl	$-4096, %edx
	movl	%esp, %ecx
	subl	%edx, %ecx
	movl	%ecx, %edx
.L5:
	cmpl	%edx, %esp
	je	.L6
	subl	$4096, %esp
	orl	$0, 4092(%esp)
	jmp	.L5
.L6:
	movl	%eax, %edx
	andl	$4095, %edx
	subl	%edx, %esp
	movl	%eax, %edx
	andl	$4095, %edx
	testl	%edx, %edx
	je	.L7
	andl	$4095, %eax
	subl	$4, %eax
	addl	%esp, %eax
	orl	$0, (%eax)
.L7:
	movl	%esp, %eax
	addl	$3, %eax
	shrl	$2, %eax
	sall	$2, %eax
	movl	%eax, -24(%ebp)
	movl	-32(%ebp), %eax
	leal	-1(%eax), %edx
	movl	%edx, -20(%ebp)
	leal	0(,%eax,4), %edx
	movl	$16, %eax
	subl	$1, %eax
	addl	%edx, %eax
	movl	$16, %ecx
	movl	$0, %edx
	divl	%ecx
	imull	$16, %eax, %eax
	movl	%eax, %edx
	andl	$-4096, %edx
	movl	%esp, %ecx
	subl	%edx, %ecx
	movl	%ecx, %edx
.L8:
	cmpl	%edx, %esp
	je	.L9
	subl	$4096, %esp
	orl	$0, 4092(%esp)
	jmp	.L8
.L9:
	movl	%eax, %edx
	andl	$4095, %edx
	subl	%edx, %esp
	movl	%eax, %edx
	andl	$4095, %edx
	testl	%edx, %edx
	je	.L10
	andl	$4095, %eax
	subl	$4, %eax
	addl	%esp, %eax
	orl	$0, (%eax)
.L10:
	movl	%esp, %eax
	addl	$3, %eax
	shrl	$2, %eax
	sall	$2, %eax
	movl	%eax, -16(%ebp)
	movl	$0, -40(%ebp)
	jmp	.L11
.L12:
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
.L11:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jl	.L12
	movl	$0, -44(%ebp)
	jmp	.L13
.L14:
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
.L13:
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L14
	movl	$0, -40(%ebp)
	movl	$0, -44(%ebp)
	movl	12(%ebp), %eax
	movl	%eax, -48(%ebp)
	jmp	.L15
.L19:
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	(%eax,%edx,4), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	cmpl	%eax, %ecx
	jg	.L16
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-24(%ebp), %eax
	movl	-40(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -40(%ebp)
	jmp	.L17
.L16:
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-60(%ebp), %eax
	leal	(%edx,%eax), %ecx
	movl	-16(%ebp), %eax
	movl	-44(%ebp), %edx
	movl	(%eax,%edx,4), %eax
	movl	%eax, (%ecx)
	addl	$1, -44(%ebp)
.L17:
	addl	$1, -48(%ebp)
.L15:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jge	.L20
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L19
	jmp	.L20
.L21:
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
.L20:
	movl	-40(%ebp), %eax
	cmpl	-36(%ebp), %eax
	jl	.L21
	jmp	.L22
.L23:
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
.L22:
	movl	-44(%ebp), %eax
	cmpl	-32(%ebp), %eax
	jl	.L23
	movl	%ebx, %esp
	nop
	movl	-12(%ebp), %ebx
	xorl	%gs:20, %ebx
	je	.L24
	call	__stack_chk_fail_local
.L24:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE7:
	.size	merge, .-merge
	.globl	mergeSort
	.type	mergeSort, @function
mergeSort:
.LFB8:
	.cfi_startproc
	endbr32
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jge	.L26
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
.L26:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE8:
	.size	mergeSort, .-mergeSort
	.globl	mergeSortasm
	.type	mergeSortasm, @function
mergeSortasm:
.LFB9:
	.cfi_startproc
	endbr32
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
	movl	12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jge	.L28
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
.L28:
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE9:
	.size	mergeSortasm, .-mergeSortasm
	.section	.rodata
.LC1:
	.string	"a[%d] %d <  a[%d] %d\n"
.LC2:
	.string	"ARRAY NOT SORTED"
.LC3:
	.string	"SORTED"
	.text
	.globl	assert_sorted
	.type	assert_sorted, @function
assert_sorted:
.LFB10:
	.cfi_startproc
	endbr32
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%esi
	pushl	%ebx
	subl	$16, %esp
	.cfi_offset 6, -12
	.cfi_offset 3, -16
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	$1, -12(%ebp)
	jmp	.L30
.L32:
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-12(%ebp), %eax
	addl	$1073741823, %eax
	leal	0(,%eax,4), %ecx
	movl	8(%ebp), %eax
	addl	%ecx, %eax
	movl	(%eax), %eax
	cmpl	%eax, %edx
	jge	.L31
	movl	-12(%ebp), %eax
	addl	$1073741823, %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-12(%ebp), %eax
	leal	-1(%eax), %ecx
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %esi
	movl	8(%ebp), %eax
	addl	%esi, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%edx
	pushl	%ecx
	pushl	%eax
	pushl	-12(%ebp)
	leal	.LC1@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$32, %esp
	subl	$12, %esp
	leal	.LC2@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$-1
	call	exit@PLT
.L31:
	addl	$1, -12(%ebp)
.L30:
	movl	-12(%ebp), %eax
	cmpl	12(%ebp), %eax
	jl	.L32
	subl	$12, %esp
	leal	.LC3@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	nop
	leal	-8(%ebp), %esp
	popl	%ebx
	.cfi_restore 3
	popl	%esi
	.cfi_restore 6
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE10:
	.size	assert_sorted, .-assert_sorted
	.section	.rodata
.LC4:
	.string	"Given array is "
.LC6:
	.string	"\nc-code time spent: %f\n\n"
.LC7:
	.string	"\nc-code sorted array is "
.LC8:
	.string	"\nassembly time spent: %f\n\n"
.LC9:
	.string	"\nasm sorted array is "
	.text
	.globl	main
	.type	main, @function
main:
.LFB11:
	.cfi_startproc
	endbr32
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
	subl	$12, %esp
	pushl	$4000000
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, -68(%ebp)
	subl	$12, %esp
	pushl	$4000000
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, -64(%ebp)
	subl	$12, %esp
	pushl	$1000000
	call	srand@PLT
	addl	$16, %esp
	movl	$0, -72(%ebp)
	jmp	.L34
.L35:
	call	rand@PLT
	movl	%eax, %ecx
	movl	-72(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-68(%ebp), %eax
	leal	(%edx,%eax), %esi
	movl	$1441151881, %edx
	movl	%ecx, %eax
	imull	%edx
	sarl	$25, %edx
	movl	%ecx, %eax
	sarl	$31, %eax
	subl	%eax, %edx
	movl	%edx, %eax
	imull	$100000000, %eax, %eax
	subl	%eax, %ecx
	movl	%ecx, %eax
	movl	%eax, (%esi)
	movl	-72(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-68(%ebp), %eax
	addl	%edx, %eax
	movl	-72(%ebp), %edx
	leal	0(,%edx,4), %ecx
	movl	-64(%ebp), %edx
	addl	%ecx, %edx
	movl	(%eax), %eax
	movl	%eax, (%edx)
	addl	$1, -72(%ebp)
.L34:
	cmpl	$999999, -72(%ebp)
	jle	.L35
	movl	-68(%ebp), %eax
	movl	%eax, -60(%ebp)
	subl	$12, %esp
	leal	.LC4@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -56(%ebp)
	subl	$4, %esp
	pushl	$999999
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
	fldl	.LC5@GOTOFF(%ebx)
	fdivrp	%st, %st(1)
	fstpl	-40(%ebp)
	subl	$4, %esp
	pushl	-36(%ebp)
	pushl	-40(%ebp)
	leal	.LC6@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$8, %esp
	pushl	$1000000
	pushl	-60(%ebp)
	call	assert_sorted
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC7@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -48(%ebp)
	subl	$4, %esp
	pushl	$999999
	pushl	$0
	pushl	-64(%ebp)
	call	mergeSortasm
	addl	$16, %esp
	subl	$8, %esp
	pushl	$1000000
	pushl	-64(%ebp)
	call	assert_sorted
	addl	$16, %esp
	call	clock@PLT
	movl	%eax, -44(%ebp)
	movl	-44(%ebp), %eax
	subl	-48(%ebp), %eax
	movl	%eax, -76(%ebp)
	fildl	-76(%ebp)
	fldl	.LC5@GOTOFF(%ebx)
	fdivrp	%st, %st(1)
	fstpl	-32(%ebp)
	subl	$4, %esp
	pushl	-28(%ebp)
	pushl	-32(%ebp)
	leal	.LC8@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC9@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	-68(%ebp)
	call	free@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	-64(%ebp)
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
.LFE11:
	.size	main, .-main
	.section	.rodata
	.align 8
.LC5:
	.long	0
	.long	1093567616
	.section	.text.__x86.get_pc_thunk.ax,"axG",@progbits,__x86.get_pc_thunk.ax,comdat
	.globl	__x86.get_pc_thunk.ax
	.hidden	__x86.get_pc_thunk.ax
	.type	__x86.get_pc_thunk.ax, @function
__x86.get_pc_thunk.ax:
.LFB12:
	.cfi_startproc
	movl	(%esp), %eax
	ret
	.cfi_endproc
.LFE12:
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB13:
	.cfi_startproc
	movl	(%esp), %ebx
	ret
	.cfi_endproc
.LFE13:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (Ubuntu 9.3.0-17ubuntu1~20.04) 9.3.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 4
	.long	 1f - 0f
	.long	 4f - 1f
	.long	 5
0:
	.string	 "GNU"
1:
	.align 4
	.long	 0xc0000002
	.long	 3f - 2f
2:
	.long	 0x3
3:
	.align 4
4:
