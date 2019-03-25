import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo3 {
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
	for(String state : europe.keySet())
	    inverseEurope.put(europe.get(state),state);
	System.out.println(inverseEurope);
	// skladanie zobrazeni hl.mesto->stat x stat->prezident = hl.mesto->prezident
	TreeMap<String,String> sefHlavnehoMesta = new TreeMap<String, String>();
	for(String capital : inverseEurope.keySet()) {
		String state = inverseEurope.get(capital);
		if (state != null) {
			String president = europePresidents.get(state);
			if (president != null)
				sefHlavnehoMesta.put(capital,president);
		}
	}
	System.out.println(sefHlavnehoMesta);
  }
}
