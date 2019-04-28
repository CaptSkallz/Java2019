package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PiskvorkyGridButton extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState();

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new Piskyground());
		primaryStage.setTitle("Pišky cez Button");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends GridPane {
		public Piskyground() {
			//setVgap(10);
			//setHgap(10);
			for (int i = 0; i < PiskvorkyGridButton.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridButton.SIZE; j++)
					add(new PiskyCell(i, j), j, i);
		}
	}

	class PiskyCell extends Button {
		int i, j;
		// ImageView imageX = new ImageView(new Image(getClass()
		// .getResourceAsStream("o.gif")));
		// ImageView imageO = new ImageView(new Image(getClass()
		// .getResourceAsStream("x.gif")));
		ImageView imageX = new ImageView(new Image("o.gif"));
		ImageView imageO = new ImageView(new Image("x.gif"));

		public PiskyCell(int i, int j) {
			this.i = i;
			this.j = j;
			setPrefSize(40, 40);
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