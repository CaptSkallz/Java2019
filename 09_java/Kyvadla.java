
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// zakomentovane triedy su hint pre to, co mate/mozete pouzit
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Line;
import javafx.stage.Stage;
//import javafx.util.Duration;

public class Kyvadla extends Application {
	Random rnd = new Random();
	private static Kyvadlo red = new Kyvadlo(Math.PI / 2, 100, 10, Color.RED);
	private static Kyvadlo blue = new Kyvadlo(-Math.PI / 4, 150, 15, Color.BLUE);
	private static Kyvadlo green = new Kyvadlo(-Math.PI / 2, 200, 13, Color.GREEN);
	private static Kyvadlo yellow = new Kyvadlo(Math.PI / 4, 50, 19,Color.YELLOW);
	static ArrayList<Kyvadlo> ks = new ArrayList<>(Arrays.asList(new Kyvadlo[]{red, blue, green, yellow}));
	
	@Override
	public void start(Stage primaryStage) {
		KyvadloPane panel = new KyvadloPane();
			
		// Timeline animation =
		// Timeline animation2 = 
		
		Scene scene = new Scene(panel, 450, 450);// vytvor scenu

		primaryStage.setTitle("Kyvadlo"); 		// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko		
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class KyvadloPane extends Pane {
	protected void paintBallPane() {
		getChildren().clear();
		double klinecX = getWidth() / 2, klinecY = getHeight() / 4;
		for (Kyvadlo1 k : PendulumFx.ks) {
			// kresli kyvadlo
			// ...
			getChildren().addAll();
		}
	}
}

class Kyvadlo {
    private double uhol;
    private double velkost;
    private int dlzkaLanka;
    private Color farba;
    public Kyvadlo(double pociatocnyUhol, int dlzkaLanka, double velkost, Color farba) {
		super();
		this.uhol = pociatocnyUhol;
		this.dlzkaLanka = dlzkaLanka;
		this.farba = farba;
		this.velkost = velkost;
	}
	public void update() {
		// ... sem pride ta fyzika
    }
}
