

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TenisFxCvicenie extends Application implements Runnable {
	PlayGround pg;
	Thread thread;
	boolean gameOver;

	@Override
	public void start(Stage primaryStage) {
		pg = new PlayGround();

		pg.setOnKeyPressed(event -> {
			System.out.println(event.getCode());
			// co robit, ak KeyPressed
		});

		pg.setOnMouseClicked(event -> {
			System.out.println(event.getX() + ", " + event.getY() + event.getButton());
			// co robit, ak MouseClicked
		});

		
		Scene scene = new Scene(new Pane(pg));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tenis");
		primaryStage.show();
		// vytvor thread a spusti ho
		// thread = new Thread(this);
		// thread.start();
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
			//if (pg != null)
			//	pg.paint();
		}
	}

	class PlayGround extends Canvas {
		public PlayGround() {
			setWidth(800);
			setHeight(600);
			setFocusTraversable(true);  // kvoli KeyPressed evetnu
			// inicializacia
		}

		public void paint() {
			// zisti aktualnu velkost
			double width = getWidth();
			double height = getHeight();
			
			GraphicsContext gc = getGraphicsContext2D();
			// kreslenie do gc
			
			// zafarbi antuku na antukovu...
			gc.setFill(Color.ORANGE);
			gc.fillRect(0, 0, width, height);
			
			gc.setLineWidth(5);
			gc.strokeLine(0, 0, 100, 100);		
		}
	}
}
