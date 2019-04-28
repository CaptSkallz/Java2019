package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Jednoduchy applet s jednym namornikom, ktory chodi nahodne po kruhovom mole a
 * utopi sa
 */
public class NamornikAppFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane(new NamornikCanvas()); // do panelu vlozime Canvas
		Scene scene = new Scene(pane);				// vytvor scenu
		primaryStage.setTitle("Opity namornik"); 	// pomenuj okno aplikacie,
													// javisko
		primaryStage.setScene(scene); 	// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 			// zobraz javisko
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class NamornikCanvas extends Canvas {
	/** sirka appletu */
	int sizeX = 600;
	/** vyska appletu */
	int sizeY = 600;
	/** suradnice stredu mola (x) */
	int centerX = sizeX / 2;
	/** suradnice stredu mola (y) */
	int centerY = sizeY / 2;
	/** skalovaci koeficient na prepocitavanie molovych a pixlovych suradnic */
	int scale = 25;
	/** velkost mola v molovych suradniciach */
	int moloSize = 10;
	/** na mole chodi jeden namornik */
	private NamornikFx namornik;

	public NamornikCanvas() {
		this.setWidth(sizeX);
		this.setHeight(sizeY);
		namornik = new NamornikFx(this);	// vytvorime vlakno pre namornika
		namornik.start();					// nastartujeme
	}

	public void paintCanvas() {
		GraphicsContext gc = getGraphicsContext2D();	// kreslenie do canvasu
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(Color.gray(0, 0.2));
		gc.fillOval(centerX - scale * moloSize, centerY - scale * moloSize,
					scale * 2 * moloSize, scale * 2 * moloSize);
		/**
		 * vykresli namornika na jeho aktualnych suradniciach do grafickej
		 * plochy
		 */
		if (namornik.alive) { // ak sa este neutopil, nakresli obrazok namornika
			gc.drawImage(new Image("namornik.gif"),  // namornik.img, 
					namornik.getXPixel(false),
					namornik.getYPixel(false));
		} else { // ak uz je utopeny, nakresli vlny
			// kolko v molovych suradniciach potrebujeme, aby bol stred vln od
			// kraja mola?
			double sinkingDistance = (double) namornik.waves / (double) scale;
			// kde presne clupol do vody
			double sinkingPoint = Math.sqrt(
					namornik.x * namornik.x
					+ namornik.y * namornik.y)
					- moloSize;
			// posun suradnice namornika tak, aby bol bod utopenia dostatocne
			// daleko od mola,
			// aby bolo mozne nakreslit vlny, ktore sa nekrizuju s molom
			if (sinkingPoint < sinkingDistance) {
				namornik.x *= 1 + (sinkingDistance - sinkingPoint) / moloSize;
				namornik.y *= 1 + (sinkingDistance - sinkingPoint) / moloSize;
			}
			// zobraz v strede vln pocet krokov
			gc.setStroke(Color.RED);
			gc.strokeText(Integer.toString(namornik.nsteps),
					namornik.getXPixel(true) - 8, 
					namornik.getYPixel(true) + 7);
			gc.setStroke(Color.BLUE);
			int nwaves = 7; // kolko vln nakreslit?
			for (int i = 3; i <= nwaves; i++)
				// vnutorne 2 vlny sa nekreslia
				gc.strokeOval(
						namornik.getXPixel(true) - i * namornik.waves / nwaves, 
						namornik.getYPixel(true) - i * namornik.waves/ nwaves, 
						i * 2 * namornik.waves / nwaves, 
						i * 2 * namornik.waves / nwaves);
		}
	}
}

/**
 * @author PP
 */

/**
 * Trieda reprezentuje opiteho namornika, ktory sa nahodne pohybuje po kruhovom
 * mole az kym nespadne do vody. Namornik je vizualizovany obrazkom zo suboru
 * namornik.gif alebo po utopeni sustrednym vlnenim na povrchu hladiny.
 */
class NamornikFx extends Thread {
	double x;
	/** poloha namornika - x */
	double y;
	/** poloha namornika - y */
	static Image img;
	/** obrazok namornika - je zdielany medzi vsetkymi namornikmi */
	double stepSize = 1.75;
	/** maximalna velkost kroku namornika v x-ovej a y-ovej zlozke */
	NamornikCanvas molo;
	/** referencia na applet, kde sa namornik tacka */
	boolean alive = true;
	/** ci namornik este nespadol do vody */
	final int waves = 30;
	/** velkost najvacsieho kruhu vlniek po utopeni namornika */
	int nsteps = 0;

	/** pocet krokov namornika */

	public NamornikFx(NamornikCanvas where) {
		/** vytvori namornika v bode (0,0) */
		molo = where; // zapamataj si referenciu na applet s molom
		// pre istotu synchronizujeme, aby sme obrazok urcite nacitali iba raz
		if (img == null) // ak obrazok este nie je nacitany, nacitaj ho
			img = new Image("namornik.gif");
		// zaciname v bode (0,0)
		x = 0.0;
		y = 0.0;
	}

	public void run() {
		/** main() metoda pre namornika - nahodny pohyb */
		// kym sa drzi na mole
		while (x * x + y * y < molo.moloSize * molo.moloSize) {
			// chvilu pockaj
			try {
				sleep(200);
			} catch (InterruptedException e) {
			}
			x += Math.random() * 2 * stepSize - stepSize; // urob nahodny krok
			y += Math.random() * 2 * stepSize - stepSize;
			// a aktualizuj zobrazenie
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					molo.paintCanvas();
				}
			});
			nsteps++;
		}
		// ak spadol z mola, nie je nazive
		alive = false;
	}

	/**
	 * konvertuje molove suradnice na pixelove suradnice (x)
	 * 
	 * @param center
	 *            - ci chceme stred namornika (true) alebo jeho lavy horny roh
	 *            (false)
	 */
	public double getXPixel(boolean center) {
		return molo.centerX + (int) (x * molo.scale)
				- (center ? 0 : (img.getWidth() / 2));
	}

	/**
	 * konvertuje molove suradnice na pixelove suradnice (y)
	 * 
	 * @param center
	 *            - ci chceme stred namornika (true) alebo jeho lavy horny roh
	 *            (false)
	 */
	public double getYPixel(boolean center) {
		return molo.centerY + (int) (y * molo.scale)
				- (center ? 0 : (img.getHeight() / 2));
	}
}
