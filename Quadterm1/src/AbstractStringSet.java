/**
 * @author DasaK
 */
public abstract class AbstractStringSet {
    public abstract boolean contains(String s);   // vrati true ak mnozina obsahuje prvok s, inak false
    public abstract String[] getElements();   // vrati pole vsetkych prvkov, ktore su aktualne v mnozine (na poradi nezalezi)
	
	public int size() {   // vrati pocet prvkov, ktore su aktualne v mnozine
		return getElements().length;
	};
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(String s : getElements()) {
			sb.append( (s == null ? s : "\""+s+"\"") ).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length()).append("]");
		return sb.toString();
	}
}
