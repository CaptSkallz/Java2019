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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PiskvorkyGridButtonResizableControls extends Application {
	final static int SIZE = 10;
	static PiskyState ps = new PiskyState(SIZE);
	Label lbScore, lbTime, lbOnMove;
	Button btnLoad, btnSave, btnQuit;
	static Piskyground pgg;

	@Override
	public void start(Stage primaryStage) {
		Piskyground pg = new Piskyground();
		pgg = pg;
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
				// ??? ako prepreslit buttony
				updateButtons();
				// nefunguje !
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
		
		pg.layoutBoundsProperty().addListener(
				(observable, oldBounds, newBounds) -> {
					for (final Node child : pg.getChildren()) {
						final Control tile = (Control) child;
						tile.setPrefSize(
								newBounds.getWidth() / SIZE, 
								newBounds.getHeight() / SIZE);
					}
				});

		Scene scene = new Scene(bp);
		primaryStage.setTitle("Resizeable Pišky cez Button");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void updateButtons() {
		ImageView imageO = new ImageView(new Image("o.gif"));
		ImageView imageX = new ImageView(new Image("x.gif"));
		
		for (final Node child : pgg.getChildren()) { // ak sa zmení rozmer pg
			final PiskyCell tile = (PiskyCell) child; // zmeò ve¾kosti buniek
			int i = tile.i;
			int j = tile.j;
			imageX.fitWidthProperty().bind(tile.widthProperty()); 
			imageX.fitHeightProperty().bind(tile.heightProperty());
			imageO.fitWidthProperty().bind(tile.widthProperty()); 
			imageO.fitHeightProperty().bind(tile.heightProperty());
			System.out.println(i+","+j+ ","+ps.playground[i][j]);;
			if (ps.playground[i][j] == 0)
				tile.setGraphic(null);
			else if (ps.playground[i][j] == 1) 
				tile.setGraphic(imageX);
			else
				tile.setGraphic(imageO);
		}
		
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

		public PiskyCell(int i, int j) {
			this.i = i;
			this.j = j;
			ImageView imageO = new ImageView(new Image("o.gif"));
			ImageView imageX = new ImageView(new Image("x.gif"));
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
 