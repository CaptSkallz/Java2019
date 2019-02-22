/**
 * operatory ++, -- a % (modulo)
 * @author borovan
 *
 */
public class PlusPlus {
  public static void main(String[] args) {
    int i = 5, j = 1, k;
    i++;             // i bude 6
    System.out.println("i = " + i);
    j = ++i;         // j bude 7, i bude 7
    System.out.println("j = " + j + ", i = " + i);
    j = i++;         // j bude 7, i bude 8
    System.out.println("j = " + j + ", i = " + i);
    k = --j + 2;     // k bude 8, j bude 6, i bude 8
    System.out.println("k = " + k + ", j = " + j);
    i = j % 4;	      // delenie modulo
  }
}
