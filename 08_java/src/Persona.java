import java.io.*;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	String First;
	   String Middle;
	   String Last;
	   int age;
	   String SID;	// rodne cislo
	   String email;
	   String phone;
	   String photo;
	   byte[] image;
	   
	   public String toString() {
		   return First+" "+Middle+" "+Last+","+age+","+SID+","+","+email+","+phone+","+photo;
	   }
   };