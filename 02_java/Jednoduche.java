/**
 * priklad na jednorozmerne pole
 * @author PB
 * @version 2009
 */
public class Jednoduche {

 public static void main(String[] args) {
 final int MAX = 20;				// konötanta ñ veækosù poæa

 // int[] poleInt; 					// definÌcia poæa 
 // poleInt = new int[MAX];			// vytvorenie poæa
 int[] poleInt = new int[MAX];			// definÌcia poæa s vytvorenÌm
    for (int i = 0;  i < poleInt.length;  i++) {	// i < MAX
      poleInt[i] = i + 1;				// inicializ·cia poæa
      System.out.print(poleInt[i] + "  ");
    } // for
  }  // main
} // class}
