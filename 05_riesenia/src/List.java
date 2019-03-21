// Cvicenie 5

class Node<E> {
  private E element;
  private Node<E> next;

  public Node() { this(null, null);  }

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

class EmptyListException extends RuntimeException 
{  
	  public EmptyListException(String err) {
		super(err);
	  }
}

public class List<E> 
{
    Node<E> h;
    int size;
    
	public List() 
	{
		h = null;
		size = 0;
	}

	// list from array
	public List(E a[])
	{
		h = null; size = 0;
		for (int i = a.length - 1; i >= 0; i--)
			setHead(a[i]);
	}

	public boolean isEmpty()
	{
		return (h == null);	
	}

	// returns first element of list 
	public E head() throws EmptyListException
	{
		if (h == null) throw new EmptyListException("List is empty");
		return h.getElement();
	}

	// returns remaining elements of list, but does not remove it
	public List<E> tail() throws EmptyListException
	{
		if (h == null) throw new EmptyListException("List is empty");
		List<E> l = new List<E>();
		l.h = h.getNext();
		l.size = size - 1;
		return l;
	}

	// inserts new element at the list beginning
	public void setHead(E newElem) 
	{
		Node<E> n = new Node<E>(newElem,h);
		h = n;
		size++;
	}

	//  text representation of list
	public String toString() 
	{
		StringBuilder ret = new StringBuilder(size * 2 + 2);
		ret.append('(');
		Node<E> p = h;
		while (p != null)
		{
          ret.append(((E)(p.getElement())).toString() + " ");
          p = p.getNext();
		}
		if (ret.length() > 1) ret.setCharAt(ret.length() - 1, ')');
		else ret.append(')');
		return ret.toString();
	}

	public int size()
	{
		return size;
	}

	// appends list y to the end of the list, the list will now share the elements!
	public void append(List<E> y) 
	{
		if (h == null) 
		{
			h = y.h;
			size = y.size;
		}
		else
		{
			Node<E> t = h;
			while(t.getNext() != null) t = t.getNext();
			t.setNext(y.h);
			size += y.size;
		}
	}
	
	public static void main(String args[]) {
		List<Integer> l1 = new List<Integer>();
		l1.setHead(1);
		l1.setHead(2);
		l1.setHead(3);
		System.out.println(l1);
		List<Integer> l2 = new List<Integer>();
		l2.setHead(5);
		l2.setHead(6);
		l2.setHead(7);
		System.out.println(l2);
		l1.append(l2);
		while (!l1.isEmpty()) {
			System.out.println("head: " + l1.head());
			l1 = l1.tail();
		}
	}	
}
