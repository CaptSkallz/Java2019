import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Hodinky extends DrawPanel {
	
	public Hodinky(int width, int height) {
		super(width, height);
	}
	
	public void paint(Graphics g) {
	  Graphics2D  g2d = (Graphics2D) g;
	  Rectangle2D backgr =  
		  new Rectangle2D.Double(0,0,getSize().getWidth(),getSize().getHeight());
	  g2d.fill(backgr);
	  g2d.setFont( new Font("Serif", Font.PLAIN, 32));
	  g2d.setPaint(Color.YELLOW);
	  g2d.drawString(new Date().toString(),20,40);
	}
   public static void main(String[] args) {
	 Hodinky hodinky = new Hodinky(600,100);  
	 DrawFrame.display("Hodinky", hodinky); 
	 Timer timer = new Timer(1000, hodinky);
	 timer.start();
   }
}
