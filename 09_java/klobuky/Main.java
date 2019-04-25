package klobuky;
	
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	StateK st;
	Playground pg;

	public static void main(String[] args) {
		  launch(args);
	}

	/*
	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			pg = new Playground();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		st = new StateK(3,2,3);
		//st = new StateK(2,1,2);
		//st = new StateK(4,3,4);
		//st = new StateK(6,5,6);
		//st = new StateK(7,6,7);
		//st = new StateK(16,15,16);
		pg = new Playground();

		Scene scene = new Scene(new Group(pg), 1000, 800);
		pg.widthProperty().bind(scene.widthProperty());
		pg.heightProperty().bind(scene.heightProperty());
		primaryStage.setScene(scene);
		primaryStage.show();
		pg.paint();
		Simulation s = new Simulation();
		s.start();
	}
	
	class Playground extends Canvas {
		Image bielyKlobuk;
		Image ciernyKlobuk;

		public Playground() {
			bielyKlobuk = new Image("bielyKlobuk.png", 70, 50, false, false);
			ciernyKlobuk = new Image("ciernyKlobuk.png", 70, 50, false, false);
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			double width = getWidth();
			double height = getHeight();
			gc.clearRect(0, 0, width, height);
			double polomer = width;
			if (height < polomer)
				polomer = height;
			polomer = 0.70 * polomer/2;
			double maly = polomer/3;
			double velky = polomer/2;
			gc.setFill(Color.YELLOW);
			gc.fillRect(0,0,width,height);
			
			gc.setStroke(Color.BLACK);
			gc.setFont(Font.font ("Verdana", 32));
			//gc.strokeText("Time: " + st.time + "/" + st.kolo, 10,120);
			gc.strokeText("Time: " + st.time , 10,120);

			double xx = 10;
			double yy = 20;
			for(int i = 0; i < st.biele; i++) {
				gc.drawImage(bielyKlobuk, xx, yy);
				xx += 75;
			}
			for(int i = 0; i < st.cierne; i++) {
				gc.drawImage(ciernyKlobuk, xx, yy);
				xx += 75;
			}
			
			for (int i = 0; i < st.pocet; i++) {
				double x = width*0.45 + polomer*Math.cos(i*2*Math.PI/st.pocet);
				double y = height/2 + polomer*Math.sin(i*2*Math.PI/st.pocet);
				if (st.mamfarbu[i] != null) {
					gc.setFill(st.mamfarbu[i] == st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.fillOval(x-velky/2, y-velky/2, velky, velky);
					gc.setLineWidth(2);
					gc.setStroke(st.mamfarbu[i] != st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.strokeOval(x-velky/2, y-velky/2, velky, velky);
				}
				gc.setFill(st.klobuky[i] == st.CIERNY ? Color.BLACK: Color.WHITE );
				gc.fillOval(x-maly/2, y-maly/2, maly, maly);
				gc.setStroke(st.klobuky[i] != st.CIERNY ? Color.BLACK: Color.WHITE );
				gc.setLineWidth(2);
				gc.strokeOval(x-maly/2, y-maly/2, maly, maly);
				if (st.crying[i]) {
					int oci = 5;
					gc.setFill(st.klobuky[i] != st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.fillOval(x+maly/4-7*oci, y+maly/4, 6*oci, 3*oci);			
				}
				if (st.seeing[i]) {
					int oci = 5;
					gc.setStroke(st.klobuky[i] != st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.setLineWidth(2);
					gc.strokeOval(x-maly/4-oci, y-maly/4, 2*oci, oci);
					gc.strokeOval(x+maly/4-oci, y-maly/4, 2*oci, oci);			
				}
				if (st.hearing[i]) {
					int usi = 20;
					//System.out.println("kreslim usi");
					//gc.setStroke(st.klobuky[i] != st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.setStroke(Color.RED);
					gc.setLineWidth(2);
					gc.setFill(st.klobuky[i] == st.CIERNY ? Color.BLACK: Color.WHITE );
					gc.fillOval(x-maly/2-usi, y-maly/2+usi, usi, 2*usi);
					gc.fillOval(x+maly/2-usi+20, y-maly/2+usi, usi, 2*usi);
				}
			}
		}
	}

	class Simulation extends Thread {
		public void run() {
			aa:
			while (true) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (st.time % 10 == 0) {
					st.kolo++;
					for (int i = 0; i < st.pocet; i++) { 
						st.seeing[i] = false;
						st.hearing[i] = false;
					}
					st.bielyvtomtokole = false;
				}
				st.time ++;
				if (st.time % 10 == 1)  {
					//System.out.println("counting");
					for (int i = 0; i < st.pocet; i++) {
						int b = 0;
						int c = 0;
						for (int j = 0; j < st.pocet; j++) {
							if (i != j) {
								if (st.klobuky[j] == st.CIERNY)
									c++;
								else
									b++;
							}
						}
						if (c == st.pocet-st.kolo) {
							st.seeing[i] = true;
							//System.out.println("seeing: " + i);
							st.mamfarbu[i] = st.BIELY;
							st.bielyvtomtokole = true;
						}
					}
				}
				if (st.time % 10 == 2)  {
					for (int i = 0; i < st.pocet; i++) {
						if (st.seeing[i])
							st.crying[i] = true;
					}
				}
				if (st.time % 10 == 3)  {
					//System.out.println("listinig");
					for (int i = 0; i < st.pocet; i++) {
						if (st.bielyvtomtokole && !st.seeing[i]) {
							st.hearing[i] = true;
							//System.out.println("hearing: " + i);
						}
					}
				}
				if (st.time % 10 == 4)  {
					// System.out.println("finish");
					for (int i = 0; i < st.pocet; i++) {
						if (st.hearing[i]) {
							st.mamfarbu[i] = st.CIERNY;
							st.crying[i] = true;
							//System.out.println("cierny: " + i);
						}
					}
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						pg.paint();
					}
				});
				for (int i = 0; i < st.pocet; i++) {
					if (st.mamfarbu[i] == null)
						continue aa;
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
				for (int i = 0; i < st.pocet; i++) { 
					st.seeing[i] = false;
					st.hearing[i] = false;
					st.crying[i] = false;
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						pg.paint();
					}
				});
				break;
			}
		}
	}
}

class StateK {
	Random rnd = new Random();
	int time;
	boolean bielyvtomtokole;
	int kolo;
	int biele;
	int cierne;
	int pocet;
	boolean []klobuky;
	boolean []seeing;
	boolean []hearing;
	boolean []crying;
	Boolean []mamfarbu;
	final boolean BIELY = false;
	final boolean CIERNY = true;
	
	public StateK(int biele, int cierne, int pocet) {
		this.biele = biele;
		this.cierne = cierne;
		this.pocet = pocet;
		klobuky = new boolean[pocet];
		seeing = new boolean[pocet];
		hearing = new boolean[pocet];
		crying = new boolean[pocet];
		mamfarbu = new Boolean[pocet];
		Random rnd = new Random();
		boolean []vsetkyFarby = new boolean[cierne+biele];
		for (int i = 0; i < biele; i++) {
			vsetkyFarby[i] = BIELY;
		}
		for (int i = biele; i < biele+cierne; i++) {
			vsetkyFarby[i] = CIERNY;
		}
		for (int i = 0; i < 20; i++) {
			int m = rnd.nextInt(cierne+biele);
			int n = rnd.nextInt(cierne+biele);
			boolean b = vsetkyFarby[m];
			vsetkyFarby[m] = vsetkyFarby[n];
			vsetkyFarby[n] = b;
		}
		for (int i = 0; i < pocet; i++) {
			klobuky[i] = vsetkyFarby[i];
			seeing[i] = false;
			crying[i] = false;
			hearing[i] = false;
			mamfarbu[i] = null;
		}
	}	
}

/*
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
*/
