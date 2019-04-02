import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Karta {
	int hodnota;
	String farba;

	public Karta(int hodnota, String farba) {
		this.hodnota = hodnota;
		this.farba = farba;
	}

	public void setFarba(String farba) {
		this.farba = farba;
	}

	public int getHodnota() {
		return hodnota;
	}

	public void setHodnota(int hodnota) {
		this.hodnota = hodnota;
	}

	public String getFarba() {
		return farba;
	}

	public String toString() {
		return farba + "/" + hodnota;
	}
}

public class MapFilter {

	public static void main(String[] args) {
		List<Karta> karty = new ArrayList<>(Arrays.asList(
							new Karta(7,"Gula"),
							new Karta(8,"Zalud"),
							new Karta(9,"Cerven"),
							new Karta(10,"Zelen")));
		System.out.println(karty);
		
		karty.forEach(k -> k.setFarba("Cerven"));
		System.out.println(karty);
		
		Stream<Karta> vacsieKartyStream = 
			karty.stream().filter(k -> k.getHodnota() > 8);
		List<Karta> vacsieKarty = vacsieKartyStream.collect(Collectors.toList());
		System.out.println(vacsieKarty);
		
		List<Karta> vacsieKarty2 = 
				karty
				.stream()
				.filter(k -> k.getHodnota() > 8)
				.collect(Collectors.toList());
		System.out.println(vacsieKarty2);
		
		List<Karta> vacsieKarty3 = 
				karty
				.stream()
				.map(k->new Karta(k.getHodnota()+1,k.getFarba()))
				.filter(k -> k.getHodnota() > 8)
				.collect(Collectors.toList());
		System.out.println(vacsieKarty3);
		
		List<Karta> vacsieKarty4 = 
				karty
				.stream()
				.parallel()
				.filter(k -> k.getHodnota() > 8)
				.sequential()
				.collect(Collectors.toList());
		System.out.println(vacsieKarty4);		
		
		//----------------------------------------------------------
		// vyrobi stream obsahujuci 0..99
		Stream<Integer> stream = IntStream.range(0, 100).boxed(); // 0..99
		List<Integer> lst = stream.collect(Collectors.toList());
		//System.out.println(stream.count()); // 100
		//stream.forEach(e -> System.out.println(e+e));
		//stream.forEach(System.out::println);		
		// Excetion: stream has already been operated upon or closed

		// toto už nedostaneme v poradí 0, 1, ...
		//stream.parallel().forEach(e -> System.out.println(e+e));
		//----------------------------------------------------------
		lst.
			stream().
			filter(e -> (e % 2 == 0)).
			forEach(System.out::print);	// 02468101214161820222…
		lst.
			stream().
			map(e -> e*e).
			forEach(System.out::print);	// 01491625364964 …

		System.out.println(lst.stream().anyMatch(e -> (e == 51)));	// true
		System.out.println(lst.stream().anyMatch(e -> (e * e == e)));	// true
		System.out.println(lst.stream().noneMatch(e -> (e > 100)));	// true
		System.out.println(lst.stream().noneMatch(e -> (e + e == e))); 	// false
		System.out.println(lst.stream().allMatch(e -> e>0 )); 	 	// false
		System.out.println(lst.stream().filter(e -> e>0 ).count()); 	// 99

		//----------------------------------------------------------
		System.out.println(lst.stream().findFirst());		// Optional[0]
		System.out.println(lst.stream().findFirst().get());	// 0

		System.out.println(lst.parallelStream().findAny().get()); // 56,65,… nejednoznaèné

		System.out.println(lst.stream().min(Integer::compare).get());		// 0
		System.out.println(lst.stream().min(Integer::compare).isPresent()); 	// true
		System.out.println(lst.stream().max(Integer::compare).get());		// 99

		lst.stream().map(i->i%10).sorted().forEach(System.out::print);
		//0000000000111111111122222222223333333333444444444455555555556666
		//666666777777777788888888889999999999

		lst.stream().map(i->i%10).distinct().forEach(System.out::print);
		//0123456789

		//----------------------------------------------------------
		lst.stream().map(e -> { System.out.print(e); return e+e;});

		lst.stream().filter(e -> {System.out.print(e);return true;});

		lst.stream().map(e -> { System.out.print(e); return e+e;}).
				findFirst().get();			// 0

		lst.stream().map(e -> { System.out.print(e); return e+e;}).
				collect(Collectors.toList());

		//----------------------------------------------------------
		lst.parallelStream().
		map(e -> e+e).
			filter(e -> (e % 3 > 0)).
			forEach(e -> System.out.println(e));

		lst.parallelStream().
			filter(e -> (e % 3 > 0)).
			map(e -> e+e).
			forEach(e -> System.out.println(e));

		System.out.println(
			lst.parallelStream().
			map(e -> e+e).
			filter(e -> (e % 3 > 0)).
			collect(Collectors.toList()).size());  // ko¾ko je výsledok

		lst.parallelStream().
			map(e -> f1(e)).  // èo vieme poveda o kompozícii ?
			map(e -> f2(e)).
			collect(Collectors.toList());

		lst.parallelStream().
			map(e -> f2(f1(e))).  // èo vieme poveda o kompozícii ?
			collect(Collectors.toList());
		//-------------------------------------------------
		lst.parallelStream().
			map(e->funWithSideEffect(e)).
			filter(e -> (e % 3 > 0)).
			sorted().
			collect(Collectors.toList()).
			forEach(e -> System.out.println(e));

		Stream.of(0,1,2,3,4,5,6,7,9).collect(Collectors.toList());
		// [0, 1, 2, 3, 4, 5, 6, 7, 9]

		Stream.of("Palo", "Peter", "Jano", "Jana").collect(Collectors.toList());
		//[Palo, Peter, Jano, Jana]

		Arrays.stream(new Integer[]{0,1,2,3,4,5,6,7,8,9}).collect(Collectors.toList());
		//[0, 1, 2, 3, 4, 5, 6, 7, 9]

		IntStream.range(0,100).forEach(e -> System.out.print(e));
		//0123456789101112131415161718192021222324…

		//---------------------------------------------
		Map<Integer, List<Integer>>mapa = lst.parallelStream().collect(
				Collectors.groupingBy( e -> (String.valueOf(e).length()) ));
			//{1=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9], 2=[10, 11, 12, ... , 94, 95, 96, 97, 98, 99]}
		mapa.forEach((len, list) -> System.out.println(len + ", "+ list));
		//	1, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		//	2, [10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, …
		

		Map<Boolean, List<Integer>>partitions = lst.parallelStream().collect(
				Collectors.partitioningBy( e-> e % 3 == 0));
		System.out.println(partitions);
		
		Long count = lst.parallelStream().collect(
				Collectors.reducing(0L, e -> 1L, Long::sum));
		System.out.println(count);
		Long sum = lst.parallelStream().collect(
				Collectors.reducing(0L, e -> new Long(e), Long::sum));
		System.out.println(sum);
		int sumInt = lst.parallelStream().reduce(0, Integer::sum);
		System.out.println(sumInt);
		//--------------------------------------------------
		
	    int[] splitters = 
	    	      Stream.of(IntStream.of(-1), 
	    	    		    IntStream.range(0, lst.size()).filter(i -> isPrime(lst.get(i))), 
	    	    		    IntStream.of(lst.size()))
	    	      .flatMapToInt(s -> s)
	    	      .toArray();
	    List<List<Integer>> chunks = 
	    	      IntStream.range(0, splitters.length - 1)
	    	      .mapToObj(i -> lst.subList(splitters[i] + 1, splitters[i + 1]))
	    	      .filter(chunk -> chunk.size() > 0)
	    	      .collect(Collectors.toList());
		System.out.println(chunks);
	    	 
		
	}
	static Integer f1(Integer e) { return e+e; }
	static Integer f2(Integer e) { return 5*e; }
	//--------------------
	static Integer globalVariable = 0;
	static Integer funWithSideEffect(Integer n) {
		return n+n + (++globalVariable);
	}
	static boolean isPrime(Integer n) {
		return IntStream.range(2, 1+(int)Math.floor(Math.sqrt(n))).allMatch(i -> n % i != 0);
	}
}
