import java.io.*;
import java.util.*;

public class Formater {
	
  public static void main(String[] args) {
    Formatter formatter  = new Formatter((OutputStream)System.out);
    formatter.format("Text%n");
    boolean a_boolean =  false;
    int     an_int    =  1234567;
    float   a_float   =  983.6f;

    formatter.format("boolean = %9b %n", a_boolean);
	System.out.printf("boolean = %9b %n",   a_boolean);
    formatter.format("int     = %9d %n",   an_int);
	System.out.printf("int     = %9d %n",   an_int);
    formatter.format("float   = %9.3f %n", a_float);
 	System.out.printf("float   = %9.3f %n", a_float);

 	Calendar rightNow = Calendar.getInstance(); 
 	String s1 = String.format("%1$tm %1$te,%1$tY",rightNow); 
    String s2 = String.format("%1$tB %1$te,%1$tY",rightNow); 
    System.out.println(s1);
    System.out.println(s2);
    
 	formatter.flush ();
    formatter.close ();  
  }
}
