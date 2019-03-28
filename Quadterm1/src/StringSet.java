/**
 * @author DasaK
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StringSet  extends AbstractStringSet {
	
	Set<String> mnoz;
	int capacity;

	// Konstruktor vytvori prazdnu mnozinu s danou kapacitou.
	public StringSet(int capacity) {
		mnoz = new HashSet<>();
		this.capacity = capacity;
	}

	// Prida prvok do mnoziny. Vrati true ak sa prvok naozaj pridal,
	// vrati false ak nie (lebo taky prvok v mnozine uz bol, alebo je mnozina naplnena - pocet prvkov == kapacita).
    // Pridat mozeme aj null.
	public boolean add(String x) {
		//if (x == null && !mnoz.contains(null)) return mnoz.add(x);
		if (mnoz.size() == capacity) return false;
		return mnoz.add(x);
		//return false;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		return mnoz.contains(s);
	}

	@Override
	public String[] getElements() {
		// TODO Auto-generated method stub
		List<String> out = new ArrayList<>();
		//String[] out = new String[mnoz.size()];
		for (String s: mnoz) {
			out.add(s);
		}
		return out.toArray(new String[0]);
	}

}
