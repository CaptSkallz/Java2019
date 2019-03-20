public class NodeStack<E> implements StackInterface<E> {
  protected Node<E> top;		
  protected int size;		

  public NodeStack() { top = null; size = 0; }

  public void push(E elem) {
    Node<E> v = new Node<E>(elem, top);	
    top = v;
    size++;
  }
  public E top() throws EmptyStackException {
    if (isEmpty()) throw new EmptyStackException("empty");
    return top.getElement();
  }
  public E pop() throws EmptyStackException {
    if (isEmpty()) throw new EmptyStackException("empty");
    E temp = top.getElement();
    top = top.getNext();	
    size--;
    return temp;
  }
  public int size() {
	return size;  
  }
  public boolean isEmpty() {
	return size==0;  
  }
}
