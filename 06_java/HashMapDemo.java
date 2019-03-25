import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapDemo {
  public static void main(String[] args) {
    String [] arg = {"x","d","o","k","o","l","a","o","k","o","l","o","k","o","l","a"};
    
	HashMap<String, Integer> m = new HashMap<String, Integer>();
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
