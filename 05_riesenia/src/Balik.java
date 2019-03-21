/** exercise 5 - implementation of Dequeue (Balik) using java.lang.ArrayList
 * 
 */

import java.util.ArrayList;

/** Balik is a double-ended queue: elements can be inserted and removed 
 * from both ends of the queue. In this exercise, we are implementing Balik
 * using the standard java ArrayList class.
 * @author Pavel
 */
public class Balik<E> {

	/** internal data structure */
	private ArrayList<E> l;
	
	/** constructor - creates empty Balik */
	public Balik()
	{
		l = new ArrayList<E>();
	}
	
	/** returns the number of elements in Balik */
	public int size()
	{
		return l.size();
	}
	
	/** returns true, if Balik is empty */
	public boolean isEmpty()
	{
		return l.isEmpty();
	}
	
	/** returns the first element of Balik, but does not delete it */
	public E getFirst() throws Exception
	{
		try 
		{
		  return l.get(0);
		} catch (IndexOutOfBoundsException e)
		  { 
			throw new Exception("Dequeue is Empty");
		  }
	}

	/** returns the last element of Balik, but does not delete it */
	public E getLast() throws Exception
	{
		try 
		{
		  return l.get(l.size() - 1);
		} catch (IndexOutOfBoundsException e)
		  {
			throw new Exception("Dequeue is Empty");
		  }
	}
	
	/** inserts new element to Balik at the very beginning */
	public void addFirst (E element)
	{
		try { l.add(0, element); } 
		catch (IndexOutOfBoundsException e) {} // never occurs
	}
	
	/** inserts new element to Balik at the very end */
	public void addLast (E element)
	{
		try { l.add(l.size(), element); } 
		catch (IndexOutOfBoundsException e) {} // never occurs
	}
	
	/** returns the first element of Balik and deletes it */
	public E removeFirst() throws Exception
	{
		try 
		{
		  return l.remove(0);
		} catch (IndexOutOfBoundsException e)
		  { 
			throw new Exception("Dequeue is Empty");
		  }
	}
	
	/** returns the last element of Balik and deletes it */
	public E removeLast() throws Exception
	{
		try 
		{
		  return l.remove(l.size() - 1);
		} catch (IndexOutOfBoundsException e)
		  {
			throw new Exception("Dequeue is Empty");
		  }
	}
	
	/** Testing program: inserts a few elements to Balik, and then retrieves them.
	 * @param args - program takes no arguments
	 */
	public static void main(String args[]) throws Exception
	{
		Balik<Integer> b = new Balik<Integer>();
		
		b.addFirst(1);
		b.addLast(2);
		b.addFirst(3);
		b.addLast(4);
		b.addFirst(5);
		b.addLast(6);
	
		System.out.println("size: " + b.size());
		System.out.println(b.getFirst());
		System.out.println(b.getLast());
		System.out.println(b.removeFirst());
		System.out.println(b.removeLast());
		System.out.println(b.removeFirst());
		System.out.println(b.removeLast());
		System.out.println("size: " + b.size());
		while (!b.isEmpty())
			System.out.println(b.removeFirst());
	}

}
