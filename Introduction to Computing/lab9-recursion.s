;
; CS1022 Introduction to Computing II 2018/2019
; Lab 2 - Recursion
;

N	EQU	42

	AREA	globals, DATA, READWRITE

; N word-size values

SORTED	SPACE 42*4

	AREA	RESET, CODE, READONLY
	ENTRY

	;
	; copy the test data into RAM
	;

	LDR	R4, =SORTED
	LDR	R5, =UNSORT
	LDR	R6, =0
whInit	CMP	R6, #N
	BHS	eWhInit
	LDR	R7, [R5, R6, LSL #2]
	STR	R7, [R4, R6, LSL #2]
	ADD	R6, R6, #1
	B	whInit
eWhInit


	;
	; call your sort subroutine to test it
	;
	LDR R0, =SORTED
	LDR R2, [R0]
	LDR R1, =N
	BL sort
	
STOP	B	STOP


; swap subroutine
; Swaps two elements in  a  1-dimensional  array  of  word-size  integers
; parameters
; 		r0: int[] array - array start address
;		r1: int i - first number to swap
;		r2: int j - second number to swap
; return
;		r0: int[] array - new array
swap
	PUSH	{r4, lr}
	LDR		R3, [R0, R1, LSL#2]
	LDR		R4, [R0, R2, LSL#2]
	STR		R4, [R0, R1, LSL#2]
	STR		R3, [R0, R2, LSL#2]
	POP		{r4, pc}



; sort subroutine
; Bubble sorts an array of length N
; parameters
;		r0: int[] array
;		r1: int N
; return
;		r0: int[] array
sort
	PUSH	{r4, r5, r6, lr}
	LDR 	R2, =0				; boolean swapped
sort1							; do{
	LDR		R2, =0				; 	swapped = false
	LDR		R3, =0				; 	for(i=1;
sortForLoopR					;
	ADD		R3, R3, #1			; 	i++;
	CMP		R3, R1				; 	i<N;)
	BGE		sortForLoop			; 	{
	SUB		R6, R3, #1			;		i-1
	LDR		R4, [R0, R6, LSL#2]	; 		array[i-1]
	LDR		R5, [R0, R3, LSL#2]	; 		array[i]
	CMP		R4, R5				; 		if(array[i-1]>array[i])
	BLE		sortIf1				;	 		{
	PUSH	{R1, R2}			;			Push R1&R2 to setup for swap
	MOV		R1, R3				;			Move i to R1
	MOV		R2, R6				;			Move i-1 to R2
	BL		swap				; 			swap(array, i-1, i)
	POP		{R1, R2}			; 			Pop R1&r2 back
	LDR		R2, =1				; 			swapped = true
sortIf1							;		}
	B sortForLoopR				;
sortForLoop						;
	CMP		R2, #0				; 	}
	BNE		sort1				; } while(swapped)
	POP		{r4, r5, r6, pc}				

UNSORT	DCB	1,0,0,0,0,0,0
		DCB	2,0,0,0,0,0,0
		DCB	3,0,0,0,0,0,0
		DCB	4,0,0,0,0,0,0
		DCB	5,0,0,0,0,0,0
		DCB	6,0,0,0,0,0,0

	END
