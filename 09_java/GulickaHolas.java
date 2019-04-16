import java.awt.Color;
import java.awt.Graphics;


public class GulickaHolas extends Thread {

	private final double TIME_SHIFT = 0.08;	//o aky cas sa posunie na jeden tik
	private final double BOUNCE = 0.8;		//pruznost lopticky
	private final double DIAMETER = 0.02;	//polomer lopticky
	private double x, y;			//pozicia
	private double x0, y0;			//pociatocna pozicia
	private double speedX, speedY;	//vektor rychlosti
	private double accX;			//akceleracia v X osi = vietor
	private double accY;			//akceleracia v Y osi = gravitacia
	private double timeX, timeY;	//casy pre X a Y os - su dva lebo sa nuluju pri pdraze od zeme/steny
	AppletGulickaHolas applet;
	
	public GulickaHolas(double x, double y, double speedX, double speedY, double accX, double accY, AppletGulickaHolas applet){

		if(accX != 0.0)
			timeX = speedX/accX + Math.sqrt( (speedX*speedX - accX*x*2)/(accX*accX) );
		else timeX = 0;
		if(accY != 0)
			timeY = speedY/accY + Math.sqrt( (speedY*speedY - accY*y*2)/(accY*accY) );
		else timeY = 0;
		
		this.accX = accX;
		this.accY = accY;
				
		this.speedX = speedX - accX*timeX;
		this.speedY = speedY - accY*timeY;

		x0 = (accX == 0.0) ? x : DIAMETER;
		y0 = (accY == 0.0) ? y : DIAMETER; 
				
		this.applet = applet;
		start();
	}
	
	public void run(){
		while(true){
			try {
				x = x0 + speedX*timeX + accX*timeX*timeX/2;
				y = y0 + speedY*timeY + accY*timeY*timeY/2;
				timeX += TIME_SHIFT; timeY += TIME_SHIFT;
				//naraz o zem:
				if(y-DIAMETER < 0){
					timeY = 0;
					speedY *= BOUNCE; 
					y0 = DIAMETER;
				}
				//naraz o stenu
				if(x-DIAMETER < 0 || x+DIAMETER > 1){
					timeX = 0;
					speedX *= -BOUNCE;					
					x0 = (x-DIAMETER <= 0) ? DIAMETER : 1-DIAMETER ;
				}
				applet.repaint();
				sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	int getXpixel(double x){
		return (int)(x * applet.sizeX);
	}	
	int getYpixel(double y){
		return applet.sizeY - (int)(y * applet.sizeY);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(getXpixel(x-DIAMETER), getYpixel(y+DIAMETER),
							 (int)(DIAMETER*applet.sizeX*2), (int)(DIAMETER*applet.sizeY*2));
		g.drawString("vietor = " + Math.abs(accX) + ((accX<0)?" dolava":" doprava") +
					 ", " + ((accY<0)?"gravitacia":"levitacia") + " = " + Math.abs(accY),
					 5, 15);
	}

}
