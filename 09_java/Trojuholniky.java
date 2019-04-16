import java.awt.*;
import java.applet.Applet;

public class Trojuholniky extends Applet  {
	Vrcholy th;
	public int sizeX = 600;
	public int sizeY = 600;
	Color[] cols = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};
	 
	
	public void init() {
	  resize(sizeX,sizeY);
	}
	public void start() {
	  th = new Vrcholy(this);
	  th.start();
	}
	public void paint(Graphics g) {
	  g.drawRect(0,0,sizeX-1,sizeY-1);
      for(int i=0; i<th.N; i++) {
    	g.setColor(cols[i%cols.length]);  
  	    g.drawRect(th.x[i],th.y[i],3,3);
		int i1 = i-1;
		int i2 = i+1;
		if (i1 < 0) i1 = th.N-1;
		if (i2 == th.N) i2 = 0;
  	    g.drawLine(th.x[i1]-1,th.y[i1]-1,th.x[i2]-1,th.y[i2]-1);
  	    g.drawLine(th.x[i]-1,th.y[i]-1,th.x[i2]+1,th.y[i2]+1);
  	    g.drawLine(th.x[i1]+1,th.y[i1]+1,th.x[i]+1,th.y[i]+1);
//  	    g.drawLine(th.x[i1],th.y[i1],th.x[i2],th.y[i2]);
//  	    g.drawLine(th.x[i],th.y[i],th.x[i2],th.y[i2]);
//  	    g.drawLine(th.x[i1],th.y[i1],th.x[i],th.y[i]);
      }
	}
}