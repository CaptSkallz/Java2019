package H3.uloha1;

import java.util.ArrayList;

public class ArrayPF<E> implements FrontInterface<E> {
	ArrayList<Elem> list;
	public ArrayPF(int size) {
		 list = new ArrayList<Elem>(size);
	}
	@Override
	public void enqueue(E elem, int prio) {
		int index = 0;
		while(index < list.size() && prio < list.get(index).getPrior()){ index++; }
		list.add(index, new Elem<E>(prio,elem,null));
	}

	@Override
	public String toString() {
		return "uloha1.ArrayPF{" +
				"list=" + list +
				'}';
	}

	@Override
	public E dequeue() {
		if(isEmpty()){
			return null;
		}
		return (E)list.remove(0).getElement();
	}

	@Override
	public boolean isEmpty() {
		if(list.size()==0){
			return true;
		}
		return false;
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
