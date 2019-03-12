public class Main {
	public static void main(String[] args) {
		for(int i=0; i<1; i++) {
		Nadtrieda nad = new Nadtrieda();
		Podtrieda pod = new Podtrieda();
		System.out.println("-------------");
		Nadtrieda nadInt = new Nadtrieda(i);
		Podtrieda podInt = new Podtrieda(-i);
		System.out.println("-------------");
		Nadtrieda nadString = new Nadtrieda("wow");
		Podtrieda podString = new Podtrieda("wow");
		System.out.println("-------------");
		nadString.Foo();
		podString.Foo();
		System.out.println("-------------");
		}
	}

}
