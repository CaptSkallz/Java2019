import java.applet.*;
import java.awt.*;

public class Rucicka extends Applet implements Runnable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int width, height;
   int i = 0;
   Thread t = null;

   public void init() {
      width = getSize().width;
      height = getSize().height;
      setBackground( Color.BLUE );
   }

   public void start() {
     t = new Thread( this );
     t.start();
   }
public void run() {
    try {
       while (true) {
           i++; i %= 60;
           repaint();
           t.sleep( 1000 ); 
        }
    } catch (InterruptedException e) { /*…*/ }
}

public void paint( Graphics g ) {
   g.setColor( Color.ORANGE );
   g.drawLine( width/2, height/2,                          	(int)(width/2+width/3*Math.cos(i/30.0*Math.PI)),                                                                                    	(int)(height/2+height/3*Math.sin(i/30.0*Math.PI)));
}
}
