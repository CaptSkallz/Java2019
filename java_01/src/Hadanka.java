
public class Hadanka {

	public static long quiz(int n) {
	    long a = 0, b = 1;
	    if (n <= 0) return -1;
	    for (; n-->0; a += b, b -=a, b =-b);
	    return a;
	}

	public static void main(String[] args) {
		for(int i = 1; i < 50; i++) {
			System.out.println(i + " = " + quiz(i));
		}
	}
}
