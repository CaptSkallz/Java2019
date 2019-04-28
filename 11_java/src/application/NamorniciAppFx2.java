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

// Cvicenie 9, priklad 2 - Dvaja opiti namornici

/**
 * Trieda reprezentuje polohu namornika, je synchronizovana, aby nemohlo dojst k
 * polovicnemu zapisaniu hodnoty
 */
class PolohaFx {
	private double x;
	private double y;

	public PolohaFx(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public synchronized double getX() {
		return x;
	}

	public synchronized double getY() {
		return y;
	}

	public synchronized void setX(double x) {
		this.x = x;
	}

	public synchronized void setY(double y) {
		this.y = y;
	}

	public synchronized void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

/**
 * Trieda reprezentuje opiteho namornika, ktory sa nahodne pohybuje po kruhovom
 * mole az kym nespadne do vody. Namornik je vizualizovany obrazkom zo suboru
 * namornik.gif alebo po utopeni sustrednym vlnenim na povrchu hladiny.
 */
class NamornikFx2 extends Thread {
	/** poloha namornika - x */
	PolohaFx p;
	/** obrazok modreho namornika - je zdielany medzi vsetkymi namornikmi */
	static Image modryNamornik;
	/** obrazok modreho sediaceho namornika */
	static Image modryNamornikSedi;
	/** obrazok cerveneho namornika - je zdielany medzi vsetkymi namornikmi */
	static Image cervenyNamornik;
	/** obrazok cerveneho sediaceho namornika */
	static Image cervenyNamornikSedi;
	/** maximalna velkost kroku namornika v x-ovej a y-ovej zlozke */
	double stepSize = 1.5;
	/** referencia na applet, kde sa namornik tacka */
	NamorniciCanvas molo;
	/** ci namornik este nespadol do vody */
	boolean alive = true;
	/** velkost najvacsieho kruhu vlniek po utopeni namornika */
	final int waves = 30;
	/** cislo namornika */
	int id;
	/** farba namornika */
	boolean somModry;
	/** ktorym smerom teraz pojde (X) */
	double smerX;
	/** ktorym smerom teraz pojde (X) */
	double smerY;
	/** ktorym smerom isiel naposled (X) */
	double poslednySmerX;
	/** ktorym smerom isiel naposled (Y) */
	double poslednySmerY;
	/** ako dlho este budeme pit rum */
	int budeSediet;
	/** ci prave pije rum */
	boolean sedi;
	/** pocet krokov namornika */
	int nsteps = 0;

	/** vytvori namornika v bode (0,0) */
	public NamornikFx2(NamorniciCanvas where, boolean modry, int id, double x,
			double y) {
		// cislo namornika
		this.id = id;
		// farba namornika
		somModry = modry;
		// nepije rum
		sedi = false;
		// zapamataj si referenciu na applet s molom
		molo = where;
		// pre istotu synchronizujeme, aby sme obrazky urcite nacitali iba raz
		//synchronized (this.getClass()) {
		synchronized (NamorniciAppFx2.class) {
			// ak obrazok este nie je nacitany, nacitaj ho
			if (modryNamornik == null) {
				modryNamornik = new Image("namornik.gif");
				modryNamornikSedi = new Image("namorniks.gif");
				cervenyNamornik = new Image("namornik2.gif");
				cervenyNamornikSedi = new Image("namornik2s.gif");
			}
		}
		// zaciname v bode (x,y)
		p = new PolohaFx(x, y);
	}

	/** namornik sa ma posadit */
	void sadni() {
		if (sedi)
			return; // ak uz sedi, nemusi sadat
		budeSediet = 5; // ako dlho bude pit
		sedi = true;
	}

	/** namornik ma vstane, ak uz dopil */
	void vstan() {
		// ak nesedi, nema co vstavat
		if (!sedi)
			return;
		// ak pije rum, rataj cas
		if (budeSediet > 0)
			budeSediet--;
		else // ak uz dopil, vstane a vyda sa opacnym smerom
		{
			sedi = false;
			smerX = -poslednySmerX;
			smerY = -poslednySmerY;
		}
	}

	/** main() metoda pre namornika - nahodny pohyb */
	public void run() {
		// kym sa drzi na mole
		while (p.getX() * p.getX() + p.getY() * p.getY() < molo.moloSize
				* molo.moloSize) {
			// chvilu pockaj
			try {
				sleep(200);
			} catch (InterruptedException e) {
			}
			// urob nahodny krok
			smerX = Math.random() * 2 * stepSize - stepSize;
			smerY = Math.random() * 2 * stepSize - stepSize;

			// pokus sa vstat, ak si dopil
			vstan();
			if (!sedi) {
				p.setXY(p.getX() + smerX, p.getY() + smerY);
				nsteps++; // zapocitavaj kazdy krok
				poslednySmerX = smerX; // zapamataj si posledny smer
				poslednySmerY = smerY;
				// informuj molo o svojej polohe (ak sme blizko ineho namornika,
				// posadi nas)
				molo.polohaNamornika(id, p);
			}
			// a aktualizuj zobrazenie
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					molo.paintCanvas();
				}
			});
		}
		// ak spadol z mola, nie je nazive
		alive = false;
		// prekresli este raz - tento raz len kruhy na hladine
		// a aktualizuj zobrazenie
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				molo.paintCanvas();
			}
		});

	}

	/**
	 * konvertuje molove suradnice na pixelove suradnice (x)
	 * 
	 * @param center
	 *            - ci chceme stred namornika (true) alebo jeho lavy horny roh
	 *            (false)
	 */
	public double getXPixel(boolean center) {
		return molo.centerX + (int) (p.getX() * molo.scale)
				- (center ? 0 : modryNamornik.getWidth() / 2);
	}

	/**
	 * konvertuje molove suradnice na pixelove suradnice (y)
	 * 
	 * @param center
	 *            - ci chceme stred namornika (true) alebo jeho lavy horny roh
	 *            (false)
	 */
	public double getYPixel(boolean center) {
		return molo.centerY + (int) (p.getY() * molo.scale)
				- (center ? 0 : modryNamornik.getHeight() / 2);
	}

	/** vykresli namornika na jeho aktualnych suradniciach do grafickej plochy */
	public void paintNamornik(GraphicsContext gc) {
		if (alive) // ak sa este neutopil, nakresli obrazok namornika
		{
			if (sedi) {
				if (somModry)
					gc.drawImage(modryNamornikSedi, getXPixel(false),
							getYPixel(false));
				else
					gc.drawImage(cervenyNamornikSedi, getXPixel(false),
							getYPixel(false));
			} else {
				if (somModry)
					gc.drawImage(modryNamornik, getXPixel(false),
							getYPixel(false));
				else
					gc.drawImage(cervenyNamornik, getXPixel(false),
							getYPixel(false));
			}
		}

		else // ak uz je utopeny, nakresli vlny
		{
			// kolko v molovych suradniciach potrebujeme, aby bol stred vln od
			// kraja mola?
			double sinkingDistance = (double) waves / (double) molo.scale;
			// kde presne clupol do vody
			double sinkingPoint = Math.sqrt(p.getX() * p.getX() + p.getY()
					* p.getY())
					- molo.moloSize;
			// posun suradnice namornika tak, aby bol bod utopenia dostatocne
			// daleko od mola,
			// aby bolo mozne nakreslit vlny, ktore sa nekrizuju s molom
			if (sinkingPoint < sinkingDistance) {
				p.setXY(p.getX()
						* (1 + (sinkingDistance - sinkingPoint) / molo.moloSize),
						p.getY()
								* (1 + (sinkingDistance - sinkingPoint)
										/ molo.moloSize));
			}
			// zobraz v strede vln pocet krokov
			gc.setStroke(Color.RED);
			gc.strokeText(Integer.toString(nsteps), getXPixel(true) - 8,
					getYPixel(true) + 7);
			gc.setStroke(Color.BLUE);
			int nwaves = 7; // kolko vln nakreslit?
			for (int i = 3; i <= nwaves; i++)
				// vnutorne 2 vlny sa nekreslia
				gc.strokeOval(getXPixel(true) - i * waves / nwaves,
						getYPixel(true) - i * waves / nwaves, i * 2 * waves
								/ nwaves, i * 2 * waves / nwaves);
		}
	}
}

class NamorniciCanvas extends Canvas {
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
	/** na mole chodia dvaja namornici */
	private NamornikFx2 namornik1;
	/** na mole chodia dvaja namornici */
	private NamornikFx2 namornik2;

	/** posledna znama poloha namornikov (X) */
	double namorniciX[] = new double[2];
	/** posledna znama poloha namornikov (Y) */
	double namorniciY[] = new double[2];

	public NamorniciCanvas() {
		this.setWidth(sizeX);
		this.setHeight(sizeY);
		namornik1 = new NamornikFx2(this, true, 0, -1.5, 0); // vytvorime vlakno
																// pre namornika
		namornik2 = new NamornikFx2(this, false, 1, 1.5, 0); // vytvorime vlakno
																// pre namornika
		namornik1.start(); // nastartujeme
		namornik2.start(); // nastartujeme
	}

	public void paintCanvas() {
		GraphicsContext gc = getGraphicsContext2D(); // kreslenie do canvasu
		gc.clearRect(0, 0, sizeX, sizeY);
		gc.setFill(Color.gray(0, 0.2));
		gc.fillOval(centerX - scale * moloSize, centerY - scale * moloSize,
				scale * 2 * moloSize, scale * 2 * moloSize);
		/**
		 * vykresli namornika na jeho aktualnych suradniciach do grafickej
		 * plochy
		 */
		namornik1.paintNamornik(gc);
		namornik2.paintNamornik(gc);
	}

	/** registruje aktualne polohy namornikov a posadi ich, ak su blizko seba */
	public synchronized void polohaNamornika(int id, PolohaFx p) {
		namorniciX[id] = p.getX(); // zapamataj polohu namornika
		namorniciY[id] = p.getY();

		// skontroluj, ci nie su blizko seba - ak ano, sadnu a piju
		if (((namorniciX[0] - namorniciX[1]) * (namorniciX[0] - namorniciX[1]) + (namorniciY[0] - namorniciY[1])
				* (namorniciY[0] - namorniciY[1])) < 1.0) {
			namornik1.sadni();
			namornik2.sadni();
		}
	}
}

/**
 * Jednoduchy applet s jednym namornikom, ktory chodi nahodne po kruhovom mole a
 * utopi sa
 */
public class NamorniciAppFx2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane(new NamorniciCanvas()); // do panelu vlozime Canvas
		Scene scene = new Scene(pane); // vytvor scenu
		primaryStage.setTitle("Opiti namornici"); // pomenuj okno aplikacie,
													// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}

	public static void main(String[] args) {
		launch(args);
	}
}
