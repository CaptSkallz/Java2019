package LG;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {

        System.out.println(IntStream.range(2, 30).filter(n -> IntStream.range(2,n).filter(i->n%i == 0).sum() == 0).boxed().collect(Collectors.toList())); // prvocisla < 30

        System.out.println(Stream.of(1, 2, 3).flatMap(i -> Stream.of(3, 2, 1).map(j -> i*j)).collect(Collectors.toSet())); // [1, 2, 3, 4, 6, 9], vsetky rozne suciny

        System.out.println(IntStream.range(0, 100).map(i -> i*6).filter(i -> i % 3 == 0).count()); //100
        System.out.println(IntStream.range(0, 100).map(i -> i*3).filter(i -> i % 6 == 0).count()); //50

        int MAX = 10000;
        //d
        System.out.println(
                IntStream.range(10, MAX).filter(i -> new StringBuffer(Integer.toString(i)).reverse().toString().substring(0, 2).equals("99")).boxed().sorted().collect(Collectors.toList())
        );
        //e
        System.out.println(
                IntStream.range(99, MAX).filter(i -> i == 99 || (new StringBuffer(Integer.toString(i)).reverse().toString().substring(0, 2).equals("99") && (new StringBuffer(Integer.toString(i)).reverse().toString().charAt(2) != '9'))).boxed().sorted().collect(Collectors.toList())
        );
        //f
        System.out.println(
                IntStream.range(0, MAX).filter(i -> Integer.toBinaryString(i).equals(new StringBuffer(Integer.toBinaryString(i)).reverse().toString())).boxed().sorted().collect(Collectors.toList())
        );

    }
}
