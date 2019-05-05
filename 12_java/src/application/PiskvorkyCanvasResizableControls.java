package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PiskvorkyCanvasResizableControls extends Application {
	final static int SIZE = 10;
	PiskyState ps = new PiskyState(SIZE);
	Label lbScore, lbTime, lbOnMove;
	Button btnLoad, btnSave, btnQuit;

	@Override
	public void start(Stage primaryStage) {
		Piskyground pg = new Piskyground();
		BorderPane bp = new BorderPane();
		bp.setCenter(pg);

		HBox labelPane = new HBox(
				new Label("Score:"), lbScore = new Label("0"), 
				new Label("Elapsed time:"),lbTime = new Label("0"), 
				new Label("Next:"), lbOnMove = new Label("o"));
		labelPane.setSpacing(20);
		lbScore.setFont(Font.font(18));
		lbTime.setFont(Font.font(18));
		lbOnMove.setFont(Font.font(18));
		bp.setTop(labelPane);

		HBox buttonPane = new HBox(
				btnLoad = new Button("Load"),
				btnSave = new Button("Save"),
				btnQuit = new Button("Quit"));
		buttonPane.setSpacing(50);
		bp.setBottom(buttonPane);

		btnQuit.setOnAction(event -> System.exit(0));
		btnLoad.setOnAction(event -> { 
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("pisky.cfg"));
				ps = (PiskyState) is.readObject();
				pg.paintAll();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			};
		});

		btnSave.setOnAction(event -> {
			ObjectOutputStream fs;
			try {
				fs = new ObjectOutputStream(new FileOutputStream("pisky.cfg"));
				fs.writeObject(ps);
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} );
		Timeline tl = new Timeline(1000);
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
			ps.elapsedTime++;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					lbTime.setText(""+ps.elapsedTime);
				}
			});			
		}));
		tl.play();

		Scene scene = new Scene(new Group(bp), 500, 500);
		pg.widthProperty().bind(scene.widthProperty());
		pg.heightProperty().bind(scene.heightProperty().subtract(60));
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
				lbOnMove.setText((ps.nextPlayerIsX) ? "x" : "o");
			} );
		}

		public void paintAll() {
			GraphicsContext gc = getGraphicsContext2D();
			gc.clearRect(0, 0, getWidth(), getHeight());
			for (int col = 0; col < SIZE; col++)
				for (int row = 0; row < SIZE; row++)
					paintCell(col, row);
			lbTime.setText("" + ps.elapsedTime);
			lbOnMove.setText((ps.nextPlayerIsX) ? "x" : "o");
		}

		public void paintCell(int col, int row) {
			Image imageO = new Image("o.gif", cellWidth() - 2, cellHeight() - 2, false, false);
			Image imageX = new Image("x.gif", cellWidth() - 2, cellHeight() - 2, false, false);

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
			return getWidth() / SIZE;
		}

		private double cellHeight() {
			return getHeight() / SIZE;
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

