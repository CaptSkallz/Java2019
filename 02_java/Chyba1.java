/**
 * priklad triedenia BubbleSort s chybou, najdite ju
 * skuste prist na to, co robi tento program bez toho
 * aby ste ho spustili
 * @author PB
 * @version 2009
 */
public class Chyba1 {

  public static void main(String[] args) {
      int[] a = {4,5,2,12,1,2,3,0,3,4,1,2,3,6,4,5,5,2,12,1,2,3,55,2,2,4,8,9,3,54,2,3};
      int i;
      for (i = 0; i < a.length ; i++) {	// cyklus for-to-do
         for (i = a.length-1; i>0 ; i--) {	// cyklus for-downto-do
            if (a[i-1] > a[i]) {
               int temp = a[i];
               a[i] = a[i-1];
               a[i-1] = temp;
             } // if
         } // for
     } // for
     for (int elem:a) 			// cyklus for-each-element
    	 System.out.println(elem);
  }
}
