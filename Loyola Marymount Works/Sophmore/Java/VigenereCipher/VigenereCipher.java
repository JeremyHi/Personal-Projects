import java.util.Scanner;

public class VigenereCipher {
    public static void main(String[] args) {
        Scanner keyInput = new Scanner(System.in);
        Scanner messageInput = new Scanner(System.in);
       
        System.out.println("Enter in your key");
        String key = keyInput.next();
       
        System.out.println("Enter in you passphrase");
        String ori = messageInput.next();
        String enc = encrypt(ori, key);
        
        System.out.println("Your encrypted mesasge: " + enc);
    }
 
    static String encrypt(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
}








