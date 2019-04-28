package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PiskvorkyGridCanvas extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState();

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new Piskyground());
		primaryStage.setTitle("Pišky grid canvasov");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends GridPane {
		public Piskyground() {
			for (int i = 0; i < PiskvorkyGridCanvas.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridCanvas.SIZE; j++) {
					PiskyCell pc;
					add(pc = new PiskyCell(i, j), j, i);
					pc.paintCell();
				}
		}
	}

	class PiskyCell extends Canvas {
		int i, j;
		Image imageX = new Image("o.gif");
		Image imageO = new Image("x.gif");
		//Image imageX = new Image(getClass().getResourceAsStream("o.gif"));
		//Image imageO = new Image(getClass().getResourceAsStream("x.gif"));

		public PiskyCell(int i, int j) {
			this.i = i;
			this.j = j;
			setWidth(imageX.getWidth()+2);
			setHeight(imageX.getHeight()+2);
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
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
				event.consume();
			});
		}
		public void paintCell() {
			GraphicsContext gc = getGraphicsContext2D();
			gc.strokeRect(0, 0, getWidth(), getHeight());
			if (ps.playground[i][j] == 1)
				gc.drawImage(imageX, 1, 1);
			else if (ps.playground[i][j] == -1) {
				gc.drawImage(imageO, 1, 1);
			}
				
		}
	}
}