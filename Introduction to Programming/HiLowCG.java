import java.util.Random;
import java.util.Scanner;

/* SELF ASSESSMENT 
1. Did I use appropriate CONSTANTS instead of numbers within the code? 
    Mark out of 5:
    Comment: 
2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE? 
    Mark out of 5:
    Comment: 
3. Did I use easy-to-understand meaningful variable names? 
    Mark out of 10:
    Comment:  
4. Did I format the variable names properly (in lowerCamelCase)? 
    Mark out of 5:
    Comment:  
5. Did I indent the code appropriately? 
    Mark out of 10:
    Comment:  
6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose? 
    Mark out of 20: 
    Comment:  
7. Did I check the input to ensure that invalid input was handled appropriately? 
    Mark out of 10: 
    Comment:  
8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)? 
    Mark out of 10: 
    Comment:  
9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King? 
    Mark out of 10: 
    Comment:  
10. Did I report whether the user won or lost the game before the program finished? 
    Mark out of 10:
    Comment:  
11. How well did I complete this self-assessment? 
    Mark out of 5:
    Comment:  
Total Mark out of 100 (Add all the previous marks): 
*/

public class HiLowCG {
		
	public static void main(String[] args) {
		int cardNumber = 0;
		Scanner inputScanner;
		Random  generator = new Random();
		boolean finished = false;
		int oldCard = generator.nextInt(12) + 1;
		if (oldCard == 1)
			{
			System.out.println("The first card is an Ace");
			}
			else if (oldCard == 11)
			{
			System.out.println("The first card is a Jack");
			}
			else if (oldCard == 12)
			{
			System.out.println("The first card is a Queen");
			}
			else if (oldCard == 13)
			{
			System.out.println("The first card is a King");
			} 
		else
		System.out.println("The first card is a " + oldCard);
		do {
			int card = generator.nextInt(12) + 1;
			if(cardNumber==3)
			{
			finished=true;
			}
			cardNumber++;
			System.out.print("Do you think the next card will be higher, lower or equal? ");
			inputScanner = new Scanner ( System.in );
			if (inputScanner.hasNext("higher")&card>oldCard) 
				{
				}
				else if (inputScanner.hasNext("higher")&card<oldCard) 
				{
				finished=true;
				}
				else if (inputScanner.hasNext("lower")&card<oldCard)
				{
				}
				else if (inputScanner.hasNext("lower")&card>oldCard)
				{
				finished=true;
				}
			else finished=true;
			if (card == 1)
				{
				System.out.println("The card is an Ace");
				}
				else if (card == 11)
				{
				System.out.println("The card is a Jack");
				}
				else if (card == 12)
				{
				System.out.println("The card is a Queen");
				}
				else if (card == 13)
				{
				System.out.println("The card is a King");
				} 
			else
			System.out.println("The card is a " + card);
			card=oldCard;

		} 
		while (!finished);
		if (cardNumber==4)
			{
			System.out.println("Congratulations. You got them all correct.");
			}
			else if (!inputScanner.hasNext("lower")||(inputScanner.hasNext("higher")||(inputScanner.hasNext("equal"))))
			{
			System.out.println("Invalid input, restart program please.");
			}
		else
		{
			System.out.println("You lost on try number: " + cardNumber);
		}
		inputScanner.close();
	}
}