import java.util.Scanner;
import javax.swing.JOptionPane;

/* SELF ASSESSMENT

1. Did I use appropriate, easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5:	 5
       Comment: Constants were formatted correctly and were meaningful.

2. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
       Mark out of 5:	 5
       Comment: Outside the mathematical formula all constants had meaningful names, overall every constant was formatted properly.

3. Did I indent the code appropriately?
       Mark out of 5:	 5
       Comment: The code was indented appropriately.

4. Did I define the required function correctly (names, parameters & return type) and invoke them correctly?
      Mark out of 20:	20
       Comment: The required functions were defined and invoked correctly.

5. Did I implement the dayOfTheWeek function correctly and in a manner that can be understood?
      Mark out of 20:	20
       Comment: The dayOfTheWeek function was correctly used and in a manner that can be understood.

6. Did I implement the other functions correctly, giving credit for any code that you take from elsewhere?
      Mark out of 20:	20
       Comment: other functions were implemented correctly and the ValidDate.java file from the course was used as a base. Using namely it's error handling, functions: validDate, daysInMonth and isLeapYear.

7. Did I obtain (and process) the input from the user in the correct format (dd/mm/yyyy), and deal with any invalid input properly?       
     Mark out of 10:	10
       Comment: The input from the user is in the correct format and invalid errors are dealt with properly.

8. Does the program produce the output in the correct format (e.g. Monday, 25th December 2017)?
      Mark out of 10:	10
       Comment: The program produces the output in the correct format.

9. How well did I complete this self-assessment?
       Mark out of 5:	 5
       Comment: I completed this self-assessment fully and in a satisfactory manner.

Total Mark out of 100 (Add all the previous marks):	100

*/

public class DayOfTheWeek {

	public static final int DAYS_IN_APRIL_JUNE_SEPT_NOV = 30;
	public static final int DAYS_IN_FEBRUARY_NORMALLY = 28;
	public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int NUMBER_OF_MONTHS = 12;	
	public static final int JANUARY_MONTH_NUMBER = 1;
	public static final int FEBRUARY_MONTH_NUMBER = 2;

	public static void main(String[] args) {
		try
		{
			String inputScanner = JOptionPane.showInputDialog("Enter date (day/month/year):");
			Scanner scanner = new Scanner( inputScanner );
			scanner.useDelimiter("/");
			int day = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();
			if (validDate(day, month, year))
			{
				JOptionPane.showMessageDialog(null, dayOfTheWeek(year, month, day) + ", " + day + numberEnding(day) + " " 
																							+ monthName(month) + " " + year);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "" + day + "/" + month + "/" + year + " is not a valid date.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		scanner.close();
		}
		catch (NullPointerException exception)
		{
		}
		catch (java.util.NoSuchElementException exception)
		{
			JOptionPane.showMessageDialog(null, "No number entered.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static boolean validDate( int day, int month, int year)
	{
		return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) &&
				(day >= 1) && (day <= daysInMonth( month, year )));
	}
	
	public static int daysInMonth( int month, int year )
	{
		int numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
		switch (month)
		{
		case 2:
			numberOfDaysInMonth = isLeapYear( year ) ? DAYS_IN_FEBRUARY_IN_LEAP_YEAR :
				                                       DAYS_IN_FEBRUARY_NORMALLY;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPT_NOV;
			break;
		default:
			numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
			break;
		}
		return numberOfDaysInMonth;
	}
	
	public static boolean isLeapYear( int year )
	{
		return (((year%4 == 0) && (year%100 != 0)) || (year%400 == 0));
	}
	
	public static String dayOfTheWeek( int year, int month, double day )
	{
			if (month==JANUARY_MONTH_NUMBER||month==FEBRUARY_MONTH_NUMBER)
			{
			year=year-1;
			}
		int c = Integer.parseInt((""+year).substring(0, 2));
		int y = Integer.parseInt((""+year).substring(2, 4));

		double w = (day + Math.floor(2.6 * (((month + 9) % 12) + 1) - 0.2) + y + Math.floor(y/4) + Math.floor(c/4) - 2*c);
		int dayLetters = (int)Math.round(w);
		dayLetters = Math.floorMod(dayLetters, 7);
		String dayOfTheWeek = null;
		switch (dayLetters)
		{
		case 0:
			dayOfTheWeek = "Sunday";
			break;
		case 1:
			dayOfTheWeek = "Monday";
			break;
		case 2:
			dayOfTheWeek = "Tuesday";
			break;
		case 3:
			dayOfTheWeek = "Wednesday";
			break;
		case 4:
			dayOfTheWeek = "Thursday";
			break;
		case 5:
			dayOfTheWeek = "Friday";
			break;
		case 6:
			dayOfTheWeek = "Saturday";
			break;
		}
	return (dayOfTheWeek);
	}
	
	public static String numberEnding( int day )
	{
		String ordinal = null;
		switch (day)
		{
		case 1:
			ordinal = "st";
			break;
		case 2:
			ordinal = "nd";
			break;
		case 3:
			ordinal = "rd";
			break;
		case 21:
			ordinal = "st";
			break;
		case 22:
			ordinal = "nd";
			break;
		case 23:
			ordinal = "rd";
			break;
		case 31:
			ordinal = "st";
			break;
		default:
			ordinal = "th";
			break;
		}
		return ordinal;
	}
	
	public static String monthName( int month )
	{
		String monthLetter = null;
		switch (month)
		{
		case 1:
			monthLetter = "January";
			break;
		case 2:
			monthLetter = "February";
			break;
		case 3:
			monthLetter = "March";
			break;
		case 4:
			monthLetter = "April";
			break;
		case 5:
			monthLetter = "May";
			break;
		case 6:
			monthLetter = "June";
			break;
		case 7:
			monthLetter = "July";
			break;
		case 8:
			monthLetter = "August";
			break;
		case 9:
			monthLetter = "September";
			break;
		case 10:
			monthLetter = "October";
			break;
		case 11:
			monthLetter = "November";
			break;
		case 12:
			monthLetter = "December";
			break;
		}
		return monthLetter;
	}
}

