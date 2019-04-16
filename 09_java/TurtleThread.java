import java.applet.*;
import java.awt.*;

public class TurtleThread extends Thread {
TurtleCanvas can;

  public TurtleThread(TurtleCanvas can) {
	  this.can = can;
  }
  public void run() {
	 while(true) {
		 can.steps++;
		 can.repaint();
		try{ sleep(100);} catch(Exception e) {}
	 }
  }
}
