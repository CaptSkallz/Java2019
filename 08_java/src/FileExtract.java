import java.io.*;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class FileExtract {
	 public static void main(String[] args) throws IOException {
	    Scanner scan = new Scanner(new FileInputStream(new File("bodyUplne.txt")));
	    while(scan.hasNextLine()) {
    	  scan.findInLine(Pattern.compile(".*([(][a-zA-Z_0-9. @]+[)]).*[ ]([\\d.]+)"));
		  MatchResult result = scan.match();
		  for (int i=1; i<=result.groupCount(); i++)
		    System.out.print(result.group(i));
		  scan.nextLine(); System.out.println();
	    }
	    scan.close();
	 }
}


