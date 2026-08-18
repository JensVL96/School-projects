	.file	"mergesort.c"
	.text
	.section	.rodata
.LC0:
	.string	"%d "
	.text
	.globl	printArray
	.type	printArray, @function
printArray:
.LFB0:
	.cfi_startproc
	pushq	%rbp			#save callers %rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp		#copy %rsp to %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp		
	movq	%rdi, -24(%rbp)		#copy %rdi into %rdp-24
	movl	%esi, -28(%rbp)		#copy %esi into %esi-28
	movl	$0, -4(%rbp)		#store 0 in temp variable at %rbp-4
	jmp	.L2			#jump to .L2
.L3:
	movl	-4(%rbp), %eax		#copy %rbp-4 into %eax
	cltq				
	leaq	0(,%rax,4), %rdx	
	movq	-24(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	movl	%eax, %esi
	leaq	.LC0(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	addl	$1, -4(%rbp)
.L2:
	movl	-4(%rbp), %eax		#copy %rbp-4 into %eax
	cmpl	-28(%rbp), %eax		#compare %rbp-28 to %eax
	jl	.L3			#if less than %eax : jump to .L3
	movl	$10, %edi
	call	putchar@PLT
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	printArray, .-printArray
	.section	.rodata
.LC1:
	.string	"Given array is "
.LC2:
	.string	"\nSorted array is "
	.text
	.globl	main
	.type	main, @function
main:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$48, %rsp
	movq	%fs:40, %rax
	movq	%rax, -8(%rbp)
	xorl	%eax, %eax
	movl	$12, -32(%rbp)
	movl	$11, -28(%rbp)
	movl	$13, -24(%rbp)
	movl	$5, -20(%rbp)
	movl	$6, -16(%rbp)
	movl	$7, -12(%rbp)
	movl	$6, -36(%rbp)
	leaq	.LC1(%rip), %rdi
	call	puts@PLT
	movl	-36(%rbp), %edx
	leaq	-32(%rbp), %rax
	movl	%edx, %esi
	movq	%rax, %rdi
	call	printArray
	movl	-36(%rbp), %eax
	leal	-1(%rax), %edx
	leaq	-32(%rbp), %rax
	movl	$0, %esi
	movq	%rax, %rdi
	movl	$0, %eax
	call	mergeSort
	leaq	.LC2(%rip), %rdi
	call	puts@PLT
	movl	-36(%rbp), %edx
	leaq	-32(%rbp), %rax
	movl	%edx, %esi
	movq	%rax, %rdi
	call	printArray
	movl	$0, %eax
	movq	-8(%rbp), %rcx
	xorq	%fs:40, %rcx
	je	.L6
	call	__stack_chk_fail@PLT
.L6:
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	main, .-main
	.globl	merge
	.type	merge, @function
merge:
.LFB2:
	.cfi_startproc			# Initializes internal data structures (must always be closed by _endproc)
	pushq	%rbp			# push the contents of %rbp to top of stack
	.cfi_def_cfa_offset 16		# The value of the stack pointer is offset by 16 to the current stack pointer
	.cfi_offset 6, -16		# Previous value of register 6 is saved at offset -16
	movq	%rsp, %rbp		# %rsp = #rbp
	.cfi_def_cfa_register 6		# From now on register 6 will be used instead of the old one. Offset remains the same.
	pushq	%r15			# push the contents of %r15 to stack
	pushq	%r14			# push the contents of %r14 to stack
	pushq	%r13			# push the contents of %r13 to stack
	pushq	%r12			# push the contents of %r12 to stack
	subq	$96, %rsp		# %rsp -= 96
	.cfi_offset 15, -24		# Previous value of register 15 is saved at offset -24
	.cfi_offset 14, -32		# Previous value of register 14 is saved at offset -32
	.cfi_offset 13, -40		# Previous value of register 13 is saved at offset -40
	.cfi_offset 12, -48		# Previous value of register 12 is saved at offset -48
	movq	%rdi, -104(%rbp)	# *(%rbp -104) = %rdi
	movl	%esi, -108(%rbp)	# *(%rbp -108) = %esi
	movl	%edx, -112(%rbp)	# *(%rbp -112) = %edx
	movl	%ecx, -116(%rbp)	# *(%rbp -116) = %ecx
	movq	%fs:40, %rax		# %rax = offset of 40 into the memory segment of the cpu (could also be offset of 40 into the local storage block)
	movq	%rax, -40(%rbp)		# *(%rbp -40) = %rax
	xorl	%eax, %eax		# clears the register of %eax
	movq	%rsp, %rax		# %rax = %rsp
	movq	%rax, %rsi		# %rsi = %rax
	movl	-112(%rbp), %eax	# %eax = *(%rbp - 112)
	subl	-108(%rbp), %eax	# %eax -= *(rbp - 108)
	addl	$1, %eax		# %eax += 1
	movl	%eax, -80(%rbp)		# *(%rbp - 80) = %eax
	movl	-116(%rbp), %eax	# %eax = *(%rsp - 116)
	subl	-112(%rbp), %eax
	movl	%eax, -76(%rbp)		# *(%rsp - 76) = %eax
	movl	-80(%rbp), %eax		# %eax = *(%rbp - 80)
	movslq	%eax, %rdx
	subq	$1, %rdx
	movq	%rdx, -72(%rbp)		# *(%rsp - 72) = %rdx
	movslq	%eax, %rdx
	movq	%rdx, %r14		# %r14 = %rdx
	movl	$0, %r15d		# %r15d = 0
	movslq	%eax, %rdx
	movq	%rdx, %r12		# %r12 = %rdx
	movl	$0, %r13d
	cltq
	salq	$2, %rax
	leaq	3(%rax), %rdx
	movl	$16, %eax
	subq	$1, %rax
	addq	%rdx, %rax
	movl	$16, %edi
	movl	$0, %edx
	divq	%rdi
	imulq	$16, %rax, %rax
	subq	%rax, %rsp
	movq	%rsp, %rax
	addq	$3, %rax
	shrq	$2, %rax
	salq	$2, %rax
	movq	%rax, -64(%rbp)
	movl	-76(%rbp), %eax
	movslq	%eax, %rdx
	subq	$1, %rdx
	movq	%rdx, -56(%rbp)
	movslq	%eax, %rdx
	movq	%rdx, %r10
	movl	$0, %r11d
	movslq	%eax, %rdx
	movq	%rdx, %r8
	movl	$0, %r9d
	cltq
	salq	$2, %rax
	leaq	3(%rax), %rdx
	movl	$16, %eax
	subq	$1, %rax
	addq	%rdx, %rax
	movl	$16, %edi
	movl	$0, %edx
	divq	%rdi
	imulq	$16, %rax, %rax
	subq	%rax, %rsp
	movq	%rsp, %rax
	addq	$3, %rax
	shrq	$2, %rax
	salq	$2, %rax
	movq	%rax, -48(%rbp)
	movl	$0, -84(%rbp)
	jmp	.L8
.L9:
	movl	-108(%rbp), %edx
	movl	-84(%rbp), %eax
	addl	%edx, %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %ecx
	movq	-64(%rbp), %rax
	movl	-84(%rbp), %edx
	movslq	%edx, %rdx
	movl	%ecx, (%rax,%rdx,4)
	addl	$1, -84(%rbp)
.L8:
	movl	-84(%rbp), %eax
	cmpl	-80(%rbp), %eax
	jl	.L9
	movl	$0, -88(%rbp)
	jmp	.L10
.L11:
	movl	-112(%rbp), %eax
	leal	1(%rax), %edx
	movl	-88(%rbp), %eax
	addl	%edx, %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %ecx
	movq	-48(%rbp), %rax
	movl	-88(%rbp), %edx
	movslq	%edx, %rdx
	movl	%ecx, (%rax,%rdx,4)
	addl	$1, -88(%rbp)
.L10:
	movl	-88(%rbp), %eax
	cmpl	-76(%rbp), %eax
	jl	.L11
	movl	$0, -84(%rbp)
	movl	$0, -88(%rbp)
	movl	-108(%rbp), %eax
	movl	%eax, -92(%rbp)
	jmp	.L12
.L16:
	movq	-64(%rbp), %rax
	movl	-84(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %ecx
	movq	-48(%rbp), %rax
	movl	-88(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %eax
	cmpl	%eax, %ecx
	jg	.L13
	movl	-92(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	leaq	(%rdx,%rax), %rcx
	movq	-64(%rbp), %rax
	movl	-84(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %eax
	movl	%eax, (%rcx)
	addl	$1, -84(%rbp)
	jmp	.L14
.L13:
	movl	-92(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	leaq	(%rdx,%rax), %rcx
	movq	-48(%rbp), %rax
	movl	-88(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %eax
	movl	%eax, (%rcx)
	addl	$1, -88(%rbp)
.L14:
	addl	$1, -92(%rbp)
.L12:
	movl	-84(%rbp), %eax
	cmpl	-80(%rbp), %eax
	jge	.L17
	movl	-88(%rbp), %eax
	cmpl	-76(%rbp), %eax
	jl	.L16
	jmp	.L17
.L18:
	movl	-92(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	leaq	(%rdx,%rax), %rcx
	movq	-64(%rbp), %rax
	movl	-84(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %eax
	movl	%eax, (%rcx)
	addl	$1, -84(%rbp)
	addl	$1, -92(%rbp)
.L17:
	movl	-84(%rbp), %eax
	cmpl	-80(%rbp), %eax
	jl	.L18
	jmp	.L19
.L20:
	movl	-92(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-104(%rbp), %rax
	leaq	(%rdx,%rax), %rcx
	movq	-48(%rbp), %rax
	movl	-88(%rbp), %edx
	movslq	%edx, %rdx
	movl	(%rax,%rdx,4), %eax
	movl	%eax, (%rcx)
	addl	$1, -88(%rbp)
	addl	$1, -92(%rbp)
.L19:
	movl	-88(%rbp), %eax
	cmpl	-76(%rbp), %eax
	jl	.L20
	movq	%rsi, %rsp
	nop
	movq	-40(%rbp), %rdi
	xorq	%fs:40, %rdi
	je	.L21
	call	__stack_chk_fail@PLT
.L21:
	leaq	-32(%rbp), %rsp
	popq	%r12
	popq	%r13
	popq	%r14
	popq	%r15
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	merge, .-merge
	.globl	mergeSort
	.type	mergeSort, @function
mergeSort:
.LFB3:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$32, %rsp
	movq	%rdi, -24(%rbp)
	movl	%esi, -28(%rbp)
	movl	%edx, -32(%rbp)
	movl	-28(%rbp), %eax
	cmpl	-32(%rbp), %eax
	jge	.L23
	movl	-32(%rbp), %eax
	subl	-28(%rbp), %eax
	movl	%eax, %edx
	shrl	$31, %edx
	addl	%edx, %eax
	sarl	%eax
	movl	%eax, %edx
	movl	-28(%rbp), %eax
	addl	%edx, %eax
	movl	%eax, -4(%rbp)
	movl	-4(%rbp), %edx
	movl	-28(%rbp), %ecx
	movq	-24(%rbp), %rax
	movl	%ecx, %esi
	movq	%rax, %rdi
	call	mergeSort
	movl	-4(%rbp), %eax
	leal	1(%rax), %ecx
	movl	-32(%rbp), %edx
	movq	-24(%rbp), %rax
	movl	%ecx, %esi
	movq	%rax, %rdi
	call	mergeSort
	movl	-32(%rbp), %ecx
	movl	-4(%rbp), %edx
	movl	-28(%rbp), %esi
	movq	-24(%rbp), %rax
	movq	%rax, %rdi
	call	merge
.L23:
	nop
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE3:
	.size	mergeSort, .-mergeSort
	.ident	"GCC: (Ubuntu 7.3.0-16ubuntu3) 7.3.0"
	.section	.note.GNU-stack,"",@progbits
