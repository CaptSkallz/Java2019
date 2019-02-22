/**
 * priklad na predavanie argumentov hodnotou
 * @author PB
 * version 2009
 */
public class Test1 {

  static int zmena(int i) {
    i++; return i;
  }

  public static void main(String[] args) {
    int j, k = 4;
    j = zmena(k);
    System.out.println(
	"k=" + k + ", j=" + j);
  }
}
