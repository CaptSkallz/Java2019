/**
 * priklad triedenia. Je to vobec BubbleSort ???
 * @author PB
 * @version 2009
 */
public class Chyba2 {

  public static void main(String[] args) {
      int[] a = {4,5,2,12,1,2,3,0,3,4,1,2,3,6,4,5,5,2,12,1,2,3,55,2,2,4,8,9,3,54,2,3};
      for (int i = 0; i < a.length ; i++) {	// cyklus for-to-do
         for (int j = a.length-1; j>i ; j--) {	// cyklus for-downto-do
            if (a[i] > a[j]) {
               int temp = a[j];
               a[j] = a[i];
               a[i] = temp;
             } // if
         } // for
     } // for
     for (int elem:a) 			// cyklus for-each-element
    	 System.out.println(elem);
  }
}
