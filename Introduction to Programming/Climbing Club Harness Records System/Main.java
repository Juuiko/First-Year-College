import java.util.Scanner;

/* SELF ASSESSMENT

Harness Class: Member variables (8 marks) 8
All my data members are declared, private and the ones that don't change are marked as private. I also have a constant for the maximum number of uses of a harness.
Comment: I use a constant for the max number of uses before check, all data member variables are private with correct setters and getters used.

Harness Class: Harness constructor 1 & constructor 2 (6 marks) 6
I initialise all the variables using the parameters given and set the other members to reasonable default values.
Comment: The variables are initialised using the parameters given or are set assuming a brand new harness.

Harness Class: checkHarness method (5 marks) 5
My method takes an instructor's name as a parameter, and if the harness is not on loan sets the instructor member variable to the given parameter value (assuming the instructor's name is not null/empty). It also resets the number of times the harness was used.
Comment: My method takes the correct parameters, it checks for the loan status with an if statement and if it returns false, then the number of uses is set to 0.

Harness Class: isHarnessOnLoan method (2 marks) 2
My method has no parameters and returns the value of the loan status variable.
Comment: Returns the private variable 'onLoan'.

Harness Class: canHarnessBeLoaned method (4 marks) 4
My method has no parameters and returns true if the harness is not on loan and if the number of times it was used is less than the maximum allowed number of times.
Comment: My method has no parameters and returns true if an if-statement returns that it's not on loan and if the number of times used is less than the constant.

Harness Class: loanHarness method (6 marks) 6
My method has a member name as a parameter and it checks if harness can be loaned by using the canHarnessBeLoaned method. If true, it sets the club member value to the parameter value, sets the on loan status to true and increments the number of times used variable.
Comment: My method has the correct parameter, calls 'canHarnessBeLoaned' to check if the harness can be loaned, if true then it sets the loanee parameter to the parsed value, iterates the number of times the harness has been used and sets the loan status to 'true', if the harness cannot be loaned then the method returns 'null' instead. 
 
Harness Class: returnHarness method (5 marks) 5
My method has no parameters, checks if the harness is on loan, and if so, changes its on-loan status to false, and resets the club member value.
Comment: It doesn't check if the harness is on loan since that wouldn't change anything to the values if a off loan harness were to be checked, hence it just sets the harness to off loan values.

Harness Class: toString method (3 marks) 3
My method returns a String representation of all the member variables.
Comment: It returns a String representation of all the member variables with tags showing which is which.

HarnessRecords Class: member variables (3 marks) 3
I declare the member variable as a private collection of Harnesses 
Comment: Member variables are private with the correct setters and getters used for the variables that need to be changed from outside the class.

HarnessRecords Class: HarnessRecords constructor 1 & 2 (9 marks) 9
In the first constructor, I set the member variable to null [1 mark]. In the second constructor, I use the set of characteristics in the list to create Harness objects and add them to the collection. 
Comment: In the first constructor an empty (null) array list is created, the second constructor uses sets of characteristics to create a variety of harnesses easily and put them in the array list.

HarnessRecords Class: isEmpty method (1 marks) 1
I return true if the collection is null/empty and, false otherwise.
Comment: Check the size of the array, if the array size is 0 then the array is empty and 'true' is returned.

HarnessRecords Class: addHarness method (5 marks) 5
My method takes a Harness object as a parameter and adds the harness to the collection.
Comment: Takes a harness object and adds it onto the end of the array list.

HarnessRecords Class: findHarness method (6 marks) 6
My method takes a make and model number as parameters. It checks if a harness with such properties exists and if it does it returns harness object, otherwise returns null.
Comment: My method takes in the correct parameters, uses a for loop to check each harness in the array, if it cycles through the entire array without finding a matching harness it will return 'null' otherwise it'll return the harness object.

HarnessRecords Class: checkHarness method (6 marks) 6
must take instructor name, make and model number as parameters and return a Harness. If a harness with the make and model number exists by using the findHarness method and is not on loan, the Harness method checkHarness is called with the instructor name as a parameter and the updated Harness object is returned. If the harness is not available returns null.
Comment: My method has the correct parameters and returns, using the previous method it checks for whether the harness is in the array, if it is then sets the number of uses to 0 and sets the last checked instructor name to the parsed one, if the harness doesn't exist then 'null' is returned.

HarnessRecords Class: loanHarness method (7 marks) 7
My method takes a club member name as a parameter and looks for an available harness by calling the method canHarnessBeLoaned be loaned. If an available harness is found it is loaned by using the Harness method loanHarness with the club member as a parameter, returning the harness. If there's no available harness null is returned.
Comment: My method takes the correct parameters, iterates through all the array lists elements to check whether one can be loaned, it checks by checking if 'canHarnessBeLoaned' returns 'true'; then if a harness is found the method will call the 'loanHarness' method parsing in the loanee and getting returned the loaned harness object; if no harness is found then the method returns 'null' instead.

HarnessRecords Class: returnHarness method (7 marks) 7
My method takes a make and model number as parameters. It checks if a harness with those properties exists by using the findHarness method. If the found harness is not null, it returns the harness object by using Harness method returnHarness, otherwise returns null.
Comment: My method takes the correct parameters, checks whether the parsed harness exists in the array list using the 'findHarness' method, if the harness is found then the 'returnHarness' method is called and the returned harness object is returned, if the harness is not found then 'null' is returned instead.

HarnessRecords Class: removeHarness method (8 marks) 8
My method takes a make and model number as parameters and check the collection for a harness with those properties and removes it. It returns the harness object if it is found, otherwise returns null.
Comment: My method calls the 'findHarness' method and removes the resultant harness from the findHarness call, if findHarness results in 'null' then the method also returns 'null'.

GUI (Java main line) (5 marks) 5
My test class has a menu which implements at least the five points specified using the HarnessRecords class methods.
Comment: My test class has clear instructions which allows easy interaction with the HarnessRecords class methods.

Total: 100
*/

public class Main {

	public static void main(String[] args) {
		HarnessRecords harnessRecords = new HarnessRecords();
		Harness obj;
		boolean exit = false;
		String userInput;
		Scanner input = new Scanner ( System.in );
		do {
			System.out.print("Please input one of the 6 commands(add/remove/checked/loan/return/exit): ");
			userInput=input.nextLine();
			if(userInput.equals("exit")) exit=true;
			else if(userInput.equals("add")) {
				System.out.print("Please input the make, model number and the name of the instructor who first checked this new harness: ");
				String makeInput = input.next();
				int modelNumberInput = input.nextInt();
				String instructorInput = input.next();
				obj = new Harness(makeInput, modelNumberInput, instructorInput);
				harnessRecords.addHarness(obj);
				System.out.println(obj.toString() + " was sucessfully added!\n");
			}
			
			else if(userInput.equals("remove")) {
				System.out.print("Please input the make and model number: ");
				String makeInput = input.next();
				int modelNumberInput = input.nextInt();
				obj = harnessRecords.removeHarness(makeInput, modelNumberInput);
				System.out.println(obj.toString() + " was sucessfully removed!\n");
			}
			
			else if(userInput.equals("checked")) {
				System.out.print("Please input the intructor name,  make and model number: ");
				String instructorInput = input.next();
				String makeInput = input.next();
				int modelNumberInput = input.nextInt();
				obj = harnessRecords.checkHarness(makeInput, modelNumberInput, instructorInput);
				System.out.println(obj.toString() + " was sucessfully checked!\n");
			}
			
			else if(userInput.equals("loan")) {
				System.out.print("Please input the loanee name: ");
				String loaneeInput = input.next();
				obj = harnessRecords.loanHarness(loaneeInput);
				System.out.println(obj.toString() + " has been sucessfully loaned off!\n");
			}
			
			else if(userInput.equals("return")) {
				System.out.print("Please input the make and model number: ");
				String makeInput = input.next();
				int modelNumberInput = input.nextInt();
				obj = harnessRecords.returnHarness(makeInput, modelNumberInput);
				System.out.println(obj.toString() + " has been sucessfully returned!\n");
			}
			if(!userInput.equals("exit")) userInput=input.nextLine();
		}
		while(exit==false);
		input.close();
	}
	
}
