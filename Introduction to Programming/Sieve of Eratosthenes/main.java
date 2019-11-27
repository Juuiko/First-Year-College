import java.util.Scanner;

/* SELF ASSESSMENT 
1.  createSequence:

 Did I use the correct method definition?
 Mark out of 5:	 5
 Comment: I used the correct method definition.
 Did I create an array of size n (passed as the parameter) and initialise it?
 Mark out of 5:	 5
 Comment: I created the array outside the method to make easily accessible to other methods, although I initialised it in the method
 Did I return the correct item?
 Mark out of 5:	 5
 Comment: Since the array was created outside the method, the method doesn't return anything, but it does result in the correct item after running.

2.  crossOutMultiples

 Did I use the correct method definition?
 Mark out of 5:	 5
 Comment: I used the correct method definition.
 Did I ensure the parameters are not null and one of them is a valid index into the array
 Mark out of 2:	 2
 Comment: The parameters are by default '0' when creating an int array, this was a desired state as it allowed indexing of non processed numbers.
 Did I loop through the array using the correct multiple?
 Mark out of 5:	 5
 Comment: I looped through the array using the correct multiple.
 Did I cross out correct items in the array that were not already crossed out?
 Mark out of 3:	 3
 Comment: I crossed out items in the array that were not already crossed out.

3.  sieve   

 Did I have the correct function definition?
 Mark out of 5:	 5
 Comment: I had the correct function definition?
 Did I make calls to other methods?
 Mark out of 5:	 5
 Comment: I made calls to other methods?
 Did I return an array with all non-prime numbers are crossed out?
 Mark out of 2:	 2
 Comment: I returned an array with all non-prime numbers are crossed out?

4.  sequenceTostring  

 Did I have the correct function definition?
 Mark out of 5:  5
 Comment: I had the correct function definition?
 Did I ensure the parameter to be used is not null?
 Mark out of 3:  3
 Comment: Since the parameters are either supplied by earlier parts of the code or the user where error catching has already been done.
 Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
 Mark out of 10:10
 Comment: I looped through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?    

5.  nonCrossedOutSubseqToString  

 Did I have the correct function definition
 Mark out of 5: 5
 Comment: I had the correct function definition?        
 Did I ensure the parameter to be used is not null?  
 Mark out of 3: 3
 Comment: Since the parameters are either supplied by earlier parts of the code or the user where error catching has already been done.
 Did I loop through the array updating the String variable with just the non-crossed out numbers? 
 Mark out of 5:	 5
 Comment: I looped through the array updating the String variable with just the non-crossed out numbers? 

6.  main  

 Did I ask  the user for input n and handles input errors?  
 Mark out of 5:	 5
 Comments: I asked the user for 'n' and handled input errors.
 Did I make calls to other methods (at least one)?
 Mark out of 5:	 5
 Comment: I made calls to 3 methods in total.
 Did I print the output as shown in the question?  
 Mark out of 5:	 5
 Comment: I printed the output as shown in the question keeping only the most important lines of the example output.  

7.  Overall

 Is my code indented correctly?
 Mark out of 4:	 4
 Comments: I indented my code correctly.
 Do my variable names make sense?
 Mark out of 4:	 4
 Comments: My variable names made sense.
 Do my variable names, method names and class name follow the Java coding standard
 Mark out of 4:	 4
 Comments: My variable names, method names and class name follow the Java coding standard.

   Total Mark out of 100 (Add all the previous marks): 100
*/ 

public class main {

	public static void main(String[] args) {
		boolean finished = false;
		Scanner userInput = new Scanner ( System.in );
		do {
			System.out.print("Enter int >= 2 or type 'exit': ");
			if (userInput.hasNext("exit")){
				finished = true;
			}
			else if (!userInput.hasNextInt()) {
				userInput.nextLine();
				System.out.println("Invalid input, please try again!");
			}
			else {
				int input = userInput.nextInt();
				if (input<2) {
					System.out.println("Invalid input, please try again!");
				}
				else {
					int[][] sequenceArray = sieve(input);
					sequenceTostring(sequenceArray, input);
					nonCrossedOutSubseqToString(sequenceArray, input);
				}
			}

		} 
		while (finished==false);
		userInput.close();
	}

	public static int[][] sieve ( int input ) {
		sieve obj = new sieve();
		obj.upperBound = input;
		obj.createSequence();
		obj.crossOutMultiples();
		int [][] output = obj.sequence;
		return output;
	}
	
	public static void sequenceTostring ( int[][] sequence, int upperBound ) {
		String sequenceString = "";
		for (int i=0; i < upperBound-1; i++) {
			if(sequence[i][1]==2) {
				sequenceString = sequenceString + sequence[i][0] + ", ";
			}
			
			if(sequence[i][1]==1) {
				sequenceString = sequenceString + "[" + sequence[i][0] + "], ";
			}
		}
		System.out.println(sequenceString);
	}
	
	public static void nonCrossedOutSubseqToString ( int[][] sequence, int upperBound ) {
		String primeString = "";
		for (int i=0; i < upperBound-1; i++) {
			if(sequence[i][1]==2) {
				primeString = primeString + sequence[i][0] + ", ";
			}
		}
		System.out.println(primeString);
	}
	
}