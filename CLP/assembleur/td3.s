.equ NCAR_MAX,10

@ struct t_chaine {
@        char tcar[NCAR_MAX]; // bytes 0 -> 9
@        int lg;              // bytes 12 -> 15 (align to nearest word)
@ }

.set offset_tcar,0
.set offset_lg,12
.set offset_t_chaine,16

.data
chaine:
        .byte '1','0','2','3','6' @ or .ascii "10236"
        .skip 5
        .align
        .word 5 @ length
                
i: .word 0
N: .word 0
.align        
        
.text
.global _start
_start:

for:
        LDR r0,=i
        LDR r0,[r0] @ r0 = i

        LDR r1,=chaine
        LDR r2,[r1,#lg] @ r2 = *(r1+lg) = *(&chaine+lg);

        CMP r0,r2   
        BEQ end_for  @ if (r0=r2) break;

        LDR r0,=i
        LDR r0,[r0] @ r0 = i        
        LDR r1,=chaine
        ADD r2,r1,#offset_tcar @ r2 = &(chaine.tcar)

        @chaine.tcar[i]
        LDRB r3,[r2,r0] @ r3 = *(r2+r0) = *(&chaine.tcar + i)

        ...
        
        LDR r6,=i
        LDR r7,[r6]  @ r7 = i
        ADD r7,r7,#1 @ i++
        STR r7,[r6]

        BAL for

end_for:
