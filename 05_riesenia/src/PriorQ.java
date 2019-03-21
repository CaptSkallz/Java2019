import java.util.PriorityQueue;

public class PriorQ<E> implements FrontInterface<E> {
	PriorityQueue<Elem<E>> pq = null;
	
	public PriorQ() {
		pq = new PriorityQueue<Elem<E>>();
	}
	
	@Override
	public void enqueue(E elem, int prio) {
		pq.add(new Elem(prio, elem, null));
	}

	@Override
	public E dequeue() {
		if (!isEmpty()) {
			E e = pq.peek().getElement();
			pq.remove();
			return e;
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return pq == null || pq.size() == 0;
	}
	
	 public static void main(String[] args) {
		 PriorQ<String> f = new PriorQ<String>();
	        f.enqueue(new String("janka"), 5);
	        f.enqueue(new String("danka"), 2);
	        f.enqueue(new String("hanka"), 1);
	        f.enqueue(new String("anka"), 4);
	        f.enqueue(new String("zuzanka"), 3);
	        f.enqueue(new String("elenka"), 1);
	        f.enqueue(new String("zofka"), 6);
	        f.enqueue(new String("evka"), 4);
	        System.out.println(f);
	        while (!f.isEmpty())
	            System.out.println(f.dequeue());    
	    }		
}
