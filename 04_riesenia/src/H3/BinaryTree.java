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
    public boolean find(String x) {
        BinaryNode node = root;

        if(root == null) return false;

        return root.find(x);
        // cvicenie 4	
      }
    public BinaryTree insert(String x) {
        if(root == null) return new BinaryTree(new BinaryNode(x));

   	    return new BinaryTree(root.insert(x));
      // cvicenie 4
    }
    public BinaryTree delete(String key) {
    	return null;
    	// cvicenie 4
    }    
    public String toString(){
      	if(root == null) return "";
        return root.toString();  // cvicenie 4
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
      BinaryTree s = new BinaryTree();
      Random random = new Random();
      for(int i=0; i<50; i++) {
          String x = new BigInteger(10, random).toString();
          s = s.insert(x);
          if (!s.find(x)) System.out.println(x + "   !!!!!!!!");
      }
      System.out.println(s.toString());
      System.out.println("....");
      }
}
