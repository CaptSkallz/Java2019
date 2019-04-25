import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RucickyFxCvicenie extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ClockPaneCvicenie clock = new ClockPaneCvicenie();
		
		clock.setOnMouseClicked(ev -> {clock.bezi = !clock.bezi;});
		
		// Create a handler for animation
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime(); // Set a new clock time
		};

		// Create an animation for a running clock
		//Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {clock.setCurrentTime();}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
		
		Scene scene = new Scene(clock);	// vytvor scenu
		
		// nastavena vazieb
		scene.widthProperty().addListener(ov -> clock.setW(scene.getWidth()));
		scene.heightProperty().addListener(ov -> clock.setH(scene.getHeight()));
		
		primaryStage.setTitle("Hodinky"); 	// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class ClockPaneCvicenie extends Pane {
	private int hour;
	private int minute;
	private int second;
	boolean bezi = true;

	// Clock pane's width and height
	private double w = 450, h = 450;

	public ClockPaneCvicenie() {
		setPrefSize(w, h);
		setCurrentTime();
	}
	public void setH(double h) {
		this.h = h;
		paintClock();
	}
	public void setW(double w) {
		this.w = w;
		paintClock();
	}
	public void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		// nastav this.hour, minute, second podla aktualneho casu
		//.... dorobit
		this.hour = calendar.get(Calendar.HOUR);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		System.out.println(hour + " - " + minute + " - " + second);
		paintClock(); // Repaint the clock
	}

	protected void paintClock() {
		if (bezi) {
		System.out.println("kreslime");
		double clockRadius = Math.min(w, h) * 0.7/2;
		double centerX = w / 2;
		double centerY = h / 2;
		getChildren().clear();

		// kresli cifernik
		// dorobit
		Circle c = new Circle(centerX, centerY, clockRadius);
		c.setStroke(Color.RED);
		c.setFill(Color.YELLOW);
		// kresli sekudnovku
		// dorobit
		Line hodRuc = kresliRucicku(clockRadius, centerX, centerY, 12, this.hour, 7, 0.3);
		// kresli minutovku
		// dorobit
		Line minRuc = kresliRucicku(clockRadius, centerX, centerY, 60, this.minute, 4, 0.5);
		// kresli hodinovku
		// dorobit
		Line secRuc = kresliRucicku(clockRadius, centerX, centerY, 60, this.second, 2, 0.7);
		
		// kresli ciselnik
		// dorobit
		getChildren().addAll(c, hodRuc, minRuc, secRuc);
		}
	}
	private Line kresliRucicku(double clockRadius, double centerX, double centerY, double dvanast, int cas, int hrubka, double dlzka) {
		double alfaH = Math.toRadians((360.0/dvanast)*cas);
		double dlzkaH = clockRadius * dlzka;
		Line hodRuc = new Line(centerX, centerY, centerX + dlzkaH*Math.sin(alfaH), centerY - dlzkaH*Math.cos(alfaH));
		hodRuc.setStrokeWidth(hrubka);
		hodRuc.setStroke(Color.BLACK);
		return hodRuc;
	}
}