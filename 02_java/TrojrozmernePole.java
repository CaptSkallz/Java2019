import java.util.*;
/**
 * priklad vytvorenia trojrozmerneho pola
 * @author PB
 */
public class TrojrozmernePole {
  static Random rand = new Random();
  
  public static void vypis(String name, int[][][] a) {
    for(int i = 0; i < a.length; i++)
      for(int j = 0; j < a[i].length; j++)
        for(int k = 0; k < a[i][j].length; k++)
          System.out.println(name+"[" + i + "][" + j + "][" + k + "] = " + a[i][j][k]);
  }

  public static void main(String[] args) {
    int[][][] a = new int[2][2][2];
    vypis("a",a);

    int[][][] b = new int[rand.nextInt(7)][][];
    for(int i = 0; i < b.length; i++) {
      b[i] = new int[rand.nextInt(5)][];
      for(int j = 0; j < b[i].length; j++)
        b[i][j] = new int[rand.nextInt(5)];
    }
    vypis("b",b);
  }
}
