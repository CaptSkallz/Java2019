/**
 * objektovy pristup
 * @author PB
 * @version  2009
 */
/**
 * trieda <code>Gulicka5</code> v jednom subore obsahuje aj definiciu pomocnej triedy <code>Gulicka</code>, 
 * teraz <code>LokalnaGulicka</code>
 */
public class Gulicka5 {
/**	
 * hlavny program
 * @param args
 */
  public static void main(String[] args) {
 		   LokalnaGulicka g = new LokalnaGulicka(0.0,5.0);  // vytvor objekt triedy LokalnaGulicka s inicializaciou x=0.0 a y=5.0
 		   LokalnaGulicka h = new LokalnaGulicka(1.0,5.0);  // vytvor dalsi objekt triedy Gulicka
		   double fi=0.56;
		   for (int t=0; t<10; t++) {	// cyklus, v ktorom simulujeme posun oboch guliciek
			   g.posun(fi);				// volanie metody posun na objekt ulozeny v premennej g
			   h.posun(fi);				// to iste pre objekt v premennej h
		   }
		   System.out.println("done");
	    }
}
class LokalnaGulicka {
	double x;		// lokalne premenne triedy
	double y;		// reprezetnujuce suradnice simulovanej gulicky
	
	/**
	 * konstruktor triedy <code>LokalnaGulicka</code> s dvomi argumentami - inicialne hodnoty suradnic
	 * konstruktor triedy <code>LokalnaGulicka</code> sa MUSI volat LokalnaGulicka
	 * @param xx - suradnica gulicky
	 * @param yy - suradnica gulicky
	 */
	public LokalnaGulicka(double xx, double yy) {
		x = xx; y = yy;
	}
	/**
	 * jedina metoda triedy <code>LokalnaGulicka</code>, ktora meni staru polohu gulicky na novu
	 * @param fi - sklon sikmej plochy
	 */
	public void posun(double fi) {
		x += Math.cos(fi);
		y += Math.sin(fi);
	}	
}
