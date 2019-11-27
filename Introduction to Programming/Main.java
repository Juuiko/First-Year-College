import java.util.Scanner;

/* SELF ASSESSMENT

1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?

       Mark out of 5:	 5
       Comment: I used easy-to-understand and meaningful variable names which were formatted properly.

2. Did I indent the code appropriately?

       Mark out of 5:	 5
       Comment: I indented the code appropriately.

3. Did I write the createCipher function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20:	20
       Comment: I wrote the createCipher function correctly and invoked it correctly.

4. Did I write the encrypt function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20:	20
       Comment: I wrote the encrypt function correctly and invoked it correctly. 

5. Did I write the decrypt function correctly (parameters, return type and function body) and invoke it correctly?

      Mark out of 20:	20
       Comment: I wrote the decrypt function correctly and invoked it correctly. 

6. Did I write the main function body correctly (repeatedly obtaining a string and encrypting it and then decrypting the encrypted version)?

      Mark out of 25:	25
       Comment: I wrote the main body correctly: repeatedly obtaining a string and encrypting it and then decrypting the encrypted version.

7. How well did I complete this self-assessment?

       Mark out of 5:	 5
       Comment: I completed this self-assessment to the best of my abilites.

Total Mark out of 100 (Add all the previous marks):	100

*/ 

public class Main {

	public static final int COUNTER_SETUP = 0;
	public static final int ALPHABET_ARRAY = 0;
	public static final int CIPHERED_ARRAY = 1;
	
	public static void main(String[] args) {
		char[][] cipherKey = createCipher();
		boolean finished = false;
		Scanner userInput = new Scanner ( System.in );
		System.out.println("Input the character to be ciphered, or type 'exit' (only lower case a-z letters and spaces are allowed):");
		do {
			if (userInput.hasNext("exit")){
				finished = true;
			}
			else {
				String input = userInput.nextLine();
				String lowercaseInput = input.toLowerCase();
				char[] characterArray = lowercaseInput.toCharArray(); 
				char[] encrypted = encrypt(characterArray, cipherKey);
				System.out.println(encrypted);
				char[] decrypt = decrypt(encrypted, cipherKey);
				System.out.println(decrypt);
				System.out.println();
			}
		} 
		while (finished==false);
		userInput.close();
		
	}

	public static char[][] createCipher ( ) {
		char[][] cipherKey = {{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', +
										'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ' },
							  { 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', +
										'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', 'a' }};
		return cipherKey;
	}
	
	public static char[] encrypt ( char[] characterArray, char[][] cipherkey ) {
		int charArrayLength = characterArray.length;
		for (int i=COUNTER_SETUP; i < charArrayLength; i++) {
			int j=ALPHABET_ARRAY;
			while(characterArray[i]!=cipherkey[ALPHABET_ARRAY][j]) {
				j++;
			}
			characterArray[i] = cipherkey[CIPHERED_ARRAY][j];
		}
		return characterArray;
	}
	
	public static char[] decrypt ( char[] encrypted, char[][] cipherkey ) {
		int charArrayLength = encrypted.length;
		for (int i=COUNTER_SETUP; i < charArrayLength; i++) {
			int j=ALPHABET_ARRAY;
			while(encrypted[i]!=cipherkey[CIPHERED_ARRAY][j]) {
				j++;
			}
			encrypted[i] = cipherkey[ALPHABET_ARRAY][j];
		}
		return encrypted;
		
	}
}
