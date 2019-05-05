package application;

import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PiskvorkyCanvas extends Application {
	final static int SIZE = 10;
	//final static int SIZE = 20;
	PiskyState ps = new PiskyState(SIZE);

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
		double cellSize = 2 + Math.max(Math.max(imageX.getWidth(), imageO.getWidth()),
				Math.max(imageX.getHeight(), imageO.getHeight()));

		public Piskyground() {
			setWidth(SIZE * cellSize);
			setHeight(SIZE * cellSize);
			setOnMouseClicked(event -> {
				int col = getCol(event.getX());
				int row = getRow(event.getY());
				if (ps.playground[col][row] != 0)
					return;
				ps.playground[col][row] = (ps.nextPlayerIsX) ? 1 : -1;
				paintCell(col, row);
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
			} );
			for (int col = 0; col < SIZE; col++)
				for (int row = 0; row < SIZE; row ++)
					paintCell(col, row);
		}

		public void paintCell(int col, int row) {
			double px = getPixelX(col);
			double py = getPixelY(row);
			GraphicsContext gc = getGraphicsContext2D();
			gc.strokeRect(px, py, cellSize, cellSize);
			if (ps.playground[col][row] == 1)
				gc.drawImage(imageX, px + 1, py + 1);
			else if (ps.playground[col][row] == -1)
				gc.drawImage(imageO, px + 1, py + 1);
		}

		private int getRow(double pixel) {
			return (int) (pixel / cellSize);
		}

		private int getCol(double pixel) {
			return (int) (pixel / cellSize);
		}

		private double getPixelX(int i) {
			return i * cellSize;
		}

		private double getPixelY(int j) {
			return j * cellSize;
		}
	}
}