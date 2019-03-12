public class Kompozicia {
	private Nadtried n = new Nadtried(); // vložená nadtrieda
	private int addCount = 0;
	// @Override
	public void add(String s) { //pridaj 1
		addCount++;
		n.add(s);
	}
	// @Override
	public void addAll(String[] c) {// pridaj všetky
		addCount += c.length;
		n.addAll(c);
	}
	public int getAddCount() {
		return addCount;
	}
	public static void main(String[] args) {
	    Kompozicia s = new Kompozicia();
	    s.addAll(new String[]{"Peter", "Pavol"});
	    System.out.println(s.getAddCount());
	}
}

