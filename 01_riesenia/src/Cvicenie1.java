/**
 * Riesenia cvicenia 1
 * @author PB
 * @version 2009
 */
public class Cvicenie1 {
final static int nnnn = 12; 
final static int N = 16;	
	/**
	 * funkcia spocita pocet jednotiek v binarnom zapise cisla n
	 * @param n
	 * @return vrati pocet jednotiek v binarnom zapise cisla n
	 */
    public static int binarneJednotky(final int n) {
  		return (n > 0) ? n % 2 + binarneJednotky(n / 2) : 0;
    }
    /**
     * najvacsi spolocny delitel cisel a,b
     * @param a
     * @param b
     * @return najvacsi spolocny delitel cisel a,b
     */
    public static int gcd(int a, int b) {
    	if (a*b == 0) {
			return a+b;
		} else {
			return gcd(b,a%b);
		}
    }
    public static long desatNa(int n) {
    	return (n == 0)?1:(10L*desatNa(n-1));
    }
    /**
     * hlavny program obsahuje riesenie
     */
	public static void main(String[] args) {
		/**
		 * tabulka faktorialov
 		 */
		long f=1;
		for(int i=1; i<=20; i++, f*=i) 
			System.out.println(i + "!= \t" + f);
		/**
		 *  tabulka fibonaccianov
		 */
		long fibA = 0, fibB = 1;
		for(int i=1; i<=30; i++, fibB += fibA, fibA = fibB-fibA) {
			System.out.println("fib (" + i + ")= \t" + fibB);
		}
		/**
		 *  trojuholnik
		 */
		final String medzery = "                                             ";  // vsetko ma
		final String starsy =  "*********************************************";  // svoje hranice
		for(int i=1; i<N; i++)
		  System.out.println(medzery.substring(1,N-i)+starsy.substring(1,2*i));	
		/**
		 *  puzzle
		 */
	       // ten program, ak nedostane v prikazovom riadku argument "paj" tak vypise Help yourself
	       // v pripade, ze dostane "paj", pocita aproximaxiu Ludolfovho cisla pomocou metody Monte Carlo
	       { // toto je vnoreny blok, premenne v nom deklarovane za nim neexistuju
		     int n = 0;
		     int m = 0;
		     while(m < 314588) {
		       double a = Math.random();
		       double b = Math.random();
		       if (a*a < 1-b*b) n++;
		       m++;
		     }
		     System.out.println("ak zadas argument paj, tak ti to vypocita aprocimaciu cisla Pi (metoda Monte-Carlo) !" + (4F*n/m));
		   }
		/**
		 * pocet binarnych jednotiek
		 */
		for(int i=1; i<N; i++) 
			System.out.println(i + " ma  \t" + binarneJednotky(i) + " binjednotiek");
		/** najmensi spolocny nasobok
		 */
		System.out.println("NSD(18,12)="+gcd(18,12));
		System.out.println("NSN(18,12)="+(12*18)/gcd(18,12));   // dobre vediet do buducna, ze lcd(a,b)*gcd(a,b) = a*b
		/**
		 *  najvacsie cislo samych binarnych jednotiek
		 */
		long i = 1;
		while (i < desatNa(N)) {
			i = 2*i+1;
		}
   		System.out.println("najvacsie "+N+" miestne cislo samych binarnych jednotiek je: " + (i/2));
   		/**
   		 *  najvacsie kombinacne cislo v riadku
   		 */
   		long comb = 1;
   		for(int K=0; K< N/2; K++) { 
   			comb *= N-K;
   			comb /= K+1;
   		}
		System.out.println("najvacsie cislo v "+N+"-tom riadku pascla je :" + comb);
		//*** Newtonova iteracia pre sqrt(2)
		double x;
		double xx = 100;  // velmi zla pociatocna aproximacia
		do {
		  x = xx;	
		  xx = 1/x + x/2;		// vzorec zo zadania
			System.out.println("odmocnina z dvoch: " + xx);
		} while (Math.abs(x-xx) > 1e-10);	// opakuj, kym odchylka dvoch posebeiducich je mensia ako epsilin
			// ina podmienka na zastavenie moze byt Math.abs(x*x-2) < epsilon
		System.out.println("odmocnina z dvoch: " + xx);
		
		/*
		 * Chyba1 - v programe ma byt miesto 	
		 * 	public static void main(String args) {
		 * 	public static void main(String[] args) {
		 * preto nema hlavny program a nejde spustit
		 * 
		 * Chyba2 - v programe metoda main nie je staticka, preto nejde spustit
		 * 
		 * Chyba3 - trieda chyba3 je definovana v subore Chyba3, problem je velke/male C
		 * 
		 * Chyba4 - zle hranice cyklu, pravdepodobne chcelo byt i < 10
		 * 
		 * Chyba5 - program sa zacykli, lebo telo cyklu tvori len prikaz
		 * System.out.println(a + ", " + b);
		 * malo byt:
		 *  { 
		 *   System.out.println(a + ", " + b);
		 *   if (a>b)a = a-b; else b = b-a;
		 *  }
		 *  
		 *  Chyba6 - hned vyskoci z cyklu, lebo podmienka v prikaze do { } while (a == b);
		 *  mala byt znegovana na while (a != b); 
		 *  do-while nie je repeat-until
		 *  
		 *  Chyba7 - v swtich prikaze sme zabudli prikazy break, malo byt:
		 *    switch(n % 2) { // n mod 2
		 *    case 0: 	// parne
		 *    		n /=2; 	// n = n/2;
		 *    		break;
		 *    case 1:		// neparne
		 *    		n = 3*n+1;
		 *    		break;
		 *    }
		 *    
		 *  Chyba8 - to uz fakt neviem... ;-)
		 *    okrem problemu nepresnosti v double nevidim nic...
		 */
	}
}
