import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
  public static void main(String[] args) {
	  {
	    //Set<String> s = new HashSet<String>(); // >= Java 1.5, zaviedla generics
	    Set<String> s = new HashSet<>();  // >= Java 1.7, zaviedla diamond operator <>
	    //Set<String> s = new HashSet<~>(); // specialitka IntelliJ
	    
	    for (String a : args)
	      if (!s.add(a))	// nepodarilo sa pridaù
	        System.out.println("opakuje sa: " + a);
	    System.out.println(s.size() + " rozne: " + s);
	    Object[] poleObj = s.toArray();
	    for(Object o:poleObj) System.out.print(o);
	    
	    String[] poleStr = s.toArray(new String[0]);
	    for(String str:poleStr) System.out.print(str);
	}
    {
    	Set<Integer> s = new HashSet<>();
    	s.add(1); s.add(2); s.add(3);   	
    }
    } 
}


