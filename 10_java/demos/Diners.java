package demos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Diners extends Application {
	PhilCanvas display;
	Thread[] phil = new Thread[PhilCanvas.NUMPHILS];
	Fork[] fork = new Fork[PhilCanvas.NUMPHILS];
	ScrollBar slider;
	Button restart;
	Button freeze;
	boolean fixed = false;


	@Override
	public void start(Stage stage) throws Exception {
		BorderPane bp = new BorderPane();
		//fixed = true; // FIXED
		fixed = false; // not FIXED
		bp.setCenter(display = new PhilCanvas());
		slider = new ScrollBar();
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(50);
		slider.setPrefSize(250, 20);
		FlowPane buttonPanel = new FlowPane();
		
		buttonPanel.getChildren().add(restart = new Button("Restart"));
		restart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (display.deadlocked()) {
					stop();
					slider.setValue(50);
					start();
				}
				display.thaw();
			}
		});
		buttonPanel.getChildren().add(slider);
		
		buttonPanel.getChildren().add(freeze = new Button("Freeze"));
		freeze.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				display.freeze();
			}
		});
		
		bp.setBottom(buttonPanel);
		Scene scene = new Scene(bp, 400, 400, Color.GREY);
		stage.setScene(scene);
		stage.setTitle("Diners");
		stage.show();
		start();
	}

	public static void main(String[] args) {
		launch(args);
	}


	Thread makePhilosopher(Diners d, int id, Fork left, Fork right) {
		if (fixed)
			return new FixedPhilosopher(d, id, left, right);
		else
			return new Philosopher(d, id, left, right);
	}

	public int sleepTime() {
		return ((int)slider.getValue() * (int) (100 * Math.random()));
	}

	public int eatTime() {
		return ((int)slider.getValue() * (int) (50 * Math.random()));
	}

	public void start() {
		System.out.println("---------------------------------------------");
		for (int i = 0; i < PhilCanvas.NUMPHILS; ++i)
			fork[i] = new Fork(display, i);
		for (int i = 0; i < PhilCanvas.NUMPHILS; ++i) {
			phil[i] = makePhilosopher(this, i, 
					fork[i],
					fork[(i - 1 + PhilCanvas.NUMPHILS) % PhilCanvas.NUMPHILS] 
						);
			phil[i].start();
		}
	}

	public void stop() {
		for (int i = 0; i < PhilCanvas.NUMPHILS; ++i) {
			phil[i].interrupt();
		}
	}
}
