import java.util.Scanner;

/* SELF ASSESSMENT 

1. ResolveBet

    I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
    Comment: I have defined ResolveBet which takes the userInput and the Wallet object and has a void return type.
    My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
    Comment: My program presents the amount of cash in the wallet and asked for the bet amount.
    My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
    Comment: My program checks for the amount of the bet and compares it to the total, if the bet is too large the program asks the user to input a valid bet amount.
    My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15].
    Comment: My program creates three die objects, rolls them and later in the betting method the total is calculated.
    My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
    Comment: The program first takes the bet type and compares the dice values to see if they match the bet type, winnings are then adjusted by bet type.
    My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
    Comment: Based on the earlier check, a 'win' flag is set which adds the winnings to the wallet, if the flag isn't set, the amount of the bet is deducted from the total.


2. Main

    I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15].
    Comment: I ask the for the money, then create the wallet with that much money.
    My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5].
    Comment: It loops until either "quit"==true or until moneyInWallet==0.
    I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
    Comment: I asked the user to "Enter any of the four bet types (or 'quit'): ".
    My program calls resolveBet for each bet type entered [Mark out of 5: 5].
    Comment: Every bet starts off by calling resolveBet.
    At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5].
    Comment: At the end of the program it outputs the winnings/losses/breaking even/running out of money.


 Total Mark out of 100 (Add all the previous marks): 100
*/

public class Main {
	
	public static void main(String[] args) {
		wallet obj = new wallet();
		int startingMoney;
		boolean exit=false;
		Scanner userInput = new Scanner ( System.in );
		System.out.print("Enter your starting amount of money: ");
		obj.moneyInWallet=userInput.nextInt();
		startingMoney=obj.moneyInWallet;
		while(obj.moneyInWallet>0&&exit==false){
			System.out.print("Enter any of the four bet types (or 'quit'): ");
			obj.betType=userInput.next();
			if(obj.betType.equals("quit")==true) exit=true;
			else obj.resolveBet(userInput);
		}
		if(obj.moneyInWallet==0) System.out.println("No money left, better luck next time! You've lost $"+startingMoney);
		else if(obj.moneyInWallet>startingMoney) System.out.println("Congratz! You made $"+(obj.moneyInWallet-startingMoney)+" of profit!");
		else if(obj.moneyInWallet<startingMoney) System.out.println("Unlucky! You lost $"+(startingMoney-obj.moneyInWallet));
		else System.out.println("Broke even! See you next time!");
	}	
}