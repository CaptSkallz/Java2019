import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
interface FunkcionalnyInterface {
	public void doit(String s);
}

@FunctionalInterface
interface FunkcionalnyInterface1 {
	public String doit(String s);
}

@FunctionalInterface
interface RealnaFunkcia {
	public double doit(double s);
}

public class Funkcie {

	public static void foo(FunkcionalnyInterface fi) {
		fi.doit("hello");
	}

	public static FunkcionalnyInterface goo() {
		//return (String s) -> System.out.println(s + s);
		return s -> System.out.println(s + s);
	}

	public static String foo1(FunkcionalnyInterface1 fi) {
		return fi.doit("hello");
	}

	public static FunkcionalnyInterface1 goo1() {
		return
				//(String s)->(s+s);
				s->s+s;
	}
	//	----------------
	public static RealnaFunkcia iterate(int n, RealnaFunkcia f) {
		if (n == 0)
			//return (double d)->d;
			return d->d;
		else {			
			RealnaFunkcia rf = iterate(n-1, f);
			//return (double d)->f.doit(rf.doit(d));
			//return d->f.doit(rf.doit(d));
			return d->f.doit(iterate(n-1, f).doit(d));
		}
	}
	
	public static void main(String[] args) {
		foo(goo());
		System.out.println(foo1(goo1()));
		
		RealnaFunkcia rf = iterate(5, d->d*2);
		System.out.println(rf.doit(1));


		Function<Double,Double> celsius2Fahrenheit = x -> (x*9/5)+32;
		Function<Double,Double> rad2Deg = r -> (r/Math.PI)*180;
		Function<String, Integer> string2Int = x -> Integer.valueOf(x);
		Function<Integer, String> int2String = x -> String.valueOf(x);
		Predicate<Integer> odd = n -> n % 2 > 0;
		Predicate<Integer> square = n -> Math.pow(Math.floor(Math.sqrt(n)),2) == n;

		System.out.println("C->F: "+celsius2Fahrenheit.apply(30.0)); // 86.0
		System.out.println("rad2Deg: "+rad2Deg.apply(Math.PI)); // 180
		System.out.println(" string2Int: " + string2Int.apply("4")); // 4
		System.out.println(" int2String: " + int2String.apply(123)); // "123"

		System.out.println(" odd: " + odd.test(5)); // true
		System.out.println(" odd: " + odd.test(4)); // false
		System.out.println(" square: " + square.test(9)); // true
		System.out.println(" square: " + square.test(8)); // false
		
		Runnable r = new Runnable() {
			public void run() {
				// Run Forest, Run !
			};
		};
		
		Runnable r1 = () -> {
				// Run Forest, Run !
		};

		ArrayList<String> list = new ArrayList<>(Arrays.asList("Xenia", "Jan", "Peter", "Zora", "Pavel", "Jana"));
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
		System.out.println(list);
		System.out.println(list);
		
		String[] pole = { "GULA", "cerven", "zelen", "ZALUD" };
		Comparator<String> comp = 
			(fst, snd)->Integer.compare(fst.length(), snd.length());

		Arrays.sort(pole, comp);
		for (String e : pole) System.out.println(e);

		Arrays.sort(pole, 
			(fst, snd) -> 
				fst.toUpperCase().compareTo(snd.toUpperCase()));

		for (String e : pole) System.out.println(e);
		
	}
}
