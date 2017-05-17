import java.util.*;
import java.io.*;

/** This program takes a file from standard input and produces an 
inventory of its words, with their frequencies, on standard output. 
(1) When forming words, only letters and digits count- ignore all commas, 
semi-colons and colons, hyphens, apostrophes, etc. (2) By default, all letters 
should be converted to upper-case; however, if case SENSITIVITY is desired, 
then the program should be run with the -s option. (3) Hyphens should be ignored. 
For example, all of the following will be counted as CATS: CATS, cats, cAtS, cat-s,
C-at--s-, -c-AT----S. (4) All characters OTHER THAN STATNDARD PUNCTUATION (see 1), 
and, of course, spaces, are presumed to be delimiters. (5) Frequencies should outputted 
along with the strings. If CLEAN output (i.e., without frequencies) is desired, 
then the program should be run with the -c option. */
public class WordFrequencies {
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
		Scanner in = new Scanner(new FileInputStream(args[0]));
      	while (in.hasNext()) {
        	String word = cleanStringUpperCase(in.next());    
         	Integer count = frequencies.get(word);
         	
         	if (count == null)
         		count = 1; 
         	else {
         		count = count + 1;
         	}        
         	
         	frequencies.put(word, count);
      	}
      	//Print all words and counts
      	for (String key : frequencies.keySet()) {
         	System.out.printf("%-20s%10d\n", key, frequencies.get(key));
      	}
   	}

    /** Removes characters from a string that are not letters.
    return a string with all the letters from "word" in UpperCase */
   	public static String cleanStringUpperCase(String word) {
   		String replacementString = "";
      	for (int i = 0; i < word.length(); i++) {
        	char characterToReplace = word.charAt(i);
         	if (Character.isLetter(characterToReplace)) {
            	replacementString = replacementString + characterToReplace;
         	}
      	}
      	return replacementString.toUpperCase();
   	}

   	/**Removes characters from a string that are not letters.
   	return a string with all the letters from "word" in UpperCase */
   	public static String cleanStringLowerCase(String word) {
   		String replacementString = "";
      	for (int i = 0; i < word.length(); i++) {
        	char characterToReplace = word.charAt(i);
         	if (Character.isLetter(characterToReplace)) {
            	replacementString = replacementString + characterToReplace;
         	}
      	}
      	return replacementString.toLowerCase();
   	}

}



