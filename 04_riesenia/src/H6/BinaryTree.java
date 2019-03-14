package H6;
import java.math.BigInteger;
import java.util.Random;

final class BinaryTree implements BinaryTreeInterface {
	private BinaryNode root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(BinaryNode root) {
		this.root = root;
	}

	public boolean find(String key) {
		return false;
		// cvicenie 4
	}

	public BinaryTree insert(String x) {
		if (root == null) {
			root = new BinaryNode(x);
		} else {
			root.insert(x);
		}

		return this;

		// cvicenie 4
	}

	public BinaryTree delete(String key) {
		if (root == null) {
			root = new BinaryNode(key);
		}
		else {
			if (root.delete(key) == null) {
				root = null;
			}		
		}
		
		return this;
		// cvicenie 4
	}

	public String toString() {
		if(root == null) {
			return "";
		}
		return root.toString(); // cvicenie 4
	}

	public String[] toArray() {
		String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
		// cvicenie 4
		return p;
	}

	public static void main(String args[]) {
		BinaryTree s = new BinaryTree();
		/*s.insert("100");
		s.insert("200");
		s.insert("0");
		s.delete("0");
		s.delete("0");
		System.out.println(s);*/
		
		Random random = new Random();
		for (int i = 0; i < 110; i++)
			s = s.insert(new BigInteger(10, random).toString());
		System.out.println(s.toString());
		System.out.println("....");
		
		while (s.root != null) {
			s.delete(s.root.key);
		}
		
		System.out.println(s.toString());
		
	}
}
