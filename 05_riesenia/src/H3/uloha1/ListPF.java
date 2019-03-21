package H3.uloha1;

public class ListPF<E> implements FrontInterface<E>{
	Elem<E> first;
	public ListPF() {

	}
	@Override
	public void enqueue(E elem, int prio) {
		Elem pom1 = first;
		Elem nova = new Elem<E>(prio,elem,null);
		if(first == null){
			first = nova;
		}
		else {
			if (pom1.getNext() == null) {
				if(pom1.getPrior()> prio){
					pom1.setNext(nova);
				}
				else{
					first = nova;
					first.setNext(pom1);
				}
			} else {
				Elem pom2 = first.getNext();

				while (pom1.getNext() != null && prio < pom2.getPrior()) {
					pom1 = pom1.getNext();
					pom2 = pom1.getNext();
				}
				if(nova.getPrior() > pom1.getPrior()){
					first = nova;
					nova.setNext(pom1);
				}
				else {
					pom1.setNext(nova);
					nova.setNext(pom2);
				}
			}
		}
	}

	@Override
	public E dequeue() {
		if(isEmpty()){
			return null;
		}
		Elem result = first;
		Elem pom = first.getNext();
		first = pom;
		return (E)result.getElement();
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public String toString() {
		return "uloha1.ListPF{" +
				"first=" + first +
				'}';
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
