;
; CS1022 Introduction to Computing II 2018/2019
; Lab 1 - Addressing Modes
;


N	EQU	10

	AREA	globals, DATA, READWRITE

; N word-size values

ARRAY	SPACE	N*4		; N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY

	; for convenience, let's initialise test array [0, 1, 2, ... , N-1]

	LDR	R0, =ARRAY
	LDR	R1, =0
L1	CMP	R1, #N
	BHS	L2
	STR	R1, [R0, R1, LSL #2]
	ADD	R1, R1, #1
	B	L1
L2

	; initialise registers for your program

	LDR	R0, =ARRAY	; array start address
	LDR	R1, =N		; size of array (words)

	; your program goes here

	; immediate offset
	; 83 instructions
;
;	MOV	R4, #0
;L3	CMP	R4, R1
;	BHS	L4
;	LDR	R5, [R0]
;	MUL	R6, R5, R5
;	STR	R6, [R0]
;	ADD	R0, R0, #4
;	ADD	R4, R4, #1
;	B	L3
;L4

	; immediate offset and register offset
	; 72 instructions
;
;	MOV	R4, #0
;L3	CMP	R4, R1
;	BHS	L4
;	LDR	R5, [R0, R7]
;	MUL	R6, R5, R5
;	STR	R6, [R0, R7]
;	ADD	R7, R7, #4
;	ADD	R4, R4, #1
;	B	L3
;L4

	; immediate offset and scaled register offset
	; 68 instructions
;
;	MOV	R4, #0
;L3	CMP	R4, R1
;	BHS	L4
;	LDR	R5, [R0, R4, LSL #2]
;	MUL	R6, R5, R5
;	STR	R6, [R0, R4, LSL #2]
;	ADD	R4, R4, #1
;	B	L3
;L4

	; immediate offset and post-indexed
	; 68 instructions
;
;	MOV	R4, #0
;L3	CMP	R4, R1
;	BHS	L4
;	LDR	R5, [R0]
;	MUL	R6, R5, R5
;	STR	R6, [R0], #4
;	ADD	R4, R4, #1
;	B	L3
;L4

STOP	B	STOP

	END