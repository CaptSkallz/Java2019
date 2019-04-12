import java.io.*;

/**
 * vstup riadku z konzoly, citame znaky az po znak konca riadku
 * @author PB
 */
public class InputLine {

  public static void main(String[] args) {
	System.out.println("Napis riadok:");   
	String inputLine = "";
	while (true) {
	  try { 
        int tmp = System.in.read ();
        if (tmp == -1) break;
        if (tmp == '\n') break;
        char c = (char) tmp;
        inputLine = inputLine + c;
      } catch (IOException e) {
	    // !!! Skutoène žiaden kód
	 }
   }
   System.out.println ("echo: " + inputLine);
  }
}
