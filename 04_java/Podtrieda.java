public class Podtrieda extends Nadtrieda{
	int n = 0; 
	String s;
		public Podtrieda() {
			System.out.println("Konstruktor podtriedy");
		}
		public Podtrieda(int n) {
			System.out.println("Iny konstruktor podtriedy n="+n);
			this.n = n;
		}
		public Podtrieda(String s) {
			super(s+s);
			System.out.println("Konstruktor podtriedy s="+s);
			this.s = s;
		}
		public void Foo() {
			System.out.println("Nicnerobiaca funkcia Foo v podtriede");
			super.Foo();
		}
		public void finalize() {
		  try {
			System.out.println("GC vola destruktor v podtriede n="+n+" s="+s);
		  } finally {
			  super.finalize();
		  }
		}
}

