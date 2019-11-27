import java.util.Scanner;

/* SELF ASSESSMENT
1. Did I use easy-to-understand meaningful variable names?
    Mark out of 10: 10
    Comment: I used easy-to-understand & meaningful variable names.
2. Did I format the variable names properly (in lowerCamelCase)?
    Mark out of 5:   5
    Comment: I formatted variable names in lower camel case.
3. Did I indent the code appropriately?
    Mark out of 10: 10
    Comment: I indented the code as is required in the coding standard.
4. Did I input the numbers one at a time from the command line?
    Mark out of 10: 10
    Comment: After each question you can enter one number at a time, one at a time.
5. Did I check the input to ensure that invalid input was handled appropriately?
    Mark out of 10: 10
    Comment: After each input it checks whether the number is indeed an int or if it's exit, or an invalid input.
6. Did I use an appropriate while or do-while loop to allow the user to enter numbers until they entered exit/quit?
    Mark out of 20: 20
    Comment: I used a do-while loop to allow the user to keep entering numbers until they enter 'exit'.
7. Did I implement the loop body correctly so that the average and variance were updated and output appropriately?
    Mark out of 30: 30
    Comment: I implemented the loop body so that the average and variance are updated and output appropriately.
8. How well did I complete this self-assessment?
    Mark out of 5:   5
    Comment: I completed this self-assessment as comprehensively as possible.
Total Mark out of 100 (Add all the previous marks): 100
*/

public class IncrementalStats {
			
	public static void main(String[] args) {
			
		int numberCount = 0;
		double input = 0;
		double previousAverage = 0;
		double previousVariance = 0;
		boolean finished = false;
		Scanner inputScanner;
		do {
			inputScanner = new Scanner ( System.in );	
			if (numberCount < 1) {
				System.out.print("Enter a whole number (or type 'exit'): ");
			}
			else {
				System.out.print("\nEnter another whole number (or type 'exit'): ");
			}
				
			
			if (inputScanner.hasNextInt()) {
				input = inputScanner.nextDouble();
				numberCount++;
				double average = previousAverage + (input - previousAverage) / numberCount;
				double variance = ((previousVariance * (numberCount-1)) + 
												 (input - previousAverage) * (input - average)) / numberCount;
				System.out.println("\nSo far the average is " + average + " and the variance is " + variance);
				previousAverage=average;
				previousVariance=variance;
			}
			else if (inputScanner.hasNext("exit")) {
					finished = true;
			}	
			else System.out.println("\nNot a valid whole number.  Try again.\n");
			
		} 
		while (!finished);
			
		if (numberCount > 0) {
			System.out.println("\nGoodbye.");
		}
			
		else System.out.println("\nNo numbers entered, goodbye.");
		inputScanner.close();
	}

}

