import java.util.Arrays;

public class Connect4Grid2DArray implements Connect4Grid {
	
	char[][] board = new char[6][7];

	public void emptyGrid() {		
		
		board = new char[7][6];
		for(char[] row:board) Arrays.fill(row, '0');
	}
	
	public String toString() {
		int rowNumbering = 1;
		String boardString = "\n   1 2 3 4 5 6 7\n";
		for(int i=0; i<6; i++) {
			boardString=boardString+rowNumbering+"  ";
			for (int j=0; j<7; j++) {
				boardString=boardString+board[j][i]+" ";
			}
			rowNumbering++;
			boardString = boardString+"\n";
		}
		return boardString;
		
	}

	public boolean isValidColumn(int column) {
		if(column>0&&column<8) return true;
		else return false;
	}

	public boolean isColumnFull(int column) {
		if(board[column-1][0]!='0') {
			return true;
		}
		else return false;
	}

	public void dropPiece(ConnectPlayer player, int column) {	
		int tokenPlacer = 5;
		while(board[column-1][tokenPlacer]!='0'){
			tokenPlacer--;
		}
		board[column-1][tokenPlacer]=player.playerToken();
	}

	public boolean didLastPieceConnect4() {
		char tokenColour = 'Y';
		for(int k=0; k<2; k++) {
		    // horizontal 
		    for (int j = 0; j<6-3 ; j++ ){
		        for (int i = 0; i<5; i++){
		            if (board[i][j]==tokenColour&&board[i][j+1]==tokenColour&&board[i][j+2]==tokenColour&&board[i][j+3]==tokenColour){
		                return true;
		            }           
		        }
		    }
		    // vertical
		    for (int i = 0; i<6-3 ; i++ ){
		        for (int j = 0; j<5; j++){
		            if (board[i][j]==tokenColour&&board[i+1][j]==tokenColour&&board[i+2][j]==tokenColour&&board[i+3][j]==tokenColour){
		                return true;
		            }           
		        }
		    }
		    // diagRight 
		    for (int i=3; i<6; i++){
		        for (int j=0; j<5-3; j++){
		            if (board[i][j]==tokenColour&&board[i-1][j+1]==tokenColour&&board[i-2][j+2]==tokenColour&&board[i-3][j+3]==tokenColour)
		                return true;
		        }
		    }
		    // diagLeft
		    for (int i=3; i<6; i++){
		        for (int j=3; j<5; j++){
		            if (board[i][j]==tokenColour&&board[i-1][j-1]==tokenColour&&board[i-2][j-2]==tokenColour&&board[i-3][j-3]==tokenColour)
		                return true;
		        }
		    }
	    tokenColour='R';
		}
		
		return false;
	}

	public boolean isGridFull() {
		for (int i=0; i<7; i++) {
			if(board[i][0]=='0') return false;
		}
		return true;
	}

}
