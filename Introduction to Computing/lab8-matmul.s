;
; CS1022 Introduction to Computing II 2018/2019
; Lab 1 - Matrix Multiplication
;

N	EQU	4

	AREA	globals, DATA, READWRITE

; result matrix R

ARR_R	SPACE	N*N*4		; 4 * 4 * word-size values


	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =ARR_A
	LDR	R1, =ARR_B
	LDR	R2, =ARR_R
	LDR	R3, =N
	LDR R4, =0			;i
	LDR R5, =0			;j
	LDR R6, =0			;k
	LDR R7, =0			;r
	
	; your program goes here
	
	MOV R4, #-1						;for(i=0;
forLoop1R							;
	ADD R4, R4, #1					;i++;
	CMP R4, R3						;i<N;
	BGE forLoop1					;{
	
	MOV R5, #-1							;for{j=0;
forLoop2R								;
	ADD R5, R5, #1						;j++;
	CMP R5, R3							;j<N;
	BGE forLoop2						;{
	
	MOV R7, #0								;r=0
	
	MOV R6, #-1								;for(k=0;
forLoop3R									;
	ADD R6, R6, #1							;k++;
	CMP R6, R3								;k<N;
	BGE forLoop3							;{
	
	MUL R8, R4, R3								;index=row*row size
	ADD R8, R8, R6								;indexFinal=index+column
	LDR R9, [R0, R8, LSL #2]					;A[i,k]
	MUL R8, R6, R3								;index=row*row size
	ADD R8, R8, R5								;indexFinal=index+column
	LDR R10, [R1, R8, LSL #2]					;B[k,j]
	MUL R8, R9, R10								;A[i,k]*B[k,j]
	ADD R7, R7, R8								;r=r+A[i,k]*B[k,j]
	B forLoop3R								;{
forLoop3

	MUL R8, R4, R3							;index=row*row size
	ADD R8, R8, R5							;indexFinal=index+column
	STR R7, [R2, R8, LSL #2]				;R[i,j]=r

	B forLoop2R							;{
forLoop2	

	B forLoop1R						;{
forLoop1

STOP	B	STOP

; two constant value matrices, A and B

ARR_A	DCD	 1,  2,  3,  4
		DCD	 5,  6,  7,  8
		DCD	 9, 10, 11, 12
		DCD	13, 14, 15, 16

ARR_B	DCD	 1,  2,  3,  4
		DCD	 5,  6,  7,  8
		DCD	 9, 10, 11, 12
		DCD	13, 14, 15, 16

	END
