import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Scaner {

	public static void main(String[] args) {	
		System.out.println("citam z konzoly");
	    try {
   	      Scanner scanner = new Scanner(System.in);
	      for(;;)
	        if (scanner.hasNextInt())	
	          System.out.println ("Ivstup " + scanner.nextInt() +"\n");
	        else if (scanner.hasNextFloat())	
	          System.out.println ("Fvstup " + scanner.nextFloat () +"\n");
	        else if (scanner.hasNextBoolean())		      
	    	  System.out.println ("Bvstup " + scanner.nextBoolean() +"\n");
	        else
	          break;
	      scanner.close();
	      Scanner scan = new Scanner(new FileInputStream(new File("lenBody.txt")));
	      TreeMap<Double,Integer> tmap = new TreeMap<Double,Integer>();
    	  while(scan.hasNextDouble()) { 
	    	double val = scan.nextDouble();
	    	if (tmap.get(val)==null) tmap.put(val,1);
	    	else tmap.put(val,1+tmap.get(val));
    	  }
    	  scan.close();
    	  for(double i=0; i<18; i+=0.5)
    		  System.out.println(i+"\t"+
    				  "**************************".
    				  substring(0,(tmap.get(i)==null)?0:tmap.get(i)));
    	  
	      Scanner scan_csv = new Scanner(new FileInputStream(new File("body.csv")));
          double priemery[] = {0,0,0,0,0,0,0,0};	      
          int pocet = 0;	      
	      scan_csv.useDelimiter("[;\r\n]");
	      while(scan_csv.hasNextLine()) {
	    	int i = 0;  
    	    while(scan_csv.hasNextDouble()) 
  	    	  priemery[i++]+=scan_csv.nextDouble();
    	    scan_csv.nextLine(); pocet++;
	      }
	      scan_csv.close();
	      for(int i=0; i<priemery.length; i++)
	    	  System.out.println(i+".cvicenie ma priemer: "+priemery[i]/pocet);
	      
    	  
	    	
	    	/*
	      String patternString = "([A-Za-z]+)[;]((\\d+[;])*)";
	      //String patternString = "[A-Z]([a-z]*)[,; ][A-Z]([a-z]*)";
	      //while(scanner.hasNext(patternString)) {
	      {
	    	System.out.println("w+ w+ " + scanner.findInLine(patternString));
	      	MatchResult result = scanner.match();
	        for (int i=1; i<=result.groupCount(); i++)
	          System.out.println(i+"="+result.group(i));
	      }
	      */
	      
	      Scanner s = new Scanner("3 / 2 == 1,5 je pravda");
	      //s.findInLine("(\\d+) (\\W) (\\d+) (\\W+) (\\d+).(\\d+) je (\\w+)");
	      s.findInLine(Pattern.compile("(\\d+) (\\W) (\\d+) (\\W+) (\\d+)[,/.](\\d+) je (\\w+)"));
	      MatchResult result = s.match();
	      for (int i=1; i<=result.groupCount(); i++)
	           System.out.println(result.group(i));

	      s.close(); 
	    } catch  (Exception e) {
	      System.out.println ("Mismatch exception:" + e );
	    }
	}
} 
