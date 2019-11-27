;
; CS1022 Introduction to Computing II 2018/2019
; Mid-Term Assignment - Connect 4 - SOLUTION
;
; get, put and puts subroutines provided by jones@scss.tcd.ie
;

PINSEL0	EQU	0xE002C000
U0RBR	EQU	0xE000C000
U0THR	EQU	0xE000C000
U0LCR	EQU	0xE000C00C
U0LSR	EQU	0xE000C014


	AREA	globals, DATA, READWRITE

BOARD	SPACE	42*4

	AREA	RESET, CODE, READONLY
	ENTRY

	; initialise SP to top of RAM
	LDR	R13, =0x40010000	; initialse SP

	; initialise the console
resetGame	
	BL	inithw
	
	; initialise board into RAM
	BL	initBoard

	;
	; your program goes here
	;
	LDR R0, =str_go
	BL puts
	LDR R1, =BOARD
	BL printBoard
	LDR R4, =0			;Player turn
	LDR R12, =0			;Game end
	LDR R6, =7			;Board width
	LDR R8, =6			;Board height
	LDR R9, =BOARD
game
	BL playerInput
	CMP R12, #0
	BEQ game
	LDR R0, =str_redW
	BL puts
stop	B	stop


; initialise board subroutine
; loads in a clear board into ram
; parameters
;		void
; return
;		void

initBoard
	PUSH {LR, R0-R2}			;
	LDR R0, =BOARD				;Load board start address
	LDR R1, =0x30				;Load 0 ascii value
	LDR R2, =0					;init board size counter
initB							;do{
	STR R1, [R0, R2, LSL #2]	;	store '0' into board array
	ADD R2, R2, #1				;	board size counter++
	CMP R2, #42					;}
	BLT initB					;while(board size counter<board size max)
	POP {PC, R0-R2}				;
	
	
; print board
; prints the board from RAM memory
; parameters
;		void
; return
;		void

printBoard
	PUSH	{LR, R0-R4}			;
	LDR 	R0, =str_topNums	;load first row (key) of board
	BL 		puts				;print first row of board
	LDR		R1, =BOARD			;load board start address
	LDR 	R2, =1				;vertical counter
	LDR		R3, =0				;pointer loaction
printBoardL1					;do {
	MOV		R0, R2				;	
	ADD		R0, R0, #48			;	
	BL		put					;	
	LDR		R4, =1				;	horizontal counter
printBoardL2					;	do {
	MOV		R0, #0x20			;		load space
	BL 		put					;		print space char
	LDR		R0, [R1, R3]		;		load board value
	BL		put					;		
	ADD		R4, R4, #1			;		
	ADD		R3, R3, #4			;		
	CMP 	R4, #7				;		
	BLE		printBoardL2		;	}
	ADD		R2, R2, #1			;	
	LDR 	R0, =str_newLine	;	
	BL		puts				;	
	CMP 	R2, #7				;	
	BLT		printBoardL1		;}
	LDR 	R0, =str_newLine	;load blank line
	BL		puts				;print blank line
	POP		{PC, R0-R4}			;

;
; PLAYER INPUT
;
playerInput
	PUSH	{LR}
	CMP		R4, #0
	BEQ		redPlayer
	LDR R0, =str_yellow
	BL puts
	BL get
	BL put
	CMP R0, #0x71
	BEQ resetGame
	
	BL playerTurn
	
	LDR R1, =BOARD
	BL printBoard
	MOV		R4, #0
	B playerInputEnd
redPlayer
	LDR R0, =str_red
	BL puts
	BL get
	BL put
	CMP R0, #0x71
	BEQ resetGame
	
	BL playerTurn
	
	LDR R1, =BOARD
	BL printBoard
	MOV		R4, #1
playerInputEnd	
	POP		{PC}
	
	
;	
; PLAYER TURN	
;	R0=column
;	R1=Board start adress
;	R4 = player turn
playerTurn
	PUSH	{LR, R0-R8}
	SUB		R1, R0, #0x31
	MOV		R2, R8
playerTurnL1
	SUB		R2, R2, #1
	BL		arrLoad
	CMP		R0, #0x30
	BGT		playerTurnL1
	
	
	
	CMP		R4, #0
	BEQ		playerTurnL2
	LDR		R4, =1
	LDR		R0, =0x59
	STR		R0, [R9, R3, LSL #2]
	BL		winning
	POP		{PC, R0-R8}	
playerTurnL2
	LDR		R4, =1
	LDR		R0, =0x52
	STR		R0, [R9, R3, LSL #2]
	BL		winning
	POP		{PC, R0-R8}
	
;
;CHECK IF WINNING
;
;
winning
	PUSH	{LR, R0-R4, R6-R10}
	BL		winningVertically
	CMP		R12, #1
	BEQ		win
	BL		winningHorizontally
	CMP		R12, #1
	BEQ		win
;	BL		winningDiagonally
;	CMP		R12, #1
;	BEQ		win
win
	POP		{PC, R0-R4, R6-R10}

;
;CHECK IF WINNING HORIZONTALLY
;
;
winningVertically
	PUSH	{LR, R0-R10}
	LDR		R7, =0				;how many in a row counter
	MOV 	R8, R0				;save colour
winningVL2
	ADD		R7, R7, #1
	ADD		R2, R2, #1			;vertical pointer down 1
	CMP		R2, #5				;vertical point vs off board
	BGT		winningVL1
	BL		arrLoad
	CMP		R0, R8
	BEQ		winningVL2
winningVL1
	CMP		R7, #4				;if(
	BLT		noWinV				;less than 4 in a row, no win)
	ADD		R12, R12, #1			;4+ in a row = win boolean set to true
noWinV	
	POP		{PC, R0-R10}

;
;CHECK IF WINNING HORIZONTALLY
;
;
winningHorizontally
	PUSH	{LR, R0-R10}
	LDR		R7, =0				;how many in a row counter
	MOV 	R8, R0				;save colour
	MOV		R5, R1
	;right
	ADD		R1, R1, #1			;horizontal pointer right 1
winningHRL2
	ADD		R7, R7, #1			;row counter++
	ADD		R1, R1, #1			;horizontal pointer right 1
	CMP		R1, #6				;horizontal point vs off board
	BGT		winningHRL1
	BL		arrLoad
	CMP		R0, R8
	BEQ		winningHRL2
winningHRL1
	;left
	MOV		R1, R5
winningHLL2
	ADD		R7, R7, #1			;row counter++
	SUB		R1, R1, #1			;horizontal pointer left 1
	CMP		R1, #0				;horizontal point vs off board
	BLT		winningHLL1
	BL		arrLoad
	CMP		R0, R8
	BEQ		winningHLL2
winningHLL1
	;won?
	CMP		R7, #4				;if(
	BLT		noWinH				;less than 4 in a row, no win)
	ADD		R12, R12, #1			;4+ in a row = win boolean set to true
noWinH	
	POP		{PC, R0-R10}

;
;CHECK IF WINNING DIAGONALLY
;
;
;winningDiagonally
;	PUSH	{LR, R0-R11}
;	LDR		R7, =0				;how many in a row counter
;	MOV 	R8, R0				;save colour
;	MOV		R5, R1				;save pointers
;	MOV		R11, R2				;save pointers
;	;/up
;winningDSUL2
;	ADD		R7, R7, #1			;row counter++
;	ADD		R1, R1, #1			;horizontal pointer right 1
;	SUB		R2, R2, #1			;vertical pointer right 1
;	CMP		R1, #6				;horizontal point vs off board
;	BGT		winningDSUL1
;	BL		arrLoad
;	CMP		R0, R8
;	BEQ		winningDSUL2
;winningDSUL1
;	MOV		R1, R5
;	MOV		R2, R11
;	;/down
;winningDSDL2
;	ADD		R7, R7, #1			;row counter++
;	SUB		R1, R1, #1			;horizontal pointer left 1
;	ADD		R2, R2, #1
;	CMP		R1, #0				;horizontal point vs off board
;	BLT		winningDSDL1
;	BL		arrLoad
;	CMP		R0, R8
;	BEQ		winningDSDL2
;winningDSDL1
;	;won?
;	CMP		R7, #4				;if(
;	BLT		noWinD				;less than 4 in a row, no win)
;	ADD		R12, R12, #1			;4+ in a row = win boolean set to true
;noWinD	
;	POP		{PC, R0-R11}


;2D Array Load
;R1 = x
;R2 = y
arrLoad
	PUSH	{LR, R4-R8}
	MUL		R3, R2, R6				;<row*row size>
	ADD		R3, R3, R1				;(<row> × <row size>) + <col> 
	LDR		R0, [R9, R3, LSL #2]	
	POP		{PC, R4-R8}
	
	
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
put	
	PUSH {R1}
	LDR	R1, =U0LSR		; R1 -> U0LSR (Line Status Register)
	LDRB	R1, [R1]		; wait until transmit
	ANDS	R1, R1, #0x20		; holding register
	BEQ	put			; empty
	LDR	R1, =U0THR		; R1 -> U0THR
	STRB	R0, [R1]		; output charcter
put0	LDR	R1, =U0LSR		; R1 -> U0LSR
	LDRB	R1, [R1]		; wait until
	ANDS	R1, R1, #0x40		; transmitter
	BEQ	put0			; empty (data flushed)
	POP	{R1}
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
; hint! put the strings used by your program here ...
;

str_go		DCB	"Let's play Connect4!!", 0

str_topNums	DCB 0xA, 0xD, 0xA, 0xD, "  1 2 3 4 5 6 7", 0xA, 0xD, 0

str_newLine	DCB 0xA, 0xD, 0

str_red		DCB	"RED: choose a column for your next move (1-7, q to restart): ", 0

str_yellow	DCB	"YELLOW: choose a column for your next move (1-7, q to restart): ", 0

str_redW	DCB	"RED WINS!", 0

str_yellowW	DCB	"YELLOW WINS!", 0

	END
