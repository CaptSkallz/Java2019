
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BojovniciFx extends Application {
  final int N = 100;
  Bojovnik[] bojovnik = new Bojovnik[N]; // pole vsetkych bojovnikov
  Color[] cols = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};
  
	@Override
	public void start(Stage primaryStage) {
		
		Playground playground = new Playground();
		
		Scene scene = new Scene(playground, 450, 450);// vytvor scenu

		// nastavena vazieb
		scene.widthProperty().addListener(ov -> playground.setW((int) scene.getWidth()));
		scene.heightProperty().addListener(ov -> playground.setH((int) scene.getHeight()));

		primaryStage.setTitle("Bojovnici"); // pomenuj okno
																	// aplikacie,
																	// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	
	}
 
  public static void main(String[] args) {
		launch(args);
	}
}


class Playground extends Pane {
	// Clock pane's width and height
	private int w = 450, h = 450;
	final static int N = 100;
	BojovnikFx[] bojovnik; 		// pole vsetkych bojovnikov
	Color[] cols = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};

	public Playground() {
		Random rnd = new Random();
		bojovnik = new BojovnikFx[N]; // pole vsetkych bojovnikov
		for(int i=0; i<N; i++)	// vytvorenie bojovnikov
	  	  bojovnik[i] = new BojovnikFx(this, rnd.nextInt(w),rnd.nextInt(h),
	  		 cols[rnd.nextInt(cols.length)]);
		for(int i=0; i<N; i++) {	// priradenie zabijaka a stitu
		  bojovnik[i].zabijak(bojovnik[(i+1)%N]);
		  bojovnik[i].stit(bojovnik[(i>0)?i-1:N-1]);
		}
		for(int i=0; i<N; i++)	// spustenie
		  bojovnik[i].start();
	}

	public void update() {
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	protected void paintPlayground() {
		getChildren().clear();
				for(int i=0; i<N; i++) {
					Circle c = new Circle((int)Math.round(bojovnik[i].x),
							  			(int)Math.round(bojovnik[i].y),3);
					c.setFill(bojovnik[i].col);
					c.setStroke(Color.BLACK);
					getChildren().add(c);
				}
	}
}

class BojovnikFx extends Thread {
	public double  x,y; //public int x,y;
	public Color col;
	BojovnikFx killer, defender;
	Playground ap;
	
	public BojovnikFx(Playground ap, int x, int y, Color col) {
		this.x = x; this.y = y; this.col = col;
		this.ap = ap;
	}
	public void zabijak(BojovnikFx killer) {
		this.killer = killer;
	}
	public void stit(BojovnikFx defender) {
		this.defender = defender;
	}
	public void run() {
	  while (true) {
		  // suradnice bodu, kam sa treba postavit, aby
		  // defender bol v strede medzi mnou a killerom
		  double xx = 2*defender.x - killer.x;
		  double yy = 2*defender.y - killer.y;
		  // parameter lenivosti (0-1)
		  //double laziness = 0.5;
		  double laziness = 0.1;
		  //double laziness = 0.01;
		  x = (xx-x)*laziness+x;
		  y = (yy-y)*laziness+y;
		  /*
		  Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ap.paintPlayground();
				}
			});
		  */
		  Platform.runLater(() ->ap.paintPlayground());
		  try { sleep(100); } catch(Exception e) {}
	  }
	}
}

