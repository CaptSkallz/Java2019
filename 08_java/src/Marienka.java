import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Marienka {
	  public static void main(String[] args) {
		   Adresar a = new Adresar();
		   a.ab = new HashMap<String,Persona>(); 
		   try {
			   Scanner sc = new Scanner(new FileInputStream(new File("marienka.cfg")));
			   sc.useDelimiter("[,\n]");
			   while (sc.hasNextLine()) {
				   Persona p = new Persona();
				   String nick = sc.next().trim();
				   if (nick.length() == 0) break;
				   String firstMiddleLast = sc.next().trim();
				   if (firstMiddleLast.length() == 0) break;
				   String[] names = firstMiddleLast.split(" ");
				   if (names.length == 3) {
					   p.First = names[0];
					   p.Middle = names[1];
					   p.Last = names[2];
				   } else if (names.length == 2) {
					   p.First = names[0];
					   p.Middle = null;
					   p.Last = names[1];
				   } else if (names.length == 1) {
					   p.First = null;
					   p.Middle = null;
					   p.Last = names[0];
				   }
				   String ageStr = sc.next().trim();
				   try {
					   p.age = Integer.parseInt(ageStr);
				   } catch (Exception E) {
					   p.age = 0;
				   }
				   p.SID = sc.next().trim();
				   p.email = sc.next().trim();
				   p.phone = sc.next().trim();
				   p.photo = sc.next().trim();
				   // nacitanie suboru do image
				   p.image = null;
				   System.out.println(p);
				   a.ab.put(nick, p);
			   }
			   sc.close();
	   		} catch (Exception E) {
				System.err.println("Nastala chyba:"+E.getMessage());
			}
		   System.out.println(a.ab.size());
		   try {
			   ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("marienka.ab"));
			   os.writeObject(a);
			   os.close();
		   } catch (Exception E) {
			   System.err.println(E.getMessage());
		   }
	   }
}
