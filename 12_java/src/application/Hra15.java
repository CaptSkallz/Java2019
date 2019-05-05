package application;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Hra15 extends Application {
	final int SIZE = 4;
	final int COLS = SIZE;
	final int ROWS = SIZE;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		GridPane gp = new GridPane();
		for (int i = 0; i < 16; i++) {
			Button button = (i == 15) ? new Button("") : new Button("" + (i + 1));
			gp.add(button, i % COLS, i / COLS);
		}
		/*
		gp.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(final ObservableValue<? extends Bounds> observableValue, final Bounds oldBounds,
					final Bounds newBounds) {
				double cellHeight = newBounds.getHeight() / nRows;
				double cellWidth = newBounds.getWidth() / nColumns;
				for (final Node child : gp.getChildren()) {
					// for (final Node child :
					// gridPane.getChildrenUnmodifiable()) {
					final Control tile = (Control) child;
					tile.setPrefSize(cellWidth, cellHeight);
				}
			}
		});
		*/
		gp.layoutBoundsProperty().addListener((observable, oldBounds, newBounds) -> {
			double cellHeight = newBounds.getHeight() / ROWS;
			double cellWidth = newBounds.getWidth() / COLS;
			for (final Node child : gp.getChildren()) {
				final Control tile = (Control) child;
				tile.setPrefSize(cellWidth, cellHeight);
			}
		} );
		primaryStage.setScene(new Scene(gp));
		primaryStage.show();
	}
	public static void main(final String[] args) {
		Application.launch(args);
	}
}