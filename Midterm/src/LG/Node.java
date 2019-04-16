package LG;
import java.util.*;

public class Node {
    private Integer number;
    private List<Node> divisors;

    public Node(Integer number, List<Node> divisors) {
        this.number = number;
        this.divisors = divisors;
    }

    public String toString() {
        StringBuilder res = new StringBuilder("Node(" + number + ",[");
        for (int i = 0; i < divisors.size(); i++) {
            if (i != 0)
                res.append(", ");
            res.append(divisors.get(i).toString());
        }
        res.append("])");
        return res.toString();
    }

    private Node(Integer n) {
        this.number = n;
        this.divisors = new ArrayList<>();
        for (int i = 1; i <= n/2; i++) {
            if (n % i == 0) {
                this.divisors.add(new Node(i));
            }
        }
    }

    private int depth() {
        if (number == 1)
            return 0;
        int max = 0;
        for (Node child : divisors) {
            int actual = child.depth();
            if (actual > max) {
                max = actual;
            }
        }
        return 1 + max;
    }

    private void dict(Map<Integer, Integer> map) {
        if (map.keySet().contains(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
        }
        for (Node child : divisors) {
            child.dict(map);
        }
    }

    private Map<Integer, Integer> dict() {
        Map<Integer, Integer> map = new TreeMap<>();
        dict(map);
        return map;
    }

    public static void main(String[] args) {
        Node node1 = new Node(24);
        System.out.println(node1.depth());
        System.out.println(node1.dict());
        Node node2 = new Node(6);
        System.out.println(node2.toString());
    }
}