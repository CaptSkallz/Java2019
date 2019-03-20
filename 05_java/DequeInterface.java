
public interface DequeInterface<E> {

  public int size();
  public boolean isEmpty();

  public E getFirst() throws Exception;
  public E getLast() throws Exception;

  public void addFirst (E element); 
  public void addLast (E element); 

  public E removeFirst() throws Exception;
  public E removeLast() throws Exception;
}

