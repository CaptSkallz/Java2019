package H6;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Slova {

	public static void main(String[] args) {
		System.out.println(slova(4));
		System.out.println(slovaStream(4)
				.filter(slovo -> slovo.endsWith("0"))
				.map(slovo -> slovo.length())
				.collect(Collectors.toList()));

	}
	public static List<String> slova(int n){
		if(n==0) {
			List<String> pole = new ArrayList<String>();
			pole.add("");
			return  pole;
		}
		List<String> list = new ArrayList<String>();
		for (String subslovo : slova(n-1)) {
			list.add(subslovo.concat("0"));
			list.add(subslovo.concat("1"));
		}
		return list;

	}
	
	public static Stream<String> slovaStream(int n) {
		if (n == 0) return Stream.of("");
		return slovaStream(n - 1)
				.flatMap(slovo -> Stream.of(slovo + "0", slovo + "1"));
	}
	

}
