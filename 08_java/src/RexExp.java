import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.*;

public class RexExp {

	public static int matchRexExp(String regex, String text) {
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(text);
	    int matches = 0;
	    while (matcher.find()) {
	        matches++;
	    }
	    return matches;
	}
	public static void main(String[] args) {
		{
			Scanner sc = new Scanner(new StringReader("java"));
			System.out.println(sc.hasNext("java"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("java9"));
			System.out.println(sc.hasNext("java."));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("java9"));
			System.out.println(sc.hasNext("j.*"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("java9"));
			System.out.println(sc.hasNext(".*v.*"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("java9"));
			System.out.println(sc.hasNext("[a-z0-9]+"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("java9"));
			System.out.println(sc.hasNext("[^python]+"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("FF00FF"));
			System.out.println(sc.hasNext("[A-F[0-9]]+"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("KLM"));
			System.out.println(sc.hasNext("[A-Q&&[K-Z]]+"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("KLM"));
			System.out.println(sc.hasNext("[A-Z&&[^F-H]]+"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("158"));
			System.out.println(sc.hasNext("\\d{3}"));
			sc.close();
		}		
		{
			Scanner sc = new Scanner(new StringReader("558"));
			System.out.println(sc.hasNext("(\\d)\\1\\d"));
			sc.close();
		}
		{
			Scanner sc = new Scanner(new StringReader("567567"));
			System.out.println(sc.hasNext("(\\d\\d\\d)\\1"));
			sc.close();
		}		
		
	}
}
