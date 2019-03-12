import java.util.Arrays;

/** reprezentuje MyPolyInt s celociselnymi koeficientami */
public class MyPolyInt {

	    private long coeff[]; // koeficienty MyPolyIntu
	    
	    /** skonstruuje MyPolyInt so zadanymi koeficientami, 
	     *  p[0] reprezentuje a0 vo vyraze (a0 * x^0 + a1 * x^1 + ... + an * x^n) */ 
	    public MyPolyInt(long[] p) {
	       coeff = Arrays.copyOf(p, p.length);      
	    }
	    
	    /** k MyPolyIntu pripocita MyPolyInt p */
	    public void add(MyPolyInt p) {
	        int i;
	        long newcoeff[];
	        if (p.coeff.length > coeff.length) newcoeff = new long[p.coeff.length];
	        else newcoeff = coeff;
	        for (i = 0; i < newcoeff.length; i++)
	        	newcoeff[i] = ((i < coeff.length)?coeff[i]:0) + ((i < p.coeff.length)?p.coeff[i]:0);
	        i--;
	        while (++i < coeff.length) newcoeff[i] = coeff[i];
	        while (++i < p.coeff.length) newcoeff[i] = p.coeff[i];
	        
	        coeff = newcoeff;
	    }
	    
	    /** vynasobi MyPolyInt konstantou */
	    public void times(long c) {
	        for (int i = 0; i < coeff.length; i++) coeff[i] *= c;
	    }
	    
	    /** MyPolyInt vynasobi s MyPolyIntom p */
	    public void times(MyPolyInt p) {
	        long newcoeff[] = new long[coeff.length + p.coeff.length - 1];
	        for (int i = 0; i < coeff.length; i++)
	            for (int j = 0; j < p.coeff.length; j++)
	                newcoeff[i+j] += coeff[i] * p.coeff[j];
	        coeff = newcoeff;
	    }

	    /** MyPolyInt derive s MyPolyIntom p */
	    public void derive() {
	        long newcoeff[] = new long[coeff.length-1];
	        for (int i = 1; i < coeff.length; i++)
	            newcoeff[i-1] += coeff[i] * i;
	        coeff = newcoeff;
	    }
	    
	    /** MyPolyInt vynasobi s MyPolyIntom p */
	    public long value(long x) {
	    	long value = 0;
	        for (int i = coeff.length-1; i >= 0; i--)
	        	value = x*value + coeff[i];
	        return value;
	    }
	    
	    /** konvertuje MyPolyInt na vypisatelny String */
	    public String toString() {        
	        StringBuffer s = new StringBuffer(coeff.length * 10);
	        for (int i = coeff.length - 1; i>0; i--) {
	        	if (coeff[i] == 0)
	        		continue;
	        	if (coeff[i] != 1) {
		        	if (coeff[i] >= 0)
		        		s.append(coeff[i]);
		        	else {
		        		s.append("(");
		        		s.append(coeff[i]);
		        		s.append(")");	        		
		        	}
	                s.append("*");
	        	}
                s.append("x");
                if (i > 1) {
                    s.append("^");                
                    s.append(i);
                }
	            s.append("+");
	        }
	        s.append(coeff[0]);
	        return s.toString();
	    }
	    
	    /** hlavny program, ktory otestuje cinnost metod tejto triedy */
	    public static void main(String[] args) {
	        
	        for (int test = 0; test < 6; test++) {            
	          MyPolyInt p1 = new MyPolyInt(new long[]{1,2,3,-4,1});
	          MyPolyInt p2 = new MyPolyInt(new long[]{1,0,0,2});
	           
	          switch (test) {
	          case 0: 
	              	System.out.println("p1 = " + p1);
	              	System.out.println("p2 = " + p2);
	        	  	p1.add(p2); 
	        	  	System.out.println("p1 += p2, print p1 "+p1);
	          		break;
	          case 1: 
	        	  	p1.times(p2); 
	        	  	System.out.println("p1 *= p2, print p1 = " + p1);
	        	  	break;
	          case 2:
	        	  	System.out.println(p1 + " at 1 = " + p1.value(1)); 
	        	  	break;
	          case 3: 
	        	  	p1.derive(); 
	        	  	System.out.println("p1 dx = " + p1);
	        	  	break;
	          case 4: 
	        	  	p1.times(2); 
	        	  	System.out.println("p1 *= 2 " + p1);
	        	  	break;
	          case 5: 
	        	  	p1.times(0); 
	        	  	System.out.print("p1 *= 0 " + p1);
	        	  	break;
	          }
	        }
	    }
}
