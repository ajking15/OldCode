	.file	"matmul2.c"
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"starting multiply "
.LC3:
	.string	"a result %g \n"
	.text
	.p2align 4,,15
.globl main
	.type	main, @function
main:
	leal	4(%esp), %ecx
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%edi
	pushl	%esi
	xorl	%esi, %esi
	pushl	%ebx
	movl	$1074528256, %ebx
	pushl	%ecx
	subl	$24, %esp
	movl	%esp, -20(%ebp)
	subl	$80016, %esp
	leal	27(%esp), %eax
	subl	$80016, %esp
	andl	$-16, %eax
	movl	%eax, -24(%ebp)
	leal	27(%esp), %eax
	subl	$80016, %esp
	andl	$-16, %eax
	movl	%eax, -28(%ebp)
	leal	27(%esp), %edi
	movl	$.LC0, (%esp)
	andl	$-16, %edi
	call	puts
	movl	-24(%ebp), %eax
	movl	$858993459, %ecx
	movl	$1072902963, %edx
	movl	%esi, 808(%eax)
	xorl	%esi, %esi
	movl	%ebx, 812(%eax)
	movl	-28(%ebp), %eax
	movl	%ecx, 808(%eax)
	movl	%edx, 812(%eax)
	.p2align 4,,15
.L2:
	movl	-24(%ebp), %ebx
	leal	(%esi,%esi,4), %eax
	movl	-28(%ebp), %ecx
	leal	(%eax,%eax,4), %eax
	sall	$5, %eax
	leal	(%edi,%eax), %edx
	addl	%eax, %ebx
	addl	%eax, %ecx
	xorl	%eax, %eax
	.p2align 4,,15
.L3:
.L4:
	fldl	(%ebx)
	incl	%eax
	addl	$8, %ebx
	fmull	(%ecx)
	addl	$8, %ecx
	fstpl	(%edx)
	addl	$8, %edx
	cmpl	$100, %eax
	jne	.L3
	incl	%esi
	cmpl	$100, %esi
	jne	.L2
	fldl	808(%edi)
	movl	$.LC3, (%esp)
	fstpl	4(%esp)
	call	printf
	movl	-20(%ebp), %esp
	leal	-16(%ebp), %esp
	popl	%ecx
	xorl	%eax, %eax
	popl	%ebx
	popl	%esi
	popl	%edi
	popl	%ebp
	leal	-4(%ecx), %esp
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.1.2"
	.section	.note.GNU-stack,"",@progbits
