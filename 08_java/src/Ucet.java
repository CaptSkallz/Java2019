import java.io.BufferedReader;
import java.io.FileReader;

public class Ucet {
  public static void main(String[] args) {	
	try {  
      FileReader fr = new FileReader("ucet.txt");
      BufferedReader in = new BufferedReader(fr);
      String riadok;
      double suma = 0;

      while((riadok = in.readLine()) != null) {
    	String[] tokeny = riadok.split("[;]");
    	if (tokeny != null) {
    		suma += Double.parseDouble(tokeny[2]);
            System.out.printf("%7.2f", Double.parseDouble(tokeny[2]));
    	}
        System.out.println();
      }
      System.out.printf("%7.2f", suma);
      fr.close();
	} catch (Exception e) {
	      System.out.println("Chyba na vstupe" + e.getMessage());
	}
  }
}
