
.equ N,3

.equ offsetI,-4
.equ offsetJ,-8
.equ offsetK,-12

.equ offsetMA,8
.equ offsetMB,12
.equ offsetMRes,16

.data
m1: .word 1,2,3,4,5,6,7,8,9
m2: .word 1,1,1,2,2,2,3,3,3
.align

.bss
res: .skip 4*N*N
.align

.text
.global _start
_start:
        LDR r0,=m1
        LDR r1,=m2
        LDR r2,=res

        STMFD sp!, {r0,r1,r2}        @ store parameters to stack

        BL produit

        ADD sp,sp,#12                @ discart parameters
        BAL end

produit:
        STMFD sp!, {lr}
        STMFD sp!, {fp}
        MOV fp, sp
        SUB sp,sp,#12                @ reserve space for i,j and k
        STMFD sp!, {r0-r8}        @ save registers

        @ start of the function

        MOV r0,#0
        STR r0,[fp,#offsetI]   @ i = 0
        forI:
                LDR r0,[fp,#offsetI]
                CMP r0,#N              @ for (i=0;i<=N-1;i++)
                BEQ endforI

                MOV r0,#0
                STR r0,[fp,#offsetJ]   @ j = 0
                forJ:
                        LDR r0,[fp,#offsetJ]
                        CMP r0,#N                @ for (j=0;j<=N-1;j++)
                        BEQ endforJ

                        LDR r0,[fp,#offsetMRes]            @ r0 = &res
                        LDR r1,[fp,#offsetI]
                        LDR r2,[fp,#offsetJ]
                        MOV r5,#N
                        MLA r3,r1,r5,r2        @ r3 = i*N+j
                        MOV r7,#0
                        STR r7,[r0,r3, LSL #2] @ res[i][j] = 0

                        MOV r0,#0
                        STR r0,[fp,#offsetK]   @ k = 0
                        forK:
                                LDR r0,[fp,#offsetK]
                                CMP r0,#N
                                BEQ endforK

                                LDR r0,[fp,#offsetMRes]@ r0 = Result[0][0]
                                LDR r1,[fp,#offsetI]   @ r1 = i
                                LDR r2,[fp,#offsetJ]   @ r2 = j
                                LDR r3,[fp,#offsetK]   @ r3 = k
                                MOV r4,#N
                                LDR r5,[fp,#offsetMA]  @ r5 = A[0][0]
                                LDR r6,[fp,#offsetMB]  @ r6 = B[0][0]

                                MLA r7,r1,r4,r2        @ r7 = i*N+j
                                LDR r7,[r0,r7, LSL #2] @ r7 = res[i][j]

                                MLA r8,r1,r4,r3        @ r8 = i*N+k
                                LDR r8,[r5,r8, LSL #2] @ r8 = A[i][k]

                                ADD r7,r7,r8           @ res[i][j] += A[i][k]

                                MLA r8,r3,r4,r2        @ r8 = k*N+j
                                LDR r8,[r6,r8, LSL #2] @ r8 = B[k][j]

                                ADD r7,r7,r8           @ res[i][j] += B[k][j]

                                MLA r8,r1,r4,r2        @ r8 = i*N+j
                                STR r7,[r0,r8, LSL #2] @ store res[i][j]
                                
                                LDR r0,[fp,#offsetK]
                                ADD r0,r0,#1
                                STR r0,[fp,#offsetK]
                                BAL forK
                        endforK:
                        
                        LDR r0,[fp,#offsetJ]
                        ADD r0,r0,#1
                        STR r0,[fp,#offsetJ]
                        BAL forJ
                endforJ:  
                                      
                LDR r0,[fp,#offsetI]
                ADD r0,r0,#1
                STR r0,[fp,#offsetI]
                BAL forI
        endforI:
        
        @ end of the function
        
        LDMFD sp!, {r0-r8}        @ restore registers
        ADD sp,sp,#12
        LDMFD sp!, {fp}
        LDMFD sp!, {lr}
        BX lr
        


end:
        BAL end
