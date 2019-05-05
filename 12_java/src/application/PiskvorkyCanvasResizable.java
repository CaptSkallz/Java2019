package application;

import java.io.Serializable;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PiskvorkyCanvasResizable extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState(SIZE);

	@Override
	public void start(Stage primaryStage) {
		Piskyground pg = new Piskyground();
		Scene scene = new Scene(new Group(pg), 500, 500);
		pg.widthProperty().bind(scene.widthProperty());
		pg.heightProperty().bind(scene.heightProperty());
		pg.paintAll();

		scene.widthProperty().addListener(event -> pg.paintAll());
		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				System.out.println("Height: " + newSceneHeight);
				pg.paintAll();
			}
		});
		primaryStage.setTitle("Resizable Pišky jeden canvas");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends Canvas {
		public Piskyground() {
			setOnMouseClicked(event -> {
				int col = getCol(event.getX());
				int row = getRow(event.getY());
				if (ps.playground[col][row] != 0)
					return;
				ps.playground[col][row] = (ps.nextPlayerIsX) ? 1 : -1;
				paintCell(col, row);
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
			} );
		}
		
		public void paintAll() {
			GraphicsContext gc = getGraphicsContext2D();
			gc.clearRect(0, 0, getWidth(), getHeight());
			for (int col = 0; col < SIZE; col++)
				for (int row = 0; row < SIZE; row++)
					paintCell(col, row);
		}

		public void paintCell(int col, int row) {
			Image imageO = new Image("o.gif", cellWidth()-2, cellHeight()-2, false, false);
			Image imageX = new Image("x.gif", cellWidth()-2, cellHeight()-2, false, false);
			
			double px = getPixelY(col);
			double py = getPixelX(row);
			GraphicsContext gc = getGraphicsContext2D();
			gc.strokeRect(px, py, cellWidth(), cellHeight());
			if (ps.playground[col][row] == 1)
				gc.drawImage(imageX, px + 1, py + 1);
			else if (ps.playground[col][row] == -1)
				gc.drawImage(imageO, px + 1, py + 1);
		}

		private double cellWidth() {
			return getWidth()/SIZE;
		}
		private double cellHeight() {
			return getHeight()/SIZE;
		}
		private int getRow(double pixelY) {
			return (int) (pixelY / cellHeight());
		}
		private int getCol(double pixelX) {
			return (int) (pixelX / cellWidth());
		}
		private double getPixelX(int col) {
			return col * cellHeight();
		}
		private double getPixelY(int row) {
			return row * cellWidth();
		}
	}
}