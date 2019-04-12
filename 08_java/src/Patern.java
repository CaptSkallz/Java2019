import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patern {
	public static void main(String[] args) {	
		
		Pattern p = Pattern.compile("a*b"); 
		Matcher m = p.matcher("aaaaabbb"); 
		//boolean b = m.matches(); 

		//boolean b = Pattern.matches("a*b", "aaaaabbb"); 
						// Pattern.compile("a*b"). matcher("aaaaab"); 

		while (m.find ()) {
		   System.out.println ("retazec=" + m.group ());
		   System.out.println ("start=" + m.start () +
			                             ", end=" + m.end ());
		    System.out.println ();
		}


	}
}
