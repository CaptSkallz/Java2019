/**
 * @author DasaK
 */

import java.util.HashMap;
import java.util.Random;

public class Palindrom {

	public static boolean jePalindrom(String vstup) { 
		StringBuilder sb = new StringBuilder(vstup);
		//System.out.println(sb.toString() + " " + sb.reverse().toString());
		return (sb.toString()).equals(sb.reverse().toString());
		//return false;
	}
	public static int najdlhsi(String vstup) {
		int max = 0;
		for (int i = 0; i < vstup.length(); i++) {
			for (int j = i; j < vstup.length(); j++) {
				String pom = vstup.substring(i, j + 1);
				if (jePalindrom(pom)) {
					int dlzka = pom.length();
					max = (dlzka > max) ? dlzka : max;
				}
			}
		}
		return max;
		//return -1; 
	}
	public static int minPalindrom(String vstup) {
		if (jePalindrom(vstup)) return vstup.length();
		int min = Integer.MAX_VALUE;
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int dlzka = vstup.length();
		for (int i = 0; i < vstup.length(); i++) {
			sb1.append(vstup.charAt(i));
			sb2.append(vstup.charAt(vstup.length() - 1 - i));
			dlzka++;
			if (jePalindrom(vstup + sb1.reverse()) || jePalindrom(sb2 + vstup)) {
				min = (dlzka < min) ? dlzka : min;
			}
			sb1.reverse();
			if (min != Integer.MAX_VALUE) break;
		}
		return min;
		//return -1; 
	}
//	public static int minPalindrom(String vstup) {
//		if (jePalindrom(vstup)) return vstup.length();
//		int min = Integer.MAX_VALUE;
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < vstup.length(); i++) {
//			sb.append(vstup.charAt(i));
//			String pom = vstup + sb.reverse();
//			sb.reverse();
//			if (jePalindrom(pom)) {
//				min = (pom.length() < min) ? pom.length() : min;
//			}
//		}
//		sb = new StringBuilder("");
//		for (int i = vstup.length() - 1; i >= 0; i--) {
//			sb.append(vstup.charAt(i));
//			String pom = sb + vstup;
//			if (jePalindrom(pom)) {
//				min = (pom.length() < min) ? pom.length() : min;
//			}
//		}
//		return min;
//		//return -1; 
//	}
	public static void main(String[] args) {
		System.out.println("jePalindrom(\"aba\")=" + jePalindrom("aba"));
		System.out.println("jePalindrom(\"abaa\")=" + jePalindrom("abaa"));
		System.out.println("jePalindrom(\"abaaba\")=" + jePalindrom("abaaba"));
		//--
		System.out.println("najdlhsi(\"aba\")=" + najdlhsi("aba"));
		System.out.println("najdlhsi(\"abaa\")=" + najdlhsi("abaa"));
		System.out.println("najdlhsi(\"abc\")=" + najdlhsi("abc"));
		//--
		System.out.println("minPalindrom(\"aba\")=" + minPalindrom("aba"));
		System.out.println("minPalindrom(\"abaa\")=" + minPalindrom("abaa"));
		System.out.println("minPalindrom(\"abc\")=" + minPalindrom("abc"));
		System.out.println("minPalindrom(\"abca\")=" + minPalindrom("abca"));
		System.out.println("minPalindrom(\"aaaab\")=" + minPalindrom("aaaab"));
		System.out.println("minPalindrom(\"aaaba\")=" + minPalindrom("aaaba"));
		System.out.println("minPalindrom(\"aabaa\")=" + minPalindrom("aabaa"));
		System.out.println("minPalindrom(\"100010101011100\")=" + minPalindrom("100010101011100"));
		System.out.println("minPalindrom(\"10111100000111100\")=" + minPalindrom("10111100000111100"));
	}
}
