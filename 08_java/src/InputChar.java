import java.io.*;
/**
 * vstup znaku z konzoly
 * @author PB
 */
public class InputChar {
  public static void main(String[] args) {
	System.out.println("Klepni znak");
	char ch = ' ';
	try {	
	   ch = (char)System.in.read ();
	} catch (IOException e) {
	    System.err.println("chyba");
	    e.printStackTrace();
	}
	System.out.println ("klepol si: " + ch);
	}
}
