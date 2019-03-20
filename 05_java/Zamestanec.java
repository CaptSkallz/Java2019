import java.util.*;

public class Zamestanec implements Comparable<Zamestanec> {
  private final String meno, priezvisko;

  public Zamestanec(String meno, String priezvisko) {
	  this.meno = meno; this.priezvisko = priezvisko;
  }
  public String toString() {
	return meno+" "+priezvisko;
  }
  /*
  public boolean equals(Zamestanec n) {
	return n.meno.equals(meno) && n.priezvisko.equals(priezvisko);
  }*/
  public int compareTo(Zamestanec n) {
    int lastCmp = priezvisko.compareTo(n.priezvisko);
    return (lastCmp != 0 ? lastCmp : meno.compareTo(n.meno));
  }
  public static void main(String[] args) {
	  Zamestanec nameArray[] = {
          new Zamestanec("Jozef", "Novak"),
          new Zamestanec("Peter", "Novy"),
          new Zamestanec("Paul", "Newman"),
          new Zamestanec("Adam", "Novak")
      };
      List<Zamestanec> names = Arrays.asList(nameArray);
      Collections.sort(names);
      System.out.println(names);
  }
}
