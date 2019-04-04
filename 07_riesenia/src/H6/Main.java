package H6;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		Set<String> a = new TreeSet<String>(
				Arrays.asList(DataForYou.spoluziaci));
		
		System.out.println(a.size());
		System.out.println(a);
		
		/*Comparator<String> comp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				
				if(Integer.compare(o1.length(), o2.length()) == 0) {
					return o1.compareTo(o2);
				}
				
				return Integer.compare(o1.length(), o2.length());
			}
			
		};*/
		
		Set<String> b = new TreeSet<String>(
				(o1, o2) -> {
					if(Integer.compare(o1.length(),
							o2.length()) == 0) {
						
						return o1.compareTo(o2);
					}
					return Integer.compare(o1.length(),
							o2.length());
				}
				);
		b.addAll(Arrays.asList(DataForYou.spoluziaci));
		
		System.out.println(b);
		
		
		
		
		Map<String,Integer> map = new TreeMap<>();
		for (String s : DataForYou.spoluziaci) {
//			if (map.containsKey(s)) {
//				map.put(s, map.get(s) + 1);
//			} else {
//				map.put(s, 1);
//			}
			
			map.put(s, map.getOrDefault(s, 0) + 1);
		}
		int max = 0;
		String maxmeno = "";
//		for(String kluc : map.keySet()) {
//			int hodnota = map.get(kluc);
		
//		for(Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
//			Map.Entry<String, Integer> entry = it.next();
//			String kluc = entry.getKey();
//			int hodnota = entry.getValue();
		
		for(Map.Entry<String, Integer> e : map.entrySet()) {
			String kluc = e.getKey();
			int hodnota = e.getValue();
			
			if (hodnota > max) {
				max = hodnota;
				maxmeno = kluc;
			}
		}
		
		
		System.out.println(max + " " + maxmeno);
		System.out.println(map);
		
		
		
		
		
		Map<Integer, Set<String>> mapa = new TreeMap<>();
		for(int i =0; i < DataForYou.spoluziaci.length; i++) {
			String meno = DataForYou.spoluziaci[i];
			Integer  dlzka = meno.length();
			if(mapa.containsKey(dlzka)) {
				 mapa.get(dlzka).add(meno);
			}
			else {
				Set<String> mnozina = new HashSet<String>();
				mnozina.add(meno);
				mapa.put(dlzka,mnozina);
			}
			
		}
		System.out.println(mapa);
		
		
		
		Map<Integer, List<String>> mapa2 = Arrays.stream(DataForYou.spoluziaci)
			.distinct()
			.collect(Collectors.groupingBy(
						meno -> meno.length()
					));
		System.out.println(mapa2);
	}

}
