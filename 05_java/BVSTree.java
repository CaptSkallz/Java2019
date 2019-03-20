import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class BVSTree<E extends Comparable<E> & Clonable>  implements Clonable {
    BVSNode<E> root;
	static int allInstances = 0;
	private int instanceIndex;

    public BVSTree () {
	  instanceIndex = allInstances++;
	  System.out.println("create BVSTree " + instanceIndex);
 	  root = null;
    }
    public BVSTree (E x) {
	  this();
	  insert(x);
    }
	public BVSTree<E> copy()  {
	   	 System.out.println("copy BVSTree " + instanceIndex);
	   	 BVSTree<E> clone = new BVSTree<E>();
	   	 clone.root = (root != null)?root.copy():null;
	     return clone;
     }   
    
    public void insert(E x) {
		if (root == null)
			root = new BVSNode<E>(x);
		else
			root.insert(x);
    }
    @Override
    public String toString() {
	  return root.toString();
    }
    public Object[] toArray() {
    	return root.toArray();
    }
    public E[] toMArray() {
    	return root.toMArray();
    }
    public E[] toMArray2(E[] x) {
    	return root.toMArray2(x);
    }
    ArrayList<E> toList() {
    	return root.toList();
    }
    boolean find(E key) {
      return false; // cvicenie 6	
    }
    public void ddelete(E key) {
    	// cvicenie 6
    }   
    public void delete(E k ) {  root = delete(k, root); }
    private BVSNode<E> delete(E k, BVSNode<E> t ) {
      if (t == null )
          return t; 			
      if  (k.compareTo(t.key) < 0)		// element je v ¾avom podstrome
          t.left = delete(k, t.left);		// delete v ¾avom podstrome
      else if(k.compareTo(t.key) > 0)	// element je v pravom podstrome
          t.right = delete(k, t.right);		// delete v prevom podstrome
      else if( t.left != null && t.right != null ) {	// je to on, a má oboch synov
          t.key = findMin(t.right).key;	// nájdi min.pravého podstromu
          t.right = delete(t.key, t.right);	// rekurz.zmaž minimum
      } else					// pravého podstromu
          t = (t.left != null) ? t.left : t.right;	// ak nemá 2 synov, je to ¾ahké
      return t;
    }
    private BVSNode<E> findMin(BVSNode<E> t) {
        return null; // cvicenie 6	
    }
    
    public static void main (String args[]) {
      BVSTree<Hruska> s = new BVSTree<Hruska>();
      Random r = new Random();
      for(int i=0; i<5; i++)
          s.insert(new Hruska(r.nextInt(19)));
       System.out.println(s);
   	   BVSTree<Hruska> ss = (BVSTree<Hruska>)s.copy();
   	   System.out.println("NULL".compareTo("NULLOVA"));
   	   System.out.println("NULLXAVER".compareTo("NULLOVAMATILDA"));
   	   //ss.copy();
      
      /*
      BVSTree<Integer> s = new BVSTree<Integer>();
      Random r = new Random();
      for(int i=0; i<11; i++)
        s.insert(r.nextInt(100));
      s.print();
      System.out.println("....");
      // riesenie 1
      Object[] p1 = s.toArray(); for(Object elem:p1) System.out.print(elem); System.out.println();
      // riesenie 2
      Object[] p2 = s.toMArray(); 
      for(Object elem:p2) System.out.print(elem); System.out.println();
      // riesenie 3
      Integer[] p3 = s.toMArray2(new Integer[2]); // poslem mu dummy pole, aby vedel vystupny typ
      for(Integer elem:p3) System.out.print(elem); System.out.println();
      
      ArrayList<Integer> al = s.toList();
      System.out.println(al);
      */
      }
}

