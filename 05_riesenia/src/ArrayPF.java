public class ArrayPF<E> implements FrontInterface<E> {
	E[] f;
	int[] prior;
	int first = 0;
	int last = 0;
	int size = 0;

	public ArrayPF(int size) {
		f = (E[]) new Object[size];
		prior = new int[size];
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
		int i = first;
		int i_1 = mod(first-1);
		first = i_1;
		while(prior[i] < prio && i != last) {
			f[i_1] = f[i];
			prior[i_1] = prior[i];
			i = mod(i+1);
			//i++;
			//i %= size;
			i_1 = mod(i_1+1);
			//i_1++;
			//i_1 %= size;
		}
		f[i_1] = elem;
		prior[i_1] = prio;
	}

	@Override
	public E dequeue() {
		if (isEmpty())
			return null;
		else {
			E el = f[first];
			first++;
			first %= size;
			return el;
		}
	}

	@Override
	public boolean isEmpty() {
		return first == last;
	}

	 public static void main(String[] args) {
		 ArrayPF<String> f = new ArrayPF<String>(100);
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
