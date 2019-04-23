package demos;

class FixedPhilosopher extends Thread {

    int identity;
    boolean stopRequested = false;
    PhilCanvas view;
    Diners controller;
    Fork left;
    Fork right;

    FixedPhilosopher(Diners controller, int identity, Fork left, Fork right) {
        this.controller = controller;
        this.view = controller.display;
        this.identity = identity;
        this.left = left;
        this.right = right;
    }

    public void run() {
        while (!stopRequested) {
      	  	try {
                //thinking
                view.setPhil(identity,PhilCanvas.THINKING);
                sleep(controller.sleepTime());
                //hungry
                view.setPhil(identity,PhilCanvas.HUNGRY);
                //get forks
                if (identity%2 == 0) {
                    left.get();
                    view.setPhil(identity,PhilCanvas.GOTLEFT);
                } else {
                    right.get();
                    view.setPhil(identity,PhilCanvas.GOTRIGHT);
                }
                sleep(200);
                if (identity%2 == 0)
                    right.get();
                else
                    left.get();
                //eating
                view.setPhil(identity,PhilCanvas.EATING);
                sleep(controller.eatTime());
                right.put();
                left.put();
             } catch (java.lang.InterruptedException e) {}
        }
    }


    public void stopRequested() {
        stopRequested = true;
    }
}
