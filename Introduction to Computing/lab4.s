;
; CS1021 2018/2019	Lab 4
; 

	AREA	RESET, CODE, READONLY
	ENTRY

;
; code for Q1 here
;
;	LDR R1, =42				;
;	LDR R2, =32				;
;loop						;
;	MOVS R1, R1, LSR #1		;
;	BCC carryFalse			;
;	ADD R3, R3, #1			;
;carryFalse					;
;	SUB R2, R2, #1			;
;	CMP R2, #0				;
;	BNE loop				;
;	
;
; code for Q2 here
;
;	LDR R0, =16				; Numerator
;	LDR R1, =2				; Denominator
;	LDR R2, =0				; Quotient
;	LDR R3, =0				; Remainder
;	MOV R3, R0				; R := N
;whileLoop					; do {
;	ADD R2, R2, #1			; Q + 1
;	SUB R3, R3, R1			; R - D
;	CMP R3, R1				; } while (
;	BGE whileLoop			; D >= R
;	MOV R0, R2				;
;	MOV R1, R3				;
;
;
; code for Q3 here
;
	LDR R0, =444444			; Numerator
	LDR R1, =23				; Denominator
	LDR R2, =0				; Quotient
	LDR R3, =0				; Remainder
	MOV R5, R0				; safe copy Numerator
	LDR R6, =0				; i
	
loop
	MOVS R5, R5, LSR #1		; logical shift number
	ADD R6, R6, #1			; number of bits ++
	CMP R5, #0				; is number counter 0
	BNE loop				; if not, continiue
	SUB R6, R6, #1			; i-1
iLoop						;
	MOV R5, R0				;
	MOV R3, R3, LSL #1		; logical shift number
	MOV R5, R5, LSR R6		; logical shift number
	AND R5, R5, #1			; mask out every digit but 1
	ORR R3, R3, R5			; add last binary digit
	CMP R3, R1				; if R !>= D 
	BLT subSkip				; skip statement
	SUB R3, R3, R1			; R - D
	ADD R2, R2, #1			; [Q{i}] + 1
	MOV R2, R2, LSL R6		; Q(i) + 1
subSkip						;
	SUB R6, R6, #1			; i - 1
	CMP R6, #0				; i < 0
	BGE iLoop				; if  i !< 0 = !end
	
L	B	L		; infinite loop to end

        END
			