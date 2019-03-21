
public class ListPF<E> implements FrontInterface<E>{
	Elem<E> first;
	public ListPF() {
		first = null;
	}
	@Override
	public void enqueue(E elem, int prio) {
		if (first == null) {
			first = new Elem<E>(prio, elem, null);
		} else if (first.getPrior() >= prio) {
			first = new Elem<E>(prio, elem, first);			
		} else {
			Elem<E> index_1 = null;
			Elem<E> index = first;
			while (index.getPrior() < prio) {
				index_1 = index;
				index = index.getNext();
				if (index == null)
					break;
			}
			index_1.setNext(new Elem<E>(prio, elem, index));			
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			return null;
		else {
			E e =  first.getElement();
			first = first.getNext();
			return e;
		}
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	 public static void main(String[] args) {
		 ListPF<String> f = new ListPF<String>();
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
