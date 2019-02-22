import java.util.*;
/**
 * ukazka ako vygenerovat pole nahodnych cisel
 * nahodnej dlzky
 * @author PB
 * @version 2009
 */
public class NahodnePole {
	
  public static void main(String[] args) {
    Random rand = new Random();			// generator nah.cisiel
    int[] a = new int[rand.nextInt(20)]; // 0..19
    System.out.println("dlzka pola = " + a.length);
    for(int i = 0; i < a.length; i++) {
      a[i] = rand.nextInt(500);			// 0..499
      System.out.println("a[" + i + "] = " + a[i]);
    }
  }
}
