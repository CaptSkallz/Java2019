import java.util.Arrays;

/** Mnozina celych cisel reprezentovana ako pole */
public class Mnozina 
{

  private int prvky[];   // bude utriedene podla velkosti
  private int pocet;     // kolko prvkov v poli je prave pouzitych
    
  /** konstruktor vytvori prazdnu mnozinu so zadanym maximalnym poctom prvkov */
  public Mnozina(int maxpocet)
  {
     prvky = new int[maxpocet];
     pocet = 0;
  }

  /** vrati pocet prvkov v mnozine */
  public int pocetPrvkov()
  {
      return pocet;
  }
  
  /** efektivne prida prvok do mnoziny */
  public void pridajPrvok(int elem)
  {
      int i = 0;
      
      // najskor binarnym vyhladavanim najdime kam prvok patri
      if (pocet > 0)
      {
          i = Arrays.binarySearch(prvky, 0, pocet, elem);
          if (i > 0) return; // prvok sa uz v mnozine nachadza
          i = -(1+i);
      }

      // ak uz je kapacita mnoziny vycerpana, zvacsime ju na dvojnasobnu
      if (pocet == prvky.length) 
        prvky = Arrays.copyOf(prvky, 2 * prvky.length);

      // potom posunieme vsetky prvky od najdeneho indexu o jeden vpravo
      // (cize vytvorime miesto pre novy prvok)
      for (int j = pocet; j > i; j--)
          prvky[j] = prvky[j-1];
      pocet++;
      
      // nakoniec prvok vlozime na vytvorene miesto
      prvky[i] = elem;      
  }
  
  /** efektivne zisti, ci sa prvok v mnozine nachadza */
  public boolean nachadzaSa(int elem)
  {
      if (pocet > 0)
          return (Arrays.binarySearch(prvky, 0, pocet, elem) >= 0);
      return false;
  }

  /** efektivne k tejto mnozine prida vsetky prvky zadanej mnoziny */
  public void zjednot(Mnozina m)
  {
      // kapacita zjednotenej mnoziny bude sucet kapacit zjednocovanych mnozin
      int noveprvky[] = new int[prvky.length + m.prvky.length];
      int p = 0, p1 = 0, p2 = 0;
      // postupne zlucuj prvky oboch mnozin, zachovaj vzostupne usporiadanie
      while ((p1 < pocet) && (p2 < m.pocet))
         if (prvky[p1] < m.prvky[p2]) noveprvky[p++] = prvky[p1++];
         else 
         {
             if (prvky[p1] == m.prvky[p2]) p1++;
             noveprvky[p++] = m.prvky[p2++];             
         }
      // jedno z dvoch poli je komplet, dokopirujeme druhe z nich
      while (p1 < pocet) noveprvky[p++] = prvky[p1++];
      while (p2 < pocet) noveprvky[p++] = m.prvky[p2++];
      // vysledne zlucene pole patri novej mnozine
      prvky = noveprvky;
      pocet = p;      
  }

  /** vyluci z mnoziny prvky, ktore nie su prvkami zadanej mnoziny */
  public void prienik(Mnozina m)
  {
      // z pola vyhodime tie prvky, ktore nie su v druhej mnozine
      int v = 0; // pocet doteraz vyhodenych prvkov
      int p = 0, p2 = 0;
      while ((p < pocet) && (p2 < m.pocet))
          if (prvky[p] == m.prvky[p2]) // spolocny prvok => vezmi a posun sa v oboch 
              prvky[p++ - v] = m.prvky[p2++];
          else if (prvky[p] < m.prvky[p2]) // dalsi prvok v povodnej na vyhodenie 
              { p++; v++; } // posun sa a zvys pocet vyhodenych prvkov  
          else p2++; // prvok v mnozine m na vyhodnie
      pocet -= v;
  }

  /** vyluci z mnoziny prvky, ktore su prvkami zadanej mnoziny */
  public void rozdiel(Mnozina m)
  {
      // z pola vyhodime tie prvky, ktore su v druhej mnozine
      int v = 0; // pocet doteraz vyhodenych prvkov
      int p = 0, p2 = 0;
      while ((p < pocet) && (p2 < m.pocet))
          if (prvky[p] == m.prvky[p2]) // dalsi prvok v povodnej na vyhodenie
            { p++; p2++; v++; } // posun sa a zvys pocet vyhodenych prvkov  
          else if (prvky[p] < m.prvky[p2]) // prvok, ktory treba zachovat
          {
              prvky[p - v] = prvky[p]; 
              p++;
          }
          else p2++; // prvok z druhej mnoziny m, ktory si netreba vsimat (nie je v nasej)
      while (p < pocet) { prvky[p - v] = prvky[p]; p++; }
      pocet -= v;
  }

  /** otestuje tuto triedu - moze sa spustit ako samostatny program */
  public static void main(String args[])
  {
      for (int test = 0; test < 3; test++)
      {
        // vytvorime dve mnoziny 
        Mnozina a = new Mnozina(3);
        for (int i = 1; i <= 5; i++) a.pridajPrvok(i);
        a.pridajPrvok(3);
        Mnozina b = new Mnozina(3);
        for (int i = 3; i <= 7; i++) b.pridajPrvok(i);
        if (test == 0)
        {
          System.out.print("Mnozina A: ");  
          for (int i = 1; i <= 7; i++) if (a.nachadzaSa(i)) System.out.print(i + " ");
          System.out.println();
          System.out.print("Mnozina B: ");
          for (int i = 1; i <= 7; i++) if (b.nachadzaSa(i)) System.out.print(i + " ");
          System.out.println();
        }
      
        // vykoname mnozinovu operaciu
        switch (test)
        {
        case 0: a.zjednot(b); System.out.print("Zjednotenie: "); break;
        case 1: a.prienik(b); System.out.print("Prienik: "); break;
        case 2: a.rozdiel(b); System.out.print("Rozdiel: "); break;
        }
       
        // vypisme prvky vyslednej mnoziny
        for (int i = 1; i <= 7; i++) if (a.nachadzaSa(i)) System.out.print(i + " ");
        System.out.println();
      }
  }
}
