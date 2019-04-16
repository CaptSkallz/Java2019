
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class ProgressFx extends Application {
 
 	@Override
	public void start(Stage primaryStage) {
		
 		/*
 		final ProgressBar bar = new ProgressBar();
 		new Thread(new Runnable() {
 		    @Override public void run() {
 		        for (int i=1; i<=1000000; i++) {
 		            final int counter = i;
 		            //Platform.runLater(new Runnable() {
 		              //  @Override public void run() {
 		                	System.out.println(counter);
 		                    bar.setProgress(counter/1000000.0);
 		                //}
 		            //});
 		        }
 		    }
 		}).start();
		*/
 		
 		/*
 		final ProgressBar bar = new ProgressBar();
 		new Thread(new Runnable() {
 		    @Override public void run() {
 		        for (int i=1; i<=1000000; i++) {
 		            final int counter = i;
 		            Platform.runLater(new Runnable() {
 		               @Override public void run() {
 		                	System.out.println(counter);
 		                    bar.setProgress(counter/1000000.0);
 		                }
 		            });
 		        }
 		    }
 		}).start();
 		*/
 		
 		Task<Void> task = new Task<Void>() {
 		    @Override public Void call() {
 		        final int max = 1000000;
 		        for (int i=1; i<=max; i++) {
 		        	//Thread.sleep(10);
 		            updateProgress(i, max);
 		        }
 		        return null;
 		    }
 		};
 		ProgressBar bar = new ProgressBar();
 		bar.progressProperty().bind(task.progressProperty());
 		new Thread(task).start();
 		
 		
		Scene scene = new Scene(bar, 450, 450);// vytvor scenu

		primaryStage.setTitle("ProgressBar"); // pomenuj okno
																	// aplikacie,
																	// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	
	}
 
  public static void main(String[] args) {
		launch(args);
	}
}

