package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TenisFxCvicenie extends Application implements Runnable {
	static PlayGround pg;
	Thread thread;
	boolean gameOver;

	@Override
	public void start(Stage primaryStage) {
		pg = new PlayGround();

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
		} catch (InterruptedException e) { }
	}

	public void run() {
		System.out.println("started");
		while (!gameOver) {
			// simulacia
			// sleep
			// vykreslenie
		}
	}

	class PlayGround extends Canvas {
		public PlayGround() {
			// inicializacia
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			// kreslenie do gc
		}
	}
}
