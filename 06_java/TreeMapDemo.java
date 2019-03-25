import java.util.*;

public class TreeMapDemo{
  public static TreeMap<String,String> getTreeMap(String[][] p) {
	TreeMap<String,String> tmp = new TreeMap<String, String>();
	for(int i=0; i<p.length; i++)
	  tmp.put(p[i][0], p[i][1]);
	return tmp;  
  }  
  public static void main(String[] args) {
	TreeMap<String,String> europe = getTreeMap(CountryCapitals.EUROPE);
	TreeMap<String,String> america = getTreeMap(CountryCapitals.AMERICA);
	TreeMap<String,String> europePresidents = getTreeMap(CountryCapitals.EUROPE_PRESIDENT);
	System.out.println(europe);
	System.out.println(europe.keySet());
	System.out.println(europe.values());
	america.putAll(europe);
	System.out.println(europe);
	// inverzia zobrazenia hl.mesto->stat
	TreeMap<String,String> inverseEurope = new TreeMap<String, String>();
	for(
	  Iterator<Map.Entry<String,String>> it = europe.entrySet().iterator();
	  it.hasNext(); ) {
	    Map.Entry<String,String> item = it.next(); 	
	    inverseEurope.put(item.getValue(),item.getKey());
	}
	System.out.println(inverseEurope);
	// skladanie zobrazeni hl.mesto->stat x stat->prezident = hl.mesto->prezident
	TreeMap<String,String> sefHlavnehoMesta = new TreeMap<String, String>();
	for(
	  Iterator<Map.Entry<String,String>> it = inverseEurope.entrySet().iterator();
	  it.hasNext(); ) {
	    Map.Entry<String,String> item = it.next();
	    String president = europePresidents.get(item.getValue());
	    if (president != null)
	      sefHlavnehoMesta.put(item.getKey(),president);
	}
	System.out.println(sefHlavnehoMesta);
  }
}
