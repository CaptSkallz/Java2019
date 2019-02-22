/**
 * pascalovsky trojuholnik generovany v poli
 * na vypocet sa pouziva rekuretny vztah C(n+1,k+1) = C(n,k)+ C(n,k+1)
 * @author PB
 */
public class PascalPole {
  public static void main(String[] args) {
	final int MAX = 20;
	int[][] pp = new int[MAX][];
	pp[0] = new int[1];
	pp[0][0] = 1; 
	System.out.println(pp[0][0]);
	for (int i=1; i<MAX; i++) {
	  pp[i] = new int[i+1];
	  pp[i][i] = 1; 
	  System.out.print(pp[i][i]+"\t");
	  for(int j=i-1; j>0; j--) {
		pp[i][j] = pp[i-1][j-1]+ pp[i-1][j];
		System.out.print(pp[i][j]+"\t");
	  }
	  pp[i][0] = 1;
	  System.out.println(pp[i][0]);
	}
  }
}
