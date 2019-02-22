/**
 * priklad statickeho inicializacneho bloku
 * @author PB
 * @version 2009
 */
public class Prvocisla {

  public static final int MAX = 1000;
  public static final int cisla[] = new int[MAX];
  
  static {
    int pocet = 2;
    cisla[0] = 1;
    cisla[1] = 2;

  dalsi:
    for (int i = 3;  pocet < MAX;  i += 2) {
      for (int j = 2;  j < pocet;  j++)
        if (i % cisla[j] == 0)
          continue dalsi;
      cisla[pocet] = i;
      pocet++;
    }
  }
  
  public static void main(String[] args) {
	  for (int i=1;i<Prvocisla.cisla.length; i++)
	      System.out.print(cisla[i] + "  ");
	  }
	}
