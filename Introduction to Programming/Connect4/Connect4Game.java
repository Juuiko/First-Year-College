import java.util.Scanner;

/* SELF ASSESSMENT

Connect4Game class (35 marks) 35
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: My class creates the connect 4 grid object and after asking whether player 2 is an 'AI' or another player, creates both connect 4 players. The user can nearly always exit with "Quit" and gets prompted to quit after every game. If the user decides to play the Grid is initialised (it's created just a little earlier for the final win check), both connect players are created with the second one being left null until the player decided whether it's a player or an AI, I ask where the user wants to drop his piece, I perform checks by calling both methods in the Connect4Grid interface, a check after every move is also performed to check for a win state.

Connect4Grid interface (10 marks) 10
I define all 7 methods within this interface.
Comment: i defined all 7 methods within this interface as laid out in the assignment.

Connect4Grid2DArray class (25 marks) 25
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: My class implements the Connect4Grid interface and all the required methods. (Creates an empty grid of 0s, checks whether the column to drop the token is valid, checks whether that column is full and checks the entire board for both coloured tokens to check for a win state)

ConnectPlayer abstract class (10 marks) 10
My class provides at lest one non-abstract method and at least one abstract method. 
Comment: My class provides two non-abstract methods (play and changeToken) and one abstract method (playerToken).

C4HumanPlayer class (10 marks) 10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: My class extends the ConnectPlayer class and overrides the abstract method playerToken, the main line allows the second player to play.

C4RandomAIPlayer class (10 marks) 10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality. 
Comment: My class extends the ConnectPlayer class and overrides the abstract method playerToken, it provides RNG placement of tokens.

Total Marks out of 100: 100

*/

public class Connect4Game {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		int playerTurn = 1;
		String endInput;
		String input;
		do {
			System.out.print("Play/Quit?: ");
			endInput = userInput.nextLine();
			Connect4Grid2DArray board = new Connect4Grid2DArray();
			if(endInput.equals("Play")) {
				board.emptyGrid();
				ConnectPlayer p1 = new C4HumanPlayer();
				ConnectPlayer p2 = null;
				System.out.print("Is player 2 AI?(Y/N): ");
				input = userInput.nextLine();
				if(input.equals("Y")) {
					p2 = new C4RandomAIPlayer();
					while(board.didLastPieceConnect4()==false&&board.isGridFull()!=true&&!input.equals("Quit")) {
						if (playerTurn==1) {
							System.out.print("Player "+playerTurn+", entre your drop column: ");
							input = userInput.nextLine();
							endInput = input;
								if(!input.equals("Quit")&&
											board.isValidColumn(Integer.parseInt(input))==true&&
												board.isColumnFull(Integer.parseInt(input))==false) {
									board.dropPiece(p1, Integer.parseInt(input));
									playerTurn=2;
								}
						}
						else if (playerTurn==2) {
							board.dropPiece(p2, p2.play(board));
							playerTurn=1;
						}
						System.out.println(board.toString());
					}
				}
				
				else {
					p2 = new C4HumanPlayer();
					p2.changeToken('R');
					while(board.didLastPieceConnect4()==false&&board.isGridFull()!=true&&!input.equals("Quit")) {
						System.out.print("Player "+playerTurn+", entre your drop column: ");
						input = userInput.nextLine();
						if(!input.equals("Quit")&&
								board.isValidColumn(Integer.parseInt(input))==true&&
									board.isColumnFull(Integer.parseInt(input))==false) {
							if(playerTurn==1) {
								board.dropPiece(p1, Integer.parseInt(input));
								playerTurn=2;
							}
							else if(playerTurn==2) {
								board.dropPiece(p2, Integer.parseInt(input));
								playerTurn=1;
							}
						}
						System.out.println(board.toString());
						endInput = input;
					}				
				}
			}
			if(board.didLastPieceConnect4()==true) {
				if(playerTurn==1) System.out.println("Player 2 won!\n");
				else if (playerTurn==2) System.out.println("Player 1 won!\n");
			}
		}while(!endInput.equals("Quit"));
		System.out.println("\nSee you next time!");
		userInput.close();
	}

}
