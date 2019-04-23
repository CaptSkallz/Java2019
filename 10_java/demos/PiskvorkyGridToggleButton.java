package demos;

import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PiskvorkyGridToggleButton extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState();

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new Piskyground(), 600, 600);
		primaryStage.setTitle("Pisky");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class Piskyground extends GridPane {

		public Piskyground() {
			for (int i = 0; i < PiskvorkyGridToggleButton.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridToggleButton.SIZE; j++)
					add(new PiskyCell(i, j), j, i);
		}
	}

	class PiskyCell extends ToggleButton {
		int i, j;
		// Image imageX = new Image(getClass().getResourceAsStream("o.png"));
		// Image imageO = new Image(getClass().getResourceAsStream("x.png"));
		ImageView imageX = new ImageView(new Image(getClass()
				.getResourceAsStream("o.gif")));
		ImageView imageO = new ImageView(new Image(getClass()
				.getResourceAsStream("x.gif")));

		// ImageView imageX = new ImageView(new Image("o.png"));
		// ImageView imageO = new ImageView(new Image("x.png"));

		public PiskyCell(int i, int j) {
			// super(i + "," + j);

			this.i = i;
			this.j = j;
			setPrefSize(50, 50);
			setOnAction(event -> {
				if (ps.nextPlayerIsX) {
					ps.playground[i][j] = 1;
					setGraphicTextGap(0);
					setGraphic(imageX);
				} else {
					ps.playground[i][j] = -1;
					setGraphic(imageO);
				}
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
			});
		}
	}

	class PiskyState implements Serializable {

		private static final long serialVersionUID = 1L;
		public int[][] playground = new int[PiskvorkyGridToggleButton.SIZE][PiskvorkyGridToggleButton.SIZE];
		public boolean nextPlayerIsX = false;
		public long elapsedTime = 0;
	}
}