/**
 * najdite chybu a opravte ju
 * @author PB
 */
public class Chyba7 {
	/**
	 * tzv.Collatzov problem bol slho otvorenym matematickym problemom
	 * a programatorskou vyzvou. 
	 * http://en.wikipedia.org/wiki/Collatz_conjecture
	 * 
	 * Ide o to, ze ked lub.prirodzene cislo vydelite dvomi, ak je parne,
	 * resp. vynasobite 3 a pripocitate 1, casom dokonvergujete k 1.
	 * 
	 * Naprikad:  
	 * 15, 46, 23, 70, 35, 106, 53, 160, 80, 40, 20, 10, 5, 16, 8, 4, 2, 1
	 * 
	 * Nasledujuci program, ktory priamociaro implementuje predpisany recept
	 * sa tak nesprava, preco ? Opravte ho...
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 15;
		while (n != 1) { 
			System.out.println(n);
			switch(n % 2) { // n mod 2
				case 0: 	// parne
					n /=2; 	// n = n/2;
				case 1:		// neparne
					n = 3*n+1;
			}
		}
	}
}
