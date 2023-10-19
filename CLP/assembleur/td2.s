.equ N,10

.data
        i: .word 1
        fact: .word 1
.align

.text
.global _start
_start:
faire:
        LDR r0,=i
        LDR r1,[r0]     @ r1 = i

        CMP r1,#N       @ if (r1>N)
        BGT fait        @ break

        LDR r2,=fact
        LDR r3,[r2]     @ r3 = fact
        LDR r0,=i
        LDR r1,[r0]     @ r1 = i
                
        MUL r3,r3,r1    @ fact = fact*i
        STR r3,[r2]     

        ADD r1,r1,#1    @ i++
        STR r1,[r0]

        BAL faire

fait:
        BAL fait
        
