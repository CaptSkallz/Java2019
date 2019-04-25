import java.util.Random;

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
 * Jednoducha aplikacia s jednym namornikom, ktory chodi nahodne po kruhovom mole a
 * utopi sa
 */
public class NamornikAppFxCvicenie extends Application {

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
	/** sirka*/
	int sizeX = 600;
	/** vyska */
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
	private NamornikFx namornik2;

	public NamornikCanvas() {
		this.setWidth(sizeX);
		this.setHeight(sizeY);
		// TODO
		// vytvor namornika
		namornik = new NamornikFx(this);
		namornik2 = new NamornikFx(this);
		
		namornik.start();
		namornik2.start();
		// spusti jeho simulaciu
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
			// TODO
			//... kresli ziveho namornika
			gc.drawImage(namornik.img, namornik.getXPixel(true), namornik.getYPixel(true));
		
		} else { // ak uz je utopeny, nakresli vlny
			// TODO
			//... kresli utopeneho namornika
		}
		if(namornik2.alive) {
			gc.drawImage(namornik2.img, namornik2.getXPixel(true), namornik2.getYPixel(true));
			
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
	/** poloha namornika - x */
	double x;
	/** poloha namornika - y */
	double y;
	/** obrazok namornika - je zdielany medzi vsetkymi namornikmi */
	static Image img;
	/** maximalna velkost kroku namornika v x-ovej a y-ovej zlozke */
	double stepSize = 1.75;
	/** referencia na canvas, kde sa namornik tacka */
	NamornikCanvas molo;
	/** ci namornik este nespadol do vody */
	boolean alive = true;
	/** velkost najvacsieho kruhu vlniek po utopeni namornika */
	final int waves = 30;
	/** pocet krokov namornika */
	int nsteps = 0;


	public NamornikFx(NamornikCanvas where) {
		/** vytvori namornika v bode (0,0) */
		molo = where; // zapamataj si referenciu na canvas s molom
		// pre istotu synchronizujeme, aby sme obrazok urcite nacitali iba raz
		if (img == null) // ak obrazok este nie je nacitany, nacitaj ho
			img = new Image("file:namornik.gif");
		// zaciname v bode (0,0)
		x = 0.0;
		y = 0.0;
	}

	public void run() {
		/** main() metoda pre namornika - nahodny pohyb */
		while (Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(molo.moloSize, 2) /* TODO: kym sa drzi na mole */ ) {
			// TODO
			System.out.println(x + " " + y);
			// sem pride simulacia pohybu namornika
			nsteps++;
			Random r = new Random();
			x += r.nextInt(3) - 1;
			y += r.nextInt(3) - 1;
			Platform.runLater(()->molo.paintCanvas());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
