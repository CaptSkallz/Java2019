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


public class RucickyFx extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ClockPane clock = new ClockPane();
		
		// Create a handler for animation
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime(); // Set a new clock time
		};

		// Create an animation for a running clock
		//Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {clock.setCurrentTime();}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
		
		//Scene scene = new Scene(clock);// vytvor scenu
		Scene scene = new Scene(clock, 450, 450);// vytvor scenu
		
		// nastavena vazieb
		scene.widthProperty().addListener(ov -> clock.setW(scene.getWidth()));
		scene.heightProperty().addListener(ov -> clock.setH(scene.getHeight()));
		
		primaryStage.setTitle("DisplayClock"); 	// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;

	// Clock pane's width and height
	private double w = 450, h = 450;

	public ClockPane() {
		setCurrentTime();
	}
	/*
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		paintClock();
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}
	public double getW() {
		return w;
	}
	public double getH() {
		return h;
	}
	*/
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
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		paintClock(); // Repaint the clock
	}

	protected void paintClock() {
		double clockRadius = Math.min(w, h) * 0.7/2;
		double centerX = w / 2;
		double centerY = h / 2;
		getChildren().clear();

		// Draw circle
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		/*
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
		Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
		*/
		// Draw second hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
		double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);

		// Draw minute hand
		double mLength = clockRadius * 0.65;
		double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
		double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, xMinute, minuteY);
		mLine.setStroke(Color.BLUE);

		// Draw hour hand
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		hLine.setStroke(Color.GREEN);

		getChildren().addAll(circle, sLine, mLine, hLine);
		for(int h = 0; h < 12; h++) {
			double sl = clockRadius * 0.9;
			double sX = centerX + sl * Math.sin(h * 360/12 * (2 * Math.PI)/360 );
			double sY = centerY - sl * Math.cos(h * 360/12 * (2 * Math.PI)/360);
			Text t = new Text(sX - 3, sY, ""+(h==0?12:h));
			t.setStroke(Color.BLUE);
			getChildren().add(t);
		}
	}
}