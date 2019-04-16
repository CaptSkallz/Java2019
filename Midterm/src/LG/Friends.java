package LG;

import java.util.*;

public class Friends {
    private Map<String, Set<String>> friends;

    private Friends(Map<String, Set<String>> friends) {
        this.friends = friends;
    }

    private Set<String> reflexive() {
        Set<String> res = new HashSet<>();
        for (String human : friends.keySet()) {
            if (friends.get(human).contains(human))
                res.add(human);
        }
        return res;
    }

    private Set<String> transitive(String s) {
        Set<String> res = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.add(s);
        while (!stack.empty()) {
            String f = stack.pop();
            res.add(f);
            for (String o: friends.get(f)) {
                if (!res.contains(o))
                    stack.add(o);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, Set<String>> friends = new HashMap<>();
        friends.put("Jano", new HashSet<>(Arrays.asList("Palo", "Fero")));
        friends.put("Palo", new HashSet<>(Collections.singletonList("Jano")));
        friends.put("Nobody", new HashSet<>());
        friends.put("Jana", new HashSet<>(Arrays.asList("Palo", "Jana")));
        friends.put("Fero", new HashSet<>(Collections.singletonList("Jana")));
        friends.put("Ignac", new HashSet<>(Arrays.asList("Fero", "Palo", "Jano", "Jana")));
        Friends fr = new Friends(friends);
        System.out.println(fr.reflexive());
        System.out.println(fr.transitive("Jano"));
        System.out.println(fr.transitive("Ignac"));
    }
}
