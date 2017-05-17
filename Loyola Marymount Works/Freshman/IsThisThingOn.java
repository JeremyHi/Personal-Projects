public class IsThisThingOn {
	public static void main(String[] args) {
		String combined = "";
		for (int o = 0; o < args.length; o++) {
			combined += args[o];
		}
		System.out.println(countTheVowels(combined));
	}

	public static int countTheVowels(String combined) {
		int count = 0;
		char[] vowels = {'a', 'e', 'i', 'o', 'u'};
		for(int i = 0; i < combined.length(); i++) {
			for(int p = 0; p < vowels.length; p++) {
				if(combined.charAt(i) == vowels[p]) {
					count++;
				}
			}
		}
		return count;
	}
}