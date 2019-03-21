import java.util.ArrayList;

public class BinNode<E extends Comparable<E>> {
	BinNode<E> left;
	E key;
	BinNode<E> right;

	BinNode(E theKey) {
		key = theKey;
		left = right = null;
	}

	public BinNode<E> insert(E k) {
		if (k.compareTo(key) < 0)
			if (left == null)
				left = new BinNode<E>(k);
			else
				left = left.insert(k);
		else if (right == null)
			right = new BinNode<E>(k);
		else
			right = right.insert(k);
		return this;
	}

	public void print() {
		System.out.println("<" + key + ":" + "> - <"
				+ (left == null ? "x" : "" + left.key) + ">, <"
				+ (right == null ? "x" : "" + right.key) + ">");
		if (left != null)
			left.print();
		if (right != null)
			right.print();
	}

	public String toString() {
		return ""; // cvicenie 6
	}

	public E[] toArray() {
		return null; // cvicenie 6
	}

	ArrayList<E> toList() {
		return null; // cvicenie 6
	}

	boolean find(E k) {
		return false; // cvicenie 6
	}

	void delete(E key) {
		// cvicenie 6
	}
}
