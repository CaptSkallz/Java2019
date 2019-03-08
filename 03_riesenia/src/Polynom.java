/**
 * Cvicenie 3
 * @author PB
 */
public class Polynom
{
  final int MAXDEG = 100;			// pre 1.cvicenie staci
  double[] p = new double[MAXDEG];  // koeficienty
  int deg = 0;						// stupen
  /** constructor */
  Polynom(double coeff[]) {
	deg = coeff.length;
	if (deg >= MAXDEG)
	  System.out.println("Huston, mame problem !");
	else
	  System.arraycopy(coeff, 0, p, 0, deg);
  }

  /** returns string representation of this object */
  public String toString()
  {
    String retString = "";
    for(int i=deg-1; i >= 0; i--)
    	retString += (p[i]==0)?		// trochu tricky
    					"":
    					(((p[i]<0)?"-":"+") + 
    					  ((p[i] == Math.floor(p[i]))?
    						 (""+((int)(Math.abs(p[i])))):
    						 Math.abs(p[i]))+
    					  ((i>0)?"*x":"") +((i>1)?"^"+i:""));
    return retString;
  }

  /** returns the value of this polynom in a given point */
  public double hodnota(double x)
  {
	 double retValue = p[deg-1];
	 for(int i=deg-2; i >= 0; i--) 
		 retValue = retValue*x + p[i];
	 return retValue;
  }
  public static void main(String args[]) {
	    Polynom p1;
	    double x;
	    double coeff[] = {2, -4, 1 };

	    p1 = new Polynom(coeff);
	    System.out.println("Tabulka hodnot polynomu f(x) = " + p1 + " od -10 po 10:");
	    for (x = -10.0; x <= 10.0; x += 1.0)
	      System.out.println("f(" + x + ")=" + p1.hodnota(x));
	  }  
}
