import java.util.*;

public class Tree<E> {
  private E elem;
  private Forest<E> sons;
  
  public Tree(E elem) {
	  this(elem,null);
  }
  public Tree(E elem, Forest<E> sons) {
	 this.elem = elem;
	 this.sons = sons; 
  }
  public String toString() {
	 if (sons == null)
	     return elem.toString();
	 else
	     return "("+elem.toString()+":"+sons.toString()+")";
  }
  public boolean find(E key) {
	  if (elem.equals(key)) return true;
	  else if (sons != null) return sons.find(key);
	  else return false;
  }
  public int size() {
	  if (sons != null) return 1+sons.size();
	  else return 1;
  }
  public void insert(E parent, E el) {
    if (elem.equals(parent)) {
	  Tree<E> t = new Tree<E>(el); 
	  if (sons == null)
		sons = new Forest<E>(new ArrayList<Tree<E>>());
  	  sons.addLast(t);
	} else {
		if (sons != null) 
		  sons.insert(parent,el);
	 }
  }
  public Tree<E> remove(E el, Tree<E> t) {
    if (t == null) 
      return t;
    else if ((t.elem).equals(el)) 
      return null;
    else {
      if (sons != null)	
        sons = sons.remove(el,sons);
      return t;
    }  
  }  
}
 