import java.io.*;
import java.util.*;

/* SELF ASSESSMENT

1. readDictionary
- I have the correct method definition [Mark out of 5: 5]
- Comment: The method correctly returns an array list of Strings and has no parameters.
- My method reads the words from the "words.txt" file. [Mark out of 5: 5]
- Comment: My method reads the the words from "words.txt" using a file reader and a buffered reader.
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5: 5]
- Comment: It returns the contents from "words.txt" in a List<String>.

2. readWordList
- I have the correct method definition [Mark out of 5: 5]
- Comment: My method correctly returns and array list and takes in a String from the scanner.
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:]
- Comment: My method reads the words provided and after every comma adds the new word to the next entry into the input array, returning the final array.

3. isUniqueList
- I have the correct method definition [Mark out of 5: 5]
- Comment: My method correctly returns a boolean and takes an array list of user input strings as parameter.
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5: 5]
- Comment: My method compares each word with every other word using two for-loops.
- Exits the loop when a non-unique word is found. [Mark out of 5: 5]
- Comment: When a non-unique word is found, my method gives a false return straight away.
- Returns true is all the words are unique and false otherwise. [Mark out of 5: 5]
- Comment: If all the loops run to completion, then it means the array list is unique and 'true' is returned, else 'false' is returned.

4. isEnglishWord
- I have the correct method definition [Mark out of 5: 5]
- Comment: My method returns a boolean and takes a String as parameter.
- My method uses the binarySearch method in Arrays library class. [Mark out of 3: 3]
- Comment: My method uses Arrays.binarySearch to search through the dictionary array list (and converts said array list to an array for this comparison).
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2: 2]
- Comment: My method returns 'true' if binarySearch returns a value >= 0, if a value <0 is returned, my method returns 'false'.

5. isDifferentByOne
- I have the correct method definition [Mark out of 5: 5]
- Comment: My method correctly returns a boolean and takes in two String as parameters.
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10: 10]
- Comment: My method compares the length of two String, if they're equal it proceeds to check character by character for difference, if more than 1 difference is found, 'false' is returned, if 0 or 1 differences are found, 'true' is returned.

6. isWordChain
- I have the correct method definition [Mark out of 5: 5]
- Comment: My method correctly returns nothing as it prints the final result in console and only takes in the array list of user input words as a parameter.
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10: 10]
- Comment: My method calls all the previously written methods and prints the appropriate message in console.

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10: 10]
- Comment: All the words were read from "words.txt" using java.io.BufferedReader, java.io.File, java.io.FileReader and java.io.IOException.
- Asks the user for input and calls isWordChain [Mark out of 5: 5]
- Comment: The Main class asks the user for input, if the userInput wasn't blank then calls isWordChain and allows the user to input more regardless of the output, if the userInput was blank the Main finishes.

 Total Mark out of 100 (Add all the previous marks): 100
*/

public class WordLinks {

	static List<String> dictionary = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		dictionary = readDictionary();
		Scanner userInput = new Scanner(System.in);
		String inputString = "empty";
		while(!inputString.equals("")) {
			System.out.println("Enter a comma seperated list of words (or an empty list to quit): \n");
			inputString=userInput.nextLine();
			if(!inputString.equals("")) {
				List<String> inputArray = readWordList(inputString);
				isWordChain(inputArray);
			}
		};
		userInput.close();
	}

	public static List<String> readDictionary() throws IOException{
		List<String> dictionary = new ArrayList<>();

		File file = new File ("C:\\Users\\AlexM\\Documents\\College Work\\CS1010\\WordLinks\\words.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;

		while((line=buffer.readLine())!=null)
        {
			dictionary.add(line);
        }
		buffer.close();

		return dictionary;
	}

	public static List<String> readWordList(String inputString){
		List<String> inputArray = Arrays.asList(inputString.split("\\s*,\\s*"));
		return inputArray;
	}

	public static boolean isUniqueList(List<String> inputArray) {
		for (int i = 0; i < inputArray.size(); i++) {
			for (int j = i+1; j< inputArray.size(); j++) {
				if(inputArray.get(i).equals(inputArray.get(j))) return false;
			}
		}
		return true;
	}

	public static boolean isEnglishWord(String inputWord) {
		int binSearchResult = Arrays.binarySearch(dictionary.toArray(), inputWord);
		if(binSearchResult>=0) return true;
		else return false;
	}

	public static boolean isDifferentByOne(String w1, String w2) {
		char c1, c2; int j=0;
		if(w1.length()==w2.length()) {
			for(int i=0; i<w1.length(); i++) {
				c1 = w1.charAt(i);
				c2 = w2.charAt(i);
				if(c1!=c2) j++;
			}
			if(j<2) return true;
		}
		return false;
	}

	public static void isWordChain(List<String> inputArray) {
		if(isUniqueList(inputArray)==true) {
			boolean forLoopExit=true;
			for(int i = 0; i<inputArray.size()&&forLoopExit==true; i++) {
				forLoopExit=isEnglishWord(inputArray.get(i));
			}
			if(forLoopExit==true) {
				for(int i=0; i<inputArray.size()-1&&forLoopExit==true; i++) {
					forLoopExit=isDifferentByOne(inputArray.get(i), inputArray.get(i+1));
				}
				if(forLoopExit==true) {
					System.out.println("\nValid chain of words from Lewis Carroll's word-links game.\n");
					return;
				}
			}
		}
		System.out.println("\nNot a valid chain of words from Lewis Carroll's word-links game.\n");
	}
}
