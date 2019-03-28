/**
 * @author DasaK
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Union extends AbstractStringSet{
	
	AbstractStringSet a;
	AbstractStringSet b;
	
	// Konstruktor, zapamata si ake mnoziny zjednocujeme
	public Union(AbstractStringSet a, AbstractStringSet b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		return a.contains(s) || b.contains(s);
		//return false;
	}

	@Override
	public String[] getElements() {
		// TODO Auto-generated method stub
		String[] s1 = a.getElements();
		String[] s2 = b.getElements();
		List<String> out = new ArrayList<>();
		//String[] out = new String[mnoz.size()];
		for (String s: s1) {
			out.add(s);
		}
		for (String s: s2) {
			if (! a.contains(s)) out.add(s);
		}
		return out.toArray(new String[0]);
		//return null;
	}

}
