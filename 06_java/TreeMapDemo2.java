import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo2 {
  public static void main(String[] args) {
    String [] arg = {"x","d","o","k","o","l","a","o","k","o","l","o","k","o","l","a"};

    
	TreeMap<String, Integer> m = new TreeMap<String, Integer>();
    for (String a : arg) {
      Integer freq = m.get(a);
	  m.put(a, (freq == null) ? 1 : freq + 1);
	}
	System.out.println(m);
	
	for(String key : m.keySet())
		System.out.println("[" + key + "]=" + m.get(key));
	
	for(Iterator<Map.Entry<String, Integer>> it = m.entrySet().iterator(); it.hasNext(); ) {
		Map.Entry<String, Integer> item = it.next();
		System.out.println("[" + item.getKey() + "]=" + item.getValue());
	}
	
  } 
}
