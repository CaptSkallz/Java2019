import java.io.*;
import java.util.*;

public class Vstup {
  public static void main(String[] args) throws IOException {
	  PrintStream fw = new PrintStream(
			    			       new FileOutputStream(new File("vstup.txt")));	
	Random r = new Random();
	for(int i=0; i<123456; i++)
		fw.println(r.nextInt(123456));
    fw.close();
  }
}
