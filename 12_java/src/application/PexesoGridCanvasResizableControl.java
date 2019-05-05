package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.GroupLayout.Alignment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PexesoGridCanvasResizableControl extends Application {
	final static int SIZE = 15;
	PexesoState ps = new PexesoState(SIZE);
	Pexesoground pg;
	Label lbScore, lbTime, lbOnMove;
	Button btnLoad, btnSave, btnQuit;

	@Override
	public void start(Stage primaryStage) {
		pg = new Pexesoground();
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
		btnLoad.setPrefHeight(20);
		btnSave.setPrefHeight(20);
		btnQuit.setPrefHeight(20);
		btnLoad.setPrefWidth(100);
		btnSave.setPrefWidth(100);
		btnQuit.setPrefWidth(100);
		buttonPane.setPrefHeight(30);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setSpacing(50);
		bp.setBottom(buttonPane);

		btnQuit.setOnAction(event -> System.exit(0));
		btnLoad.setOnAction(event -> { 
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("Pexeso.cfg"));
				ps = (PexesoState) is.readObject();
				pg.paintAll();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			};
		});

		btnSave.setOnAction(event -> {
			ObjectOutputStream fs;
			try {
				fs = new ObjectOutputStream(new FileOutputStream("Pexeso.cfg"));
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

		Scene scene = new Scene(bp);

		//scene.widthProperty().addListener(event -> pg.paintAll());
		//scene.heightProperty().addListener(event -> pg.paintAll());

		scene.widthProperty().addListener((observableValue, old, newSceneWidth) -> {
			pg.prefWidth((double) newSceneWidth);
			pg.paintAll();
		} );
		scene.heightProperty().addListener((observableValue, old, newSceneHeight) -> {
			pg.prefHeight((double) newSceneHeight);
			pg.paintAll();
		} );
		pg.setPrefHeight(scene.getHeight());
		pg.setPrefWidth(scene.getWidth());
		pg.paintAll();
		primaryStage.setTitle("Resizable Pexeso grid canvasov");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
    //--------------------------------------
	class Pexesoground extends GridPane {
		PexesoCell[][] canvasGrid = new PexesoCell[SIZE][SIZE];

		public Pexesoground() {
			setWidth(500);
			setHeight(500);
			for (int i = 0; i < PexesoGridCanvasResizableControl.SIZE; i++)
				for (int j = 0; j < PexesoGridCanvasResizableControl.SIZE; j++) {
					System.out.println(getWidth());
					canvasGrid[i][j] = new PexesoCell(i, j);
					canvasGrid[i][j].setWidth(getWidth()/SIZE);
					canvasGrid[i][j].setHeight(getHeight()/SIZE);
					add(canvasGrid[i][j], j, i);
					canvasGrid[i][j].widthProperty().bind(widthProperty().divide(SIZE));
					canvasGrid[i][j].heightProperty().bind(heightProperty().divide(SIZE));
				}
		}

		public void paintAll() {
			for (int i = 0; i < PexesoGridCanvasResizableControl.SIZE; i++)
				for (int j = 0; j < PexesoGridCanvasResizableControl.SIZE; j++)
					canvasGrid[i][j].paintCell();
		}
	}
    //--------------------------------------
	class PexesoCell extends Canvas {
		int i, j;

		public PexesoCell(int i, int j) {
			this.i = i;
			this.j = j;
			setOnMouseClicked(event -> {
				ps.playground[i][j] = -ps.playground[i][j];
				paintCell();
				//pg.paintAll();
				ps.nextPlayerIsX = !ps.nextPlayerIsX;
				event.consume();
			} );
		}

		public void paintCell() {
			GraphicsContext gc = getGraphicsContext2D();
			double w = getWidth();
			double h = getHeight();
			
			gc.clearRect(0, 0, w, h);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(0, 0, w, h);
			if (ps.playground[i][j] > 0) {
				System.out.println("repaint " + i + "," + j);
				String s = "images" + System.getProperty("file.separator")+ ps.pika[ps.playground[i][j]-1];
				System.out.println("load image:" + s);
				Image img = new Image(s, w-2, h-2, false, true);
				//imgview.setFitHeight(100);
				//imageView.setFitWidth(100);
				gc.drawImage(img, 1, 1);
			} else //if (ps.playground[i][j] < 0) 
				{
				System.out.println("gray " + i + "," + j);
				System.out.println("gray " + w + "," + h);
				gc.setFill(Color.GRAY);
				gc.fillRect(1, 1, w-2, h-2);
			}
		}
	}
}