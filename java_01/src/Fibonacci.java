/**
 * priklad programu, ktory cita cele cislo z konzoly do premennej N, 
 * na ktoru potom vypise prvych N fibonacciho cisel.
 * <br>
 * Fib.cisla su dane vztahom 
 * <br>
 * <ul>
 * <li>fib(1)=0, </li>
 * <li>fib(2)=1, </li>
 * <li>fib(N+2)=fib(N)+fib(N+1)</li>
 * </ul>
 * <br>
 * Pozn.:program pouziva triedu Input ako pomocku na cistanie cisla
 * @author PB
 * @version  2019
 * @see Input
 */
public class Fibonacci {
  public static void main(String[] args) {	
	Input in = new Input();
	System.out.println("Zadaj N:");
	int N = in.nextInt();
	// v pripade, ak nechceme hodnotu N ziskavat z konzoly ale z
	// prikazoveho riadku (argument programu), pouzijeme:
	//int N = Integer.parseInt(args[0]);
	long a = 1;		// inicializacia dvoch premennych na vypocet
	long b = 0;		// fib.cisel
	while (N-- > 0) {	// cyklus, ktory prebehne N-krat
		System.out.println(b);
		a = a+b;
		b = a - b;		// vypocitanie noveho fib.cisla
	}
	in.close();
  } 
}
