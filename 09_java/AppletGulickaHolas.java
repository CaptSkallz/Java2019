import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class AppletGulickaHolas extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4280742240730908774L;
	int sizeX = 600;			/* sirka appletu */
	int sizeY = 600;			/* vyska appletu */
	private BufferedImage bufImage;	/* aby applet neblikal, kreslime do obrazka v pamati */
	private GulickaHolas gula;

	public void init(){
		resize(sizeX, sizeY);
	    bufImage = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
	    gula = new GulickaHolas(	0,		//tu si nastav startovnu poziciu na X osi
	    					0.9,	//tu si nastav startovnu poziciu na Y osi
	    					0.18,	//tu si nastav startovnu rychlost v smere osi X 
	    					0,		//tu si nastav startovnu rychlost v smere osi X
	    					-0.005,	//tu si nastav silu vetra (zrychlenie v smere osi X)
	    					-0.02,	//tu si nastav silu gravitacie (zrychlenie v smere osi Y)
	    					this);
	}
	

	public void update(Graphics g) {/* predefinujeme update(), aby applet neblikal */
		paint(g);
	}

	public void paint(Graphics g) {	/* vykreslenie grafickeho obsahu appletu */
		Graphics bg = bufImage.getGraphics();	// kreslime do obrazka v pamati
		// zmaz pozadie
		bg.setColor(new Color(255,255,255));
		bg.fillRect(0, 0, sizeX, sizeY);
		//nakresli gulicku
		gula.paint(bg);
		// zobraz obrazok z pamate v applete
		g.drawImage(bufImage, 0, 0, this);
	}

}
