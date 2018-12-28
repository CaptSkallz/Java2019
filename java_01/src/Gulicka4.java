/**
 * objektovy pristup
 * @author PB
 * @version  2009
*/
/**
 * trieda <code>Gulicka4</code> pouziva triedu <code>Gulicka</code>
 * kedze sa zatial nachadzaju v jednom adresari/package, netreba toto pouzitie explicitne deklarovat
 */
public class Gulicka4 {
/**	
 * hlavny program
 * @param args
 */
  public static void main(String[] args) {
 		   Gulicka g = new Gulicka(0.0,5.0);  // vytvor objekt triedy Gulicka s inicializaciou x=0.0 a y=5.0
		   Gulicka h = new Gulicka(1.0,5.0);  // vytvor dalsi objekt triedy Gulicka
		   double fi=0.56;
		   for (int t=0; t<10; t++) {	// cyklus, v ktorom simulujeme posun oboch guliciek
			   g.posun(fi);				// volanie metody posun na objekt ulozeny v premennej g
			   h.posun(fi);				// to iste pre objekt v premennej h
			   System.out.println(g.x + ", " + g.y);
			   System.out.println(h.x + ", " + h.y);
		   }
	    }
}
