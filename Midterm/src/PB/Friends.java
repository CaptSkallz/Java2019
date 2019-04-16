package PB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Friends {
	Map<String, Set<String>> friends;
	
	public Friends() {
		friends = new HashMap<>();
		friends.put("Nobody", new HashSet<>());
		friends.put("Jano", new HashSet<>(Arrays.asList(new String[] {"Palo", "Fero"} )));
		friends.put("Palo", new HashSet<>(Arrays.asList(new String[] {"Jano"} )));
		friends.put("Jana", new HashSet<>(Arrays.asList(new String[] {"Palo", "Jana"} )));
		friends.put("Fero", new HashSet<>(Arrays.asList(new String[] {"Jana"} )));
		friends.put("Ignac", new HashSet<>(Arrays.asList(new String[] {"Fero", "Palo", "Jano", "Jana"} )));
		/*
		friends = Map.of("Jano", Set.of("Palo", "Fero"),
		       "Palo", Set.of("Jano"),
		       "Jana", Set.of("Palo", "Jana"),
		       "Fero", Set.of("Jana"),
		       "Ignac",Set.of("Fero", "Palo", "Jano", "Jana"));
		*/
	}
	public Set<String> reflexive() {
		return friends.keySet().stream().filter(f -> friends.get(f).contains(f)).collect(Collectors.toSet());
	}
	
	public Set<String> transitive(String s) {
		Queue<String> queue = new LinkedList<>(); queue.add(s);
		Set<String> visited = new HashSet<>(); visited.add(s);
		while (queue.size() > 0) {
			String first = queue.remove();
			Set<String> nexts = friends.get(first);
			if (nexts != null) {
				Set<String> realNexts = nexts.stream().filter(f -> !visited.contains(f)).collect(Collectors.toSet());
				queue.addAll(realNexts);
				visited.addAll(realNexts);
				/* alebo klasika...
				for(String n : nexts) 
					if (!visited.contains(n)) {
						queue.add(n);
						visited.add(n);
					}
				*/
			}
		}
		return visited;
	}
	
	public static void main(String[] args) {
		Friends fr = new Friends();
		System.out.println(fr.reflexive());
		System.out.println(fr.transitive("Jano"));
		System.out.println(fr.transitive("Ignac"));
	}
}
