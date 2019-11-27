import java.util.Scanner;

/* SELF ASSESSMENT 
1.  clearBoard:

 Did I use the correct method definition?
 Mark out of 5: 5
 Comment: I use the correct method definition.
 Did I use loops to set each position to the BLANK character?
 Mark out of 5: 5
 Comment: I use loops to set each position to the ' ' character.

2.  printBoard

 Did I use the correct method definition?
 Mark out of 5: 5
 Comment: I use the correct method definition.
 Did I loop through the array and prints out the board in a way that it looked like a board?
 Mark out of 5: 5
 Comment: I loop through the array and prints out the board in a way that it looked like a board.

3.  canMakeMove

 Did I have the correct function definition and returned the correct item?
 Mark out of 5: 5
 Comment: I have the correct function definition and returned the correct item.
 Did I check if a specified location was BLANK?
 Mark out of 5: 5
 Comment: I check if a specified location was ' '.

4.  makeMove

 Did I have the correct function definition?
 Mark out of 5: 5
 Comment: I have the correct function definition.
 Did I set the  currentPlayerPiece in the specified location?
 Mark out of 5: 5
 Comment: I set the  currentPlayerPiece in the specified location.   

5.  isBoardFull

 Did I have the correct function definition and returned the correct item?
 Mark out of 5: 5
 Comment: I have the correct function definition and returned the correct item.       
 Did I loop through the board to check if there are any BLANK characters?
 Mark out of 5: 5
 Comment: I loop through the board to check if there are any ' ' characters.

6.  winner

 Did I have the correct function definition and returned the winning character
 Mark out of 5: 5
 Comment: I have the correct function definition and returned a winning state with the winning char defined later.    
 Did I identify all possible horizontal, vertical and diagonal winners  
 Mark out of 15: 15
 Comment: I identified all possible horizontal, vertical and diagonal winners.

7.main

 Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
 Mark out of 3: 3
 Comments: I created a board of size 3 by 3 and used the clearBoard method to set all the positions to the BLANK character ('  ').
 Did I loop asking the user for a location until wither the board was full or there was a winner?
 Mark out of 5: 5
 Comments: I loop asking the user for a location until there was a winner.
 Did I call all of the methods above?
 Mark out of 5: 5
 Comments: I call all of the methods above.
 Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
 Mark out of 3: 3
 Comments: I handle incorrect locations provided by the user (only occupied).
 Did I switch the current player piece from cross to nought and vice versa after every valid move?
 Mark out of 3: 3
 Comments: I switched the current player piece from cross to nought and vice versa after every valid move.
 Did I display the winning player piece or a draw at the end of the game?
 Mark out of 3: 3
 Comments: I display the winning player piece or a draw at the end of the game.

8.  Overall

 Is my code indented correctly?
 Mark out of 3: 3
 Comments: My code is indented correctly
 Do my variable names and Constants (at least four of them) make sense?
 Mark out of 3: 3
 Comments: My variable names and Constants make sense.
 Do my variable names, method names and class name follow the Java coding standard
 Mark out of 2: 2
 Comments: My variable names, method names and class name follow the Java coding standard.

   Total Mark out of 100 (Add all the previous marks): 100
*/



public class main {
	
	public static final char PLAYER_ONE = 'O';
	public static final char PLAYER_TWO = 'X';

	public static void Main(String[] args) {
		board obj = new board();
		int userInputC;
		int userInputR;
		char playerTurn = PLAYER_ONE;
		while(winner(obj.board)!=' ') {
		while(isBoardFull(obj.board)==false) {
			obj.printBoard(obj.board);
			System.out.print("Player " + playerTurn + " make your move: ");
			Scanner userInput = new Scanner ( System.in );
			userInputC = userInput.nextInt();
			userInputR = userInput.nextInt();
			userInputC = coordinateConverter(userInputC);
			userInputR = coordinateConverter(userInputR);
			if (canMakeMove(obj.board, userInputR, userInputC)==true) {
				makeMove(obj.board, playerTurn, userInputR, userInputC);
				if (playerTurn==PLAYER_ONE) playerTurn=PLAYER_TWO;
				else playerTurn=PLAYER_ONE;
			}
			else System.out.println("Invalid move, please try again!");
			System.out.print(winner(obj.board));
			userInput.close();
		}
		obj.printBoard(obj.board);
		System.out.println("Tie, clearning the board");
		obj.clearBoard(obj.board);
		}
		System.out.println("The winner is: " + playerTurn);
	}
	
	public static boolean isBoardFull(char[][] board) {
		int k=0;
		for (int j=0; j<5; j+=2) {
			for (int i=0; i<5; i+=2 ) {
				if(board[j][i]==' ') k++;
			}
		}
		if(k==0) return true;
		else return false;
	}
	
	public static boolean canMakeMove(char[][] board, int row, int column) {
		if(board[row][column]==' ') return true;
		else return false;
	}
	
	public static void makeMove(char[][] board, char currentPlayerPiece, int row, int column) {
		if(currentPlayerPiece==PLAYER_ONE) board[row][column]=PLAYER_ONE;
		else board[row][column]=PLAYER_TWO;
	}
	
	public static char winner(char[][] b) {
		if(b[1][1]==b[1][2]&&b[1][2]==b[1][3]&&b[1][1]==b[1][3]&&b[1][1]!=' '||
		   b[2][1]==b[2][2]&&b[2][2]==b[2][3]&&b[2][1]==b[2][3]&&b[2][1]!=' '||
		   b[3][1]==b[3][2]&&b[3][2]==b[3][3]&&b[3][1]==b[3][3]&&b[3][1]!=' '||
		   
		   b[1][1]==b[2][1]&&b[2][1]==b[3][1]&&b[1][1]==b[3][1]&&b[1][1]!=' '||
		   b[1][2]==b[2][2]&&b[2][2]==b[3][2]&&b[1][2]==b[3][2]&&b[1][2]!=' '||
		   b[1][3]==b[2][3]&&b[2][3]==b[3][3]&&b[1][3]==b[3][3]&&b[1][3]!=' '||
		   
		   b[1][1]==b[2][2]&&b[2][2]==b[3][3]&&b[1][1]==b[3][3]&&b[1][1]!=' '||
		   b[3][1]==b[2][2]&&b[2][2]==b[1][3]&&b[1][3]==b[3][1]&&b[3][1]!=' ') {
			return '1';
		}
		else return ' ' ;
	}
	
	public static int coordinateConverter(int input){
		int output=0;
		if(input==1) output=0;
		else if (input==2) output=2;
		else if (input==3) output=4;
		return output;
	}
}

/*public class board {
	char[][] board = {{' ','•',' ','•',' '}, 
					  {'•','•','•','•','•'},
					  {' ','•',' ','•',' '}, 
					  {'•','•','•','•','•'}, 
					  {' ','•',' ','•',' '},};	
	
	public void clearBoard(char[][] board) {
		for (int j=0; j<5; j+=2) {
			for (int i=0; i<5; i+=2 ) {
				board[j][i]=' ';
			}
		}
	}
	
	public void printBoard(char[][] board) {
		System.out.println();
		for (int j=0; j<5; j++) {
			for (int i=0; i<5; i++ ) {
				System.out.print(board[j][i]);
			}
			System.out.println();
		}
	}	
}*/
