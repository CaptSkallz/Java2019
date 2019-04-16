
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BallThreadFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		BallThreadPanel balls = new BallThreadPanel();

		Scene scene = new Scene(balls, 450, 450);// vytvor scenu

		scene.widthProperty().addListener(ov -> balls.setW((int) scene.getWidth()));
		scene.heightProperty().addListener(ov -> balls.setH((int) scene.getHeight()));

		primaryStage.setTitle("Balls in the Box using threads"); // pomenuj okno aplikacie, javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class BallThreadPanel extends Pane {
	private int w = 450, h = 450;
	private BallThread red;
	private BallThread blue;

	public BallThreadPanel() {
		Random rnd = new Random();
		red = new BallThread(this, rnd.nextInt(w), rnd.nextInt(h), 
				rnd.nextInt(3) - 1, rnd.nextInt(3) - 1, 5, w, h);
		red.start();
		blue = new BallThread(this, rnd.nextInt(w), rnd.nextInt(h), 
				rnd.nextInt(3) - 1, rnd.nextInt(3) - 1, 10, w, h);
		blue.start();
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	protected void paintBallPane() {
		getChildren().clear();
		if (blue != null) {
			Circle blueR = new Circle(blue.x, blue.y, blue.size);
			blueR.setFill(Color.BLUE);
			blueR.setStroke(Color.BLACK);
			getChildren().add(blueR);
		}
		if (red != null) {
			Circle redR = new Circle(red.x, red.y, red.size);

			redR.setFill(Color.RED);
			redR.setStroke(Color.BLACK);
			getChildren().addAll(redR);
		}
	}
}

class BallThread extends Thread {
	int x, y;
	int dx, dy;
	int size;
	int w, h;
	BallThreadPanel bp;

	public BallThread(BallThreadPanel bp, int x, int y, int dx, int dy, int size, int w, int h) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.w = w;
		this.bp = bp;
		this.h = h;
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

	@Override
	public void run() {
		while (true) {
			update(w, h);
			try {
				Thread.sleep(10);
				/*
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						bp.paintBallPane();
					}
				});
				*/
				Platform.runLater(() ->	bp.paintBallPane() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}