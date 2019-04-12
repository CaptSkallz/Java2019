import java.io.BufferedReader;
import java.io.FileReader;

public class Tokeny {
  public static void main(String[] args) {	
	try {  
      FileReader fr = new FileReader("matica.txt");
      BufferedReader in = new BufferedReader(fr);
      String riadok;
      int  suma = 0;

      while((riadok = in.readLine()) != null) {
    	/*
        StringTokenizer st = new StringTokenizer(riadok, " ;%");
        while (st.hasMoreTokens()) {
          cislo = st.nextToken();
          k = Integer.valueOf(cislo).intValue();
          suma += k;
        }
        */
    	String[] tokeny = riadok.split("[ ;%]");
    	if (tokeny != null)
    	  for(String token:tokeny)
            suma += Integer.valueOf(token).intValue();
      }
      System.out.println("Sucet je: " + suma);
      fr.close();
	} catch (Exception e) {
	      System.out.println("Chyba na vstupe" + e.getMessage());
	}
  }
}
