
public class C4HumanPlayer extends ConnectPlayer {
	char token = 'Y';
	
	public void changeToken(char newToken) {
		token=newToken;
	}
	
	char playerToken(){
		return token;
	}
}
