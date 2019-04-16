import java.applet.*;
import java.awt.*;

public class Turtles extends Applet {
	TurtlePanel c1, c2, c3, c4;

		public void init() {
		  setSize(400,400);	
		  setLayout(new GridLayout(2,2));
		  c1 = new TurtlePanel(0,1,91,Color.MAGENTA);
	 	  add(c1);
		  c2 = new TurtlePanel(70,1,161,Color.BLUE);
	 	  add(c2);
		  c3 = new TurtlePanel(10,1,189,Color.RED);
	 	  add(c3);
		  c4 = new TurtlePanel(0,1,121,Color.GREEN);
		  add(c4);
		}
}
