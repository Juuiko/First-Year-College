import java.util.Scanner;

public class eTest1 {

	public static void main (String[] args) {
		boolean finished = false;
		while (finished == false){
			Scanner input = new Scanner ( System.in );
			System.out.print("Please enter a positive whole number (or type 'ext'): ");
			if(input.hasNextInt()){
				int userInput = input.nextInt();
				int factorialCounter = 1;
				int primeNumberChecker = 0;
				System.out.print("The factors of " + userInput + " are ");
				while (factorialCounter<=userInput){
						int remainder = userInput%factorialCounter;
						if (remainder == 0){
							System.out.print(factorialCounter+ ", ");
							primeNumberChecker++;
						}
						factorialCounter++;
				}
				if (primeNumberChecker==2|userInput==2){
					System.out.println("\n"+ userInput + " is a prime number.\n");
				}
				else{
					System.out.println("\n"+ userInput + " is not a prime number.\n");
				}
			}
			else if (input.hasNext("exit")){
				finished = true;
				System.out.print("Have a good day!");
			}
			else {
				System.out.println("Invalid input, please try again!\n");
			}
		}
	}
}
