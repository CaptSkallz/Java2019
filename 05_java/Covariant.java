/**
 * priklad na covarianciu typov
 * @author PB
 */
public class Covariant {

	public static void main(String[] args) {
		Podtrieda[] a = { new Podtrieda(), new Podtrieda()};
		Nadtrieda[] b = a;	// polia su covariantne
		//Podtrieda[] c = b;
		System.out.println(b.length);
		
		Stack50<Podtrieda> stA = new Stack50<Podtrieda>(100);
		//Stack50<Nadtrieda> stB = stA;
		//Stack50<Podtrieda> stC = stB;
		
		Stack50<Integer>[] p;
		p = new Stack50<Integer>[5];
	}
}
