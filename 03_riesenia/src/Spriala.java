/**
 * Cvicenie 3
 * @author PB
 */
public class Spriala {
	static String Spirala(int N) {
	  int[][] m = new int[N][N];	// dalo by sa aj bez pola ;-)
	  int i = 0;
	  int j = 0;
	  int next = 0;
	  int n = N;
	  while (N > 1) {
		  for(int ii=0; ii<N-1; ii++) m[i++][j] = ++next; 
		  for(int ii=0; ii<N-1; ii++) m[i][j++] = ++next; 
		  for(int ii=0; ii<N-1; ii++) m[i--][j] = ++next; 
		  for(int ii=0; ii<N-1; ii++) m[i][j--] = ++next; 
		  N -=2;
		  i++; j++;
	  }
	  if (N == 1)
		  m[i][j] = ++next;
	  String retString = "";
	  for(int ii=0; ii<n; ii++) {
		  for(int jj=0; jj<n; jj++)
			  retString += "\t"+m[jj][ii];
		  retString += "\n";
	  }
	  return retString;
		
	}
	public static void main(String[] args) {
		System.out.println(Spirala(Integer.valueOf(args[0])));

	}

}
