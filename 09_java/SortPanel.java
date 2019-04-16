import java.awt.*;
import java.applet.*;

public class SortPanel extends Panel {
SortThread thread ;
SortCanvas can;
String algo;
int[] a;

	public SortPanel(String algo, Color col) {
		setForeground(col);
		can = new SortCanvas(this);
		can.setSize(200,200);
		add(can);
		this.algo = algo;
	}	
    public void start() {
    	a = new int[100];
    	for(int i=0; i < a.length; i++)
    		a[i] = (int)(200*Math.random());
        thread = new SortThread(can, algo, a);
        thread.start();
    }
}