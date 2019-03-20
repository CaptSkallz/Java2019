import java.util.Arrays;

public class Zvrhlost {
	
	static <T> T[] append(T[] arr, T element) { /* moja funkcia na pridavanie prvkov do pola na koniec */
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
	
}
