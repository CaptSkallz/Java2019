/**
 * retazce a retazcove metody
 * @author PB
 */
public class Retazce {
	
  public static void main(String[] args) {	
    byte[] bajty = {
         (byte)'E', (byte)'v', (byte)'a'};
    char[] znaky = {
         'M', 'a', 'r', 't', 'i', 'n', 'a'};
       
    String 
     	s1 = new String("cao"),	    
        s2 = new String(s1),
        s3 = new String(bajty),
        s4 = new String(bajty, 1, 2),
        s5 = new String(znaky),
        s6 = new String(znaky, 3, 4); 
    String 
       t1 = new String("ahoj"),
       t2 = new String("ahoi"),
       t3 = new String("AHOJ");
     
       
       t1.compareTo(t2);		// 1
       t2.compareTo(t1);		// -1
       t1.compareToIgnoreCase(t3);// 0
       t1.equals(t3);		// false
       t1.equalsIgnoreCase(t3);	// true

   	   s1.toLowerCase();	// ahoj
   	   s1.toUpperCase();	// AHOJ
   	   s1.concat(s2); 	// ahojahoi	
   	   s1.replace('h', 'H'); 	// aHoj
   	   s1.substring(2); 	// oj
   	   s1.substring(2,3); 	// o
   	   s1.startsWith("ah");	// true
   	   s1.charAt(2);	// o
   	   s1.indexOf('o');	// 2

	s1.trim().toUpperCase().substring(2).indexOf('O');

 	String s = "male a VELKE";
	int  i = s.indexOf('a');			// prvÈ a
	i = s.indexOf('a', i + 1);		// Ôalöie 
	i = s.lastIndexOf('a');			// poslednÈ
    i = s.lastIndexOf('a', i - 1);		// predposlednÈ		
	i = s.lastIndexOf("VEL");		// podreùazec
	
	String int2String = String.valueOf(1234567);
	System.out.println(int2String);
	int String2Integer = Integer.valueOf("1234567");
	System.out.println(String2Integer);	
	System.out.println(Integer.toBinaryString(254));
    System.out.println(Integer.toHexString(254));	
  }
}
 
