
.equ offsetA,12
.equ offsetB,16
.equ offsetRes,8

.data
        a: .word 5
        b: .word 2
.align

.bss
        res: .skip 4
.align

.text
.global _start
_start:
        LDR r2,=a
        LDR r0,[r2]          @ r0 = a
        LDR r2,=b
        LDR r1,[r2]          @ r1 = b

        STMFD sp!,{r0,r1}    @ store a and b
        SUB sp,sp,#4         @ reserve space for res
        BL pgcd              @ pgcd()

        LDMFD sp!,{r2}       @ r2 = res
        ADD sp,sp,#8         @ discart parameters

        LDR r0,=res
        STR r2,[r0]          @ store result
        
pgcd:
        STMFD sp!,{lr}
        STMFD sp!,{fp}
        MOV fp,sp

        LDR r0,[fp,#offsetA]         @ r0 = a
        CMP r0,#0                    @ if a == 0
        MOVEQ r2,#0                  
        STREQ r2,[fp,#offsetRes]     @ res = 0
        BEQ return                   @ return

        LDR r0,[fp,#offsetB]         @ r0 = b
        CMP r0,#0                    @ if b == 0
        MOVEQ r2,#0                  
        STREQ r2,[fp,#offsetRes]     @ res = 0
        BEQ return                   @ return

        LDR r0,[fp,#offsetA]         @ r0 = a
        LDR r1,[fp,#offsetB]         @ r1 = b
        CMP r0,r1                    @ if a == b
        STREQ r0,[fp,#offsetRes]     @ res = a
        BEQ return                   @ return

        LDR r0,[fp,#offsetA]         @ r0 = a
        LDR r1,[fp,#offsetB]         @ r1 = b        
        CMP r0,r1                    @ if a > b
        BGT call_gt

        @ a < b
        @ same as call_gt but different parameters
        LDR r0,[fp,#offsetA]         @ r0 = a
        LDR r1,[fp,#offsetB]         @ r1 = b
        SUB r1,r1,r0                 @ b = b-a
        STMFD sp!,{r0,r1}            @ save a and b to stack
        SUB sp,sp,#4                 @ reserve space for res
        BL pgcd                      @ pgcd()
        LDMFD sp!,{r2}               @ r2 = res
        ADD sp,sp,#8                 @ discard variables
        STR r2,[fp,#offsetRes]       @ store res for return
        BAL return

call_gt:
        LDR r0,[fp,#offsetA]         @ r0 = a
        LDR r1,[fp,#offsetB]         @ r1 = b
        SUB r0,r0,r1                 @ a = a-b
        STMFD sp!,{r0,r1}            @ save a and b to stack
        SUB sp,sp,#4                 @ reserve space for res
        BL pgcd                      @ pgcd()
        LDMFD sp!,{r2}               @ r2 = res
        ADD sp,sp,#8                 @ discard variables
        STR r2,[fp,#offsetRes]       @ store res for return
        BAL return

return:
        LDMFD sp!,{fp}
        LDMFD sp!,{lr}
        BX lr
