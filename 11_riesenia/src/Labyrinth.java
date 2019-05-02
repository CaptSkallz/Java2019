import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Labyrinth extends Application {

	private int boxSize = 30;
	private int sizeX = 0;
	private int sizeY = 0;
	private char[][] map;
	private int startX, startY;
	private int finishX, finishY;
	private int width = 500;
	private int height = 500;
	private char[][] mapa;
	private int ballX, ballY;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		mapa = readMap("map.txt");	// nacitaj mapu
		sizeY = map.length;
		sizeX = map[0].length;
		width = sizeX * boxSize;
		height = sizeY * boxSize;
		ballX = startY;
		ballY = startX;

		PlayGround pg = new PlayGround();
		Scene scene = new Scene(pg, width, height, Color.rgb(63, 63, 63));
		scene.setOnKeyPressed(event -> {
			int bX = ballX;
			int bY = ballY;
			if (event.getCode().equals(KeyCode.UP)) {
				ballX--;
				//event.consume();
			} else if (event.getCode().equals(KeyCode.DOWN)) {
				ballX++;
				//event.consume();
			} else if (event.getCode().equals(KeyCode.LEFT)) {
				ballY--;
				//event.consume();
			} else if (event.getCode().equals(KeyCode.RIGHT)) {
				ballY++;
				//event.consume();
			}
			if (map[ballX][ballY] == '#') {  // neda sa do stany
				ballX = bX;
				ballY = bY;
			} else {
				// System.out.println(ballX + "," + ballY);
				pg.paint();
			}
		});
		pg.paint();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	class PlayGround extends Pane {

		public PlayGround() { }

		public void paint() {
			getChildren().clear();
			Rectangle box = new Rectangle(width, height, Color.rgb(255, 255, 255));
			box.setX(0);
			box.setY(0);
			getChildren().add(box);
			for (int idx = 0; idx < map.length; idx++) {
				for (int idy = 0; idy < map[idx].length; idy++) {
					if (Math.abs(idx - ballX) < 3 && Math.abs(idy - ballY) < 3) {
						Color col = Color.WHITE; 
						switch (map[idx][idy]) {
							case '#': col = Color.BLACK; break;
							case 'A': col = Color.RED; break;
							case 'B': col = Color.GREEN; break;
						}
						Rectangle box1 = new Rectangle(boxSize, boxSize, col);
						box1.setX(idy * boxSize);
						box1.setY(idx * boxSize);
						getChildren().add(box1);
						if (idx == ballX && idy == ballY) {
							Circle ci = new Circle(boxSize / 2, Color.YELLOW);
							ci.setCenterX(idy * boxSize + boxSize / 2);
							ci.setCenterY(idx * boxSize + boxSize / 2);
							getChildren().add(ci);
						} 
					}
				}
			}
			if (ballY == finishX && ballX == finishY) {
				Text t = new Text(width/2 - 50, height/2, "Gratulujem !");
				t.setFont(new Font("Arial", 24));
				getChildren().add(t);
			}
		}
	}

	public char[][] readMap(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String[] dimensions = br.readLine().split(" ");
			// int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			map = new char[height][];
			for (int i = 0; i < height; i++) {
				String row = br.readLine();
				int idxA = row.indexOf('A');
				if (idxA != -1) {
					startX = idxA;
					startY = i;
				}
				int idxB = row.indexOf('B');
				if (idxB != -1) {
					finishX = idxB;
					finishY = i;
				}
				map[i] = row.toCharArray();
			}
			br.close();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
