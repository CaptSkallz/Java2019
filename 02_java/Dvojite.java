/**
 * priklad na dvojrozmerne pole dynamicky vytvorene
 * @author PB
 * version 2009
 */
public class Dvojite {

  public static void main(String[] args) {		
    int[][] a = new int[4][];		// hlavné pole
    for (int i = 0;  i < a.length;  i++) {			
      a[i] = new int[i + 1]; 		// podpole 				
      for (int j = 0;  j < a[i].length;  j++) {		
        a[i][j] = i * 10 + j;			
        System.out.print(a[i][j] + "  ");		
      } // for					
      System.out.println();
    } // for
 } // main
} // class
