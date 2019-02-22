/**
 * priklad triedenia na ilustraciu cyklov a poli
 * @author PB
 * @version 2009
 */
public class BubbleSortObama1 {

  public static void main(String[] args) {
      int[] a = {4,5,2,12,1,2,3,0,3,4,1,2,3,6,4,5,5,2,12,1,2,3,55,2,2,4,8,9,3,54,2,3};
      for (int i = 0; i < a.length ; i++) {	// cyklus for-to-do
         for (int j = a.length-1; j>i ; j--) {	// cyklus for-downto-do
            if (a[j-1] > a[j]) {
               int temp = a[j];
               a[j] = a[j-1];
               a[j-1] = temp;
             } // if
         } // for
     } // for
     for (int elem:a) 			// cyklus for-each-element
    	 System.out.println(elem);
  }
}
