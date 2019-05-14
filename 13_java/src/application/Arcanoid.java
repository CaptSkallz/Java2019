package application;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Arcanoid extends Application {
	static int SIZEX = 10;
	static int SIZEY = 5;
	State st;
	Playground pg;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		st = new State();
		pg = new Playground();

		Scene scene = new Scene(new Group(pg), 300, 500);
		pg.widthProperty().bind(scene.widthProperty());
		pg.heightProperty().bind(scene.heightProperty());
		pg.init();
		primaryStage.setScene(scene);
		primaryStage.show();
		pg.paint();
		Ball b = new Ball();
		b.start();
	}

	class Playground extends Canvas {

		public Playground() {
			setOnMouseClicked(event -> {
				st.x = event.getX();
				st.y = event.getY();
			} );
		}

		public void init() {
			st.w = getWidth();
			st.h = getHeight();
			st.ballX = getWidth() / 2;
			st.ballY = getHeight() - 30;
			st.x = getWidth() / 2;
			st.y = getHeight() - 20;
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			//double width = st.w = getWidth();
			//double height = st.h = getHeight();
			gc.clearRect(0, 0, st.w, st.h);
			for (int i = 0; i < Arcanoid.SIZEX; i++)
				for (int j = 0; j < Arcanoid.SIZEY; j++)
					if (st.boxes[i][j].visible) {
						gc.setFill(st.boxes[i][j].c);
						gc.fillRect(st.getX(i), st.getY(j), st.cellSizeX(), st.cellSizeY());
						gc.setStroke(Color.BLACK);
						gc.strokeRect(st.getX(i), st.getY(j), st.cellSizeX(), st.cellSizeY());
					}
			gc.setFill(Color.BLUEVIOLET);
			gc.fillRect(st.x - st.rSize / 2, st.y, st.rSize, st.ballSize);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(st.x - st.rSize / 2, st.y, st.rSize, st.ballSize);

			gc.setFill(Color.BLACK);
			gc.fillOval(st.ballX, st.ballY, st.ballSize, st.ballSize);
			gc.setStroke(Color.BLACK);
			gc.strokeOval(st.ballX, st.ballY, st.ballSize, st.ballSize);

		}
	}

	class Ball extends Thread {
		public void run() {
			while (st.ballY < st.h && st.ballY >= 0) {
				//System.out.println(st.ballX + "" + st.ballY);
				st.ballX += st.balldX;
				st.ballY += st.balldY;
				if (st.ballPlayerCollision()) {
					System.out.println("player collistion");
					st.balldY = -st.balldY;
					st.ballX += st.balldX;
					st.ballY += st.balldY;
				} else if ((st.ballX + st.ballSize >= st.w)||(st.ballX - st.ballSize <= 0)) {
					st.balldX = -st.balldX;
					st.ballX += st.balldX;
					st.ballY += st.balldY;
				}
				else {
					touch: for (int i = 0; i < Arcanoid.SIZEX; i++)
						for (int j = Arcanoid.SIZEY - 1; j >= 0; j--)
							if (st.boxes[i][j].visible && st.ballBrickCollision(i, j)) {
								System.out.println("brik collistion "+ i + j);
								st.balldY = -st.balldY;
								st.boxes[i][j].visible = false;
								break touch;
							}
				}
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						pg.paint();

					}
				});

			}
			if (st.ballY >= st.h)
				System.out.println("prehral si");
			else
				System.out.println("vyhral si");
		}
	}
}

class State {
	Random rnd = new Random();
	double w, h;
	double x, y;
	double ballX, ballY;
	double balldX = 3*rnd.nextDouble()-1, balldY = -0.5-rnd.nextDouble();
	double rSize = 100;
	double brickSize = 20;
	double ballSize = 10;
	Box[][] boxes;
	Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };

	public double cellSizeX() {
		return w / Arcanoid.SIZEX;
	}

	public double cellSizeY() {
		return brickSize;
	}

	public double getX(int i) {
		return cellSizeX() * i;
	}

	public double getY(int j) {
		return 20 + cellSizeY() * j;
	}

	public boolean ballBrickCollision(int i, int j) {
		return (ballX >= getX(i)) && (ballX <= getX(i) + cellSizeX()) 
				&&
				(ballY >= getY(j)) && (ballY <= getY(j) + cellSizeY());
	}

	public boolean ballPlayerCollision() {
		return (ballX >= x - rSize / 2) && 
				(ballX <= x + rSize / 2) && 
				ballY + ballSize >= y && ballY <= y;
	}

	public State() {
		Random rnd = new Random();
		boxes = new Box[Arcanoid.SIZEX][Arcanoid.SIZEY];
		for (int i = 0; i < Arcanoid.SIZEX; i++)
			for (int j = 0; j < Arcanoid.SIZEY; j++) {
				boxes[i][j] = new Box();
				boxes[i][j].x = i;
				boxes[i][j].y = i;
				boxes[i][j].c = colors[rnd.nextInt(colors.length)];
				boxes[i][j].visible = true;
			}
	}
}

class Box {
	double x, y;
	Color c;
	boolean visible;
}