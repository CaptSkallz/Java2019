import java.awt.*;
import java.applet.*;
import java.util.*;

/**
 * Binarne hodinky
 * @author PB
 * @version 2009
 */
public class BinaryClock extends Applet {
	private static final long serialVersionUID = 1L;
	/**
	 * init nastavi velkost appletu a vytvori a spusti casovac 
	 */
	public void init() {
		resize(400,180);		// velkost okna
		Timer t = new Timer(false);	// vytvor timer
		t.schedule(new TimerTask() {	// definuj metodu, ktora
			public void run() {			// sa pusti pri kazdom ticku
				repaint();				// casovaca
			}
		},0, 1000);						// a to kazdych 1000 milisec.
	}
	/**
	 * reukrzivna procedura vykresli binarne cislo do appletu
	 * @param g - kam sa kresli
	 * @param positionX - suradnice zodpovedajuceho bitu v applete
	 * @param positionY - suradnice zodpovedajuceho bitu v applete
	 * @param n - binarne cislo
	 */
	static void showBinaryNumber(Graphics g, int positionX, int positionY, int n) {
      if (n>0) {						// koniec rekurzie
		if (n%2 != 0)					// ak je parne
			g.fillOval(positionY, positionX, 30, 30);	// kresli plny kruh
		else 							// ak je neparne
			g.drawOval(positionY, positionX, 30, 30);	// kresli kruh bez vyplne
		showBinaryNumber(g,positionX, positionY-50, n/2);	// a chod do rekurzie
  	  }
	}
	/**
	 * metoda, ktora prekresluje applet, zavola sa z metody run v TimerTasku
	 */
	public void paint(Graphics g) {
		Calendar calendar = new GregorianCalendar();	// zisti si datum a cas
		getAppletContext().showStatus(calendar.getTime().toString());	// vypis do spodneho riadku appletu cas
		g.setColor(Color.RED);							// cervenymi gulickami nakresli hodiny
		showBinaryNumber(g,30,300,calendar.get(Calendar.HOUR_OF_DAY));
		g.setColor(Color.GREEN);						// zelenymi gulickami kresli minuty
		showBinaryNumber(g,80,300,calendar.get(Calendar.MINUTE));
		g.setColor(Color.BLUE);							// modrymi gulickami kresli sekundy
		showBinaryNumber(g,130,300,calendar.get(Calendar.SECOND));
	}
}
