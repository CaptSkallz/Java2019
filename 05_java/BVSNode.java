import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BVSNode<E extends Comparable<E> & Clonable> implements Clonable {
	BVSNode<E> left;
    E key; 
    BVSNode<E> right;
	static int allInstances = 0;
	private int instanceIndex;
    
    BVSNode(E theKey) {
		instanceIndex = allInstances++;
		System.out.println("create BVSNode " + instanceIndex);
        key = theKey;
        left = right = null;
    }
	public BVSNode<E> copy()  {
	   	 System.out.println("copy BVSNode " + instanceIndex);
	   	 BVSNode<E> clone = new BVSNode<E>((key!=null)?(E)(key.copy()):null);
	   	 clone.left = (left != null)?left.copy():null;
	 	 clone.right = (right != null)?right.copy():null;
	     return clone;
     }   
	public void insert(E k) {
		if (k.compareTo(key) < 0)
			if (left == null)
				left = new BVSNode<E>(k);
			else
				left.insert(k);
		else if (k.compareTo(key) > 0) 
			if (right == null)
				right = new BVSNode<E>(k);
			else
				right.insert(k);
	}
    @Override
    public String toString() {
    	String res = "";
    	res =  "<key:" + key + ":" + "> - <" + (left == null ? "x" : "left:" + left.key) + ">, <" + (right == null ? "x" : "right:" + right.key) + ">\n";
    	if (left != null) res += left;
    	if (right != null) res += right;
    	return res;
    }
    public Object[] toArray() {
    	Object[] p = new Object[10]; 
    	p[0] = key; 
    	p[1] = (left != null)?left.key:key; // ...
    	p[2] = (right != null)?right.key:key;
    	return p;
    }
    @SuppressWarnings("unchecked")
	public E[] toMArray() {
        E[] p =  (E[])new Comparable[10]; 
    	p[0] = key;  
    	p[1] = (left != null)?left.key:key; // ...
    	p[2] = (right != null)?right.key:key;
    	return p;
    }
    public E[] toMArray2(E[] x) {
    	List<E> l = new ArrayList<E>();
    	l.add(key);
    	l.add((left != null)?left.key:key);
    	l.add((right != null)?right.key:key);
    	// ...
        return l.toArray(x); 
    }
    ArrayList<E> toList() {
    	ArrayList<E> al = new ArrayList<E>();
    	al.add(key);
    	return al;
    }
    boolean find(E key) {
      return false; // cvicenie 6	
    }
    void delete(E key) {
    	// cvicenie 6
    }
}
