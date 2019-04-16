public class PriorityThread extends Thread {
	private int countDown = 5;
	private volatile double d = 0; // No optimization
	private static int threadCount = 0;
	private int priority = 0;

	public PriorityThread(int priority) {
		super("" + ++threadCount);
		setPriority(priority);
		this.priority = priority;
		start();
	}
	public String toString() {
	    return "#" + getName() + ": " + countDown + ", priority: " + priority;
	  }
	public void run() {
		while (true) {
			for (long j = 0; j < 500000000L; j++) { // kým toto zráta
				 //d = d + (Math.PI * Math.E) / (double) j;
				 double gg = 0-Math.PI+j+j-j+Math.PI;
			}
			System.out.println(this);
			if (--countDown == 0) {
				System.out.println("done #" + getName());
				return;
			}

		}
	}

	public static void main(String[] args) {
		
		new PriorityThread(Thread.MAX_PRIORITY);
		for (int i = 0; i < 15; i++)
			new PriorityThread(Thread.MIN_PRIORITY);
		
		/*
		for (int i = 1; i <= 10; i++)
			new PriorityThread(i);
			*/
	}
}
