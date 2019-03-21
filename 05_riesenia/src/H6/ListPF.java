package H6;
public class ListPF<E> implements FrontInterface<E> {
	
	Elem<E> from;
	
	public ListPF(){
		this.from = null;
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



	@Override
	public void enqueue(E elem, int prio) {
		if (isEmpty()) {
			from = new Elem(prio, elem, null);
		}
		else if (prio < from.getPrior()) {
			Elem<E> pom_from = from;
			from = new Elem(prio, elem, pom_from);
		}
		else {
			Elem<E> predchadzajuci = from;
			Elem<E> nasledujuci = from.getNext();
			while(true) {
				if (nasledujuci == null) {
					predchadzajuci.setNext(new Elem<E>(prio, elem,null));
					break;
				}
				if (prio <= nasledujuci.getPrior()) {
					predchadzajuci.setNext(new Elem<E>(prio, elem,nasledujuci));
					break;
				}
				predchadzajuci = nasledujuci;
				nasledujuci = nasledujuci.getNext();
			}
		}
	}



	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		
		
		E pom_e = from.getElement();
		this.from = this.from.getNext();
		
		return pom_e;
	}



	@Override
	public boolean isEmpty() {
		return (this.from == null);
	}   
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Elem<E> pom_from = from;
		
		while(pom_from != null) {
			sb.append(pom_from.getElement()+" ");
			sb.append(pom_from.getPrior());
			pom_from = pom_from.getNext();
		}
		return sb.toString();
	}
}
