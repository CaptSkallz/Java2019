package H3;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Slova {

    public static List<String> slova(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            list.add("");
            return list;
        }
        List<String> tmp = slova(n - 1);
        for (String s : tmp) {
            list.add(s + "0");
            list.add(s + "1");
        }
        return list;
    }

    public static List<String> slova(int n, String abeceda) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            list.add("");
            return list;
        }
        List<String> tmp = slova(n - 1, abeceda);
        for (String s : tmp) {
            for (char ch : abeceda.toCharArray()) {
                list.add(s + ch);
            }
        }
        return list;
    }

    public static Stream<String> slovaStream(int n, String abeceda) {
        //return slova(n, abeceda).stream();
        if (n == 0) {
            return Stream.of("");
        }
        Stream<String> tmp = slovaStream(n-1, abeceda);
        //return tmp.flatMap(prvok -> (Stream.of(prvok+ "0", prvok +"1")));
        return tmp.flatMap(prvok ->
                IntStream.range(0, abeceda.length()).mapToObj(index ->
                        prvok+ abeceda.charAt(index)));
    }
    public static List<String> slova2(int n, String abeceda) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.addAll(slova(i, abeceda));
        }
        return list;
    }
    public static Stream<String> slovaStream2(int n, String abeceda){
        if (n == 0) {
            return Stream.of();
        }
        Stream<String> tmp = slovaStream2(n-1, abeceda);
        return Stream.concat(
                Stream.of(""),
                tmp.flatMap(prvok ->
                IntStream.range(0, abeceda.length()).mapToObj(index ->
                        prvok+ abeceda.charAt(index))));
    }

    public static void main(String[] args) {
        List<String> zoz = slova(5);
        System.out.println(zoz.size());
        System.out.println(zoz);

        List<String> zoz2 = slova(4, "abc");
        System.out.println(zoz2.size());
        System.out.println(zoz2);

        System.out.println(slovaStream(4, "abc").count());
        System.out.println(slovaStream(4, "abc").collect(Collectors.toList()));
        System.out.println(slova2(4, "abc"));
        System.out.println(slova2(4, "abc").size());

        System.out.println(slovaStream2(4, "abc").collect(Collectors.toList()));
        System.out.println(slovaStream2(4, "abc").count());
    }
}
