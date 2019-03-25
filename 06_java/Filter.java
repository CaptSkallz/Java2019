import java.util.*;

public class Filter {

	static <E> boolean cond(E elem) {
		return true; 		// nejako doplnit 
	}
	
	// ako prejsù collection, nechaù x takÈ, ûe platÌ cond(x)
	static <E> void filter(Collection<E> c) {
	    for (Iterator<E> it = c.iterator(); it.hasNext(); )
	        if (!cond(it.next()))		// cond je logick· podmienka
	            it.remove();
	}

	// ako prejsù collection, nechaù x takÈ, ûe platÌ cond(x)
	static void filter1(Collection<?> c) {
	    for (Iterator<?> it = c.iterator(); it.hasNext(); )
	        if (!cond(it.next()))		// cond je logick· podmienka
	            it.remove();
	}

	// ako prejsù collection, nechaù x takÈ, ûe platÌ cond(x)
	static void filter2(Collection c) {
	    for (Iterator it = c.iterator(); it.hasNext(); )
	        if (!cond(it.next()))		// cond je logick· podmienka
	            it.remove();
	}
	
	// ako ju vypÌsaù
	static <E> void printCollection(Collection<E> c) {
		for (Iterator<E> it = c.iterator(); it.hasNext(); )
	        System.out.print(it.next());
        System.out.println();
	}

	// ako ju vypÌsaù
	static void printCollection1(Collection<?> c) {
		for (Iterator<?> it = c.iterator(); it.hasNext(); )
	        System.out.print(it.next());
        System.out.println();
	}
	
	static void printCollection2(Collection c) {
		for (Iterator it = c.iterator(); it.hasNext(); )
	        System.out.print(it.next());
        System.out.println();
	}
	static void printCollection3(Collection c) {
		for (Object o : c)
	        System.out.print(o);
        System.out.println();
	}
	static <E> void printCollection4(Collection<E> c) {
		for (E o : c)
	        System.out.print(o);
        System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();  // homogenny list
		al.add(1);
		al.add(2);
		// al.add("wow");								// toto nepojde
		filter(al);
		filter1(al);
		filter2(al);
		printCollection(al);		
		printCollection1(al);		
		printCollection2(al);
		printCollection3(al);
		printCollection4(al);
		
		ArrayList alo = new ArrayList();			// heterogenny list
		alo.add(1);	alo.add("wow");
		filter(alo);
		filter1(alo);
		filter2(alo);
		printCollection(alo);		
		printCollection1(alo);		
		printCollection2(alo);		
		printCollection3(alo);		
		printCollection4(alo);		
	}
}
