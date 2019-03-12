public class Podtried extends Nadtried {
	private int addCount = 0;
	@Override
	public void add(String s) { //pridaj 1
	    addCount++;
	    super.add(s);
	}
	@Override
	public void addAll(String[] c) {// pridaj
	    addCount += c.length;      // všetky
	    super.addAll(c);
	}
	public int getAddCount() {
	    return addCount; }
	
	public static void main(String[] args) {
		Podtried s = new Podtried();
		s.addAll(new String[]{"Peter", "Pavol"});
		System.out.println(s.getAddCount());
	}
}

