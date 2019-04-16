import java.awt.*;
import java.applet.Applet;

public class Ball extends Applet  {
	public int sizeX = 200;
	public int sizeY = 213;
    Gulicka th;
    Gulicka th2;
	
	public void init() {
	  resize(sizeX,sizeY);
	}
	public void start() {
  	  th = new Gulicka(this);
      //th.setPriority(Thread.MAX_PRIORITY);
	  th.start();
	  th2 = new Gulicka(this);
      //th2.setPriority(Thread.MIN_PRIORITY);
      th2.start();
	}
	public void paint(Graphics g) {
      g.drawRect(0,0,sizeX-1,sizeY-1);
      g.setColor(Color.BLUE);
	  g.drawRect(th.x,th.y,2,2);
	  g.setColor(Color.RED);
	  g.drawRect(th2.x,th2.y,2,2);
	}
}