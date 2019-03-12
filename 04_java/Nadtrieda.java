public class Nadtrieda { 
	int n = 0;
	String s;
	public Nadtrieda() {
		System.out.println("Konstruktor nadtriedy");
	}
	public Nadtrieda(int n) {
		System.out.println("Konstruktor nadtriedy n="+n);
		this.n = n;
	}
	public Nadtrieda(String s) {
		System.out.println("Konstruktor nadtriedy s="+s);
		this.s = s;
	}
	public void Foo() {
		System.out.println("Nicnerobiaca funkcia Foo v nadtriede");		
	}
	public void finalize() {
		System.out.println("GC vola destruktor v nadtriede n="+n+" s="+s);	
	}	
}