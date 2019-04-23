package demos;

class Fork {

  private boolean taken=false;
  private PhilCanvas display;
  private int identity;

  Fork(PhilCanvas disp, int id) { 
	  display = disp; identity = id;
  }

  synchronized void put() {
    taken=false;
    display.setFork(identity,taken);
    notify();
    System.out.println("Fork " + identity + " is released");
  }

  synchronized void get() throws java.lang.InterruptedException {
    while (taken) wait();
    System.out.println("Fork " + identity + " is taken");
    taken=true;
    display.setFork(identity,taken);
  }
}
