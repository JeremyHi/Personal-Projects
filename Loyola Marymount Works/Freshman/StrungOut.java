import java.util.Arrays;

public class StrungOut {

	public static String reverse(String text) {
		String newText = "";
		for (int i = 1; i <= text.length(); i++ ) {
			newText += text.charAt(text.length() - i);
		}
		return newText;
	}
	public static boolean isPalindrome(String text) {
		String reversed = reverse(text);
		if (reversed.equals(text)) {
			return true;
		} else {
			return false;
		}
	}
	public static String evensOnly(String text) {
		String newText = "";
		for (int i = 0; i < text.length(); i += 2 ) {
			newText += text.charAt(i);
		}
		return newText;
	}
	public static String oddsOnly(String text) {
		String newText = "";
		for (int i = 1; i < text.length(); i += 2 ) {
			newText += text.charAt(i);
		}
		return newText;
	}
/*
	public static int substringCount(String string1, String string2) {
		String longWord = "";
		String shortWord = "";
		if (string1.length() >= string2.length()) {
			longWord = string1;
			shortWord = string2;
		} else {
			longWord = string2;
			shortWord = string1;
		}
		int answer = 0;
		int count = 0;
		for (int i = 0; i < shortWord.length(); i+= longWord.indexOf(shortWord)) {
			if (longWord.indexOf(shortWord, i) != -1) {
				answer++;
				count += longWord.indexOf((shortWord + i));
			}

		}
		return answer;
	}
*/
	public static boolean areAnagrams(String string1, String string2) {
		if (string1.length() == string2.length()) {	
			char[] first = string1.toCharArray();
  			char[] second = string2.toCharArray();
  			Arrays.sort(first);
  			Arrays.sort(second);
  			return Arrays.equals(first, second);
		} else {
			return false;
		}

	}





	public static void main(String[] args) {
		if (args[0].length() == 0 ) {
			System.out.println("Usage: java StrungOut <some string>");
		}else {
		System.out.println(" Reverse: " + reverse(args[0]));
		System.out.println(" Palindrome: " + isPalindrome(args[0]));
		System.out.println(" Evens: " + evensOnly(args[0]));
		System.out.println(" Odds: " + oddsOnly(args[0]));
		if (args.length < 2) {
			System.out.println("Need one more argument for substringCount" +
				" and areAanagrams.");
		} else {
			//System.out.println("Substring Count: " +
				//substringCount(args[0], args[1]));
			System.out.println("Anagrams: " + areAnagrams(args[0], args[1]));
		}
	}
}
}