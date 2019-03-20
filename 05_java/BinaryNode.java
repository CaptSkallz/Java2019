import java.util.ArrayList;

public class BinaryNode {
    BinaryNode left;
    int key; 
    BinaryNode right;
    
    BinaryNode(int theKey) {
      key = theKey;
      left = right = null;
    }
    public BinaryNode insert (int k) {
      if (k < key)
        if (left == null)
          left = new BinaryNode (k);
        else
    	  left = left.insert(k);
      else
        if (right == null)
          right = new BinaryNode (k);
        else 
    	  right = right.insert(k);
      return this;
      }
      public void print () {
      	System.out.println ("<" + key + ":" + "> - <" + (left == null ? "x" : "" + left.key) + ">, <" + (right == null ? "x" : "" + right.key) + ">");
      	if (left != null) left.print ();
      	if (right != null) right.print ();
       }
      public String toString(){
      	return "";  // cvicenie 6
      }
      public int[] toArray() {
      	int[] p = new int[100]; // 100 asi nie je dobra konstanta :-)
      	// cvicenie 6
      	return p;
      }
      ArrayList<Integer> toList() {
      	ArrayList<Integer> al = new ArrayList<Integer>();
      	// cvicenie 6
      	return al;
      }
      boolean find(int key) {
        return false; // cvicenie 6	
      }
      void delete(int key) {
      	// cvicenie 6
      }
          
}
