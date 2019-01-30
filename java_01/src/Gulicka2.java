/**
 * priklad s dvomi funkciami (resp. procedurami s vystupnou hodnotou)
 * @author PB
 * @version  2019
 */
public class Gulicka2 {
	/**
	 * definicia funkcie <code>posunX</code>
	 * @param x - suradnica gulicky
	 * @param fi - sklon sikmej plochy
	 * @return vrati novu X-ovu suradnicu gulicky
	 */
	public static double posunX(double x, double fi) {
		return x+Math.cos(fi);		
	}
	/**
	 * definicia funkcie <code>posunY</code>
	 * @param y - suradnica gulicky
	 * @param fi - sklon sikmej plochy
	 * @return vrati novu Y-ovu suradnicu gulicky 
	 */
	public static double posunY(double y, double fi) {
		return y+Math.sin(fi);		
	}
	/**
	 * toto je hlavny program.
	 * @param args - argumenty prikazoveho riadku, ktore zatial nevyuzivame
	 */
	public static void main(final String[] args) {
		double x=0.0, y=5.0, fi=0.56;
		for (int t = 0; t < 10; t++) {  	// definicia premennej cyklu t priamo v cykle
			x = posunX(x, fi);			// volanie funkcie s dvomi argumentami
			y = posunY(y,fi);			// a priradenie vyslednej hodnoty do premennej
			System.out.println(x + ", " + y);
		}
		System.out.println("done ");
	}
}
