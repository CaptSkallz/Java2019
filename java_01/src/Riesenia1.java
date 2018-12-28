/**
* riesenia cvicenia 1
* @author PB
* @version 2009
*/
public class Riesenia1 {
final static int N = 16;          /**
    * rekurzivna funkcia, ktora spocita pocet jednotiek v bin.zapise cisla n
    */
   public static int binarneJednotky(int n) {
         return (n>0)?n%2+binarneJednotky(n/2):0;
   }
   /**
    * rekurzivna funkcia pre vypocet najvacsieho spolocneho delitela cisel a, b
    */
   public static int gcd(int a, int b) {
       if (a*b == 0)
           return a+b;
       else
           return gcd(b,a%b);
   }
   /**
    * rekurzivna funkcia, ktora pocita 10^N
    */
   public static long desatNa(int n) {
       return (n == 0)?1:(10L*desatNa(n-1));
   }
   /**
    * hlavny program
    */
   public static void main(String[] args) {
       // *** tabulka faktorialov
       long f=1;
       for(int i=1; i<=20; i++, f*=i)
           System.out.println(i + "!= \t" + f);
       // *** tabulka fibonaccianov
       long fibA = 0, fibB = 1;
       for(int i=1; i<=30; i++, fibB += fibA, fibA = fibB-fibA)
           System.out.println("fib (" + i + ")= \t" + fibB);
       // *** trojuholnik
       final String medzery = "                                             ";  // vsetko ma
       final String starsy =  "*********************************************";  // svoje hranice
       for(int i=1; i<N; i++)
         System.out.println(medzery.substring(1,N-i)+starsy.substring(1,2*i));           // *** puzzle
       // ten program, ak nedostane v prikazovom riadku argument "paj" tak vypise Help yourself
       // v pripade, ze dostane "paj", pocita aproximaxiu Ludolfovho cisla pomocou metody Monte Carlo
       { // toto je vnoreny blok, premenne v nom deklarovane za nim neexistuju
            int n = 0;
            int m = 0;
            while(m < 314588) {
              double a = Math.random();    // generovanie nahodneho cisla [0-1)
              double b = Math.random();
              if (a*a < 1-b*b) n++;        // a*a+b*b < 1 je znamena, ze bod [a,b] lezi v jednotkovej kruznici
              m++;                            // m je pocet vsetkych nahodnych bodov
            }                                // n je pocet tych nahodnych bodov, ktore padli do
                                            // stvrtkruhu prveho kvadrantu
            System.out.println("ak zadas argument paj, tak ti to vypocita aprocimaciu cisla Pi (metoda Monte-Carlo) !" + (4F*n/m));
          }
       // *** pocet binarnych jednotiek
       for(int i=1; i<N; i++)
           System.out.println(i + " ma  \t" + binarneJednotky(i) + " binjednotiek");
       // *** najmensi spolocny nasobok
       System.out.println("NSD(18,12)="+gcd(18,12));
       System.out.println("NSN(18,12)="+(12*18)/gcd(18,12));   // dobre vediet do buducna, ze lcd(a,b)*gcd(a,b) = a*b
       // *** najvacsie cislo samych binarnych jednotiek
       long i = 1;
       while (i < desatNa(N))
           i = 2*i+1;
          System.out.println("najvacsie "+N+" miestne cislo samych binarnych jednotiek je: " + (i/2));
          // *** najvacsie kombinacne cislo v riadku
          long comb = 1;
          for(int K=0; K< N/2; K++) {        // najvacsie kombinacne cislo v riadku pascalovho trojuholnika je v strede
              comb *= N-K;                // alebo skoro v strede :-)
              comb /= K+1;
          }
       System.out.println("najvacsie cislo v "+N+"-tom riadku pascla je :" + comb);
   }
}
