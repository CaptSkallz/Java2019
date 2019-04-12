import java.util.*;
import java.util.regex.*;
import java.io.*;

public class USD2EU {
  public static void main(String[] args) throws IOException {
	Scanner fr = new Scanner(new FileInputStream(new File("priceList.txt")));
    String pat = "([$ ]+)([0-9]*[.0-9]+)(\\sUSD)";
	while (fr.hasNextLine()) {
	  String line = fr.findInLine(".*"+pat+".*");
	  if (line == null)
		System.out.println(fr.nextLine());
	  else {
	    MatchResult result = fr.match();
		double usd_value = Double.parseDouble(result.group(2));
		double euro_value = usd_value*1.3;
		String euro_str = String.format("%6.2f", euro_value);
		line = line.replaceAll(pat, euro_str+" EUR");
		System.out.println(line);
	  }
	} 
	fr.close();
  }
}
