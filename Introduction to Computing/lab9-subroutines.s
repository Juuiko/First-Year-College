;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Subroutines
;

N	EQU	4
BUFLEN	EQU	32

PINSEL0	EQU	0xE002C000
U0RBR	EQU	0xE000C000
U0THR	EQU	0xE000C000
U0LCR	EQU	0xE000C00C
U0LSR	EQU	0xE000C014


	AREA	globals, DATA, READWRITE

; char buffer
BUFFER	SPACE	BUFLEN		; BUFLEN bytes

; result array
ARR_R	SPACE	N*4		; N words (4 bytes each)


	AREA	RESET, CODE, READONLY
	ENTRY

	BL	inithw

	;
	; invoke your max subroutine to test it
	;
;	LDR 	R1, =5
;	LDR 	R2, =7
;	BL 		max

	;
	; invoke your gets subroutine to test it
	;
;	LDR		R1, =BUFFER
;	LDR		R2, =BUFLEN
;	BL 		gets

	;
	; invoke your matmul subroutine to test it
	;
	LDR R0, =ARR_A
	LDR R1, =ARR_B
	LDR R2, =ARR_R
	LDR R3, =N
	BL matmul

STOP	B	STOP


; max subroutine
; Return maximum of a and b
; parameters
;		r1: int a - first int
;		r2: int b - second int
; return
;		r0: int max - maximum of a and b
max
	PUSH	{lr}
	CMP		R1, R2
	BGT		max1
	MOV		R0, R2
	POP		{pc}
max1	
	MOV		R0, R1
	POP		{pc}


; gets subroutine
; Reads up to len-1 chars from from the console, storing the resulting 
; NULL-terminated string in buffer and returning the number of characters read
; parameters
;		r1: char[] buffer
;		r2: unsigned int len - 
; return
;		r0: int numChars - number of chars read
;		r1: char[] buffer
gets
	PUSH	{R4, lr}
	LDR		R4, =0				; number of char counter
gets1							;
	CMP 	R2, #1				; if (len!=1)
	BLE 	gets2				; {
	PUSH	{R1, R2}			;	save R1 & R2 since they're volotile
	BL		get					;	ref doc
	BL		put					;	ref doc
	POP		{R1, R2}			;	restore R1 & R2
	CMP		R0, #13				;	compare char to enter key (end)
	BEQ		gets2				;	if enter key is pressed, exit
	STR		R0, [R1, R4, LSL#2]	;	store latest char in buffer
	ADD 	R4, R4, #1			;	numberOfChar++
	SUB		R2, R2, #1			; 	len--
	B		gets1				; }
gets2							;
	MOV		R0, R4				; move numberOfChars to the return register
	POP		{R4, pc}

; matmul subroutine
; Multiply two N×N matrices, storing the result in a third matrix
; parameters
;		r0: int[][] matrix_a -	first matix
;		r1: int[][] matrix_b -  second matrix
;		r2: int[][] result - result matrix location
;		r3: unsigned int N - size of the two matrices
; return
;		r2: int[][] result - first matrix * second matrix
matmul
	PUSH	{R4-R10,lr}
	LDR R4, =0			;i
	LDR R5, =0			;j
	LDR R6, =0			;k
	LDR R7, =0			;r
	MOV 	R4, #-1						;for(i=0;
matmul1R								;
	ADD 	R4, R4, #1					;i++;
	CMP 	R4, R3						;i<N;
	BGE 	matmul1						;{
	
	MOV 	R5, #-1							;for{j=0;
matmul2R									;
	ADD 	R5, R5, #1						;j++;
	CMP 	R5, R3							;j<N;
	BGE 	matmul2							;{
	
	MOV 	R7, #0								;r=0
	
	MOV 	R6, #-1								;for(k=0;
matmul3R										;
	ADD 	R6, R6, #1							;k++;
	CMP 	R6, R3								;k<N;
	BGE 	matmul3								;{
	
	MUL 	R8, R4, R3								;index=row*row size
	ADD 	R8, R8, R6								;indexFinal=index+column
	LDR 	R9, [R0, R8, LSL #2]					;A[i,k]
	MUL 	R8, R6, R3								;index=row*row size
	ADD 	R8, R8, R5								;indexFinal=index+column
	LDR 	R10, [R1, R8, LSL #2]					;B[k,j]
	MUL 	R8, R9, R10								;A[i,k]*B[k,j]
	ADD 	R7, R7, R8								;r=r+A[i,k]*B[k,j]
	B matmul3R									;{
matmul3

	MUL 	R8, R4, R3							;index=row*row size
	ADD 	R8, R8, R5							;indexFinal=index+column
	STR 	R7, [R2, R8, LSL #2]				;R[i,j]=r

	B matmul2R								;{
matmul2	

	B matmul1R							;{
matmul1
	POP		{R4-R10,pc}

;
; inithw subroutines
; performs hardware initialisation, including console
; parameters:
;	none
; return value:
;	none
;
inithw
	LDR	R0, =PINSEL0		; enable UART0 TxD and RxD signals
	MOV	R1, #0x50
	STRB	R1, [R0]
	LDR	R0, =U0LCR		; 7 data bits + parity
	LDR	R1, =0x02
	STRB	R1, [R0]
	BX	LR

;
; get subroutine
; returns the ASCII code of the next character read on the console
; parameters:
;	none
; return value:
;	R0 - ASCII code of the character read on teh console (byte)
;
get	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
get0	LDR	R0, [R1]		; wait until
	ANDS	R0, #0x01		; receiver data
	BEQ	get0			; ready
	LDR	R1, =U0RBR		; R1 -> U0RBR (Receiver Buffer Register)
	LDRB	R0, [R1]		; get received data
	BX	LR			; return

;
; put subroutine
; writes a character to the console
; parameters:
;	R0 - ASCII code of the character to write
; return value:
;	none
;
put	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
	LDRB	R1, [R1]		; wait until transmit
	ANDS	R1, R1, #0x20		; holding register
	BEQ	put			; empty
	LDR	R1, =U0THR		; R1 -> U0THR
	STRB	R0, [R1]		; output charcter
put0	LDR	R1, =U0LSR		; R1 -> U0LSR
	LDRB	R1, [R1]		; wait until
	ANDS	R1, R1, #0x40		; transmitter
	BEQ	put0			; empty (data flushed)
	BX	LR			; return

;
; puts subroutine
; writes the sequence of characters in a NULL-terminated string to the console
; parameters:
;	R0 - address of NULL-terminated ASCII string
; return value:
;	R0 - ASCII code of the character read on teh console (byte)
;
puts	STMFD	SP!, {R4, LR} 		; push R4 and LR
	MOV	R4, R0			; copy R0
puts0	LDRB	R0, [R4], #1		; get character + increment R4
	CMP	R0, #0			; 0?
	BEQ	puts1			; return
	BL	put			; put character
	B	puts0			; next character
puts1	LDMFD	SP!, {R4, PC} 		; pop R4 and PC


;
; test arrays
;

ARR_A	DCD	 1,  2,  3,  4
		DCD	 5,  6,  7,  8
		DCD	 9, 10, 11, 12
		DCD	13, 14, 15, 16

ARR_B	DCD	 1,  2,  3,  4
		DCD	 5,  6,  7,  8
		DCD	 9, 10, 11, 12
		DCD	13, 14, 15, 16

	END
