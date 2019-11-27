;
; CS1022 Introduction to Computing II 2018/2019
; Lab 1 - Array Move
;

N	EQU	16		; number of elements

	AREA	globals, DATA, READWRITE

; N word-size values

ARRAY	SPACE	N*4		; N words


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

	LDR	R0, =ARRAY
	LDR	R1, =6
	LDR	R2, =3
	LDR	R3, =N

	; your program goes here

	CMP R1, R2
	BLE reverse

	LDR R4, [R0, R1, LSL #2]		;load value to move
	MOV R5, R1						;create pointer from starting location
stackCreation
	SUB R5, R5, #1					;move array pointer down one
	LDR R6, [R0, R5, LSL #2]		;load the previous value
	STMDB SP!, {R6}					;add value to stack
	CMP R5, R2						;compare pointer to end location
	BNE stackCreation
	
	STR R4, [R0, R5, LSL #2]		;store moved value in final pos
	
moveBack
	ADD R5, R5, #1					;move array pointer up one
	STR R6, [R0, R5, LSL #2]		;store value in the new location
	LDMIB SP!, {R6}					;pop 1 from stack
	CMP R5, R1						;compare pointer to end location
	BNE moveBack
	B STOP



reverse
	LDR R4, [R0, R1, LSL #2]		;load value to move
	MOV R5, R1						;create pointer from starting location
stackCreationR
	ADD R5, R5, #1					;move array pointer down one
	LDR R6, [R0, R5, LSL #2]		;load the previous value
	STMDB SP!, {R6}					;add value to stack
	CMP R5, R2						;compare pointer to end location
	BNE stackCreationR
	
	STR R4, [R0, R5, LSL #2]		;store moved value in final pos
	
moveBackR
	SUB R5, R5, #1					;move array pointer up one
	STR R6, [R0, R5, LSL #2]		;store value in the new location
	LDMIB SP!, {R6}					;pop 1 from stack
	CMP R5, R1						;compare pointer to end location
	BNE moveBackR	
	
STOP	B	STOP

	END
