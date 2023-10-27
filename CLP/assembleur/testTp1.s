.data
T:	.word	4,8,12,9,0,-1,0,-18,2,0
.align

.bss
X:	.skip 4
Y:  .skip 4
Z:	.skip 4

.equ N,10

.text
.global _Start

_Start:
	mov r2,#0
	mov r3,#0
	ldr r1,=T	
	
Boucle: 
	ldr r0, [r1], #4
	cmp r0,#0
	addlt r3,r3,#1
	addeq r4,r4,#1
	bge Boucle
	
	ldr r0,=X
	str r3, [r0]

	ldr r0,=Y
	str r4, [r0]
	
	add r3, r3, r4
	rsb r3, r3, #N
	ldr r0,=Z
	str r3, [r0]
.end