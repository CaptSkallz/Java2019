import java.awt.*;
import java.applet.*;
import java.util.*;

/**
 * Binarne hodinky
 * @author PB
 * @version 2009
 */
public class BinaryClock2 extends Applet {

	private static final long serialVersionUID = 1L;
	/**
	 * init nastavi velkost appletu a vytvori a spusti casovac 
	 */		
	public void init() {
		resize(300,200);
		Timer t = new Timer(false);	// vytvor timer
		t.schedule(new TimerTask() {	// definuj metodu, ktora
			public void run() {			// sa pusti pri kazdom ticku
				repaint();				// casovaca
			}
		},0, 1000);						// a to kazdych 1000 milisec.
	}
	/**
	 * reukrzivna procedura vykresli binarne cislo do appletu
	 * oproti verzi 1 sa len vymenila X-ova a Y-ova suradnica
	 * @param g - kam sa kresli
	 * @param positionX - suradnice zodpovedajuceho bitu v applete
	 * @param positionY - suradnice zodpovedajuceho bitu v applete
	 * @param n - binarne cislo
	 */		
	static void showBinaryNumber(Graphics g, int positionX, int positionY, int n) {
      if (n>0) {
		if (n%2 != 0)
			g.fillOval(positionX, positionY, 30, 30);
		else 
			g.drawOval(positionX, positionY, 30, 30);
		showBinaryNumber(g,positionX, positionY-50, n/2);
  	  }
	}
	public void paint(Graphics g) {
		Calendar calendar = new GregorianCalendar();
		getAppletContext().showStatus(calendar.getTime().toString());
		g.setColor(Color.RED);
		showBinaryNumber(g,40,150,calendar.get(Calendar.HOUR_OF_DAY)/10);	// desiatkova cifra hodin
		showBinaryNumber(g,70,150,calendar.get(Calendar.HOUR_OF_DAY)%10);	// jednotkova cifra hodin
		g.setColor(Color.GREEN);
		showBinaryNumber(g,120,150,calendar.get(Calendar.MINUTE)/10);		// desiatkova cifra minut
		showBinaryNumber(g,150,150,calendar.get(Calendar.MINUTE)%10);		// jednotkova cifra minut
		g.setColor(Color.BLUE);
		showBinaryNumber(g,200,150,calendar.get(Calendar.SECOND)/10);		// desiatkova cifra sekund
		showBinaryNumber(g,230,150,calendar.get(Calendar.SECOND)%10);		// jednotkova cifra sekund
	}
}