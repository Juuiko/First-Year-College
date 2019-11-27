import java.util.Scanner;

/* SELF ASSESSMENT

1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

       Mark out of 5: 5 Variables were as simple as possible with 'i' being used as an indexing variable in the functions.

2. Did I indent the code appropriately?

       Mark out of 5: 5 I indented the code as of coding standard v3.0.

3. Did I write the initialiseHighScores function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 15: 15 I wrote initialiseHighScores with correct parameters, return types and function bodies.

4. Did I write the printHighScores function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 15: 15 I wrote printHighScores with correct parameters, return types and function bodies.

5. Did I write the higherThan function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 15: 15 I wrote higherThan with correct parameters, return types and function bodies.

6. Did I write the insertScore function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20: 20 I wrote insertScore with correct parameters, return types and function bodies.

7. Did I write the main function body correctly (first asking for the number of scores to be maintained and then repeatedly asking for scores)?

      Mark out of 20: 20 I wrote the function body correctly asking for the scores to be maintained and then repeatedly asking for scores.

8. How well did I complete this self-assessment?

       Mark out of 5: 5 I completed this self assessment as well as I could.

Total Mark out of 100 (Add all the previous marks): 100

*/ 

public class HighScores {
	
	public static void main(String[] args) {
		
		int numberHighScores = 0;
		Scanner userInput = new Scanner(System.in);
		boolean finished = false;	
		System.out.print("Enter the number of scores to maintain: ");
		numberHighScores = userInput.nextInt();
		int[] highScores = initialiseHighScores( numberHighScores );
		System.out.println("Enter scores to add to the high scores (or type 'print' or 'exit'):");
		do
		{
			if(userInput.hasNextInt())
			{
				int latestScore =  userInput.nextInt();
				if(higherThan(highScores, latestScore))
					insertScore(highScores, latestScore);
			}
			else if (userInput.hasNext("exit"))
			{
				finished = true;
				printHighScores(highScores);
			}
			else if (userInput.hasNext("print"))
			{
				printHighScores(highScores); 
				System.out.print("");
				userInput.next();
			}
		}
		while(!finished);
		userInput.close();
	}
	
	public static int[] initialiseHighScores( int numberHighScores)
	{
		return new int[numberHighScores];
	}
	
	
	private static void printHighScores( int[] highScores ) {
		for (int i = 0; i < highScores.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(highScores[i]);
		}
	}
	
	private static boolean higherThan( int[] highScores, int userInput ) {
		for(int i=0; i<highScores.length; i++)
		{
			if(userInput>highScores[i])
				return true;
		}
		return false;
	}
	
	
	public static void insertScore( int[] highScores, int latestScore )
    {
		int positionOfNewScore = 0;
		for(int i = highScores.length-1; i>=0; i--)
		{
			if(highScores[i]>latestScore)
			{
				positionOfNewScore = i+1;
				i = 0;
			}
		}
		for(int i = highScores.length-1; i>=positionOfNewScore+1; i--)
		{

			highScores[i] = highScores[i-1];
		}
		highScores[positionOfNewScore] = latestScore;
    }
}

