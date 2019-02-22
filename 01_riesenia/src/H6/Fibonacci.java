
public class Fibonacci {

	public static void main(String[] args) {
		for (int i = 1; i < 31; i++) {
			System.out.println(i + "\t" + fib(i));
		}
	}
	public static long fib(int n) {
		long a = 0;
		long b = 1;
		for (int i = 0; i < n; i++) {
			long pom = a;
			a = b;
			b += pom;
		}
		return a;
	}

}
