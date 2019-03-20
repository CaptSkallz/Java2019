import java.util.ArrayList;
import java.util.Random;
public class BinaryTree  {

    BinaryNode root;

    public BinaryTree () {
 	  root = null;
    }
    public BinaryTree (int x) {
	  this();
	  insert(x);
    }
    public void insert(int x) {
	  if (root == null) root = new BinaryNode(x);
	  else root = root.insert (x);
    }
    public void print () {
	  root.print ();
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
    public static void main (String args[]) {
      BinaryTree s = new BinaryTree();
      Random r = new Random();
      for(int i=0; i<11; i++)
        s.insert(r.nextInt(100));
      s.print();
      System.out.println("....");
      }
}

