public class YieldingThread extends Thread {
  private int countDown = 5;
  private static int threadCount = 0;
  public YieldingThread() {
    super("" + ++threadCount);
    start();
  }
  public String toString() {
	    return "#" + getName() + ": " + countDown;
	  }
  public void run() {
	   while(true) {
	      System.out.println(this);
    	  for(long j=0; j<500000000L; j++) {	// kým toto zráta
							// zapotí sa ...
	         double gg = 0-Math.PI+j+j-j+Math.PI;
         	 //yield();
	         if (j % 100000000L == 0) {
	         	 yield();
	         }
	      }
	      //yield();
	      if(--countDown == 0) return;
	    }
	  }
  public static void main(String[] args) {
    for(int i = 0; i < 15; i++)
      new YieldingThread();
  }
} 
