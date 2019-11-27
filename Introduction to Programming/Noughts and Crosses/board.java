public class board {
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
}
