import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Oddelovac {

	public static void main(String[] args) {	
	    try {
	      Scanner scan_csv = new Scanner(new FileInputStream(new File("body1.csv")));
          double priemery[] = {0,0,0,0,0,0};	      
          int pocet = 0;	      
	      scan_csv.useDelimiter("[;\r\n]");
	      while(scan_csv.hasNextLine()) {
	    	int i = 0;  
	    	/*String meno = */ scan_csv.next();
	    	/*String skupina =*/ scan_csv.next();	
    	    while(scan_csv.hasNextDouble()) 
  	    	  priemery[i++]+=scan_csv.nextDouble();
    	    scan_csv.nextLine(); pocet++;
	      }
	      scan_csv.close();
	      for(int i=0; i<priemery.length; i++)
	    	  System.out.println((i+1)+".priklad ma priemer: "+priemery[i]/pocet);
	    } catch  (Exception e) {
	      System.out.println ("Mismatch exception:" + e );
	    }
	}
} 
