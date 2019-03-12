import java.util.Arrays;

/** trieda implementujuca dlhe cisla */
public class BigNums 
{
    byte[] cifry;  // obsahuje cifry dlheho cisla

    /** zkonstruuje dlhe cislo podla cisla zadaneho v retazci znakov */
    public BigNums(String n)
    {
        cifry = new byte[n.length()];
        for (int i=0; i<n.length(); i++)
                cifry[i] = (byte)((byte)n.charAt(n.length()-i-1)-(byte)('0'));
    }

    /** k cislu pricita ine velke cislo */
    public void add(BigNums a)
    {
        int max=Math.max(a.cifry.length, cifry.length);
        cifry = Arrays.copyOf(cifry, max+1);

        int tmp=0;
        for (int i=0; i<max; i++) {
                if (i < a.cifry.length) {
                        tmp = a.cifry[i]+cifry[i];
                        cifry[i] = (byte) (tmp % 10);
                        cifry[i+1] += tmp / 10;
                }
        }
    }

    /** cislo vynasobi inym velkym cislom */
    public void mult(BigNums a)
    {
        // ostava na cvicenie pre vas...
    }

    /** kovnertuje dlhe cislo naspat na String */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        for (int i=cifry.length - 1; i >= 0; i--)
           s.append((char)(((byte)'0') + cifry[i]));
        return s.toString();
    }

    /** otestuje metody tejto triedy */
    public static void main(String[] args) 
    {
        BigNums b = new BigNums("123456");
        BigNums c = new BigNums("910011");
        System.out.print(b + " + " + c + " = ");
        b.add(c);
        System.out.println(b);
    }
}
