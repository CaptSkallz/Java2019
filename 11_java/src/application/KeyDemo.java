package application;

import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class KeyDemo extends Application {
	Vector<Double> listOfPositions = new Vector<Double>();
	double x = 200, y = 200;

	@Override
	public void start(Stage primaryStage) {
		MousePane p = new MousePane();
		Scene scene = new Scene(p, 400, 400, Color.BLACK);
		scene.setOnKeyPressed(event -> {
			if (listOfPositions.size() >= 200) {
				listOfPositions.removeElementAt(0);
				listOfPositions.removeElementAt(0);
			}
			if (event.getCode() == KeyCode.UP) y -= 5;
			if (event.getCode() == KeyCode.DOWN) y += 5;
			if (event.getCode() == KeyCode.LEFT) x -= 5;
			if (event.getCode() == KeyCode.RIGHT) x += 5;
			listOfPositions.addElement(x);
			listOfPositions.addElement(y);
			p.paint();
			event.consume();
		} );
		primaryStage.setTitle("Keyboard");		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class MousePane extends Pane {
		public void paint() {
			getChildren().clear();
			Double[] d = listOfPositions.toArray(new Double[] {});
			Polyline pl = new Polyline();
			pl.setStroke(Color.YELLOW);
			pl.setStrokeWidth(10);
			pl.getPoints().addAll(d);
			getChildren().add(pl);
		}
	}
}
