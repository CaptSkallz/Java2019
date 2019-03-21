package H6;
public class ArrayPriorityFront<E> implements FrontInterface<E>{

	E[] pole1;
	int[] pole2;
	int from;
	int to;
	
	public ArrayPriorityFront(int size){
		this.pole1 = (E[]) new Object[size];
		this.pole2 = new int[size];
		from = 0;
		to = 0;
	}
	
	
	
	public static void main(String[] args) {
		ArrayPriorityFront<String> x = new ArrayPriorityFront<>(17);
		System.out.println(x.modulo(-1));
		System.out.println(x);
		ArrayPriorityFront<String> f = new ArrayPriorityFront<String>(100);
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

	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = this.from;
		for (int j = i; j != to; j = modulo(j+1)) {
			sb.append(this.pole1[j]+" ");
			sb.append(this.pole2[j]);
			if (modulo(j+1) != to) sb.append(", ");
		}
		return sb.toString();
	}

	@Override
	public void enqueue(E elem, int prio) {
		int from_0 = this.from;
		if(isEmpty()) {
			this.pole1[to] = elem;
			this.pole2[to] = prio;
			to = modulo(to + 1);
		}
		else {
			int from_1 = modulo(from_0-1);
			while(this.pole2[from_0] <= prio && from_0 != to) {
				this.pole1[from_1] = this.pole1[from_0];
				this.pole2[from_1] = this.pole2[from_0];
				from_1 = from_0;
				from_0 = modulo(from_0 + 1);
			}
			this.pole1[from_1] = elem;
			this.pole2[from_1] = prio;
			from = modulo(from-1);
		}
	}

	int modulo(int num) {
		if(num >= 0){
			return num % this.pole1.length;
		}
		return (num + pole1.length) % pole1.length;
	}

	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		E pom = this.pole1[from];
		this.pole1[from] = null;
		from = modulo(from+1);
		return pom;
	}



	@Override
	public boolean isEmpty() {
		return (this.from == this.to);
	}

}
