import java.util.*;

public class ArrayListSynchronized extends Thread {
	List al = Collections.synchronizedList(new ArrayList());
	int counter = 0;
	
	// 
	synchronized 
	public void add() {
		//System.out.println("add "+counter);
		al.add(counter); counter++;
	}
	
	// 
	synchronized 
	public void delete() {
		if (al.indexOf(counter-1) != -1) {
			//System.out.println("delete "+(counter-1));
			al.remove(counter-1); counter--;
		}
	}
}

