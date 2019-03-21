package H3.uloha2;

public class Elem<E extends Comparable<E>> implements Comparable<Elem<E>>{
  private E element;
  private Elem<E> next;
 
  public Elem(E new_element, Elem<E> new_next){
    next = new_next;
    element = new_element;
  }

  public Elem<E> getNext(){
    return next;  
  }
  public E getElement(){
      return element;
  }
  public void setNext(Elem<E> new_next){
    next = new_next;  
  }

  @Override
  public int compareTo(Elem<E> o) {
    return element.compareTo(o.getElement());
  }
}