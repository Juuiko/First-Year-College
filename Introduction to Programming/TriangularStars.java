/* SELF ASSESSMENT

1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

       Mark out of 5: 5       Comment: I used variable properly formatted in LCC with meaningful names.

2. Did I indent the code appropriately?

       Mark out of 5: 5       Comment: The code was indented as required by the 'Coding Standard v3.0' PDF.

3. Did I write the determineStarNumber or determineTriangleNumber function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20: 20      Comment: determineTriangleNumber was written correctly.

4. Did I write the isStarNumber function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20: 20      Comment: isStarNumber was written correctly.

5. Did I calculate and/or check triangle numbers correctly?

      Mark out of 15: 15      Comment: Triangle numbers were calculated correctly.

6. Did I loop through all possibilities in the program using system defined constants to determine when to stop?

      Mark out of 10: 10      Comment: System defined constants were used to determine when to stop looping through possibilities.

7. Does my program compute and print all the correct triangular star numbers?

      Mark out of 20: 20      Comment: My program computes and prints all the correct triangular star numbers.

8. How well did I complete this self-assessment?

       Mark out of 5: 5       Comment: I believe completed this self-assessment as well as I could.

Total Mark out of 100 (Add all the previous marks): 100

*/

public class TriangularStars {

    public static final int LAST_NUMBER_TO_TRY = 10000;
    public static final int FIRST_NUMBER_TO_TRY = 1;

    public static void main(String[] args) {

        int numberStar = FIRST_NUMBER_TO_TRY;
        int numberTriangle = numberStar;
        String numberList = "";
        do
        {
            int triangle = determineTriangleNumber( numberTriangle );
            int star = isStarNumber( numberStar );
            if(star == triangle)
            {
                if (numberList != "")
                {
                    numberList = numberList + ", ";
                }
                numberList = numberList + Integer.toString(star);
                numberStar++;
            }
            else if (star > triangle)
            {
                numberTriangle++;
            }
            else if (star < triangle)
            {
                numberStar++;
            }

        }
        while (numberStar<LAST_NUMBER_TO_TRY || numberTriangle<LAST_NUMBER_TO_TRY);
        System.out.println("The list of all whole values that are"
                + " simultaneously\nstar numbers and triangle numbers with values of N in between "+ FIRST_NUMBER_TO_TRY +
                " and " + LAST_NUMBER_TO_TRY + ":\n" + numberList);

    }

    public static int isStarNumber( int numberStar )
    {
        return (6*numberStar*(numberStar-1)+1);
    }


    public static int determineTriangleNumber( int numberTriangle )
    {
        if (numberTriangle > 1)
        {
            return numberTriangle+determineTriangleNumber(numberTriangle-1);
        }
        else return 1;
    }

}