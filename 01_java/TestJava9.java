import java.util.List;
import java.util.stream.Collectors;

public class TestJava9 {


		static List<Integer> sucty(List<Integer> as, List<Integer> bs) {
			return as.stream().mapToInt(Integer::intValue)
			.flatMap( a -> bs.stream().mapToInt(Integer::intValue).map( b -> a + b) )
			.boxed().distinct().sorted().collect(Collectors.toList());
		}
		
		static List<Integer> suctyVela(List<List<Integer>> a) {
			  if (a.size() == 1)
			    return a.get(0);
			  else 
			     return sucty(a.get(0), suctyVela(a.subList(1,a.size())));
			}
		
		public static void main(String[] args) { 
			System.out.println(sucty(List.of(1,2,3,4,5), List.of(3,4,5,6,7)));  
			System.out.println(suctyVela(List.of(List.of(1,2,3,4,5), List.of(1,2,3,4,5), List.of(1,2,3,4,5))));
			System.out.println(suctyVela(List.of(List.of(1,2,3), List.of(1,2), List.of(1,3))));
			 
		}
	}


