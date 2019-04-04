import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Slova {

    public static List<String> slova(int n) {
    	List<String> res = new ArrayList<>();
    	if (n == 0) {
    		res.add("");
    		return res;
    	} else {
    		List<String> kratsie = slova(n-1);
    		for(String s : kratsie) { 
    			res.add(s+"0");
    			res.add(s+"1");
    		}
    		return res;
    	}
    }
    public static List<String> slova(int n, String abeceda) {
    	List<String> res = new ArrayList<>();
    	if (n == 0) {
    		res.add("");
    		return res;
    	} else {
    		List<String> kratsie = slova(n-1, abeceda);
    		for(String s : kratsie) 
    		  for(char ch: abeceda.toCharArray()) {
    			res.add(s+ch);
    		  }
    		return res;
    	}    	
    }
    public static List<String> slovaBezOpakovania(int n, String abeceda) {
    	List<String> res = new ArrayList<>();
    	if (n == 0) {
    		res.add("");
    		return res;
    	} else if (abeceda.length() == 0) {
    		return res;
    	} else {
    		char prvy = abeceda.charAt(0);
    		String zvysok = abeceda.substring(1);
    		res.addAll(slovaBezOpakovania(n,zvysok));
    		List<String> res1 = new ArrayList<>();
    		for(String s : slovaBezOpakovania(n-1, zvysok))
    			res.add(s+prvy);
    		return res;
    	}    	
    }

    public static Stream<String> slovaBezOpakovaniaStream(int n, String abeceda) {
    	if (n == 0) {
    		return Stream.of("");
    	} else if (abeceda.length() == 0) {
    		return Stream.of();
    	} else {
    		char prvy = abeceda.charAt(0);
    		String zvysok = abeceda.substring(1);
    		return Stream.concat(
    				slovaBezOpakovaniaStream(n,zvysok),
    				slovaBezOpakovaniaStream(n-1, zvysok).map(s -> s+prvy));
    	}    	
    }
    public static Stream<String> slovaStream(int n, String abeceda) {
    	if (n == 0) {
    		return Stream.of("");
    	} else {
    		Stream<String> kratsie = slovaStream(n-1, abeceda);
    		//return kratsie.flatMap(s -> Stream.of(s+"0", s+"1"));
    		return kratsie.flatMap(s -> IntStream.range(0, abeceda.length()).mapToObj(index -> s+abeceda.charAt(index))); 
    	}    	
    }
    public static List<String> slovaKratsie(int n, String abeceda) {
    	List<String> res = new ArrayList<>();
    	for(int i = 0; i < n; i++) {
    		res.addAll(slova(i, abeceda));
    	}
    	return res;
    }
    public static Stream<String> slovaKratsieStream(int n, String abeceda) {
    	if (n == 0) {
    		return Stream.of();
    	} else if (n == 1) {
    		return Stream.of("");
    	} else {
    		Stream<String> kratsie = slovaKratsieStream(n-1, abeceda);
    		//return kratsie.flatMap(s -> Stream.of(s+"0", s+"1"));
    		return Stream.concat(
    				Stream.of(""),
    				kratsie.flatMap(s -> IntStream.range(0, abeceda.length())
    					.mapToObj(index -> s+abeceda.charAt(index)))); 
    	}    	
    }

	public static void main(String[] args) {
		System.out.println(slova(4).size() + "=" + slova(4));
		System.out.println(slova(4,"abc").size() + "=" + slova(4,"abc"));
		System.out.println(slovaStream(4,"abc").count() + "=" + slovaStream(4,"abc").collect(Collectors.toList()));
		System.out.println(slovaKratsie(4,"abc").size() + "=" + slovaKratsie(4,"abc"));
		System.out.println(slovaKratsieStream(4,"abc").count() + "=" + slovaKratsieStream(4,"abc").collect(Collectors.toList()));
		System.out.println(slovaBezOpakovania(4,"abcde").size() + "=" + slovaBezOpakovania(4,"abcde"));
		System.out.println(slovaBezOpakovaniaStream(4,"abcde").count() + "=" + slovaBezOpakovaniaStream(4,"abcde").collect(Collectors.toList()));
	}

}
