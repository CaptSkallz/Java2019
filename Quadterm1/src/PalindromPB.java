import java.util.HashMap;
import java.util.Random;

/**
 * @author PB
 */
public class PalindromPB {

	public static boolean jePalindrom(String vstup, int from, int to) {
		int len = to-from;
		for (int i = 0; i < len/2; i++)
			if (vstup.charAt(from+i) != vstup.charAt(from+len-i-1))
				return false;
		return true;	
	}
	public static boolean jePalindrom(String vstup) { 
		return jePalindrom(vstup, 0, vstup.length());
	}
	private static String reverse(String x) {
		return new StringBuffer(x).reverse().toString();
	}
	public static int najdlhsi(String vstup) {
		for(int len = vstup.length(); len >= 0; len--) {
			for (int i = 0; i+len <= vstup.length(); i++) {
				if (jePalindrom(vstup, i, i+len))
					return len;
			}
		}
		return -1; // nikdy nenastane
	}
	public static int minPalindrom(String vstup) {
		for(int i = 0; ; i ++) {
			String prefix = vstup.substring(0, i);
			String suffix = reverse(vstup).substring(0,i);
			String dopredu = suffix + vstup;
			String dozadu = vstup + reverse(prefix);
			if (jePalindrom(dopredu) || jePalindrom(dozadu))
				return i+vstup.length();
		}
	}
	public static void main(String[] args) {
		System.out.println("jePalindrom(\"aba\")=" + jePalindrom("aba"));
		System.out.println("jePalindrom(\"abaa\")=" + jePalindrom("abaa"));
		System.out.println("jePalindrom(\"abaaba\")=" + jePalindrom("abaaba"));

		System.out.println("najdlhsi(\"aba\")=" + najdlhsi("aba"));
		System.out.println("najdlhsi(\"abaa\")=" + najdlhsi("abaa"));
		System.out.println("najdlhsi(\"abc\")=" + najdlhsi("abc"));

		System.out.println("minPalindrom(\"aba\")=" + minPalindrom("aba"));
		System.out.println("minPalindrom(\"abaa\")=" + minPalindrom("abaa"));
		System.out.println("minPalindrom(\"abc\")=" + minPalindrom("abc"));
		System.out.println("minPalindrom(\"abca\")=" + minPalindrom("abca"));
		System.out.println("minPalindrom(\"aaaab\")=" + minPalindrom("aaaab"));
		System.out.println("minPalindrom(\"aaaba\")=" + minPalindrom("aaaba"));
		System.out.println("minPalindrom(\"aabaa\")=" + minPalindrom("aabaa"));		
	}

}
