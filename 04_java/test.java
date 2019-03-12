import java.io.*;
import java.util.*;
import java.util.regex.*;
public class test {

	public static void foo(superclass x) {
	}
	public static void goo(subclass x) {
	}
	public static superclass Hoo() {
		return new superclass();
	}
	public static subclass hoo() {
		return new subclass();
	}
	
	public static void main(String[] args) {
		List<String> ls = new ArrayList<String>(); // 1
		List<Object> lo = ls; // 2 		

		lo.add(new Object()); // 3
		String s = ls.get(0); // 4: Attempts to assign an Object to a String!
		
		foo(new subclass());
		goo(new superclass());
		superclass supcl = hoo();
		subclass subcl = Hoo();

		hoo().too();
		hoo().Too();
		Hoo().too();
		Hoo().Too();
			

		/*
		String input = "3 * 2 == 6 je pravda";
		Scanner s = new Scanner(input);
		s.findInLine("(\\d+) (\\W) (\\d+) (\\W+) (\\d+) je (\\w+)");
		MatchResult result = s.match();
		for (int i=1; i<=result.groupCount(); i++)
		     System.out.println(result.group(i));
		s.close(); 

		try {
	        File f = new File("filename");
	        RandomAccessFile raf = new RandomAccessFile(f, "rw");
	        char ch = raf.readChar();
	        raf.seek(f.length());  		// seek to end
	        raf.writeChars("<"+ch+">");	// append
	        raf.close();
	    } catch (IOException e) {
	    }
		
		
		Pattern p = Pattern.compile("a*b"); 
		Matcher m = p.matcher("aaaaabbb"); 
	     while (m.find ()) {
	         System.out.println ("retazec=" + m.group ());
	         System.out.println ("start=" + m.start () +
	                             ", end=" + m.end ());
	         System.out.println ();
	      }
	      */
	}
}
