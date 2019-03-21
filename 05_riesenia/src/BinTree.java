import java.util.ArrayList;
import java.util.Random;
public class BinTree<E extends Comparable<E>>  {

    BinNode<E> root;

    public BinTree () {
 	  root = null;
    }
    public BinTree (E x) {
	  this();
	  insert(x);
    }
    public void insert(E x) {
	  if (root == null) root = new BinNode<E>(x);
	  else root = root.insert (x);
    }
    public void print () {
	  root.print ();
    }
    public String toString(){
      	return root.toString();
      }
      public E[] toArray(E[] a) {
  			return null; //cvicenie 6
      }
      ArrayList<E> toList() {
  		return null; //cvicenie 6
      }
      boolean find(E k) {
  		return false; //cvicenie 6
      }
      void delete(E key) {
      	// cvicenie 6
      }    
    public static void main (String args[]) {
      BinTree<Integer> s = new BinTree<Integer>();
      Random r = new Random();
      for(int i=0; i<11; i++) {
        Integer q = r.nextInt(100);
        s.insert(q);
        System.out.println(s.find(q) + "," + s.find(q+1));
      }
      s.print();
      Integer[] e = {};
      for(Integer elem: s.toArray(e))
    	  System.out.print(elem + ",");  
      System.out.println("....");
      }
}

