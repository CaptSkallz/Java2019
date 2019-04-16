import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Image.*;
import java.applet.Applet;

public class Korytnacky extends Applet {
	public int sizeX = 400;
	public int sizeY = 400;
    Korytnacka k1;
	Korytnacka k2;
	Korytnacka k3;
	Korytnacka k4;
	BufferedImage bufImage = 
		new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);

	public void init() {
	  resize(sizeX,sizeY);
	}
	public void start() {
	  k1 = new Korytnacka(this, 100,100, 0, 1, 91); k1.start();
	  k2 = new Korytnacka(this, 300, 300, 0, 1, 122); k2.start();
	  k3 = new Korytnacka(this, 200, 100, 0, 1, 171); k3.start();
	  k4 = new Korytnacka(this, 100, 300, 0, 1, 59); k4.start();
    }
	public void paint(Graphics g) {
	  g.drawImage(bufImage,0,0,Color.BLUE,this);
	}
}
