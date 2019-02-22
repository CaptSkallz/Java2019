import java.util.*;

/**
 * komplenty priklad programu na triedenie
 * @author PB
 * @version 2009
 */
public class Sort {
  
  /**
   * procedura na triedenie pola
   * @param a - triedne pole ako vstupny argument
   */
  public static void bubleSortuj(int[] a) {
    for (int i = 0; i < a.length ; i++) {
      for (int j = a.length-1; j>i ; j--) {
        if (a[j-1] > a[j]) {
           int temp = a[j];
           a[j] = a[j-1];
           a[j-1] = temp;
         }
      }
    }
  }
  /**
   * pocedura na vypis pola na konzolu
   * @param a - pole ako vstupny argument
   */
  public static void vypis(int[] a) {
    for (int i:a)
      System.out.print(i+", ");
    System.out.println();
  }
  /**
   * procedura, ktora vygeneruje nahodne pole
   * @param velkost - pozadovanej velkosti
   * @return - vrati generene pole danej velkosti
   */
  public static int[] generuj(int velkost) {
	  int[] retValue = new int[velkost];
	  Random rand = new Random();
	  for(int i=0; i<velkost; i++)
		  retValue[i] = rand.nextInt(100);
	  return retValue;
  }
  /** 
   * hlavny program
   * @param args
   */
  public static void main(String[] args) {
	  int[] poleInt = generuj(100000);
	  long start = System.currentTimeMillis();
	  bubleSortuj(poleInt);
	  long end = System.currentTimeMillis();
	  System.out.println("elapsed time:"+(end - start)
			   /1000
			  );
	  //vypis(poleInt);
  }
}
