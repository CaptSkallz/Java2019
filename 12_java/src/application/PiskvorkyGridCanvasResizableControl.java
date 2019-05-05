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

public class PiskvorkyGridCanvasResizableControl extends Application {
	final static int SIZE = 15;
	PiskyState ps = new PiskyState(SIZE);
	Piskyground pg;
	Label lbScore, lbTime, lbOnMove;
	Button btnLoad, btnSave, btnQuit;

	@Override
	public void start(Stage primaryStage) {
		pg = new Piskyground();
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

		Scene scene = new Scene(bp, 500, 500);

		scene.widthProperty().addListener(event -> pg.paintAll());
		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				System.out.println("Height: " + newSceneHeight);
				pg.paintAll();
			}
		});

		pg.paintAll();
		scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
			pg.prefWidth((double) newSceneWidth);
			pg.paintAll();
		} );
		scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
			pg.prefHeight((double) newSceneHeight);
			pg.paintAll();
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
			for (int i = 0; i < PiskvorkyGridCanvasResizableControl.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridCanvasResizableControl.SIZE; j++) {
					PiskyCell pc = canvasGrid[i][j] = new PiskyCell(i, j);
					add(pc, j, i);
					pc.widthProperty().bind(widthProperty().divide(SIZE));
					pc.heightProperty().bind(heightProperty().divide(SIZE));
				}
		}

		public void paintAll() {
			for (int i = 0; i < PiskvorkyGridCanvasResizableControl.SIZE; i++)
				for (int j = 0; j < PiskvorkyGridCanvasResizableControl.SIZE; j++)
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
				pg.paintAll();
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