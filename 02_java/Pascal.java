/**
 * priklad pascalovho trojuholnika bez pola
 * riadok pascalovho trojuholnika sa pocita pomocou vztahu 
 * C(n,k+1) = C(n,k)*(n-k)/(k+1)
 * @author PB
 */
public class Pascal {
  public static void main(String[] args) {
    for(int n=0; n < 6; n++) {
	    for(int k=n; k<5; k++)
  	      	    System.out.print("\t");	    
	    System.out.print("1");
	    for (int k = 0, a=1; k <n; k++) {
		    a = a*(n-k)/(k+1);
    	      	    System.out.print("\t\t" + a);
	    }
	    System.out.println();
    }	  
  }
}
