public class SimpleThread2 extends Thread {
  private int countDown = 5;
  private int threadIndex;
  private static int threadCount = 0;
  public SimpleThread2() {
    super("" + (++threadCount)); 
	threadIndex = threadCount;
    start();  
  }
  public String toString() {
    return "#" + getName() + ": " + countDown;
  }
  public void run() {
	   while(true) {
	      System.out.println(this);
	       //if (threadIndex > 2)
	    	  for(long j=0; j<5000000000L; j++) {	// kým toto zráta
							// zapotí sa ...
	    		  double gg = 0-Math.PI*j*Math.PI; 
	    	  }
	      if(--countDown == 0) return;
	    }
	  }
  public static void main(String[] args) {
    for(int i = 0; i < 15; i++)
      new SimpleThread2();
  }
}
	