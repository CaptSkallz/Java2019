package demos;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TenisFx extends Application implements Runnable {
	int width, height;
	int rsize = 60;
	int bsize = 20;
	Thread thread;

	double y1, y2; // player 1
	double x1, x2; // player 2
	double x, y; // center of ball
	double ballDirection;
	boolean gameOver;
	static PlayGround pg;

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

		pg.setOnMouseClicked(event -> {
			// System.out.println(event.getX() + ", " + event.getY());
			if (event.getX() < width / 2) {
				y1 = (int) event.getY();
				x1 = (int) event.getX();
				if (x1 < rsize)
					x1 = rsize;
			} else {
				y2 = (int) event.getY();
				x2 = (int) event.getX();
				if (x2 > width - rsize)
					x2 = width - rsize;
			}
		});
		Scene scene = new Scene(new Pane(pg));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tenis");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void stop() {
		gameOver = true;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	public void run() {
		// System.out.println("started");
		while (!gameOver) {
			double nx = x - Math.sin(ballDirection);
			double ny = y + Math.cos(ballDirection);
			if (ny < 0) {
				// ny = -y;
				ballDirection = Math.PI - ballDirection;
			}
			if (ny > height) {
				// ny = 2 * height - ny;
				ballDirection = Math.PI - ballDirection;
			}
			if (Math.abs(x1 - nx) < 5 && (ny >= y1) && (ny <= y1 + rsize)) {
				// nx = 20 - nx;
				ballDirection = 2 * Math.PI - ballDirection;
			}
			if (nx < 10) {
				System.out.println("lavy nechytil");
				gameOver = true;
			}
			if (Math.abs(x2 - nx) < 5 && (ny >= y2) && (ny <= y2 + rsize)) {
				// nx = 2 * width - 20 - nx;
				ballDirection = 2 * Math.PI - ballDirection;
			}
			if (nx > width - 10) {
				System.out.println("pravy nechytil");
				gameOver = true;
			}
			x = nx;
			y = ny;
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
			setWidth(800);
			setHeight(600);
			width = (int) getWidth();
			height = (int) getHeight();
			x = width / 2; // ball
			y = height / 2;
			y1 = height / 2 - rsize / 2; // player1
			y2 = height / 2 - rsize / 2; // player2
			x1 = 10;
			x2 = width - 10;
			ballDirection = (new Random().nextDouble()+0.8)/2*Math.PI; //new Random().nextDouble() * 2 * Math.PI;
			setFocusTraversable(true);
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			Color bgColor;
			Color colorPlayer1;
			Color colorPlayer2;
			Color colorBall;
			Color netColor;

			bgColor = new Color(0, 0, 0, 0);
			bgColor = Color.WHITE;
			colorPlayer1 = Color.RED;
			colorPlayer2 = Color.BLUE;
			colorBall = Color.rgb(90, 60, 90);
			netColor = Color.BROWN;

			gc.setFill(bgColor);
			gc.fillRect(0, 0, width, height);

			gc.strokeLine(width / 2, 0, width / 2, height);

			gc.setLineWidth(5);
			gc.setStroke(colorPlayer1);
			gc.strokeLine(x1, y1, x1, y1 + rsize);

			gc.setLineWidth(5);
			gc.setStroke(colorPlayer2);
			gc.strokeLine(x2, y2, x2, y2 + rsize);

			gc.setFill(colorBall);
			gc.fillOval((int) (x - 5), (int) (y - 5), bsize, bsize);
		}
	}
}
