/**
 * @author DasaK
 */
import java.util.ArrayList;
import java.util.List;

public class Intersection extends AbstractStringSet {
	
	AbstractStringSet a;
	AbstractStringSet b;
	
	// Konstruktor, zapamata si ake mnoziny tvoria prienik
	public Intersection(AbstractStringSet a, AbstractStringSet b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		return a.contains(s) && b.contains(s);
	}

	@Override
	public String[] getElements() {
		// TODO Auto-generated method stub
		List<String> out = new ArrayList<>();
		for (String s: a.getElements()) {
			if (b.contains(s)) out.add(s);
		}
		return out.toArray(new String[0]);
		//return null;
	}

}
