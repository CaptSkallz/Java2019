import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TreeMapDemo1<K,V,W>{
  HashMap<K,W> kompozicia(HashMap<K,V> f, HashMap<V,W> g){
	HashMap<K,W> res = new HashMap<K, W>();
	for(
	  Iterator<Map.Entry<K,V>> it = f.entrySet().iterator(); it.hasNext(); ) {
	    Map.Entry<K,V> item = it.next(); 	
		res.put(item.getKey(),g.get(item.getValue()));
	}
	return res;  
  }
  HashMap<K,V> prienik(HashMap<K,V> f, HashMap<K,V> g) {
	HashMap<K,V> res = new HashMap<K, V>();
	for(
	  Iterator<Map.Entry<K,V>> it = f.entrySet().iterator(); it.hasNext(); ) {
	    Map.Entry<K,V> item = it.next(); 	
	    if (item.getValue().equals( g.get(item.getKey())))
			res.put(item.getKey(),item.getValue());
	}
	return res;
  }  
}
