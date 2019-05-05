package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Plumber extends Application {
	static int Width, Height;
	static int[][] playGround;
	PlumberThread Pt;
	Image img[] = new Image[10];
	Image img_blue[] = new Image[10];
	int steps = 0;
	int time = 0;
	Label lbSteps;
	Label lbTime;
	ArrayList<PlumberCanvas> pcs = new ArrayList<PlumberCanvas>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
		for (int i = 1; i <= 8; i++) {
			img[i] = new Image("images\\plumber" + i + ".png");
			img_blue[i] = new Image("images\\plumber" + i + "_blue.png");
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Plumber.txt")));
			String line = br.readLine();
			System.out.println(line);
			Width = Integer.parseInt(line);
			line = br.readLine();
			System.out.println(line);
			Height = Integer.parseInt(line);
			System.out.println(Width + ";" + Height);
			playGround = new int[Width][Height];
			for (int j = 0; j < Height; j++)
				for (int i = 0; i < Width; i++)
					playGround[i][j] = 0;
			for (int j = 0; j < Height; j++) {
				line = br.readLine();
				System.out.println(line);
				for (int i = 0; i < Width; i++)
					if (line.charAt(i) == '.')
						playGround[i][j] = 0;
					else
						playGround[i][j] = line.charAt(i) - 48;
			}
		} catch (Exception E) {
			System.out.println("file does not exist, " + E.getMessage());
		}
		BorderPane bp = new BorderPane();	
		GridPane pg = new GridPane();
		pg.setPrefSize( Width * 70, (Height + 2) * 70);
		pg.add(new PlumberCanvas( 0, -1, 7), 0, 0);
		PlumberCanvas pc;
		for (int i = 1; i < Width; i++) {
			pg.add(pc = new PlumberCanvas( i, -1, 0), i, 0);
			pcs.add(pc);
		}
		for (int j = 0; j < Height; j++)
			for (int i = 0; i < Width; i++) {
				pg.add(pc = new PlumberCanvas( i, j, playGround[i][j]), i, j+1);
				pcs.add(pc);
			}
		for (int i = 1; i < Width; i++) {
			pg.add(pc = new PlumberCanvas( i, Height + 1, 0), i, Height+1);
			pcs.add(pc);
		}
		pg.add(pc = new PlumberCanvas( Width, Height + 1, 8), Width-1, Height+1);
		pcs.add(pc);
		bp.setCenter(pg);
		
		lbSteps = new Label("Steps:     ");
		lbTime = new Label("Time:     ");
		HBox lbPanel = new HBox(lbSteps,lbTime);
		bp.setTop(lbPanel);
		new PlumberThread().start();
		Scene scene = new Scene(bp, Width * 70, (Height + 2) * 70 + 40);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

	public void paintAll() {
		for (PlumberCanvas pc : pcs)
			pc.paint();
	}
	
	class PlumberThread extends Thread {
		public void run() {
			while (true) {
				try {
					sleep(1000);
					time++;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							lbTime.setText("Time: " + time);
						}
					});
				} catch (Exception E) {
					E.printStackTrace();
				}
			}
		}
	}

	class PlumberCanvas extends Canvas {
		int sx, sy;
		int state;

		public PlumberCanvas(int x, int y, int s) {
		state = s; sx = x; sy = y;
		setWidth(70);
		setHeight(70);
		setOnMouseClicked(event -> {
				if (state == 7) {
					state = -7;
					paint();
					flow();
					paintAll();
				} else if (state == 8) {
				} else if (state == 0) {
				} else {
					steps++;
					lbSteps.setText("Steps: " + steps);
					int stat = playGround[sx][sy];
					if (stat == 1)
						stat = 2;
					else if (stat == 2)
						stat = 3;
					else if (stat == 3)
						stat = 4;
					else if (stat == 4)
						stat = 1;
					else if (stat == 5)
						stat = 6;
					else if (stat == 6)
						stat = 5;
					playGround[sx][sy] = stat;
					state = stat;
					paint();
				}
			
		});
		paint();
	  }

		public void paint() {
			int stat = state;
			GraphicsContext gc = getGraphicsContext2D();
			if (Math.abs(state) > 0 && Math.abs(state) < 7)
				// if (sx >= 0 && sy >= 0 && sy < Height)
				stat = playGround[sx][sy];
			if (stat > 0)
				gc.drawImage(img[stat], 0, 0);
			if (stat < 0)
				gc.drawImage(img_blue[-stat], 0, 0);
		}

		public void flow() {
			int x = 0;
			int y = 0;
			int dx = 0;
			int dy = 1;

			while (true) {
				if (x < 0 || x >= Width)
					break;
				if (y < 0 || y >= Height)
					break;
				//System.out.println(x + "," + y + " dx=" + dx + ", dy=" + dy + " stav:" + playGround[x][y]);
				if (playGround[x][y] == 3 && (dx == 0 && dy == 1)) {
					dx = 1;
					dy = 0;
				} else if (playGround[x][y] == 3 && (dx == -1 && dy == 0)) {
					dx = 0;
					dy = -1;
				} else if (playGround[x][y] == 2 && (dx == 1 && dy == 0)) {
					dx = 0;
					dy = -1;
				} else if (playGround[x][y] == 2 && (dx == 0 && dy == 1)) {
					dx = -1;
					dy = 0;
				} else if (playGround[x][y] == 1 && (dx == 1 && dy == 0)) {
					dx = 0;
					dy = 1;
				} else if (playGround[x][y] == 1 && (dx == 0 && dy == -1)) {
					dx = -1;
					dy = 0;
				} else if (playGround[x][y] == 4 && (dx == 0 && dy == -1)) {
					dx = 1;
					dy = 0;
				} else if (playGround[x][y] == 4 && (dx == -1 && dy == 0)) {
					dx = 0;
					dy = 1;
				} else if (playGround[x][y] == 5 && (dx == 0 && dy == 1)) {
				} else if (playGround[x][y] == 5 && (dx == 0 && dy == -1)) {
				} else if (playGround[x][y] == 6 && (dx == 1 && dy == 0)) {
				} else if (playGround[x][y] == 6 && (dx == -1 && dy == 0)) {
				} else
					break;
				playGround[x][y] = -playGround[x][y];
				x += dx;
				y += dy;
				paint();
			}
		}
	}
}
 