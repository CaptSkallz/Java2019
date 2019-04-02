import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Kombinatorika {
	public static void main(String[] args) {
		
		//System.out.println(binaries(5));
		//binaries1(5).forEach(System.out::println);
		System.out.println("perms(5) = " + perms(5).count());
		System.out.println("vso(5,3) = " + vso(5,3).count());
		System.out.println("vso(2,5) = " + vso(2,5).count());
		System.out.println("vbo(0,4) = " + vbo(0,4).count());
		System.out.println("vbo(1,5) = " + vbo(1,5).count());
		for(int n = 0; n < 5; n++) {
			for(int k = 0; k <= n; k++) {
				System.out.print("vso("+k+","+n+") = " + vso(k,n).count());
				System.out.print(" vso("+k+","+n+") = "); 
				vso(k,n).forEach(r -> System.out.print(r + ","));
				System.out.println();
			}
		}
		for(int n = 0; n < 7; n++) {
			for(int k = 0; k <= n; k++) {
				System.out.print("vbo("+k+","+n+") = " + vbo(k,n).count());
				System.out.print(", vbo("+k+","+n+") = "); 
				vbo(k,n).forEach(r -> System.out.print(r + ","));
				System.out.println();
			}
		}
		for(int n = 0; n < 7; n++) {
			for(int k = 0; k <= n; k++) {
				System.out.print("kbo("+k+","+n+") = " + kbo(k,n).count());
				System.out.print(" kbo("+k+","+n+") = "); 
				kbo(k,n).forEach(r -> System.out.print(r + ","));
				System.out.println();
			}
		}
		for(int n = 0; n < 7; n++) {
			for(int k = 0; k <= n; k++) {
				System.out.print("kso("+k+","+n+") = " + kso(k,n).count());
				System.out.print(" kso("+k+","+n+") = "); 
				kso(k,n).forEach(r -> System.out.print(r + ","));
				System.out.println();
			}
		}
	}

	static List<String> binaries(int n) {
		if (n == 0) {
			return Arrays.asList("");
		} else {
			List<String> result = new ArrayList<>();
			for (String s : binaries(n-1)) {
				result.add(s + "0");
				result.add(s + "1");
			}
			return result;
		}
	}
	static Stream<String> binaries1(int n) {
		if (n == 0) {
			return Stream.of("");
		} else {
			return binaries1(n-1).flatMap(s -> Stream.of(s + "0", s + "1"));
		}
	}
	static Stream<ArrayList<Integer>> binaries2(int n) {
		if (n == 0) {
			return Stream.of(new ArrayList<>());
		} else {
			return binaries2(n-1).flatMap(al -> Stream.of(append(al, 0), append(al, 1)));
		}
	}
	static ArrayList<Integer> append(ArrayList<Integer> lst, Integer i) {
		ArrayList<Integer> x = (ArrayList<Integer>)lst.clone();
		x.add(i);
		return x;
	}
	/*
	 * variacie s opakovanim: n prvkove z mnoziny 0..k-1
	 */
	static Stream<String> vso(int n, int k) {
		if (n == 0) {
			return Stream.of("");
		} else {
			return vso(n-1, k).flatMap(s ->	IntStream.range(0, k).mapToObj(i -> s + String.valueOf(i)) );
		}
	}
	
	/*
	 * kombinacie bez opakovania: k prvkove z mnoziny 0..n-1
	 */
	static Stream<String> kbo(int k, int n) {
		if (k > n) {
			return Stream.of();
		} else if (k == 0) {
			return Stream.of("");
		} else {
			return Stream.concat(
					kbo(k, n-1),
					kbo(k-1, n-1).map(s -> s + String.valueOf(n-1)));
		}
	}

	/*
	 * kombinacie s opakovanim: k prvkove z mnoziny 0..n-1
	 */
	static Stream<String> kso(int k, int n) {
		if (n == 0) {
			return Stream.of();
		} else if (k == 0) {
				return Stream.of("");
		} else {
			return Stream.concat(
					kso(k, n-1),
					kso(k-1, n).map(s -> s + String.valueOf(n-1)));
		}
	}
	
	/*
	 * variacie bez opakovania: k prvkove z mnoziny 0..n-1
	 */
	static Stream<String> vbo(int k, int n) {
		if (k > n) {
			return Stream.of();
		} else if (k == 0) {
				return Stream.of("");
		} else {
			return Stream.concat(
					vbo(k, n-1),
					vbo(k-1, n-1).flatMap(s -> IntStream.range(0, k).mapToObj(i -> insert(i, n-1, s))));
		}
	}
	
	static Stream<String> perms(int n) {
		if (n == 0) {
			return Stream.of("");
		} else {
			return perms(n-1).flatMap(s ->	IntStream.range(0, n).mapToObj(i -> insert(i, n, s)) );
		}
	}
	static String insert(int i, int n, String s) {
		return s.substring(0,i) + String.valueOf(n) + s.substring(i, s.length());
	}
}
