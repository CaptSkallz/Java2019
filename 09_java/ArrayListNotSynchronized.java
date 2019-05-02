import java.util.*;

public class ArrayListNotSynchronized extends Thread {
	ArrayList<Integer> al = new ArrayList<Integer>();
	int counter = 0;
	//not	
	synchronized 
	public void add() {
		//System.out.println("add "+counter);
		al.add(counter); counter++;
	}
	//not 	
	synchronized
	public void delete() {
		//System.out.println("index "+(counter-1));
		if (al.indexOf(counter-1) != -1) {
			//System.out.println("delete "+(counter-1));
			try {
				al.remove(counter-1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("index=" + al.indexOf(counter-1) + ", size= " + al.size());
				throw e;
			}
			counter--;
		}
	}
}

