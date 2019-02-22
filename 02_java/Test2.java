/**
 * priklad na predavanie argumentov referenciou
 * @author PB
 * version 2009
 */
public class Test2 {
  static int[] zmena(int[] x) {
    int[] c = x; 
    x[0] = 99; 
    return c;
  }
  public static void main(String[] args) {
    int[] a = {0,1,2,3};
    int[] b = zmena(a);
    System.out.println("a="+a[0]+",b="+b[0]);
  }
}

