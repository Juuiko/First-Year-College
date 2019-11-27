import java.util.Scanner;

public class converter {

    public static void main(String[] args) {
        boolean finished = false;
        Scanner userInput = new Scanner ( System.in );
        //userInput.useDelimiter("/|\r");
        do {
            System.out.print("Enter the number of Stones, Pounds and Ounces seperated by enters (or enter quit): ");
            if (userInput.hasNext("quit")){
                finished = true;
            }
            else {
                int stones = userInput.nextInt();
                int pounds = userInput.nextInt();
                int ounces = userInput.nextInt();
                getFormattedWeightString ( stones, pounds, ounces );
                double kilograms = convertToKilograms ( stones, pounds, ounces );
                System.out.print("is equal to " + kilograms + "kg\n\n");
            }

        }
        while(!finished);
        userInput.close();
    }

    public static double convertToKilograms ( int stones, int pounds, int ounces ) {
        double kilograms = 0;
        pounds = pounds + (14*stones);
        ounces = ounces + (16*pounds);
        kilograms = ounces*0.02834952;
        return kilograms;
    }

    public static void getFormattedWeightString ( int stones, int pounds, int ounces ) {
        if (stones == 0) {
        }
        else if (stones == 1) {
            System.out.print(stones + " stone, ");
        }
        else {
            System.out.print(stones + " stones, ");
        }

        if (pounds == 0) {
        }
        else if (pounds == 1) {
            System.out.print(pounds + " pound, ");
        }
        else {
            System.out.print(pounds + " pounds, ");
        }

        if (ounces == 0) {
        }
        else if (ounces == 1) {
            System.out.print(ounces + " ounce ");
        }
        else {
            System.out.print(ounces + " ounces ");
        }
    }

}
