
import java.util.Random;

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BallFx extends Application {

	@Override
	public void start(Stage primaryStage) {
	BallPane balls = new BallPane();
		
		// Create a handler for animation
	/*
	EventHandler<ActionEvent> eventHandler1 = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			balls.update();
			balls.paintBallPane(); 
		}
	}; 
	Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), eventHandler1));
	*/
	/*
		EventHandler<ActionEvent> eventHandler = e -> {
			balls.update();
			balls.paintBallPane(); 
		};
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), eventHandler));
*/
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), 
			x -> {
				balls.update();
				balls.paintBallPane();
			}));
			
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
		
		
		Scene scene = new Scene(balls, 450, 450);// vytvor scenu
		
		// nastavena vazieb
		scene.widthProperty().addListener(ov -> balls.setW((int)scene.getWidth()));
		scene.heightProperty().addListener(ov -> balls.setH((int)scene.getHeight()));
		
		primaryStage.setTitle("Balls in the Box"); 	// pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); 			// vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); 					// zobraz javisko		
	}

	public static void main(String[] args) {
		launch(args);
	}
}


class BallPane extends Pane {
	private int w = 450, h = 450;
	private IdealBall red;
	private IdealBall blue;

	public BallPane() {
		Random rnd = new Random();
		red = new IdealBall(rnd.nextInt(w),rnd.nextInt(h), rnd.nextInt(3)-1, rnd.nextInt(3)-1,5);
		blue = new IdealBall(rnd.nextInt(w),rnd.nextInt(h), rnd.nextInt(3)-1, rnd.nextInt(3)-1, 10);
	}
	public void update() {
		red.update(w, h);
		blue.update(w, h);
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setW(int w) {
		this.w = w;
	}
	protected void paintBallPane() {
		Circle blueR = new Circle(blue.x, blue.y, blue.size);
		blueR.setFill(Color.BLUE);
		blueR.setStroke(Color.BLACK);
		
		Circle redR = new Circle(red.x, red.y, red.size);
		redR.setFill(Color.RED);
		redR.setStroke(Color.BLACK);
		
		getChildren().clear();
		getChildren().addAll(blueR, redR);
	}
}

class IdealBall {
	int x, y;
	int dx, dy;
	int size;
	
	public IdealBall(int x, int y, int dx, int dy, int size) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
	}
	public void update(int w, int h) {
		x += dx;
		y += dy;
		if (x < size)
			dx = -dx;
		if (y < size)
			dy = -dy;
		if (x > w-size)
			dx = -dx;
		if (y > h-size)
			dy = -dy;
	}
}