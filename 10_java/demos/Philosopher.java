package demos;

class Philosopher extends Thread {
  private int identity;
  private PhilCanvas view;
  private Diners controller;
  private Fork left;
  private Fork right;
  private int slowDown = 1;//25;

  Philosopher(Diners ctr, int id, Fork l, Fork r) {
    controller = ctr; view = ctr.display;
    identity = id; left = l; right = r;
  }

  public void run() {
    try {
      while (true) {
        //thinking
        view.setPhil(identity,PhilCanvas.THINKING);
        sleep(slowDown * controller.sleepTime());
        //hungry
        view.setPhil(identity,PhilCanvas.HUNGRY);
        
        right.get();
        //gotright chopstick
        System.out.println("Philosopher " + identity + " has the right");
        view.setPhil(identity,PhilCanvas.GOTRIGHT);
        
        sleep(slowDown * 200);
        left.get();
        System.out.println("Philosopher " + identity + " has both");
        //eating
        System.out.println("Philosopher " + identity + " is eating");
        view.setPhil(identity,PhilCanvas.EATING);
        sleep(slowDown * controller.eatTime());
        left.put();
        //gotright chopstick
        view.setPhil(identity,PhilCanvas.GOTRIGHT);
        right.put();
      }
    } catch (java.lang.InterruptedException e) {}
  }
}
