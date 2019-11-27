import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{

	char token = 'R';
	Random rand = new Random();
	
	public int play(Connect4Grid2DArray grid) {
		int n;
		//do {
			n = rand.nextInt(6);
			n = n+1;
		//} while(grid.isColumnFull(n)==false);
		return n;
	}
	
	char playerToken(){
		return token;
	}
	
}
