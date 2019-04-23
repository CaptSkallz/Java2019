package demos;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class SemaphoreLoop implements Runnable {
	Semaphore semaphore;

	SemaphoreLoop(Semaphore sema) {
		semaphore = sema;
	}

	public void run() {
		try {
			while (true) {
				while (!ThreadPanel.rotate()); // zivot mimo kritickej oblasti
				semaphore.aquire(); // vkroc do kritickej oblasti
				while (ThreadPanel.rotate()); // som v kritickej oblasti
				semaphore.release(); // vystup z kritickej oblasti
			}
		} catch (InterruptedException e) {
		}
	}
}

/********************************************************/

public class SemaDemo extends Application {

	ThreadPanel thread1;
	ThreadPanel thread2;
	ThreadPanel thread3;
	NumberCanvas semaDisplay;

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		semaDisplay = new NumberCanvas("Mutex");
		StackPane.setAlignment(semaDisplay, Pos.CENTER);
		StackPane topPane = new StackPane(semaDisplay);
		bp.setTop(topPane);
		FlowPane pane = new FlowPane();
		thread1 = new ThreadPanel("Thread 1", Color.BLUE, true);
		thread2 = new ThreadPanel("Thread 2", Color.BLUE, true);
		thread3 = new ThreadPanel("Thread 3", Color.BLUE, true);
		Semaphore mutex = new DisplaySemaphore(semaDisplay, 
			//	1 );
				2 );
		thread1.start(new SemaphoreLoop(mutex));
		thread2.start(new SemaphoreLoop(mutex));
		thread3.start(new SemaphoreLoop(mutex));
		pane.getChildren().addAll(thread1, thread2, thread3);
		bp.setBottom(pane);
		Scene scene = new Scene(bp, 900, 450, Color.GREY);
		stage.setScene(scene);
		stage.setTitle("Semaphore Demo");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start() {
		Semaphore mutex = new DisplaySemaphore(semaDisplay, 1);
		thread1.start(new SemaphoreLoop(mutex));
		thread2.start(new SemaphoreLoop(mutex));
		thread3.start(new SemaphoreLoop(mutex));

	}

	public void stop() {
		thread1.stop();
		thread2.stop();
		thread3.stop();
	}
}
