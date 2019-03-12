final class Immutable {		// trieda je final, takze nemozno vytvorit jej podtriedu
	private final int x;	// stavovu premennu nemozno zmenit, dostane hodnotu v konstruktore
	public Immutable(int x) {
		super();
		this.x = x;			// tu
	}
	public int getX() {
		return x;
	}
	@Override
	public String toString() {
		return "Immutable [x=" + x + "]";
	}
	public static void main(String[] args) {
		Immutable obj1 = new Immutable(77);
		Immutable obj2 = obj1;
		System.out.println(obj1);
		System.out.println(obj2);
		obj1 = new Immutable(999);  // inak sa obj1 neda zmenit
		System.out.println(obj1);
		System.out.println(obj2);
	}

}
