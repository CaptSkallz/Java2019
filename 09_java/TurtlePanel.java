import java.awt.*;
import java.applet.*;

public class TurtlePanel extends Panel {
TurtleThread thread ;
TurtleCanvas can;
int init, delta, uhol;

  public TurtlePanel(int init, int delta, int uhol, Color col) {
	setForeground(col);
	can = new TurtleCanvas(this);
	can.setSize(200,200);
	add(can);
	this.init =init;
	this.delta = delta;
	this.uhol = uhol;
    thread = new TurtleThread(can);
    thread.start();
  }		  
}