public class Hruska implements Comparable<Hruska>, Clonable {
	static int allInstances = 0;
	private int instanceIndex;
	private int size;
	public Hruska(int size) { 
		this.size = size;
		instanceIndex = allInstances++;
		System.out.println("create Hruska " + instanceIndex);
	}
	public Hruska copy()  {
   	  System.out.println("copy Hruska " + instanceIndex);
	  return new Hruska(size);
   	 }
	@Override
	public int compareTo(Hruska inaHruska) {
		return new Integer(this.size).compareTo(inaHruska.size);
	}
	public String toString() {
		return "som hruska " + size;
	}
}