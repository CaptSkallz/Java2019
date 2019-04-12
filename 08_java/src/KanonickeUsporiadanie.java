import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KanonickeUsporiadanie {
	static String[] letters = new String[255];
	static {
			letters['A'] = ".-";		
			letters['B'] = "-...";	
			letters['C'] = "-.-.";	
			letters['D'] = "-..";		
			letters['E'] = ".";		  
			letters['F'] = "..-.";	
			letters['G'] = "--.";		
			letters['H'] = "....";	
			letters['I'] = "..";		
			letters['J'] = ".---";	
			letters['K'] = "-.-";		
			letters['L'] = ".-..";	
			letters['M'] = "--";		
			letters['N'] = "-.";		
			letters['O'] = "---";		
			letters['P'] = ".--.";	
			letters['Q'] = "--.-";	
			letters['R'] = ".-.";		
			letters['S'] = "...";		
			letters['T'] = "-";		  
			letters['U'] = "..-";		
			letters['V'] = "...-";	
			letters['W'] = ".--";		
			letters['X'] = "-..-";	
			letters['Y'] = "-.--";	
			letters['Z'] = "--..";
	}  	
	
	// Riesenie TamaraS.
	public static String[] kanonickeUsporiadane() { 
		Comparator<String> zoradPodlaDlzky = 
				(prveSlovo, druheSlovo) -> prveSlovo.length() - druheSlovo.length(); 
		Comparator<String> zoradPodlaASCII = 
				(prveSlovo, druheSlovo) -> prveSlovo.compareTo(druheSlovo); 

		List<String> povodnePismena = Arrays.asList(letters);
		System.out.println( // fuj, dalsi copy-paster ... !
				IntStream.range(0, povodnePismena.size())
				.filter(ix -> ix >= 'A' && ix <= 'Z')
				.mapToObj(povodnePismena::get)
				.sorted(zoradPodlaDlzky.thenComparing(zoradPodlaASCII))
				.collect(Collectors.toList()) 				 
				);
		return
			IntStream.range(0, povodnePismena.size())
			.filter(ix -> ix >= 'A' && ix <= 'Z')
			.mapToObj(povodnePismena::get)
			.sorted(zoradPodlaDlzky.thenComparing(zoradPodlaASCII))
			.collect(Collectors.toList()) 
			.toArray(new String[26]); 
	}
	public static void main(String[] args) {
		System.out.println(kanonickeUsporiadane());
	}

}
