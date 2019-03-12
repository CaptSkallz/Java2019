import java.util.Arrays;

/** reprezentuje polynom s realnymi koeficientami */
public class Polynom {

    private double coeff[]; // koeficienty polynomu
    private final static double EPS = 0.00000001;  // blizsie k 0 ako tato konstanta povazujeme za 0 
    
    /** skonstruuje polynom so zadanymi koeficientami, 
     *  p[0] reprezentuje a0 vo vyraze (a0 * x^0 + a1 * x^1 + ... + an * x^n) */ 
    public Polynom(double[] p)
    {
       // spocitaj cleny s nulovymi koeficientami (tie sa nebudu kopirovat)
       int numZeroCoeff = 0;
       for (int i = p.length - 1; i >= 0; i--)
           if (Math.abs(p[i]) > EPS) break; else numZeroCoeff++;
       
       coeff = Arrays.copyOf(p, p.length - numZeroCoeff);      
    }
    
    /** k polynomu pripocita polynom p */
    public void add(Polynom p)
    {
        int i;
        double newcoeff[];
        if (p.coeff.length > coeff.length) newcoeff = new double[p.coeff.length];
        else newcoeff = coeff;
        for (i = 0; (i < coeff.length) && (i < p.coeff.length); i++)
            newcoeff[i] = coeff[i] + p.coeff[i];
        i--;
        while (++i < coeff.length) newcoeff[i] = coeff[i];
        while (++i < p.coeff.length) newcoeff[i] = p.coeff[i];
        
        // spocitaj cleny s nulovymi koeficientami (tie sa nezachovaju)
        int numZeroCoeff = 0;
        for (i = newcoeff.length - 1; i >= 0; i--)
           if (Math.abs(newcoeff[i]) > EPS) break; else numZeroCoeff++;
        if (numZeroCoeff > 0)
          coeff = Arrays.copyOf(newcoeff, newcoeff.length - numZeroCoeff);
        else coeff = newcoeff;
    }
    
    /** vynasobi polynom konstantou */
    public void times(double c)
    {
        for (int i = 0; i < coeff.length; i++) coeff[i] *= c;
    }
    
    /** polynom vynasobi s polynomom p */
    public void times(Polynom p)
    {
        double newcoeff[] = new double[coeff.length + p.coeff.length - 1];
        for (int i = 0; i < coeff.length; i++)
            for (int j = 0; j < p.coeff.length; j++)
                newcoeff[i+j] += coeff[i] * p.coeff[j];
        // spocitaj cleny s nulovymi koeficientami (tie sa nezachovaju)
        int numZeroCoeff = 0;
        for (int i = newcoeff.length - 1; i >= 0; i--)
           if (Math.abs(newcoeff[i]) > EPS) break; else numZeroCoeff++;
        if (numZeroCoeff > 0)
          coeff = Arrays.copyOf(newcoeff, newcoeff.length - numZeroCoeff);
        else coeff = newcoeff;
    }
    
    /** polynom vydeli polynomom p */
    public void div(Polynom p)
    {
        if (p.coeff.length > coeff.length) // delitel vacsi ako delenec => vysledok je 0
            coeff = new double[0];
        else
        {
          double newcoeff[] = new double[coeff.length - p.coeff.length + 1];
          while (coeff.length >= p.coeff.length)
          {      
              int e = coeff.length - p.coeff.length;
              newcoeff[e] = coeff[coeff.length - 1] / p.coeff[p.coeff.length- 1];
              Polynom toSub = new Polynom(p.coeff);
              double qd[] = new double[e+1];
              qd[e] = -newcoeff[e];
              Polynom quot = new Polynom(qd);
              toSub.times(quot);
              add(toSub);
          }
          coeff = newcoeff;
        }
    }
    
    /** vypocita zvysok po deleni tohto polynomu polynomom p */
    public void mod(Polynom p)
    {
        if (p.coeff.length > coeff.length) // delitel vacsi ako delenec => vysledok je polynom sam
            coeff = new double[0];
        else
        {
          while (coeff.length >= p.coeff.length)
          {      
              int e = coeff.length - p.coeff.length;
              double nc = coeff[coeff.length - 1] / p.coeff[p.coeff.length- 1];
              Polynom toSub = new Polynom(p.coeff);
              double qd[] = new double[e+1];
              qd[e] = -nc;
              Polynom quot = new Polynom(qd);
              toSub.times(quot);
              add(toSub);
          }
        }
    }

    /** konvertuje polynom na vypisatelny String */
    public String toString() 
    {        
        StringBuffer s = new StringBuffer(coeff.length * 10);
        for (int i = coeff.length - 1; i>0; i--)
        {
            if (Math.abs(coeff[i]) > EPS) 
            {
                s.append(coeff[i]);
                s.append("*x");
                if (i > 1) 
                {
                    s.append("^");                
                    s.append(i);
                }
            }
            if (coeff[i-1]>EPS) s.append(" +");
            else if (coeff[i-1]<-EPS) s.append(" ");
        }
        s.append(coeff[0]);
        return s.toString();
    }
    
    /** hlavny program, ktory otestuje cinnost metod tejto triedy */
    public static void main(String[] args) 
    {
        double d1[] = {1.0,2.0,3.0,-4.0,1.0};
        double d2[] = {1,0,0.0,1.0};
        
        for (int test = 0; test < 4; test++)
        {            
          Polynom p1 = new Polynom(d1);
          Polynom p2 = new Polynom(d2);
          if (test==0)
          {
              System.out.println("p1 = " + p1);
              System.out.println("p2 = " + p2);
          }
           
          switch (test)
          {
          case 0: p1.add(p2); System.out.print("p1+p2 = "); break;
          case 1: p1.times(p2); System.out.print("p1*p2 = "); break;
          case 2: p1.div(p2); System.out.print("p1/p2 = "); break;
          case 3: p1.mod(p2); System.out.print("p1%p2 = "); break;
          }

          System.out.println(p1);
        }

    }

}
