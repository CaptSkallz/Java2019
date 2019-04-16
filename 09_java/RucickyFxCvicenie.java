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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RucickyFxCvicenie extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ClockPaneCvicenie clock = new ClockPaneCvicenie();
		
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
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);				
		paintClock(); // Repaint the clock
	}

	protected void paintClock() {
		double clockRadius = Math.min(w, h) * 0.7/2;
		double centerX = w / 2;
		double centerY = h / 2;
		System.out.println("repaint:" + hour + ":" + minute + ":" + second + ":") ;
		getChildren().clear();

		// kresli cifernik
		Circle c = new Circle(centerX, centerY, clockRadius);
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		getChildren().add(c);

		// kresli sekudnovku
		{
		double slen = clockRadius * 0.8;
		//double sX = centerX + slen * Math.sin(this.second * 360/60 * (2 * Math.PI)/360);
		double sX = centerX + slen * Math.sin(this.second * Math.PI/30);
		double sY = centerY - slen * Math.cos(this.second * Math.PI/30);
		Line l = new Line(centerX, centerY, sX, sY);
		l.setStroke(Color.BLUE);
		getChildren().add(l);
		}
		// kresli minutovku
		{
		double slen = clockRadius * 0.7;
		double sX = centerX + slen * Math.sin(this.minute * Math.PI/30);
		double sY = centerY - slen * Math.cos(this.minute * Math.PI/30);
		Line l = new Line(centerX, centerY, sX, sY);
		l.setStroke(Color.RED);
		getChildren().add(l);
		}

		// kresli hodinovku
		{
		double slen = clockRadius * 0.6;
		double sX = centerX + slen * Math.sin(((double)this.hour + ((double)this.minute/60)) * 360/12 * (2 * Math.PI)/360);
		double sY = centerY - slen * Math.cos(((double)this.hour + ((double)this.minute/60)) * 360/12 * (2 * Math.PI)/360);
		Line l = new Line(centerX, centerY, sX, sY);
		l.setStroke(Color.GREEN);
		getChildren().add(l);
		}

		// kresli ciselnik
		for(int h = 0; h < 12; h++) {
			double sl = clockRadius * 0.9;
			/* zle riesenia
			double sX = centerX + sl * Math.cos(h * 360/12 * (2 * Math.PI)/360 );
			double sY = centerY - sl * Math.sin(h * 360/12 * (2 * Math.PI)/360);
			Text t = new Text(sX - 3, sY, "" + h);  // nula je pre 3 hodiny
			//Text t = new Text(sX - 3, sY, ""+(h==0?12:h));  // uz je to 12
			*/
			
			
			//double sX = centerX + sl * Math.sin(h * 360/12 * (2 * Math.PI)/360 );
			//double sY = centerY - sl * Math.cos(h * 360/12 * (2 * Math.PI)/360);
			double sX = centerX + sl * Math.sin((h * Math.PI)/6 );
			double sY = centerY - sl * Math.cos((h * Math.PI)/6 );

			Text t = new Text(sX - 3, sY, ""+(h==0?12:h));
			t.setStroke(Color.BLUE);
			getChildren().add(t);
		}
	}
}