package application;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Eurace extends Application {
	static int N = 4;
	private static int dist = 120;
	private static int offset = dist;
	private int[] values = { 1, 2, 5, 10, 20, 50 };
	private Image[] images;
	private EuraceStatus s;
	Playground pg = new Playground();
	Stage pstage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		pstage = primaryStage;
		s = new EuraceStatus();
		pg.setWidth(offset + N * dist);
		pg.setHeight(offset + N * dist);
		offset = dist = (int) (Math.min(pg.getHeight(), pg.getWidth())) / (N + 1);
		s.coins = new int[N][N];
		s.linksUD = new boolean[N][N + 1];
		s.linksLR = new boolean[N][N + 1];
		images = new Image[values.length];
		Random rnd = new Random();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				s.coins[i][j] = rnd.nextInt(values.length);
		for (int i = 0; i < N; i++)
			for (int j = 0; j <= N; j++) {
				s.linksUD[i][j] = true;
				s.linksLR[i][j] = true;
			}

		pg.setOnMouseClicked(evt -> {
			int x = (int) evt.getX();
			int y = (int) evt.getY();
			if (y < 20 && x >= 3 * dist && x <= 4 * dist) 
				s = load();
			else if (y < 20 && x >= 4 * dist && x <= 5 * dist)
				save();
			for (int j = 0; j < N; j++)
				for (int k = 0; k <= N; k++)
					if (Math.sqrt((offset + j * dist - x) * (offset + j * dist - x)
							+ (offset + k * dist - offset + offset / 2 - y)
									* (offset + k * dist - offset + offset / 2 - y)) < 20) {
						s.linksUD[j][k] = false;
						// System.out.println("s.linksUD "+j+","+k);
						dropCoins();
						s.player = !s.player;
					}
			for (int i = 0; i < N; i++)
				for (int k = 0; k <= N; k++)
					if (Math.sqrt((offset + k * dist - offset + offset / 2 - x)
							* (offset + k * dist - offset + offset / 2 - x)
							+ (offset + i * dist - y) * (offset + i * dist - y)) < 20) {
						s.linksLR[i][k] = false;
						// System.out.println("s.linksLR "+i+","+k);
						dropCoins();
						s.player = !s.player;
					}
			pg.paint();
		} );
		Scene scene = new Scene(new Group(pg),500,500);
		pg.widthProperty().bind(scene.widthProperty());
		pg.heightProperty().bind(scene.heightProperty());
		scene.widthProperty().addListener(event -> pg.paint());
		scene.heightProperty().addListener(event -> pg.paint());
		primaryStage.setScene(scene);
		primaryStage.show();
		pg.paint();
	}

	public void save() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		File f = fileChooser.showSaveDialog(pstage);
		if (f != null)
			try {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.s);
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public EuraceStatus load() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		File f = fileChooser.showOpenDialog(pstage);
		if (f != null)
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				EuraceStatus s = (EuraceStatus) ois.readObject();
				this.s = s;
				ois.close();
				return s;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public void dropCoins() {
		HashSet<Point> set = new HashSet<Point>();
		for (int i = 0; i < N; i++) {
			if (s.linksUD[i][0])
				set.add(new Point(i, 0));
			if (s.linksUD[i][N])
				set.add(new Point(i, N - 1));
		}
		for (int j = 0; j < N; j++) {
			if (s.linksLR[j][0])
				set.add(new Point(0, j));
			if (s.linksLR[j][N])
				set.add(new Point(N - 1, j));
		}
		LinkedList<Point> queue = new LinkedList<Point>(set);
		while (!queue.isEmpty()) {
			Point p = queue.removeFirst();
			if (p.y + 1 < N && s.linksUD[p.x][p.y + 1] && !set.contains(new Point(p.x, p.y + 1))) {
				// System.out.print(p.x + "," + p.y + "==>" + (p.x) + "," +
				// (p.y+1) + "\t");
				queue.addLast(new Point(p.x, p.y + 1));
				set.add(new Point(p.x, p.y + 1));
			}
			if (p.y > 0 && s.linksUD[p.x][p.y] && !set.contains(new Point(p.x, p.y - 1))) {
				// System.out.print(p.x + "," + p.y + "==>" + (p.x) + "," +
				// (p.y-1) + "\t");
				queue.addLast(new Point(p.x, p.y - 1));
				set.add(new Point(p.x, p.y - 1));
			}
			if (p.x + 1 < N && s.linksLR[p.y][p.x + 1] && !set.contains(new Point(p.x + 1, p.y))) {
				// System.out.print(p.x + "," + p.y + "==>" + (p.x+1) + "," +
				// p.y + "\t");
				queue.addLast(new Point(p.x + 1, p.y));
				set.add(new Point(p.x + 1, p.y));
			}
			if (p.x > 0 && s.linksLR[p.y][p.x] && !set.contains(new Point(p.x - 1, p.y))) {
				// System.out.print(p.x + "," + p.y + "==>" + (p.x-1) + "," +
				// p.y + "\t");
				queue.addLast(new Point(p.x - 1, p.y));
				set.add(new Point(p.x - 1, p.y));
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (s.coins[i][j] != -1 && !set.contains(new Point(i, j))) {
					if (s.player)
						s.scoreTrue += values[s.coins[i][j]];
					else
						s.scoreFalse += values[s.coins[i][j]];
					s.coins[i][j] = -1;
				}
		pg.paint();
	}

	class Playground extends Canvas {
		public void paint() {
			
			GraphicsContext g = getGraphicsContext2D();
			g.clearRect(0, 0, getWidth(), getHeight());
			offset = dist = (int) (Math.min(getHeight(), getWidth())) / (N + 1);
			
			images[0] = new Image("mince\\1.gif", (double) offset / 2, (double) offset / 2, false, false);
			images[1] = new Image("mince\\2.gif", (double) offset / 2, (double) offset / 2, false, false);
			images[2] = new Image("mince\\5.gif", (double) offset / 2, (double) offset / 2, false, false);
			images[3] = new Image("mince\\10.gif", (double) offset / 2, (double) offset / 2, false, false);
			images[4] = new Image("mince\\20.gif", (double) offset / 2, (double) offset / 2, false, false);
			images[5] = new Image("mince\\50.gif", (double) offset / 2, (double) offset / 2, false, false);
			
			g.setStroke((s.player) ? Color.RED : Color.BLUE);
			g.strokeText("player: " + ((s.player) ? "red" : "blue"), 0, 20);
			g.setStroke(Color.RED);
			g.strokeText("red score: " + s.scoreTrue, dist, 20);
			g.setStroke(Color.BLUE);
			g.strokeText("blue score: " + s.scoreFalse, 2 * dist, 20);
			g.setStroke(Color.BLACK);
			g.strokeText("LOAD", 3 * dist, 20);
			g.strokeRect(3 * dist - 3, 8, 40, 16);
			g.strokeText("SAVE", 4 * dist, 20);
			g.strokeRect(4 * dist - 3, 8, 40, 16);
			g.setStroke(Color.GREEN);
			for (int i = 0; i < N; i++)
				for (int k = 0; k <= N; k++)
					if (s.linksLR[i][k])
						g.fillRect(offset + k * dist - offset + offset / 4, offset + i * dist - 2, offset / 2, 8);
			for (int j = 0; j < N; j++)
				for (int k = 0; k <= N; k++)
					if (s.linksUD[j][k])
						g.fillRect(offset + j * dist - 2, offset + k * dist - offset + offset / 4, 8, offset / 2);
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					if (s.coins[i][j] != -1) {
						Image im = images[s.coins[i][j]];
						int shift = (int) (Math.max(im.getWidth(), im.getHeight())) / 2;
						g.drawImage(im, offset + i * dist - shift, offset + j * dist - shift);
					}
				}
		}

		public void dropCoins() {
			HashSet<Point> set = new HashSet<Point>();
			for (int i = 0; i < N; i++) {
				if (s.linksUD[i][0])
					set.add(new Point(i, 0));
				if (s.linksUD[i][N])
					set.add(new Point(i, N - 1));
			}
			for (int j = 0; j < N; j++) {
				if (s.linksLR[j][0])
					set.add(new Point(0, j));
				if (s.linksLR[j][N])
					set.add(new Point(N - 1, j));
			}
			LinkedList<Point> queue = new LinkedList<Point>(set);
			while (!queue.isEmpty()) {
				Point p = queue.removeFirst();
				if (p.y + 1 < N && s.linksUD[p.x][p.y + 1] && !set.contains(new Point(p.x, p.y + 1))) {
					// System.out.print(p.x + "," + p.y + "==>" + (p.x) + "," +
					// (p.y+1) + "\t");
					queue.addLast(new Point(p.x, p.y + 1));
					set.add(new Point(p.x, p.y + 1));
				}
				if (p.y > 0 && s.linksUD[p.x][p.y] && !set.contains(new Point(p.x, p.y - 1))) {
					// System.out.print(p.x + "," + p.y + "==>" + (p.x) + "," +
					// (p.y-1) + "\t");
					queue.addLast(new Point(p.x, p.y - 1));
					set.add(new Point(p.x, p.y - 1));
				}
				if (p.x + 1 < N && s.linksLR[p.y][p.x + 1] && !set.contains(new Point(p.x + 1, p.y))) {
					// System.out.print(p.x + "," + p.y + "==>" + (p.x+1) + ","
					// +
					// p.y + "\t");
					queue.addLast(new Point(p.x + 1, p.y));
					set.add(new Point(p.x + 1, p.y));
				}
				if (p.x > 0 && s.linksLR[p.y][p.x] && !set.contains(new Point(p.x - 1, p.y))) {
					// System.out.print(p.x + "," + p.y + "==>" + (p.x-1) + ","
					// +
					// p.y + "\t");
					queue.addLast(new Point(p.x - 1, p.y));
					set.add(new Point(p.x - 1, p.y));
				}
			}
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (s.coins[i][j] != -1 && !set.contains(new Point(i, j))) {
						if (s.player)
							s.scoreTrue += values[s.coins[i][j]];
						else
							s.scoreFalse += values[s.coins[i][j]];
						s.coins[i][j] = -1;
					}
			paint();
		}

	}
}


class EuraceStatus implements Serializable {
	static final long serialVersionUID = 11212121212L;
	int[][] coins;
	boolean[][] linksUD;
	boolean[][] linksLR;
	boolean player = true;
	int scoreTrue = 0;
	int scoreFalse = 0;
}
