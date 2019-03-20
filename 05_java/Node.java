public class Node<E> {
  private E element;
  private Node<E> next;

  public Node() {this(null, null);  }

  public Node(E e, Node<E> n) {
    element = e;
    next = n;
  }

  public E getElement() {
    return element; 
  }
  public Node<E> getNext() { 
    return next;
  }
  public void setElement(E newElem) { 
	    element = newElem; 
	  }
	  public void setNext(Node<E> newNext) {
	    next = newNext; 
	  }
}
