package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PiskvorkyGridCanvasResizable extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState(SIZE);
	Piskyground pg;

	@Override
	public void start(Stage primaryStage) {
		pg = new Piskyground();
		Scene scene = new Scene(pg, 500, 500);
		pg.paint();
		scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
			pg.prefWidth((double) newSceneWidth);
			pg.paint();
		} );
		scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
			pg.prefHeight((double) newSceneHeight);
			pg.paint();
		} );
		primaryStage.setTitle("Resizable Pišky grid canvasov");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends GridPane {
		PiskyCell[][] canvasGrid = new PiskyCell[SIZE][SIZE];

		public Piskyground() {
			for (int i = 0; i < PiskvorkyGridCanvasResizable.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridCanvasResizable.SIZE; j++) {
					PiskyCell pc = canvasGrid[i][j] = new PiskyCell(i, j);
					add(pc, j, i);
					pc.widthProperty().bind(widthProperty().divide(SIZE));
					pc.heightProperty().bind(heightProperty().divide(SIZE));
				}
		}

		public void paint() {
			for (int i = 0; i < PiskvorkyGridCanvasResizable.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridCanvasResizable.SIZE; j++)
					canvasGrid[i][j].paintCell();
		}
	}

	class PiskyCell extends Canvas {
		int i, j;

		public PiskyCell(int i, int j) {
			this.i = i;
			this.j = j;
			setOnMouseClicked(event -> {
				if (ps.playground[i][j] != 0)
					return;
				if (ps.nextPlayerIsX) {
					ps.playground[i][j] = 1;
					paintCell();
				} else {
					ps.playground[i][j] = -1;
					paintCell();
				}
				pg.paint();
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
				event.consume();
			} );
		}

		public void paintCell() {
			GraphicsContext gc = getGraphicsContext2D();
			Image imageX = new Image("x.gif", getWidth() - 2, getHeight() - 2, false, false);
			Image imageO = new Image("o.gif", getWidth() - 2, getHeight() - 2, false, false);
			gc.clearRect(0, 0, getWidth(), getHeight());
			gc.strokeRect(0, 0, getWidth(), getHeight());
			if (ps.playground[i][j] == 1)
				gc.drawImage(imageX, 1, 1);
			else if (ps.playground[i][j] == -1) {
				gc.drawImage(imageO, 1, 1);
			}
		}
	}
}