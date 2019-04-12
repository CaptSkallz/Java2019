import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.TreeMap;

public class Histogram {

	public static void main(String[] args) {
		try {
	      Scanner scan = new Scanner(new FileInputStream(new File("lenBody.txt")));
	      TreeMap<Double,Integer> tmap = new TreeMap<Double,Integer>();
    	  while(scan.hasNextDouble()) { 
	    	double val = Math.floor(scan.nextDouble());
	    	if (tmap.get(val)==null) tmap.put(val,1);
	    	else tmap.put(val,1+tmap.get(val));
    	  }
    	  scan.close();
    	  for(double i=0; i<31; i++)
    		  System.out.println(i+"\t"+
    				  "**********************************************************************************".
    				  substring(0,(tmap.get(i)==null)?0:tmap.get(i)));
	    } catch  (Exception e) {
	      System.out.println ("Mismatch exception:" + e );
	    }
	}
} 
