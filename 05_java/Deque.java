public class Deque<E> implements DequeInterface<E> {

  protected DLNode<E> header, trailer;  
  protected int size;    
  
  public int size() {
	     return size;
	  }
	  public boolean isEmpty() {
		return (size==0);  
	  }
  public Deque() {  
    header = new DLNode<E>();
    trailer = new DLNode<E>();
    header.setNext(trailer);  
    trailer.setPrev(header);  
    size = 0;
  }

  public E getFirst() throws Exception {  
    if (isEmpty())
      throw new Exception("Deque is empty.");
    return header.getNext().getElement();
  }
  public void addFirst(E o) {  
	    DLNode<E> second = header.getNext();
	    DLNode<E> first = new DLNode<E>(o, header, second);
	    second.setPrev(first);
	    header.setNext(first);
	    size++;
	  }

   public E removeLast() throws Exception {
	    if (isEmpty())
	      throw new Exception("Deque is empty.");
	    DLNode<E> last = trailer.getPrev();
	    E o = last.getElement();
	    DLNode<E> secondtolast = last.getPrev();
	    trailer.setPrev(secondtolast);
	    secondtolast.setNext(trailer);
	    size--; 
	    return o;
	  }
   public void addLast(E o) { 
	   // dorobit
   }
   public E getLast() throws Exception {
     throw new Exception("dorobit");
   }
   public E removeFirst() throws Exception {
      throw new Exception("dorobit");
   }
}

