import java.util.*;			// dotiahni package java.util.Arrays

/**
 * Cvicenie 3
 * @author PB
 */
public class LexiSort {
	public static void lexiSort(String[] s) {
	    Arrays.sort(s);		// trochu zjednodusene, ale nahradte si to napr. bublesortom :-)
		for(String elem:s) System.out.print(elem+",");
		System.out.println();
	}
	public static void main(String[] args) {
		lexiSort(args);
	}
}
