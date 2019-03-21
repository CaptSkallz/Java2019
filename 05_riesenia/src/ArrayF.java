public class ArrayF<E> implements FrontInterface<E> {
	E[] f;
	int first = 0;
	int last = 0;
	int size = 0;

	public ArrayF(int size) {
		f = (E[]) new Object[size];
		first = 0;
		last = 0;
		this.size = size;

	}
	private int mod(int index) {
		if (index >=0)
			return index%size;
		else
			return size-(-index)%size;
	}
	@Override
	public void enqueue(E elem, int prio) {
		f[last] = elem;
		last = mod(last+1);
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			return null;
		else {
			E el = f[first];
			first = mod(first+1);
			return el;
		}
	}

	@Override
	public boolean isEmpty() {
		return first == last;
	}

	 public static void main(String[] args) {
		 ArrayF<String> f = new ArrayF<String>(100);
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
