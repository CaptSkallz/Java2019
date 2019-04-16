package PB;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streamovy {

	public static void main(String[] args) {
		int MAX = 1000;
		System.out.println(IntStream.range(0,MAX)
				.filter(i -> i % 100 == 99)
				.boxed()
				.sorted()
				.collect(Collectors.toList()));

		System.out.println(IntStream.range(0,MAX)
				.filter(i -> i % 100 == 99 && i % 1000 != 999)
				.boxed()
				.sorted()
				.collect(Collectors.toList()));
		
		System.out.println(IntStream.range(0,MAX)
				.filter(i -> new StringBuffer(
						Integer.toBinaryString(i)).reverse().toString().equals(Integer.toBinaryString(i)))
				.boxed()
				.sorted()
				.collect(Collectors.toList()));
	}
}
