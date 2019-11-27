;
; CS1021 2018/2019	Lab 5
;
; RAM @ 0x4000000 sz = 0x10000 (64K)
;

;
; hardware registers
;

PINSEL0	EQU	0xE002C000
	
U0RBR	EQU	0xE000C000
U0THR	EQU	0xE000C000
U0LCR	EQU	0xE000C00C
U0LSR	EQU	0xE000C014
	
	
	AREA	RESET, CODE, READONLY
	ENTRY	
	
;	
; hardware initialisation
;
	LDR	R13, =0x40010000	; initialse SP
	LDR	R0, =PINSEL0		; enable UART0 TxD and RxD signals
	MOV	R1, #0x50			;
	STRB	R1, [R0]		;
	LDR	R0, =U0LCR			; R0 - > U0LCR (line control register)
	LDR	R1, =0x02			; 7 data bits + parity
	STRB	R1, [R0]		;


;
; replace this with your code
;


;
; S0
;

start
	LDR	R0, =STR0		; R0 -> "input string"
	LDR R4, =0x40000000	; START LOCATION S0
	LDR R5, =0			; COUNTER S0
	BL	PUTS			; print "input string"
L	BL	GET				; recieve character for S0
	CMP R0, #0x08		; backspaceChecker(
	BNE notBS			; if (backspace = true) {
	BL PUT				; move cursor back
	SUB R5, R5, #1		; take one away from the memory counter
	LDR R0, =0			; set R0 to 0
	BL PUT				; override last input with blank input
	STRB R0, [R4, R5]	; clear memory of last input
	LDR R0, =0x08		; set R0 to move back cursor
	BL PUT				; move cursor back
	B L					; branch to next input
notBS					; })
	CMP R0, #0x20		; spaceChecker(
	BEQ spaceSkip1		; if (space != true) {
	CMP R0, #0x0d		; compare input to 'Enter'
	BEQ enter			; skip lowercase check
	CMP R0, #0x61		; check if lowercase
	BGE lowercase		; if (lowercase != true) {	
	BL	PUT				; print character
	ADD R0, R0, #0x20	; convert uppercase to lowercase
	STRB R0, [R4, R5]	; store input in memory
	ADD R5, R5, #1		; increment total inputs
	B L					; get next character
lowercase				; }
enter
	STRB R0, [R4, R5]	; store input in memory
	ADD R5, R5, #1		; increment total inputs
	CMP R0, #0x0d		; compare input to 'Enter'
	BEQ inputComplete	; if 'Enter' finish this phase of user input
spaceSkip1				; })
	BL	PUT				; print character
	B L					; get next character
inputComplete
	LDR R0, =0x0a		; R0 = /n
	BL PUT				; print /n
	CMP R5, #1			; if S0 = null
	BEQ start			; ask for input again


;
; S1
;

	MOV R6, R4			; copy starting location from S0
	ADD R6, R6, #0x12	; save location for S1 0x12 more bytes
start2
	ADD R6, R6, #0x12	; next save location 0x12 more bytes
	LDR R7, =0			; reset S1 length counter
L2	BL	GET				; recieve character for S1
	CMP R0, #0x08		; backspaceChecker(
	BNE notBS2			; if (backspace = true) {
	BL PUT				; move cursor back
	SUB R7, R7, #1		; take one away from the memory counter
	LDR R0, =0			; set R0 to 0
	BL PUT				; override last input with blank input
	STRB R0, [R6, R7]	; clear memory of last input
	LDR R0, =0x08		; set R0 to move back cursor
	BL PUT				; move cursor back
	B L2				; branch to next input
notBS2					; })
	CMP R0, #0x20		; spaceChecker(
	BEQ spaceSkip2		; if (space != true) {
	CMP R0, #0x0d		; compare input to 'Enter'
	BEQ enter2			; skip lowercase check
	CMP R0, #0x61		; check if lowercase
	BGE lowercase2		; if (lowercase != true) {	
	BL	PUT				; print character
	ADD R0, R0, #0x20	; convert uppercase to lowercase
	STRB R0, [R6, R7]	; store input in memory
	ADD R7, R7, #1		; increment total inputs
	B L2				; get next character
lowercase2				; }
enter2
	STRB R0, [R6, R7]	; store input in memory
	ADD R7, R7, #1		; increment total inputs
	CMP R0, #0x1b		; compare input to 'ESC'
	BEQ STOP			; if 'ESC' end program
	CMP R0, #0x0d		; compare input to 'Enter'
	BEQ inputComplete2	; if 'Enter' finish this phase of user input
spaceSkip2				; })
	BL	PUT				; print character
	B L2				; get next character
inputComplete2
	LDR R0, =0x0a		; R0 = /n
	BL PUT				; print /n
	
	
;
; isAnagram
;

	LDR R10, =0			; S0 COPY MEMORY ADDRESS COUNTER
	ADD R12, R4, #0x12	; S0 COPY MEMORY ADDRESS
copyLoop				; while(copyLoop = true) {
	LDRB R11, [R4, R10]	; load S0
	STRB R11, [R12, R10]; store in S0.1
	ADD R10, R10, #1	; move memory address up by 1
	CMP R11, #0x0d		; compare S0.1 to end point (enter)
	BEQ loop4			; if equal copyLoop = false
	B copyLoop			; if not equal copyLoop = true
loop4					; }
	LDR R11, =0			; LOAD COUNTER S0
loop2					;
	LDR R10, =0			; LOAD COUNTER S0
loop					;
	LDRB R8, [R12, R10]	; LOAD LETTER S0
	LDRB R9, [R6, R11]	; LOAD LETTER S1
	CMP R9, #0x0d		; CMP S1 to end point
	BEQ equalOut		;
	CMP R8, #0x0d		; CMP S0 to end point
	BEQ notEqualOut		;
	CMP R8, R9			; CMP S0 to S1
	BEQ equal 			;
	ADD R10, R10, #1	; Add 1 to S0 address
	B loop				;
	
equal					;
	LDR R5, =0			; make copy of letter in S0 equal to 0
	STRB R5, [R12, R10]	; load 0 into memory
	ADD R11, R11, #1	; add 1 to S1 counter
	B loop2				;
	
notEqualOut				;
	LDR R0, =0x4e		; load N
	BL PUT				; print N
	LDR R0, =0x0a		; load enter
	BL PUT				; print enter
	B start2			; await next input
equalOut				;
	LDR R0, =0x59		; load Y
	BL PUT				; print Y
	LDR R0, =0x0a		; load enter
	BL PUT				; print enter
	B start2			; await next input


STOP B STOP
STR0	DCB	"input string", 0x0a, 0, 0	
	

;
; subroutines
;	
; GET
;
; leaf function which returns ASCII character typed in UART #1 window in R0
;
GET	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
GET0	LDR	R0, [R1]	; wait until
	ANDS	R0, #0x01	; receiver data
	BEQ	GET0			; ready
	LDR	R1, =U0RBR		; R1 -> U0RBR (Receiver Buffer Register)
	LDRB	R0, [R1]	; get received data
	BX	LR				; return

;	
; PUT
;
; leaf function which sends ASCII character in R0 to UART #1 window
;
PUT	LDR	R1, =U0LSR			; R1 -> U0LSR (Line Status Register)
	LDRB	R1, [R1]		; wait until transmit
	ANDS	R1, R1, #0x20	; holding register
	BEQ	PUT					; empty
	LDR	R1, =U0THR			; R1 -> U0THR
	STRB	R0, [R1]		; output charcter
PUT0	LDR	R1, =U0LSR		; R1 -> U0LSR
	LDRB	R1, [R1]		; wait until 
	ANDS	R1, R1, #0x40	; transmitter
	BEQ	PUT0				; empty (data flushed)
	BX	LR					; return

;	
; PUTS
;
; sends NUL terminated ASCII string (address in R0) to UART #1 window
;
PUTS	PUSH	{R4, LR} 		; push R4 and LR
	MOV	R4, R0					; copy R0
PUTS0	LDRB	R0, [R4], #1	; get character + increment R4
	CMP	R0, #0					; 0?
	BEQ	PUTS1					; return
	BL	PUT						; put character
	B	PUTS0					; next character
PUTS1	POP	{R4, PC} 			; pop R4 and PC
	
	END