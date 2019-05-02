package application;

import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class MouseDemo extends Application {
	Vector<Double> listOfPositions = new Vector<Double>();

	@Override
	public void start(Stage primaryStage) {
		MousePane p = new MousePane();
		Scene scene = new Scene(p, 400, 400, Color.BLACK);
		scene.setOnMouseMoved(event -> {
			if (listOfPositions.size() >= 200) {
				listOfPositions.removeElementAt(0);
				listOfPositions.removeElementAt(0);
			}
			listOfPositions.addElement(event.getX());
			listOfPositions.addElement(event.getY());
			p.paint();
			event.consume();
		} );
		primaryStage.setTitle("Mouse");		
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
			//Polygon pl = new Polygon();
			pl.setStroke(Color.YELLOW);
			pl.setStrokeWidth(10);
			pl.getPoints().addAll(d);
			getChildren().add(pl);
		}
	}
}
