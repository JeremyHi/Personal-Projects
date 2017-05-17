import java.util.*;
import java.io.*;

/** 
  This program takes a file from standard input and produces an 
  inventory of its words, with their frequencies, on standard output. 
  (1) When forming words, only letters and digits count- ignore all commas, 
  semi-colons and colons, hyphens, apostrophes, etc. (2) By default, all letters 
  should be converted to upper-case; however, if case SENSITIVITY is desired, 
  then the program should be run with the -s option. (3) Hyphens should be ignored. 
  For example, all of the following will be counted as CATS: CATS, cats, cAtS, cat-s,
  C-at--s-, -c-AT----S. (4) All characters OTHER THAN STATNDARD PUNCTUATION (see 1), 
  and, of course, spaces, are presumed to be delimiters. (5) Frequencies should outputted 
  along with the strings. If CLEAN output (i.e., without frequencies) is desired, 
  then the program should be run with the -c option.
*/
public class WordFrequencies {
  public static void main(String[] args) throws IOException {
    try {
      HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
      Scanner in = new Scanner(System.in);
      String words = "";
      Boolean enteredS = false;

      if (args.length > 0) {
        if (!(args[0].equals("-s") || args[0].equals("-c") || args[0].equals("-cs") || args[0].equals("-cs"))) {
          throw new UnsupportedOperationException("That argument is unsupported by this class.");
        }
      }

      if (args.length > 0) {
        if (args[0].equals("-s") || args[0].equals("-cs") || args[0].equals("-sc")) {
          enteredS = true;
          while (in.hasNext()) {
            words += cleanString(in.next()) + " ";
          }
        }
      }

      if (enteredS) {
        while (in.hasNext()) {
          words += cleanString(in.next()) + " ";
        }
        String[] wordsArray = words.split(" ");
  
        for (int i = 0; i < wordsArray.length; i++) { 
          String word = cleanString(wordsArray[i]);
          Integer count = frequencies.get(word);
          if (count == null){
            count = 1; 
          }
          else {
            count = count + 1;
          }
          frequencies.put(word, count);
        }  
      } else {
        while (in.hasNext()) {
          words += cleanString(in.next()) + " ";
        }
        String[] wordsArray = words.split(" ");
  
        for (int i = 0; i < wordsArray.length; i++) { 
          String word = cleanStringToUpperCase(wordsArray[i]);
          Integer count = frequencies.get(word);
          if (count == null){
            count = 1; 
          }
          else {
            count = count + 1;
          }
          frequencies.put(word, count);
        }
      }
    
      TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(new Comparator<String>() { 
        /**
          Custom comparator that overrides default comparator so output can be 
          truly alphabetical.
        */
        public int compare(String o1, String o2) { 
          return o1.compareToIgnoreCase(o2); 
        } 
      }); 
      treeMap.putAll(frequencies);

      if (args.length > 0) {
        if (args[0].equals("-c") || (args[0].contains("c") && enteredS)) {
          for (String key : treeMap.keySet()) {
            System.out.println(key);
          }
        } else if (enteredS) {
          for (String key : treeMap.keySet()) {
            System.out.println(key + " " + treeMap.get(key));
          }
        }
      } else {
        for (String key : treeMap.keySet()) {
          System.out.println(key + " " + treeMap.get(key));
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.print("Usage: \tjava WordFrequencies [-c][-s][-cs][-sc] < myText.txt\n");
    }
  }

  /** 
    Removes characters from a string that are not letters. 
    Returns a string with all the letters from "word" in UpperCase
  */
  public static String cleanStringToUpperCase(String word) {
    String replacementString = "";
    for (int i = 0; i < word.length(); i++) {
      char characterToReplace = word.charAt(i);
      if (Character.isLetter(characterToReplace)) {
          replacementString = replacementString + characterToReplace;
      }
    }
    return replacementString.toUpperCase();
  }

  /** 
    Removes characters from a string that are not letters.
    return a string with all the letters from "word" 
  */
  public static String cleanString(String word) {
    String replacementString = "";
    for (int i = 0; i < word.length(); i++) {
      char characterToReplace = word.charAt(i);
      if (Character.isLetter(characterToReplace)) {
          replacementString = replacementString + characterToReplace;
      }
    }
    return replacementString;
  }  
}













