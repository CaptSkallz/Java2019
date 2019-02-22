/**
 * objektovy priklad - pomocna trieda k prikladu Gulicka4
 * @author PB
 * @version  2009
 */

/**
 * definicia objektu Gulicka s dvomi lokalnymi premennymi (atributmi x, y), 
 * konstruktorom a jednou metodou (posun)  
 */
public class Gulicka {
	double x;		// lokalne premenne triedy
	double y;		// reprezetnujuce suradnice simulovanej gulicky
	
	/**
	 * konstruktor triedy Gulicka s dvomi argumentami - inicialne hodnoty suradnic
	 * konstruktor triedy Gulicka sa MUSI volat Gulicka
	 * @param xx - suradnica gulicky
	 * @param yy - suradnica gulicky
	 */
	public Gulicka(double xx, double yy) {
		x = xx; y = yy;
	}
	/**
	 * jedina metoda triedy Gulicka, ktora meni staru polohu gulicky na novu
	 * @param fi - sklon sikmej plochy
	 */
	public void posun(double fi) {
		x += Math.cos(fi);
		y += Math.sin(fi);
	}	
}
