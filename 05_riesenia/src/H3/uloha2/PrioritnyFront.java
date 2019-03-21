/** PrioritnyFront - cvicenie 5
 * 
 */
package H3.uloha2;
import java.util.PriorityQueue;

/** Java contains a class PrioirtyQueue, we can utilize it for our purpose, 
 *  and simply implement a wrapper.
 */
public class PrioritnyFront<E> 
{
	PriorityQueue<E> q;
	/** a wrapper class for elements with integer priority */
	public PrioritnyFront() {
		q = new PriorityQueue<E>();
	}
	/** add new element to priority queue */
	public void enqueue(E elem) {
		q.add(elem);
	}
	/** remove (and return) the element with the highest priority from queue */
	public E dequeue() {
		return q.poll();
	}
	/** return the element with the highest priority from queue, but do not remove it */ 
	public E front() {
    	return q.peek();
	}
	
	/** return the number of elements that remain in the queue */
	public int size()
	{
		return q.size();
	}
	
	/** Test program - inserts random elements to priority queue and retrieves them
	 * @param args - program takes no arguments
	 */
	public static void main(String[] args) 
	{
		// create our implementation of the priority queue
		PrioritnyFront<Integer> f = new PrioritnyFront<Integer>();
		
		// generate 10 random numbers 0-49 and insert them on the queue
		// with the same priority as their value
		for (int i = 0; i < 10; i++)
		{
			int x = (int)(50 * Math.random());
			f.enqueue(x);
		}
		
		// now remove the numbers from the queue in the priority order and print them
		for (int i = 0; i < 10; i++)
			System.out.print(f.dequeue() + ",");
		if (f.size() == 0) System.out.println("empty");

		PrioritnyFront<String> p = new PrioritnyFront<String>();
		p.enqueue("b");
		p.enqueue("c");
		p.enqueue("a");
		while (p.size() > 0) {
			System.out.print(p.dequeue() + ", ");
		}
	}
}
