package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

public class CanvasDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(700, 700);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.fillOval(350, 350, 5, 5);
		gc.strokeText("STRED", 335, 370);
		paintShapes(gc);
		
		Affine af = new Affine(); // afinné zobrazenie
		for (int i = 0; i < 100; i++) {
			//af.append(Affine.scale(0.9, 0.9, 350, 350)); // rovnolahlost
			//af.append(Affine.rotate(60, 350, 350)); // otocenie
			//af.append(Affine.translate(20, 20)); // posunutie
			gc.setTransform(af); paintShapes(gc);
		}
		
		primaryStage.setTitle("CanvasDemo");
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.show();
	}

	public void paintShapes(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(3);
		int x = 10, y = 10;
		gc.strokeLine(x, y, x, y + 30);
		gc.fillOval(x += 50, y, 30, 30);
		gc.strokeOval(x += 50, y, 30, 30);
		gc.fillRect(x += 50, y, 30, 30);
		gc.strokeRect(x += 50, y, 30, 30);

		gc.fillArc(x += 50, y, 30, 30, 45, 240, ArcType.OPEN);
		gc.fillArc(x += 50, y, 30, 30, 45, 240, ArcType.CHORD);
		gc.fillArc(x += 50, y, 30, 30, 45, 240, ArcType.ROUND);

		gc.strokeArc(x += 50, y, 30, 30, 45, 240, ArcType.OPEN);
		gc.strokeArc(x += 50, y, 30, 30, 45, 240, ArcType.CHORD);
		gc.strokeArc(x += 50, y, 30, 30, 45, 240, ArcType.ROUND);
		gc.drawImage(new Image("namornik.gif"), x += 50, y);
	}
}
