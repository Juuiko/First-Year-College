/* SELF ASSESSMENT 
   1. Did I use easy-to-understand meaningful variable names? 
       Mark out of 10:  10
   2. Did I format the variable names properly (in lowerCamelCase)? 
       Mark out of 10:  10
   3. Did I indent the code appropriately? 
       Mark out of 10:  10
   4. Did I read the input correctly from the user using appropriate questions? 
       Mark out of 20:  20
   5. Did I use an appropriate (i.e. not more than necessary) series of if statements? 
       Mark out of 30:  30
   6. Did I output the correct answer for each possibility in an easy to read format? 
       Mark out of 15:  15
   7. How well did I complete this self-assessment? 
       Mark out of 5:    5
   Total Mark out of 100 (Add all the previous marks): 100
*/


import javax.swing.JOptionPane;

public class Umbrella {

    public static void main(String[] args)
    {
        int futureRainAnswer = JOptionPane.showConfirmDialog(null, "Is it raining/does it look as if it's going to start?");
        boolean takeUmbrella = (futureRainAnswer == JOptionPane.YES_OPTION);
        if (takeUmbrella)
        {
            int currentRainAnswer = JOptionPane.showConfirmDialog(null, "Is it raining currently?");
            boolean openUmbrella = (currentRainAnswer == JOptionPane.YES_OPTION);

            if (openUmbrella)
                JOptionPane.showMessageDialog(null,"Take your umbrella and open it.");

            else
                JOptionPane.showMessageDialog(null, "Take your umbrella but keep it closed for now.");
        }
        else
            JOptionPane.showMessageDialog(null, "You don't need to take your umbrella.");
    }
}