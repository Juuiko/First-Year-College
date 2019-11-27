;
; CS1021 2018/2019	Lab 3
; 

	AREA	RESET, CODE, READONLY
	ENTRY

;
; code for Q1 here
;
;	LDR R0, = 24			; a
;	LDR R1, = 32			; b
;	
;while						;
;	CMP R0, R1				; while ( a != b ) 
;	BEQ STOP				; { !finish }
;	CMP R0, R1				; if ( a > b )
;	BGT aGreaterB			; { jump to aGreaterB }
;	SUB R1, R1, R0			; else { b = b - a }
;	B while					; loop back to start
;aGreaterB					; aGreaterB
;	SUB R0, R0, R1			; a = a - b
;	B while					; loop back to start

;
; code for Q2 here
;
;	LDR R0, =0		; MS Fn
;	LDR R1, =0		; LS Fn
;	LDR R2, =0		; MS Fn - 1
;	LDR R3, =0		; LS Fn - 1
;	LDR R4, =0		; MS Fn - 2
;	LDR R5, =1		; LS Fn - 2
;	LDR R6, =0		;     n
;
;start
;	ADDS R1, R3, R5	; LS Fn = LS Fn-1 + LS Fn-2
;	ADC	 R0, R2, R4	; MS Fn = MS Fn-1 + MS Fn-2
;	MOV	 R5, R3		; LS Fn-2 = LS Fn-1
;	MOV  R4, R2		; MS Fn-2 = MS Fn-1
;	MOV  R3, R1		; LS Fn-1 = LS Fn
;	MOV  R2, R0		; MS Fn-1 = LS Fn
;	ADD  R6, R6, #1	; n + 1
;	CMP  R6, #64	; Compare n to the end point
;	BNE start		;

;
; code for Q3 here
;
;	LDR R0, =0			; MS Fn
;	LDR R1, =0			; LS Fn
;	LDR R2, =0			; MS Fn - 1
;	LDR R3, =0			; LS Fn - 1
;	LDR R4, =0			; MS Fn - 2
;	LDR R5, =1			; LS Fn - 2
;	LDR R6, =0			;     n
;	LDR R7, =0x7FFFFFFF	; MS MAX (signed)
;	LDR R8, =0xFFFFFFFF ; LS MAX
;
;loop
;	ADDS R1, R3, R5		; LS Fn = LS Fn-1 + LS Fn-2
;	ADC	 R0, R2, R4		; MS Fn = MS Fn-1 + MS Fn-2
;	MOV	 R5, R3			; LS Fn-2 = LS Fn-1
;	MOV  R4, R2			; MS Fn-2 = MS Fn-1
;	MOV  R3, R1			; LS Fn-1 = LS Fn
;	MOV  R2, R0			; MS Fn-1 = LS Fn
;	ADD  R6, R6, #1		; n + 1
;	SUBS R10, R8, R3	; R10 = LS MAX - LS Fn-1
;	SBC  R9, R7, R2		; R9  = MS MAX - MS Fn-1
;	CMP  R4, R9			; MAX  (S) - Fn-1 < Fn-2
;	BLT  loop

STOP	B	STOP		; infinite loop to end

        END