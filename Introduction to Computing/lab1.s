;
; CS1021 2018/2019	Lab 1
; 

	AREA	RESET, CODE, READONLY
	ENTRY

;
; start of code
;
	LDR	R1, =5   	; x = 5
	LDR	R2, =6		; y = 6
	LDR	R3, =2		; z = 2

;
; compute x*x + y + 4 (35 or 0x23)
;
;	MUL	R0, R1, R1	; R0 = x*x
;	ADD	R0, R0, R2	; R0 = x*x + y
;	ADD	R0, R0, #4	; R0 = x*x + y + 4
	
;
; compute 3*x^2 + 5*x
;
;	LDR R2, =3		; R2 = 3 CONSTANT
;	LDR R3, =5		; R3 = 5 CONSTANT
;	MUL R0, R1, R1	; R0 = x^2
;	MUL R0, R2, R0	; R0 = 3*x^2
;	MUL R1, R3, R1	; R1 = 5*x
;	ADD R0, R0, R1	; R0 = 3*x^2 + 5*x
	
;
; compute 2*x^2 + 6*x*y + 3*y^2
;
;	LDR R3, =2		; R3 = 2 CONSTANT
;	LDR R4, =6		; R4 = 6 CONSTANT
;	LDR R5, =3		; R5 = 3 CONSTANT
;	MUL R0, R1, R1	; R0 = x^2
;	MUL R0, R3, R0	; R0 = 2*x^2
;	MUL R1, R4, R1	; R1 = 6*x
;	MUL R1, R2, R1	; R1 = 6*x*y
;	MOV R6, R2		; R6 = y
;	MUL R2, R6, R2	; R2 = y^2
;	MUL R2, R5, R2	; R2 = 3*y^2
;	ADD R1, R2, R1	; R1 = 6*x*y + 3*y^2
;	ADD R0, R1, R0	; R0 = 2*x^2 + 6*x*y + 3*y^2

;
; compute x^3 - 4*x^2 + 3*x + 8
;
;	LDR R3, =4		; R3 = 4 CONSTANT
;	LDR R4, =3		; R4 = 3 CONSTANT
;	MUL R0, R1, R1	; R0 = x^2
;	MUL R2, R0, R3	; R2 = 4*x^2
;	MUL R0, R1, R0	; R0 = x^3
;	MUL R1, R4, R1	; R1 = 3*x
;	ADD R2, R2, #8	; R2 = 4*x^2 + 8
;	ADD R1, R1, R2	; R1 = 4*x^2 + 3*x + 8
;	SUB R0, R0, R1	; R0 = x^3 - 4*x^2 + 3*x + 8

;
; compute 3*x^4 - 5*x - 16*y^4*z^4
;
;	LDR	R5, =3		; R5 =  3 CONSTANT
;	LDR	R6, =5		; R6 =  5 CONSTANT
;	LDR	R7, =16		; R7 = 16 CONSTANT
;	
;	MOV R4, R1		; R4 = x
;	MUL R0, R1, R1	; R0 = x^2
;	MUL R0, R4, R0	; R0 = x^3
;	MUL R0, R4, R0	; R0 = x^4
;	MUL R0, R5, R0	; R0 = 3*x^4
;	
;	MUL R1, R5, R1	; R1 = 5*x
;	
;	MOV R4, R2		; R4 = y
;	MUL R2, R4, R2	; R2 = y^2
;	MUL R2, R4, R2	; R2 = y^3
;	MUL R2, R4, R2	; R2 = y^4
;	MOV R4, R3		; R4 = z
;	MUL R3, R4, R3	; R3 = z^2
;	MUL R3, R4, R3	; R3 = z^3
;	MUL R3, R4, R3	; R3 = z^4
;	MUL R3, R7, R3	; R3 = 16*z^4
;	MUL R2, R3, R2	; R2 = 16*y^4*z^4
;	
;	SUB R1, R1, R2	; R1 = 5*x - 16*y^4*z^4
;	SUB R0, R0, R1	; R0 = 3*x^4 - 5*x - 16*y^4*z^4

L	B	L		; infinite loop to end programme

        END
