import java.awt.*;
import java.applet.*;

public class Diners extends Applet {

	private static final long serialVersionUID = -4657334990219842034L;
	PhilCanvas display;
    Thread[] phil= new Thread[PhilCanvas.NUMPHILS];
    Fork[] fork = new Fork[PhilCanvas.NUMPHILS];
    Scrollbar slider;
    Button restart;
    Button freeze;
    boolean fixed = false;

    public void init() {
    	resize(350,350);
        setLayout(new BorderLayout());
        fixed = false; //                    // toto si nastav na true ;-)
        display = new PhilCanvas(this);
        display.setSize(300,320);
        add("Center",display);
        slider = new Scrollbar(Scrollbar.HORIZONTAL, 50, 5, 0, 100);
        restart = new Button("Restart");
        freeze = new Button("Freeze");
        Panel p1 = new Panel();
        p1.setLayout(new BorderLayout());
        p1.add("Center",slider);
        p1.add("East",restart);
        p1.add("West",freeze);
        add("South",p1);
    }

    Thread makePhilosopher(Diners d, int id, Fork left, Fork right) {
        if (fixed)
            return new FixedPhilosopher(d,id,left,right);
         else
            return new Philosopher(d,id,left,right);
        }

    public int sleepTime() {
        return (slider.getValue()*(int)(100*Math.random()));
    }

    public int eatTime() {
        return (slider.getValue()*(int)(50*Math.random()));
    }

    public void start() {
       for (int i =0; i<display.NUMPHILS; ++i)
            fork[i] = new Fork(display,i);
       for (int i =0; i<display.NUMPHILS; ++i){
            phil[i] = makePhilosopher(this,i,
                        fork[i],
                        fork[(i-1+display.NUMPHILS)% display.NUMPHILS]
                        );
            phil[i].start();
       }
    }

    public void stop() {
        for (int i =0; i<display.NUMPHILS; ++i) {
            phil[i].interrupt();
            phil[i].stop();      //required for java 1.1
        }
    }

public boolean handleEvent(Event event) {
        if (event.id != event.ACTION_EVENT) {
            return super.handleEvent(event);
        } else if(event.target==restart) {
            if (display.deadlocked()) {
                stop();
                slider.setValue(50);
                start();
            }
            display.thaw();
            return true;
        } else if (event.target == freeze) {
            display.freeze();
            return true;
        } else
            return super.handleEvent(event);
    }
} 
