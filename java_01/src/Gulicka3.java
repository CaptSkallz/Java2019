/**
 * strukturovany priklad
 * @author PB
 * @version  2009
 */

/**
 * definicia triedy s dvomi lokalnymi premennymi (atributmi x, y)
 */
public class Gulicka3 {
	static double x;	// tato staticka premenna existuje aj bez toho, aby sa vytvoril objekt triedy Gulicka3
	static double y;	
	/**
	 * staticka procedura (jej vystupna hodnota je <code>void</code> = nic) existuje aj bez toho, 
	 * aby sa vytvoril objekt triedy Gulicka3
	 * @param fi - sklon sikmej plochy
	 */
	public static void posun(double fi) {
		x += Math.cos(fi);	// procedura zmeni hodnoty lokalnych premennych zo starych suradnic gulicky na nove
		y += Math.sin(fi);
	}
	/**
	 * hlavny program
	 * @param args - zatial nevyuzite argumenty z prikazoveho riadku
	 */
	public static void main(String[] args) {
	   x=0.0; y=5.0;		// inicializacia lokalnych premennych triedy Gulicka3 
	   double fi=0.56;		// definicia a inicializacia realnej premennej fi
	   for (int t=0; t<10; t++)	// cyklus pre 10 krokov simulacie
		   posun(fi);
    }
}
