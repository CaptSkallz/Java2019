import java.util.Arrays;

public class Vektor {
  private double[] v;	
  public Vektor(double[] a) {
	 v = Arrays.copyOf(a, a.length);
  }
  public String toString() {
	String s = "[";
	for(int i=0; i<v.length; i++)
	  s += v[i]+ ((i+1==v.length)?"]":",");
	return s;
  }
  public void add(Vektor b) {
	if (v.length == b.v.length) {
	  for(int i=0; i<v.length; i++)
	    v[i] += b.v[i];
	} else
	    throw new VektorException("scitujes nerovnake vektory"); 
  }
  public static void main(String[] args) {
	double[] p = {1,2,3,4};
	double[] r = {2,3,4,5};
	Vektor v1 = new Vektor(p);
	Vektor v2 = new Vektor(r);
	  
	System.out.println(v1);
	System.out.println(v2);
	v1.add(v2);
	System.out.println(v1);
	System.out.println(v2);	  
  }
}
