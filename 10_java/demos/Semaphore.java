package demos;

public class Semaphore {
  private int value;

  public Semaphore (int initial) {
      value = initial;
  }

  synchronized public void release() {
      ++value;
      notifyAll();  
  }

  synchronized public void aquire() throws InterruptedException {
      while (value==0) wait();
      --value;
  }
}

