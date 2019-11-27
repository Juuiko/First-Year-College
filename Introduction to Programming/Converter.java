/*  SELF ASSESSMENT
(I noticed after writing the comments that the assignment states the comments are to be added if necessary. Although I do not believe them to be necessary;
	I kept them there in case further understanding was required.)

   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 10:   10
       There are no numbers within the body of the code.

   2. Did I use easy-to-understand, meaningful CONSTANT names?
       Mark out of 5:	  5
       Within the context of the code and formatting I believe them to be easy-to-understand and meaningful.

   3. Did I format the CONSTANT names properly (in UPPERCASE)?
       Mark out of 5:	  5
       The constants are all named in full uppercase with words separated by underscores.

   4. Did I use easy-to-understand meaningful variable names?
       Mark out of 10:	 10
       Within the context of the code the variable names are easy-to-understand and meaningful albeit rather long.

   5. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 10:	 10
       The variables are in lower camel case.

   6. Did I indent the code appropriately?
       Mark out of 10:	 10
       The code was indented to group same steps together to make the code more legible.

   7. Did I read the input correctly from the user using (an) appropriate question(s)?
       Mark out of 10:   10
       The questions were as descriptive as possible while telling the user what to do,
       what would cause an error and what to expect next.

   8. Did I compute the answer correctly for all cases?
       Mark out of 20: 	 20
       The answer was the same as on blackboard with every example given.

   9. Did I output the correct answer in the correct format (as shown in the examples)?
       Mark out of 10:   10
       The answer is in the exact same format as shown on blackboard.

   10.How well did I complete this self-assessment?
       Mark out of 10:   10
       I completed this self-assessment as objectively as possible and to the best of my abilities.

   Total Mark out of 100 (Add all the previous marks):  100
*/

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Converter {

    public static final int OLD_PENNY_TO_NEW_PENNY = 67;
    public static final int OLD_SHILLING_TO_OLD_PENNY = 12;
    public static final int OLD_POUND_TO_OLD_SHILLING = 20;
    public static final int NEW_PENNY_TO_NEW_POUND = 100;

    public static void main(String [] args) {

        String inputPound		= JOptionPane.showInputDialog("Number of old pounds to be converted?\n"+
                "(Shillings and pence to be input later)\n!! Non-integers and blank fields are not accepted !!");
        Scanner poundScanner 	  = new Scanner (inputPound);
        int oldPoundsUserInput 	  = poundScanner.nextInt();
        poundScanner.close();

        String inputShilling	= JOptionPane.showInputDialog("Number of old shillings to be converted?\n"+
                "(Pence to be input later)\n!! Non-integers and blank fields are not accepted !!");
        Scanner shillingScanner   = new Scanner (inputShilling);
        int oldShillingsUserInput = shillingScanner.nextInt();
        shillingScanner.close();

        String inputPennies		= JOptionPane.showInputDialog("Number of old pence to be converted?"+
                "\n!! Non-integers and blank fields are not accepted !!");
        Scanner penceScanner 	  = new Scanner (inputPennies);
        int oldPenniesUserInput   = penceScanner.nextInt();
        penceScanner.close();

        double oldShillingsTotal = (oldPoundsUserInput*OLD_POUND_TO_OLD_SHILLING) + (oldShillingsUserInput);
        double oldPenniesTotal	 = (oldShillingsTotal*OLD_SHILLING_TO_OLD_PENNY)  + (oldPenniesUserInput);
        double newPenniesTotal	 = (oldPenniesTotal*OLD_PENNY_TO_NEW_PENNY);
        double newPoundsTotal	 = (newPenniesTotal/NEW_PENNY_TO_NEW_POUND);

        JOptionPane.showMessageDialog(null, oldPoundsUserInput + " old pound, " +
                oldShillingsUserInput + " old shilling and " +
                oldPenniesUserInput + " old pence = �" + newPoundsTotal);
    }
}
