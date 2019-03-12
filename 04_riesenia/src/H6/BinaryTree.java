import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Random;

final class BinaryTree implements BinaryTreeInterface {
    private final BinaryNode root;

    public BinaryTree () {
 	  root = null;
    }
    public BinaryTree(BinaryNode root) {
		this.root = root;
	}
    public boolean find(String key) {
        return root.find(key); 
        // cvicenie 4	
      }
    public BinaryTree insert(String x) {
   	  return null;
      // cvicenie 4
    }
    public BinaryTree delete(String key) {
    	return null;
    	// cvicenie 4
    }    
    public String toString(){
      	return "";  // cvicenie 4
    }
    public String[] toArray() {
    	String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
     	// cvicenie 4
        return p;
     }
     ArrayList<String> toList() {
      	// cvicenie 4
      	return null;
      }
    public static void main (String args[]) {

      BinaryNode a = new BinaryNode("a");
      BinaryNode c = new BinaryNode("c");
      BinaryNode b = new BinaryNode(a, "b", c);
      BinaryNode w = new BinaryNode("w");
      BinaryNode d = new BinaryNode(b, "d", w);

      BinaryTree s = new BinaryTree(d);
      
      System.out.println(s.find("x"));
      System.out.println("....");
      }
}
