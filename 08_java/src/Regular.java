import java.io.*;		// match
import java.util.Scanner;

public class Regular {
	  public static void main(String[] args) throws IOException {
			Scanner sc = new Scanner(new FileReader(new File("d.txt")));
		    sc.useDelimiter("[\r\n]");
			for(;sc.hasNextLine();sc.nextLine()) {
			  String pat;
			  if (sc.hasNext(pat="[A-Z][a-zA-Z]*")) 
				  System.out.println("meno: "+sc.next(pat));
			  else if (sc.hasNext(pat="\\d{3}\\s\\d{2}"))
				  System.out.println("psc: "+sc.next(pat));
			  else if (sc.hasNext(pat="09\\d{2}\\s\\d{3}\\s\\d{3}"))
				  System.out.println("mobil: "+sc.next(pat));
			  else if (sc.hasNext(pat="[0]\\d+[/-]\\d+"))
				  System.out.println("pevna: "+sc.next(pat));
			  else if (sc.hasNext(pat="(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])"))
				  System.out.println("dat.nar.: "+sc.next(pat));
			  else if (sc.hasNext(pat="(muz|zena)"))
				  System.out.println("sex: "+sc.next(pat));
			  else if (sc.hasNext(pat="[a-zA-Z0-9_.]+[@][a-zA-Z0-9_.]+"))
				System.out.println("mail: "+sc.next(pat));
			  else 
				 break;
			}
			sc.close();
		  }
		}
