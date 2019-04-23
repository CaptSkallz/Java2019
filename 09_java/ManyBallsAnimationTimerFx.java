import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ManyBallsAnimationTimerFx  extends Application {
	static long frameCnt, lasttimeNano;

	@Override
	public void start(Stage primaryStage) {
		BallPane2 balls = new BallPane2();
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long now) { // cas v nanosekundach, 10^9, mili,
											// micro, nanoseconds
				if (now > lasttimeNano + 1000000000) { // ak uplynie sekunda,
														// tak vypis fps
					System.out.println(frameCnt + " fps");
					frameCnt = 0;
					lasttimeNano = now;
				}
				balls.update();
				balls.paintBallPane();
				frameCnt++;
			}
		};
		at.start();

		Scene scene = new Scene(balls, 450, 450);// vytvor scenu

		// nastavena vazieb
		scene.widthProperty().addListener(
				ov -> balls.setW((int) scene.getWidth()));
		scene.heightProperty().addListener(
				ov -> balls.setH((int) scene.getHeight()));

		primaryStage.setTitle("100 Balls in the Box"); // pomenuj okno aplikacie,
													// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class BallPane2 extends Pane {
	private int w = 450, h = 450;
	private ArrayList<IdealBall2> balls = new ArrayList<IdealBall2>();
	//final int SIZE = 2;
	//final int SIZE = 100;
	final int SIZE = 1000;
	//final int SIZE = 10000;
	Color[] cols = { Color.RED, Color.BLUE, Color.GREEN, Color.CYAN,
			Color.YELLOW };

	public BallPane2() {
		Random rnd = new Random();
		for (int i = 0; i < SIZE; i++)
			balls.add(new IdealBall2(rnd.nextInt(w), rnd.nextInt(h), // x,y
					rnd.nextInt(3) - 1, rnd.nextInt(3) - 1, // dx, dy
					rnd.nextInt(20), // size
					cols[rnd.nextInt(cols.length)]));
	}

	public void update() {
		for (IdealBall2 b : balls)
			b.update(w, h);
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	protected void paintBallPane() {
		getChildren().clear();
		for (IdealBall2 b : balls) {
			Circle ci = new Circle(b.x, b.y, b.size);
			ci.setFill(b.c);
			ci.setStroke(Color.BLACK);	
			getChildren().add(ci);
		}
	}
}

class IdealBall2 {
	int x, y;
	int dx, dy;
	int size;
	Color c;

	public IdealBall2(int x, int y, int dx, int dy, int size, Color c) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.c = c;
	}

	public void update(int w, int h) {
		x += dx;
		y += dy;
		if (x < size)
			dx = -dx;
		if (y < size)
			dy = -dy;
		if (x > w - size)
			dx = -dx;
		if (y > h - size)
			dy = -dy;
	}
}