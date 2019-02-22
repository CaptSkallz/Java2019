import java.util.*;

/**
 * komplenty priklad programu na triedenie
 * @author PB
 * @version 2009
 */
public class SortObama2 {
  
  /**
   * procedura na triedenie pola
   * @param a - triedne pole ako vstupny argument
   */
  public static void bubleSortuj(ArrayList<Integer> a) {
    for (int i = 0; i < a.size() ; i++) {
      for (int j = a.size()-1; j>i ; j--) {
        if (a.get(j-1) > a.get(j)) {
           Integer temp = a.get(j);
           a.set(j,a.get(j-1));
           a.set(j-1,temp);
         }
      }
    }
  }
  /**
   * pocedura na vypis pola na konzolu
   * @param a - pole ako vstupny argument
   */
  public static void vypis(ArrayList<Integer> a) {
    for (int i:a)
      System.out.print(i+", ");
    System.out.println();
  }
  /**
   * procedura, ktora vygeneruje nahodne pole
   * @param velkost - pozadovanej velkosti
   * @return - vrati generene pole danej velkosti
   */
  public static ArrayList<Integer> generuj(int velkost) {
	  ArrayList<Integer> retValue = new ArrayList<Integer>();
	  Random rand = new Random();
	  for(int i=0; i<velkost; i++)
		  retValue.add(rand.nextInt(100000));
	  return retValue;
  }
  /** 
   * hlavny program
   * @param args
   */
  public static void main(String[] args) {
	  ArrayList<Integer> poleInt = generuj(100000);
	  long start = System.currentTimeMillis();
	  bubleSortuj(poleInt);
	  long end = System.currentTimeMillis();
	  System.out.println("elapsed time:"+(end - start)/1000);
	  //vypis(poleInt);
  }
}
