package demos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ThreadDemo extends Application {
	ThreadPanel A;
	ThreadPanel B;

	@Override
	public void start(Stage stage) throws Exception {
		A = new ThreadPanel("Thread A", Color.BLUE, true);
		B = new ThreadPanel("Thread B", Color.BLUE, true);
		FlowPane pane = new FlowPane();
		pane.getChildren().addAll(A, B);
		Scene scene = new Scene(pane, 600, 300, Color.GREY);
		stage.setScene(scene);
		stage.setTitle("Thread Demo");
		stage.show();
		A.start(new Rotator());
		B.start(new Rotator());
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class Rotator implements Runnable {

	public void run() {
		try {
			while (true)
				ThreadPanel.rotate();
		} catch (InterruptedException e) {
		}
	}
}

