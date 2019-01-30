/**
 * nestrukturovany priklad
 * @author PB
 * @version  2019
 */
public class Gulicka1 {
	/** 
	 * definicia hlavneho programu musi zacinat 
	 * <code>public static void main(String[] args)</code>.
	 * @param args - 
	 */
	public static void main(final String[] args) {
		final  int max = 10;
		double x = 0.0; 
		double y = 5.0; 
		double fi = 0.56; // definicia troch realnych premennych s inicializaciou hodnot
		int t;						// definicia celociselnej premennej cyklu
		for (t = 0; t < max; t++) {	// cyklus <code>for t=0 to 9 do</code>
			x += Math.cos(fi);		// priradenie <code>x = x+cos(fi)</code>
			y += Math.sin(fi);		// priradenie <code>y = y+sin(fi)</code>
			System.out.println(x + ", " + y);
		}
		System.out.println("done");
	}
}
