;
; CS1022 Introduction to Computing II 2018/2019
; Lab 3 - Floating-Point Addition
;

	AREA	RESET, CODE, READONLY
	ENTRY

;
; Test Data
;
FP_A	EQU	0x42f6e666		;123.45
FP_B	EQU	0x42613333		;56.3


	; initialize system stack pointer (SP)
	LDR	SP, =0x40010000

	LDR	r0, =FP_B		; test value A
	LDR	r1, =FP_A		; test value B
	BL	fpadd

stop	B	stop



; fpdecode
; decodes an IEEE 754 floating point value to the signed (2's complement)
; fraction and a signed 2's complement (unbiased) exponent
; parameters:
;	r0 - ieee 754 float
; return:
;	r0 - fraction (signed 2's complement word)
;	r1 - exponent (signed 2's complement word)
;
fpdecode
	PUSH {LR, R2-R6}
	LDR R3, =0xFF800000			;exponemt and s mask
	LDR R4, =0x807FFFFF			;fraction and s mask
	LDR R5, =0x7FFFFFFF			;s bit mask
	BIC R6, R0, R5				;store s sin R6
	BIC R1, R0, R4				;remove s and frac from expo
	MOV R1, R1, LSR #23			;shift expo to start at 0
	BIC R0, R0, R3				;remove s and expo from frac
	SUB R1, R1, #127			;remove b from expo
	ADD R0, R0, #0x800000		;add hidden bit to frac
	CMP R6, #0					;compare s to 0
	BEQ fpdecodeFracPos
	MVN R0, R0
	ADD R0, R0, #1
fpdecodeFracPos	
	POP {PC, R2-R6}


; fpencode
; encodes an IEEE 754 value using a specified fraction and exponent
; parameters:
;	r0 - fraction (signed 2's complement word)
;	r1 - exponent (signed 2's complement word)
; result:
;	r0 - ieee 754 float
;
fpencode
	PUSH {LR, R1-R5}
	LDR R3, =0x7FFFFF		; load fraction mask
	LDR R4, =0x800000		; load comparing value
	BIC R5, R0, R3			; compare fraction
	CMP R5, R4
	BEQ fpencodeEnd
	CMP R5, #0				; with 0, if it equals 0
	BNE fpencodeL2			; increase size of the fracton
	
	
fpencodeL1					;	%%%%%  IF NUMBER IS TOO SMALL   %%%%%	
	MOV R0, R0, LSL #1		;	hide the bit again
	SUB R1, R1, #1			;	add 1 to the exponet to offset the shift
	BIC R5, R0, R3			;	hide all of fraction except for the hidden bit
	CMP R5, R4				;	compare to see if only hidden bit is left
	BNE fpencodeL1			;	if more than the hidden bit is left, shift again
	B fpencodeEnd
	
	
fpencodeL2					;	%%%%%  IF NUMBER IS TOO BIG   %%%%%	
	MOV R0, R0, LSR #1		;	hide the bit again
	ADD R1, R1, #1			;	add 1 to the exponet to offset the shift
	BIC R5, R0, R3			;	hide all of fraction except for the hidden bit
	CMP R5, R4				;	compare to see if only hidden bit is left
	BNE fpencodeL2			;	if more than the hidden bit is left, shift again

fpencodeEnd	
	SUB R0, R0, R3			;	remove hidden bit
	ADD R1, R1, #127		;	add b on the expo
	MOV R1, R1, LSL #23		;	shift the expo into place
	ORR R0, R0, R1			;	add the expo and the frac
	
	POP {PC, R1-R5}


; fpadd
; adds two IEEE 754 values
; parameters:
;	r0 - ieee 754 float A
;	r1 - ieee 754 float B
; return:
;	r0 - ieee 754 float A+B
;
fpadd
	PUSH {LR, R1-R4}		;push all registers not used for return
	MOV R4, R1				;move FP_B to a safe register
	BL fpdecode				;decode FP_A
	MOV R2, R0				;move FP_A into a safe register
	MOV R3, R1				;move FP_A into a safe register
	MOV R0, R4				;move FP_B to be decoded
	BL fpdecode				;decode FP_B
fpaddL2
	CMP R1, R3				;compare the exponents
	BEQ fpaddL1				;if when they're equal -> proceed
	CMP R1, R3				;compare both exponents
	BLT fpaddL3				;if FP_A exponent is smaller -> jump
	MOV R2, R2, LSR #1		;move fraction to the right
	ADD R3, R3, #1			;increase the expo once
	B fpaddL2				;back to compare and see if they're equal
fpaddL3
	MOV R0, R0, LSR #1		;move fraction to the right
	ADD R1, R1, #1			;increase the expo once
	B fpaddL2				;back to compare and see if they're equal
fpaddL1


	ADD R0, R0, R2			; add the fractions
	BL fpencode				;	re-encode
	
	POP{PC, R1-R4}

	END
