import java.applet.*;
import java.awt.*;

public class SortApplet extends Applet {
SortPanel buble, quick, merge, random;

	public void init() {
	  setSize(800,200);	
	  setLayout(new GridLayout(1,4));
	  buble = new SortPanel("Buble",Color.MAGENTA);
 	  add(buble);
	  quick = new SortPanel("Quick",Color.BLUE);
 	  add(quick);
	  merge = new SortPanel("Merge",Color.RED);
 	  add(merge);
	  random = new SortPanel("Random",Color.GREEN);
	  add(random);
	}
	 public void start() {
	  buble.start();
	  quick.start();
	  merge.start();
	  random.start();
	}
}
