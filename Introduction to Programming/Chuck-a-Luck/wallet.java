import java.util.Scanner;

public class wallet {
	
	int moneyInWallet = 0;
	String betType;
	int betAmount = 0;
	boolean winner=false;
	int diceTotal=0;
	
	public void resolveBet(Scanner userInput){
		System.out.print("You have $" + moneyInWallet + " in your wallet, how much would you like to bet? ");
		betAmount=userInput.nextInt();
		while(betAmount>moneyInWallet) {
			System.out.print("You don't have enough in your wallet to bet that much, try again! ");
			betAmount=userInput.nextInt();
		}
		
		dice die1 = new dice(); dice die2 = new dice(); dice die3 = new dice();
		diceTotal=die1.roll+die2.roll+die3.roll;
		System.out.print("  All three dice: "+die1.roll+" "+die2.roll+" "+die3.roll+" (Total="+diceTotal+")");
		winner=false;
		if(betType.equals("Triple")==true) {
			if(die1.roll==die2.roll&&die2.roll==die3.roll&&die1.roll==die3.roll) {
				if(die1.roll>1&&die1.roll<6) {
					moneyInWallet=moneyInWallet+(29*betAmount);
					winner=true;
				}
			}
		}
		else if(betType.equals("Field")&&(diceTotal<8||diceTotal>12)) {
			winner=true;
		}
		else if(betType.equals("High")&&diceTotal>10) {
			if(die1.roll==die2.roll&&die2.roll==die3.roll&&die1.roll==die3.roll);
			else winner=true;
		}
		else if(betType.equals("Low")&&diceTotal<11){
			if(die1.roll==die2.roll&&die2.roll==die3.roll&&die1.roll==die3.roll);
			else winner=true;
		}
		
		if(winner==true) {
			System.out.print("\n  You won!\n\n");
			moneyInWallet=moneyInWallet+betAmount;
		}
		else {
			moneyInWallet=moneyInWallet-betAmount;
			System.out.print("\n  You lost!\n\n");
		}
	}
	
}
