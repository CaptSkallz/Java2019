package application;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TenisFxCv extends Application implements Runnable {
	static PlayGround pg;
	final static int w = 800;
	final static int h = 600;
	static double x1, x2, y1, y2, rsize, bsize, x, y, dx, dy;
	
	Thread thread;
	boolean gameOver;

	@Override
	public void start(Stage primaryStage) {
		pg = new PlayGround();
		thread = new Thread(this);
		thread.start();

		pg.setOnKeyPressed(event -> {
			System.out.println(event.getCode());
			if (event.getCode() == KeyCode.P) {
				y2 -= 5;
			} else if (event.getCode() == KeyCode.L) {
				y2 += 5;
			}
			else if (event.getCode() == KeyCode.I) {
				x2 -= 5;
			} else if (event.getCode() == KeyCode.O) {
				x2 += 5;
			}
			pg.paint();
		});

		pg.setOnMouseClicked(event -> {
			System.out.println(event.getX() + ", " + event.getY());
			if (event.getX() < w/2) {
				x1 = event.getX();
				y1 = event.getY();
			} else {
				x2 = event.getX();
				y2 = event.getY();
			}			
			pg.paint();
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
		System.out.println("started");
		while (!gameOver) {
			x += dy;
			y += dy;
			if (x < bsize) {
				dx = -dx;
			}
			if (x + bsize > w) {
				dx = -dx;
			}
			if (y < bsize) {
				dy = -dy;
			}
			if (y + bsize > h) {
				dy = -dy;
			}
			try { Thread.sleep(10); } catch (Exception e) {}
			Platform.runLater(new Runnable() {
				public void run() {
					pg.paint();
				}
			});
		}
	}

	class PlayGround extends Canvas {
		public PlayGround() {
			// inicializacia
			setWidth(w);
			setHeight(h);
			x1 = w/4;
			x2 = 3*w/4;
			y1 = h/2;
			y2 = h/2;
			rsize = 80;
			bsize = 10;
			x = w/2;
			y = h/2;
			do {
				dx = new Random().nextInt(3)-1;
				dy = new Random().nextInt(3)-1;
			} while (dx == 0 && dy == 0);
			setFocusTraversable(true);
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			// kreslenie do gc
			System.out.println("paint " + x + ", " + y);
			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, w, h);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(10);
			gc.strokeRect(0, 0, w, h);
			gc.strokeLine(w/2, 0, w/2, h);
			
			gc.setStroke(Color.BLUE);
			gc.strokeLine(x1, y1-rsize/2, x1, y1+rsize/2);
			gc.setStroke(Color.RED);
			gc.strokeLine(x2, y2-rsize/2, x2, y2+rsize/2);
			
			gc.setStroke(Color.GREEN);
			gc.strokeOval(x, y, bsize, bsize);
			
			//gc.setStroke(Color.BLACK);
			gc.setFont(new Font("Arial", 10));
			gc.strokeText("10", 10, 40);
			gc.setStroke(Color.BLACK);
			gc.strokeText("23", w-40-10, 40);
		}
	}
}
