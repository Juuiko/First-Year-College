/* SELF ASSESSMENT 
   1. Did I use appropriate CONSTANTS instead of numbers within the code? 
       Mark out of 5:   5
       The only numbers within the code are the switch-cases.
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE? 
       Mark out of 5:   5
       The constant names are made as meaningful and formatted correctly albeit a bit long.
   3. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)? 
       Mark out of 10: 10
       I used easy-to-understand, meaningful names formatted in lowerCamelCase.
   4. Did I indent the code appropriately? 
       Mark out of 10: 10
       I indented the code appropriately.
   5. Did I use an appropriate loop (or loops) to produce the different verses? 
       Mark out of 20: 20
       I used a loop to produce different verses.
   6. Did I use a switch to build up the verses? 
       Mark out of 25: 25
       I used a switch to build up the verses.
   7. Did I avoid duplication of code and of the lines which make up the verses (each line should be referred to in the code only once (or twice))? 
       Mark out of 10: 10
       The lines are only directly refereed to in the code only once.
   8. Does the program produce the correct output? 
       Mark out of 10: 10
       The program produces the correct output.
   9. How well did I complete this self-assessment? 
       Mark out of 5:   5
       I completed this self assessment in the proper way as instructed.
   Total Mark out of 100 (Add all the previous marks): 100
*/

public class TwelveDaysChristmas {

		public static final int STARTING_VERSE_NUMBER = 0;
		public static final int TOTAL_NUMBER_OF_VERSES = 12;
		
		public static void main(String[] args) {
			
			int verseNumber=STARTING_VERSE_NUMBER;
			String total = "";
			String andWord = "and ";
			String newVerseFirstLine = null;
			String lineToAdd = null;
			String verseFirstLinePartOne = "On the ";
			String dayNumber = null;
			String verseFirstLinePartTwo = " day of Christmas\n" + "my true love sent to me:";
			do	{
				switch(verseNumber)
				{
				case 0:
					lineToAdd = "a Partridge in a Pear Tree\n";
					dayNumber = "first";
					break;
				case 1:
					lineToAdd = "2 Turtle Doves\n";
					dayNumber = "second";
					break;
				case 2:
					lineToAdd = "3 French Hens\n";
					dayNumber = "third";
					break;
				case 3:
					lineToAdd = "4 Calling Birds\n";
					dayNumber = "fourth";
					break;
				case 4:
					lineToAdd = "5 Golden Rings\n";	
					dayNumber = "fifth";
					break;
				case 5:
					lineToAdd = "6 Geese a Laying\n";
					dayNumber = "sixth";
					break;
				case 6:
					lineToAdd = "7 Swans a Swimming\n";
					dayNumber = "seventh";
					break;
				case 7:
					lineToAdd = "8 Maids a Milking\n";
					dayNumber = "eighth";
					break;
				case 8:
					lineToAdd = "9 Ladies Dancing\n";
					dayNumber = "ninth";
					break;
				case 9:
					lineToAdd = "10 Lords a Leaping\n";
					dayNumber = "tenth";
					break;
				case 10:
					lineToAdd = "11 Pipers Piping\n";
					dayNumber = "eleventh";
					break;
				case 11:
					lineToAdd = "12 Drummers Drumming\n";
					dayNumber = "twelfth";
					break;
				}
				newVerseFirstLine = verseFirstLinePartOne + dayNumber + verseFirstLinePartTwo;
				System.out.println(newVerseFirstLine);
				total=lineToAdd + total;
				System.out.println(total);
				if (verseNumber == STARTING_VERSE_NUMBER)
				{
					total=andWord + lineToAdd;	
				}
				verseNumber++;
				} while (verseNumber < TOTAL_NUMBER_OF_VERSES);
			}
}