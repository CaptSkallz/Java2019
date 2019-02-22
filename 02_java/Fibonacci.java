/**
 * Fibonacciho cisla rekurzivne
 * @author PB 
 */

public class Fibonacci {
  public static void main(String[] args) {	
	int N = Integer.parseInt(args[0]);
	while (N-- > 0)
	  System.out.println(fib(N));
  } 
  public static long fib(int n) {
//	return (n < 2)?1:fib(n-1)+fib(n-2);
	if (n < 2) 
	  return 1;
	else
	  return fib(n-1)+fib(n-2);
  }  
}
