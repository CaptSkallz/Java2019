package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PiskvorkyCanvas extends Application {
	final static int SIZE = PiskyState.SIZE;
	PiskyState ps = new PiskyState();

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new Group(new Piskyground()));
		primaryStage.setTitle("Pišky jeden canvas");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends Canvas {
		Image imageX = new Image("o.gif");
		Image imageO = new Image("x.gif");
		double cellSize = 2+Math.max(
				Math.max(imageX.getWidth(), imageO.getWidth()), 
				Math.max(imageX.getHeight(), imageO.getHeight())); 

		public Piskyground() {
			setWidth(SIZE * (imageX.getWidth() + 2));
			setHeight(SIZE * (imageX.getHeight() + 2));
			setOnMouseClicked(event -> {
				int i = getRow(event.getX());
				int j = getCol(event.getY());
				if (ps.playground[i][j] != 0)
					return;
				ps.playground[i][j] = (ps.nextPlayerIsX) ? 1 : -1;
				paintCell(i, j);
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
			} );
			for(int i=0; i<SIZE; i++)
				for(int j=0; j<SIZE; j++)
					paintCell(i, j);
		}

		public void paintCell(int i, int j) {
			double px = getPixelX(i);
			double py = getPixelY(j);
			GraphicsContext gc = getGraphicsContext2D();
			gc.strokeRect(px, py, cellSize, cellSize);
			if (ps.playground[i][j] == 1)
				gc.drawImage(imageX, px+1, py+1);
			else if (ps.playground[i][j] == -1)
				gc.drawImage(imageO, px+1, py+1);
		}
		private int getRow(double pixel) {
			return (int)(pixel/cellSize);
		}
		private int getCol(double pixel) {
			return (int)(pixel/cellSize);
		}
		private double getPixelX(int i) {
			return i*cellSize;
		}
		private double getPixelY(int j) {
			return j*cellSize;
		}
	}
}