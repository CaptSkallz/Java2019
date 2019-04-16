
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PendulumFx extends Application {
	Random rnd = new Random();
	private static Kyvadlo1 red = new Kyvadlo1(Math.PI / 2, 100, 10, Color.RED);
	private static Kyvadlo1 blue = new Kyvadlo1(-Math.PI / 4, 150, 15, Color.BLUE);
	private static Kyvadlo1 green = new Kyvadlo1(-Math.PI / 2, 200, 13, Color.GREEN);
	private static Kyvadlo1 yellow = new Kyvadlo1(Math.PI / 4, 50, 19,Color.YELLOW);
	static ArrayList<Kyvadlo1> ks = new ArrayList<>(Arrays.asList(new Kyvadlo1[]{red, blue, green, yellow}));
	
	@Override
	public void start(Stage primaryStage) {
		KyvadloPanel panel = new KyvadloPanel();
			
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), 
			x -> {
				for (Kyvadlo1 k : PendulumFx.ks)
					k.update();
				panel.paintBallPane();
			}));
				
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		Timeline animation2 = new Timeline(new KeyFrame(Duration.millis(10_000), 
				x -> {
					PendulumFx.ks.add(new Kyvadlo1(
							rnd.nextDouble()*Math.PI-Math.PI/2, 
							rnd.nextInt(100)+100,
							rnd.nextInt(20)+10,
							Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))));}));
			animation2.setCycleCount(Timeline.INDEFINITE);
			animation2.play(); // Start animation
		
		Scene scene = new Scene(panel, 450, 450);// vytvor scenu

		primaryStage.setTitle("Kyvadlo"); 	// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class KyvadloPanel extends Pane {
	protected void paintBallPane() {
		getChildren().clear();
		double klinecX = getWidth() / 2, klinecY = getHeight() / 4;
		for (Kyvadlo1 k : PendulumFx.ks) {
	        double ballX = klinecX + (Math.sin(k.dajUhol()) * k.dajDlzku());
	        double ballY = klinecY + (Math.cos(k.dajUhol()) * k.dajDlzku());
	        Line l = new Line(klinecX, klinecY, ballX, ballY);
	        l.setStroke(Color.BLACK);
	        Circle c1 = new Circle(klinecX, klinecY, 7);
	        c1.setStroke(Color.BLACK);
	        c1.setFill(Color.BLACK);
	        Circle c2 = new Circle(ballX, ballY, k.dajVelkost());
	        c2.setStroke(Color.BLACK);
	        c2.setFill(k.dajFarbu());
	        getChildren().addAll(l, c1, c2);
		}
	}
}

class Kyvadlo1 {
    private double uhol;
    private double velkost;
    private int dlzkaLanka;
    private double resist = 1;
    private Color farba;
    public Kyvadlo1(double pociatocnyUhol, int dlzkaLanka, double velkost, Color farba) {
		super();
		this.uhol = pociatocnyUhol;
		this.dlzkaLanka = dlzkaLanka;
		this.farba = farba;
		this.velkost = velkost;
	}
    private double uhlovaRychlost = 0;
	public void update() {
		double dt = 0.1;
		double angleAccel = -9.81 / dlzkaLanka * Math.sin(uhol);
        uhlovaRychlost += angleAccel * dt;
        uhlovaRychlost  *= resist;
        resist *= 0.999999;
        uhol += uhlovaRychlost * dt;
    }
	public double dajUhol() {
		return uhol;
	}
	public double dajDlzku() {
		return dlzkaLanka;
	}
	public Color dajFarbu() {
		return farba;
	}
	public double dajVelkost() {
		return velkost;
	}
}
