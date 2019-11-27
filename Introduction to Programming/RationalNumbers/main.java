import java.util.Scanner;

/* SELF ASSESSMENT 

Class Rational 
I declared two member variables: numerator and denominator (marks out of 4: 4).
Comment: I did everything that was asked for the Rational Class.

Constructor 1 
My program takes take two integers as parameters (for numerator and denominator) and initialises the member variables with the corresponding values . If the denominator is equal to 0 I throw an exception (marks out of 5: 3).
Comment: Constructor 1 does not throw an exception.

Constructor 2 
My program takes only one integer as parameter (numerator), and set the numerator to this value . I set the denominator to 1 in this case, as the resulting rational number in this case is an integer (marks out of 3: 3).
Comment: I did everything that was asked for Constructor 2.

Add Method 
My program takes only a rational number as a parameter and returns a new rational number which has a numerator and denominator which the addition of the two objects - this and the parameter. My program does not overwrite any of the other two rational numbers (marks out of 8: 8).
Comment: I did everything that was asked for the Add Method.

Subtract Method 
I have implemented this the same as add method, except it implements subtraction (marks out of 8: 8).
Comment: I did everything that was asked for the Subtract Method.

Multiply Method 
I have implemented this the same as add method, except it implements multiplication (marks out of 8: 8).
Comment: I did everything that was asked for the Multiply Method.

Divide Method 
I have implemented this the same as add method, except it implements divide (marks out of 8: 8).
Comment: I did everything that was asked for the Divide Method.

Equals Method 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication between numerators/denominators for the purpose of comparison, as integer division will lead to incorrect results. I return a boolean value ((marks out of 8: 8).
Comment: I did everything that was asked for the Equals Method.

isLessThan 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication as integer division will lead to incorrect results. I return a boolean value (marks out of 8: 8).
Comment: I did everything that was asked for the isLessThan Method.

Simplify Method 
My program returns a rational number but not a new rational number, instead it returns the current reference which is this. It doesn't take any parameters as it works only with the reference object. I first find the greatest common divisor (GCD) between the numerator and denominator, and then obtain the new numerator and denominator by dividing to the GCD (marks out of 8: 8).
Comment: I did everything that was asked for the Simplify Method.

gcd function 
My program returns the greatest common divider of two integers: the numerator and the denominator (marks out of 6: 6).
Comment: I did everything that was asked for the gcd Method.

toString Method 
My program returns a string showing the fraction representation of the number, eg. "1/2". It takes no parameters (marks out of 4: 4).
Comment: I did everything that was asked for the toString Method.

Test Client Class 
My program asks the user for two rational numbers, creates two rational objects using the constructor and passing in the provided values, calls addition, subtraction, multiplication, division, comparison and simplification and prints out the results (marks out of 22: 22).
Comment: I did everything that was asked for the Test Client Class.

Overall: 98/100
*/

public class main {

	public static void main(String[] args) {
		Scanner userInput = new Scanner ( System.in );
		do {
		System.out.print("Input your fractions in this format: 'a b c d' where a/b & c/d: ");
		int a = userInput.nextInt();
		int b = userInput.nextInt();
		int c = userInput.nextInt();
		int d = userInput.nextInt();
		rational rational1 = new rational(a,b);
		rational rational2 = new rational(c,d);
		rational rational3 = new rational(0);
		
		rational3 = rational1.rationalAdd(rational2);
		rational3 = rational3.rationalSimplify();
		System.out.println(rational1.rationalToString()+" + "+
						rational2.rationalToString()+" = "+
							rational3.rationalToString());
		
		rational3 = rational2.rationalSub(rational1);
		rational3 = rational3.rationalSimplify();
		System.out.println(rational1.rationalToString()+" - "+
							rational2.rationalToString()+" = "+
								rational3.rationalToString());
		
		rational3 = rational2.rationalMul(rational1);
		rational3 = rational3.rationalSimplify();
		System.out.println(rational1.rationalToString()+" * "+
							rational2.rationalToString()+" = "+
								rational3.rationalToString());
		
		rational3 = rational2.rationalDiv(rational1);
		rational3 = rational3.rationalSimplify();
		System.out.println(rational1.rationalToString()+" ÷ "+
							rational2.rationalToString()+" = "+
								rational3.rationalToString());
		
		if(rational1.rationalEqu(rational2)) {
			System.out.println(rational1.rationalToString()+" = "+rational2.rationalToString());
		}
		else if(rational1.rationalIsLessThan(rational2)) {
			System.out.println(rational1.rationalToString()+" > "+rational2.rationalToString());
		} 
		else System.out.println(rational1.rationalToString()+" < "+rational2.rationalToString());
	
		System.out.print("Exit?[Y/N]: ");
		}
		while(userInput.next().equals("N"));
		System.out.print("Thank you, see you next time!");
		userInput.close();
	}

}