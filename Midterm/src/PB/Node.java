package PB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Node {
	Integer number;
	List<Node> divisors;
	public Node(Integer number, List<Node> divisors) {  // vygenerovany konstruktor
		this.number = number;
		this.divisors = divisors;
	}
	@Override
	public String toString() {
		return "Node(" + number + "," + divisors + ")";
	}
	public Node(Integer n) {
		this(n, null);
		List<Node> divisors = new ArrayList<Node>();
		for (int i = 1; i <= n/2; i++)
			if (n % i == 0)
				divisors.add(new Node(i));
		this.divisors = divisors;
	}
	public int depth() {
		int max = -1;
		for(Node divisor : divisors) {
			int d = divisor.depth();
			if (d > max)
				max = d;
		}
		return max+1;
	}
	public Map<Integer, Integer> dict() {
		Map<Integer, Integer> res = new TreeMap<>();
		dict(res);
		return res;
	}
	public void dict(Map<Integer, Integer> map) {
		Integer occur = map.get(number);
		map.put(number, (occur == null)?1:occur+1);
		for (Node pn : divisors) 
			pn.dict(map);
	}
	public static void main(String[] args) {
		List<Integer> examples = Arrays.asList(new Integer[] {2,6,24,25});
		for (Integer e : examples) 
			System.out.println(
					"Node(" + e + ")= "+new Node(e) +
					"\tNode(" + e + ").depth()= "+ new Node(e).depth() +
					"\tNode(" + e + ").dict()= "+ new Node(e).dict());
	}
}
