import java.io.*;

/**
 * citanie matice cisel
 * @author borovan
 *
 */
public class InputLines {

  public static void main(String[] args) {
//	 byte stream -> char stream
	InputStreamReader in = new InputStreamReader(System.in);
//	buffrovanı vstup
	BufferedReader bufIn = new BufferedReader(in);
	try {
//	  èítanie riadku
	  System.out.println ("napis retazec:");	  
	  String inputLine = bufIn.readLine();
	  System.out.println ("echo = " + inputLine);	  
//	  èítanie èísla
	  System.out.println ("napis cislo:");	  
	  String s = bufIn.readLine(); 
	  int tmp = Integer.parseInt(s.trim());
	  System.out.println ("echo = " + tmp);
	  int c, sum = 0;
	  /* */
	  for(int i=0; i<tmp; i++)
  	    for(int j=0; j<tmp; j++)
  	      do {
  	    	c = System.in.read();  
  	        sum += (c == '*')?1:0;
  	      } while (c == '\r' || c == '\n'); 
	  /* */
	  for(;;) {
    	inputLine = bufIn.readLine();
    	if (inputLine == null)
    		break;
    	for(int j=0; j<inputLine.length(); j++)
    	  sum += (inputLine.charAt(j)== '*')?1:0;
	  }
	  
	  System.out.println("pocet *="+sum);
	} catch (IOException e) {
	  System.out.println ("IO exception = " + e);
	}
  }
}
