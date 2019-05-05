package application;

import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PiskvorkyGridButtonResizable extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState(SIZE);

	@Override
	public void start(Stage primaryStage) {
		Piskyground pg = new Piskyground();
		pg.layoutBoundsProperty().addListener(
				(observable, oldBounds, newBounds) -> {
					for (final Node child : pg.getChildren()) {
						final Control tile = (Control) child;
						tile.setPrefSize(
								newBounds.getWidth() / SIZE, 
								newBounds.getHeight() / SIZE);
					}
				});

		Scene scene = new Scene(pg);
		primaryStage.setTitle("Resizeable Pišky cez Button");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends GridPane {
		public Piskyground() {
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					add(new PiskyCell(i, j), j, i);
		}
	}

	class PiskyCell extends Button {
		int i, j;
		ImageView imageO = new ImageView(new Image("o.gif"));
		ImageView imageX = new ImageView(new Image("x.gif"));

		public PiskyCell(int i, int j) {
			this.i = i;
			this.j = j;
			imageX.fitWidthProperty().bind(widthProperty()); 
			imageX.fitHeightProperty().bind(heightProperty());
			imageO.fitWidthProperty().bind(widthProperty()); 
			imageO.fitHeightProperty().bind(heightProperty());
			setMinSize(50, 50);
			setOnAction(event -> {
				if (ps.playground[i][j] != 0)
					return;
				if (ps.nextPlayerIsX) {
					ps.playground[i][j] = 1;
					setGraphic(imageX);
				} else {
					ps.playground[i][j] = -1;
					setGraphic(imageO);
				}
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
			} );
		}
	}
}