package demos;

import javafx.scene.paint.Color;

class DisplaySemaphore extends Semaphore {

    NumberCanvas display_;
    int count_; //shadow value for display

    DisplaySemaphore(NumberCanvas t, int val) {
        super(val);
        count_=val;
        display_=t;
        display_.setcolor(Color.CYAN);
        display_.setvalue(count_);
    }

    synchronized public void release() {
        super.release();
        ++count_;
        display_.setvalue(count_);
        try {Thread.sleep(200);}
        catch (InterruptedException e){Thread.currentThread().interrupt();} //reassert
    }

    synchronized public void aquire() throws InterruptedException{
       super.aquire();
       --count_;
       display_.setvalue(count_);
    }
}

