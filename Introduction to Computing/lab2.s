;
; CS1021 2018/2019	Lab 2
; 


	AREA	RESET, CODE, READONLY
	ENTRY

;
; code for Q1 here
;
;	LDR R0, =0		; Fn
;	LDR R1, =1		; Fn-1
;	LDR R2, =0		; n
;	LDR R3, =0		; Fn-2
;
;start
;	ADD R0, R1, R3	; Fn = Fn-1 + Fn-2
;	MOV R3, R1		; Fn-2 = Fn-1
;	MOV R1, R0		; Fn-1 = Fn
;	ADD R2, R2, #1	; n + 1
;	CMP R2, #16		; Compare n to the end point
;	BNE start		

;
; code for Q2 here
;
;	LDR R0, =0			; Fn
;	LDR R1, =1			; Fn-1
;	LDR R2, =0			; n
;	LDR R3, =0			; Fn-2
;	LDR R4, =0xFFFFFFFF	; MAX (unsigned)
;	LDR R4, =0x7FFFFFFF	; MAX (signed)
;
;loop
;	ADD R0, R1, R3		; Fn = Fn-1 + Fn-2
;	MOV R3, R1			; Fn-2 = Fn-1
;	MOV R1, R0			; Fn-1 = Fn
;	ADD R2, R2, #1		; n + 1
;	SUB R5, R4, R1		; R5 = MAX - Fn-1
;	CMP R5, R3			; MAX (US) - Fn-1 > Fn-2
;	CMP R3, R5			; MAX  (S) - Fn-1 < Fn-2
;	BLT loop
	
STOP	B	STOP		; infinite loop to end

        END