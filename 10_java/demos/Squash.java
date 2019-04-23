package demos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Squash extends Application implements Runnable {
	int width, height;
	int rsize = 50;
	int player = 1;
	int stoped = 1;
	boolean served = false;

	Thread thread;

	int y1, y2; // player 1
	int x1, x2; // player 2
	double x, y; // center of ball
	double ballDirection;
	boolean gameOver = false;
	double speed = 1;
	PlayGround pg = new PlayGround();

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		pg = new PlayGround();
		gameOver = false;
		thread = new Thread(this);
		thread.start();

		pg.setOnKeyPressed(event -> {
			// System.out.println(event.getCode());
			if (event.getCode() == KeyCode.A) {
				if (y1 > 0)
					y1 -= 5;
			} else if (event.getCode() == KeyCode.Z) {
				if (y1 < height - rsize)
					y1 += 5;
			} else if (event.getCode() == KeyCode.P) {
				if (y2 > 0)
					y2 -= 5;
			} else if (event.getCode() == KeyCode.L) {
				if (y2 < height - rsize)
					y2 += 5;
			}
			pg.paint();
		});

		pg.setOnMouseClicked(e -> {
			// System.out.println(e.getX() + ", " + e.getY());
			if (!served) { // servuje
				ballDirection = -Math.atan2(-(e.getX() - x), -(e.getY() - y));

				speed = Math.sqrt(Math.pow(e.getX() - x, 2)
						+ Math.pow(e.getY() - y, 2)) / 100;
				player = 1 - player;
				served = true;
				System.out.println("served=" + ballDirection);
			} else {
				if (player == 1) {
					y1 = (int)e.getY();
					x1 = (int)e.getX();
					if (x1 < rsize / 2)
						x1 = rsize / 2;
					if (x1 > width - rsize / 2)
						x1 = width - rsize / 2;
				} else if (player != 1) {
					y2 = (int)e.getY();
					x2 = (int)e.getX();
					if (x2 < rsize / 2)
						x2 = rsize / 2;
					if (x2 > width - rsize / 2)
						x2 = width - rsize / 2;
				}
			}
		});

		
		pg.setOnMouseMoved(e -> {
			// System.out.println(e.getX() + ", " + e.getY());
			if (served) {
				if (player == 1) {
					y1 = (int)(e.getY() + 40 * y1) / 41;
					x1 = (int)(e.getX() + 40 * x1) / 41;
					if (x1 < rsize / 2)
						x1 = rsize / 2;
					if (x1 > width - rsize / 2)
						x1 = width - rsize / 2;
				} else if (player != 1) {
					y2 = (int)(e.getY() + 40 * y2) / 41;
					x2 = (int)(e.getX() + 40 * x2) / 41;
					if (x2 < rsize / 2)
						x2 = rsize / 2;
					if (x2 > width - rsize / 2)
						x2 = width - rsize / 2;
				}
			}
		});
		Scene scene = new Scene(new Pane(pg));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Squash");
		primaryStage.show();
	}
	
	public void run() {
		// Thread currentThread = Thread.currentThread();
		// while (currentThread == thread) {

		while (!gameOver) {
			if (served) {
				double nx = x + speed * Math.sin(ballDirection);
				double ny = y - speed * Math.cos(ballDirection);

				if (nx < 0) {
					ballDirection = 2 * Math.PI - ballDirection;
				}
				if (nx > width) {
					ballDirection = 2 * Math.PI - ballDirection;
				}
				if (ny < 0) {
					ballDirection = Math.PI - ballDirection;
					player = 1 - player;
				}
				if (player == 1 && ny - y1 < 5 && ny - y1 >= 0
						&& (nx >= x1 - (rsize / 2)) && (nx <= x1 + (rsize / 2))) {
					ballDirection = Math.PI - ballDirection;
					stoped = 1 - stoped;

				} else if (player != 1 && ny - y2 < 5 && ny - y2 >= 0
						&& (nx >= x2 - (rsize / 2)) && (nx <= x2 + (rsize / 2))) {
					ballDirection = Math.PI - ballDirection;
					stoped = 1 - stoped;
				}
				if (ny > height) {
					if (player == 1)
						System.out.println("prvy nechytil");
					else
						System.out.println("druhy nechytil");
					gameOver = true;
					served = false;
				}
				x = nx;
				y = ny;
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					pg.paint();
				}
			});
		}
	}

	class PlayGround extends Canvas {
		public PlayGround() {
			setWidth(650);
			setHeight(427);
			width = (int) getWidth();
			height = (int) getHeight();
			x = width / 2;
			y = height / 2;
			y1 = 3 * height / 4;
			y2 = 3 * height / 4;
			x1 = width / 4;
			x2 = 3 * width / 4;
			if (player == 1) {
				x = x1;
				y = y1;
			} else {
				x = x2;
				y = y2;
			}
			ballDirection = 2 * Math.PI / 3;
			setFocusTraversable(true);
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();

			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, width, height);

			int h = (int) (0.57 * height);
			gc.setStroke(Color.ORANGE);
			gc.strokeLine(0, h, width, h);

			gc.strokeLine((int) (width / 2), h, (int) (width / 2), height);

			gc.setLineWidth(5);
			gc.setStroke(Color.RED);
			gc.strokeLine(x1 - (rsize / 2), y1, x1 + (rsize / 2), y1);

			gc.setLineWidth(5);
			gc.setStroke(Color.BLUE);
			gc.strokeLine(x2 - (rsize / 2), y2, x2 + (rsize / 2), y2);

			gc.setFill(Color.rgb(255, 255, 100));
			gc.fillOval((int) (x - 5), (int) (y - 5), 10, 10);
		}
	}
}
