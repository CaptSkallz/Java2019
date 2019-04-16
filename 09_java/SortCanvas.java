import java.applet.*;
import java.awt.*;

public class SortCanvas extends Canvas {
SortPanel sp;
int lo, hi;

	public SortCanvas(SortPanel sp) {
		this.sp = sp;
	}
     public void swap(int i, int j) {
       lo = i; hi = j;
     }
     public void update(Graphics g) {
     	g.clearRect(0,0,200,200);
    	Color cl = g.getColor();
     	for(int i=0; i < sp.a.length; i++) {
   			g.setColor((i == lo || i == hi)?Color.BLACK:cl);
    		g.drawLine(2*i,sp.a[i],2*i,0);
    		//g.drawLine(2*i,sp.a[i],2*i,sp.a[i]);
     	}
     }
    public void paint(Graphics g) {
    	update(g);
    }

}
