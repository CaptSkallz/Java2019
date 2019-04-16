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
public class NamornikAppFxCvicenie1 extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane(new NamornikCanvas1()); // do panelu vlozime Canvas
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

class NamornikCanvas1 extends Canvas {
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
	private NamornikFx1 namornik;

	public NamornikCanvas1() {
		this.setWidth(sizeX);
		this.setHeight(sizeY);
		// vytvor namornika
		namornik = new NamornikFx1(this);
		// spusti jeho simulaciu
		namornik.start();
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
			if (NamornikFx.img != null)
				gc.drawImage(NamornikFx.img, 
					namornik.getXPixel(false),
					namornik.getYPixel(false));
			else 
				System.out.println("null");
		} else { // ak uz je utopeny, nakresli vlny
			//... kresli utopeneho namornika			
		}
	}
}


/**
 * Trieda reprezentuje opiteho namornika, ktory sa nahodne pohybuje po kruhovom
 * mole az kym nespadne do vody. Namornik je vizualizovany obrazkom zo suboru
 * namornik.gif alebo po utopeni sustrednym vlnenim na povrchu hladiny.
 */
class NamornikFx1 extends Thread {
	double x;
	/** poloha namornika - x */
	double y;
	/** poloha namornika - y */
	static Image img;
	/** obrazok namornika - je zdielany medzi vsetkymi namornikmi */
	double stepSize = 1.75;
	/** maximalna velkost kroku namornika v x-ovej a y-ovej zlozke */
	NamornikCanvas1 molo;
	/** referencia na canvas, kde sa namornik tacka */
	boolean alive = true;
	/** ci namornik este nespadol do vody */
	final int waves = 30;
	/** velkost najvacsieho kruhu vlniek po utopeni namornika */
	int nsteps = 0;

	/** pocet krokov namornika */

	public NamornikFx1(NamornikCanvas1 where) {
		/** vytvori namornika v bode (0,0) */
		molo = where; // zapamataj si referenciu na canvas s molom
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
		while (alive /*...*/) {
			System.out.println("namornik zije");
			//...alive sem pride simulacia pohybu namornika
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
