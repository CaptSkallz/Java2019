import java.io.*;
import java.util.*;

public class Matica {
  public static void main(String[] args) throws IOException {
	  PrintStream fw = new PrintStream(
			    			       new FileOutputStream(new File("Matica.txt")));	
	Random r = new Random();
    int N = r.nextInt(80);			    			     
    fw.println(N);
	for(int i=0; i<N; i++) {
		for(int j=0; j<N; j++) {
			  fw.print((float)(r.nextInt(123))/(r.nextInt(2)+1));
			  fw.print(" ");
		}
	  fw.println();
	}
    fw.close();
  }
}