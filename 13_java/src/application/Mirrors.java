package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mirrors extends Application {
	final int SIZE = 4;
	HashSet<Light> lights = new HashSet<Light>();
	GridPane gp;
	Box[][] boxes = new Box[SIZE + 2][SIZE + 2];
	String[] input = { "......", "./..\\.", "...\\..", "../...", "...\\..", "......" };
	Button btnLoad, btnSave, btnQuit;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		gp = new GridPane();
		for (int i = 0; i <= SIZE + 1; i++)
			for (int j = 0; j <= SIZE + 1; j++)
				gp.add(boxes[i][j] = new Box(this, i, j), j, i);
		// ---
		BorderPane bp = new BorderPane();
		bp.setCenter(gp);
		HBox buttonPane = new HBox(btnLoad = new Button("Load"), btnSave = new Button("Save"),
				btnQuit = new Button("Quit"));
		buttonPane.setSpacing(50);
		bp.setBottom(buttonPane);

		btnQuit.setOnAction(event -> System.exit(0));
		btnLoad.setOnAction(event -> {
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("mirror.cfg"));
				//lights = (HashSet<Light>) is.readObject();
				boxes = (Box[][]) is.readObject();
				paintAll();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		} );

		btnSave.setOnAction(event -> {
			ObjectOutputStream fs;
			try {
				fs = new ObjectOutputStream(new FileOutputStream("mirror.cfg"));
				//fs.writeObject("ss");
				//fs.writeObject(boxes);
				fs.writeObject(lights);
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} );

		primaryStage.setTitle("Mirrors");
		primaryStage.setScene(new Scene(bp));
		primaryStage.show();
		paintAll();

	}

	public static void main(final String[] args) {
		Application.launch(args);
	}

	public void paintAll() {
		for (final Node child : gp.getChildren()) {
			final Box tile = (Box) child;
			tile.paintBefore();
		}
		for (final Node child : gp.getChildren()) {
			final Box tile = (Box) child;
			tile.paint();
		}
		for (final Node child : gp.getChildren()) {
			final Box tile = (Box) child;
			tile.paintAfter();
		}
	}

	final int leftup = 0;
	final int rightup = 1;
	final int leftdown = 2;
	final int rightdown = 3;
	final int horiz = 4;
	final int verti = 5;

	class Box extends Canvas implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int i, j;
		int status; // -1, 0, 1 mirrors, -2, 2 lights
		Mirrors ap;
		MyColor[] shapes = new MyColor[6];

		public Box(Mirrors ap, int i, int j) {
			this.ap = ap;
			this.i = i;
			this.j = j;
			if (input[i].charAt(j) == '/')
				status = 1;
			else if (input[i].charAt(j) == '\\')
				status = -1;
			else
				status = 0;
			setWidth(100);
			setHeight(100);
			setOnMouseClicked(e -> {
				if (i == 0 || i == SIZE + 1 || j == 0 || j == SIZE + 1) {
					if ((i == 0 || i == SIZE + 1) && (j == 0 || j == SIZE + 1))
						return;
					Light l = null;
					int red = 100 + 30 * i;
					int green = 100 + 30 * j;
					int blue = 0;
					if (i == 0) {
						l = new Light(i, j, 1, 0, red, green, blue);
						status = 2;
					}
					if (i == SIZE + 1) {
						l = new Light(i, j, -1, 0, red, green, blue);
						status = 2;
					}
					if (j == 0) {
						l = new Light(i, j, 0, 1, red, green, blue);
						status = -2;
					}
					if (j == SIZE + 1) {
						l = new Light(i, j, 0, -1, red, green, blue);
						status = -2;
					}
					if (lights.contains(l))
						lights.remove(l);
					else
						lights.add(l);
					// System.out.println(lights);
				} else {
					if (status == 0)
						status = 1;
					else if (status == 1)
						status = -1;
					else
						status = 0;
				}
				ap.paintAll();
			} );
		}

		private Color composeColors(Color c1, Color c2) {
			if (c1 == null)
				return c2;
			if (c2 == null)
				return c1;
			return Color.rgb((int) (c1.getRed() + c2.getRed()), (int) (c1.getGreen() + c2.getGreen()),
					(int) (c1.getBlue() + c2.getBlue()));
		}

		private void composeColors(int i, int j, int shape, Light l) {
			if (l == null)
				return;
			if (ap.boxes[i][j].shapes[shape] == null) {
				ap.boxes[i][j].shapes[shape] = new MyColor(0, 0, 0);
			}
			ap.boxes[i][j].shapes[shape].red += l.red;
			ap.boxes[i][j].shapes[shape].green += l.green;
			ap.boxes[i][j].shapes[shape].blue += l.blue;

		}

		public void paintBefore() {
			GraphicsContext gc = getGraphicsContext2D();
			gc.clearRect(0, 0, getWidth(), getHeight());
			if (0 == i || i == SIZE + 1 || 0 == j || j == SIZE + 1) {
				gc.setFill(Color.BEIGE);
				gc.fillRect(0, 0, getWidth(), getHeight());
			}
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(1);
			gc.strokeRect(0, 0, getWidth(), getHeight());
			shapes[0] = null;
			shapes[1] = null;
			shapes[2] = null;
			shapes[3] = null;
			shapes[4] = null;
			shapes[5] = null;
		}

		public void paintAfter() {
			GraphicsContext gc = getGraphicsContext2D();
			if (ap.boxes[i][j].shapes[horiz] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[horiz].toColor());
				gc.strokeLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
			}
			if (ap.boxes[i][j].shapes[verti] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[verti].toColor());
				gc.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			}
			if (ap.boxes[i][j].shapes[leftup] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[leftup].toColor());
				gc.strokeLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
				gc.strokeLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, 0);
			}
			if (ap.boxes[i][j].shapes[rightdown] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[rightdown].toColor());
				gc.strokeLine(getWidth() / 2, getHeight(), getWidth() / 2, getHeight() / 2);
				gc.strokeLine(getWidth() / 2, getWidth() / 2, getWidth(), getHeight() / 2);
			}
			if (ap.boxes[i][j].shapes[leftdown] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[leftdown].toColor());
				gc.strokeLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
				gc.strokeLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight());
			}
			if (ap.boxes[i][j].shapes[rightup] != null) {
				gc.setStroke(ap.boxes[i][j].shapes[rightup].toColor());
				gc.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight() / 2);
				gc.strokeLine(getWidth() / 2, getWidth() / 2, getWidth(), getHeight() / 2);
			}
		}

		public void paint() {
			GraphicsContext gc = getGraphicsContext2D();
			/*
			 * gc.clearRect(0, 0, getWidth(), getHeight()); if (0 == i || i ==
			 * SIZE + 1 || 0 == j || j == SIZE + 1) { gc.setFill(Color.BEIGE);
			 * gc.fillRect(0, 0, getWidth(), getHeight()); }
			 * gc.setStroke(Color.BLACK); gc.setLineWidth(1); gc.strokeRect(0,
			 * 0, getWidth(), getHeight());
			 */
			gc.setLineWidth(5);
			switch (status) {
			case -1:
				gc.setStroke(Color.BLUE);
				gc.strokeLine(0, 0, getWidth(), getHeight());
				break;
			case 1:
				gc.setStroke(Color.BLUE);
				gc.strokeLine(getWidth(), 0, 0, getHeight());
				break;

			}
			for (Light l : lights) {
				// System.out.println(l);
				int x = l.x, y = l.y;
				int dx = l.dx, dy = l.dy;
				while (0 <= x && x <= SIZE + 1 && 0 <= y && y <= SIZE + 1) {
					int st = ap.boxes[x][y].status;
					// System.out.println(x + ", " + y + "," + st);
					if (i == x && j == y) {
						if (st == 0 || st == -2 || st == 2) {
							if (dx == 0)
								composeColors(i, j, horiz, l);
							if (dy == 0)
								composeColors(i, j, verti, l);
						} else { // /
							if (st == 1) {
								if ((dx == 0 && dy > 0) || (dx > 0 && dy == 0)) {
									composeColors(i, j, leftup, l);
								} else {
									composeColors(i, j, rightdown, l);
								}
							} else if (st == -1) {
								if ((dx == 0 && dy > 0) || (dx < 0 && dy == 0)) {
									composeColors(i, j, leftdown, l);
								} else {
									composeColors(i, j, rightup, l);
								}
							}
						}
					}
					if (st == 1) { // /
						// System.out.println("odraz /");
						if (dx != 0) {
							int ndx = dy;
							int ndy = -dx;
							dx = ndx;
							dy = ndy;
						} else {
							int ndx = -dy;
							int ndy = dx;
							dx = ndx;
							dy = ndy;

						}
					}
					if (st == -1) { // \
						// System.out.println("odraz \\");
						int ndx = dy;
						int ndy = dx;
						dx = ndx;
						dy = ndy;
					}
					x += dx;
					y += dy;
				}
			}
		}
	}

	class Light implements Serializable {
		int x, y;
		int dx, dy;
		int red, green, blue; /// Color is not serializable :-(
		
		private static final long serialVersionUID = -6552319171850636836L;

		public Light(int x, int y, int dx, int dy, int red, int green, int blue) {
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		public String toString() {
			return "Light" + x + "," + y + "," + dx + "," + dy;
		}

		@Override
		public boolean equals(Object oo) {
			System.out.println(":compare");
			if (oo instanceof Light) {
				Light o = (Light) oo;
				return (x == o.x && y == o.y && dx == o.dx && dy == o.dy);
			} else
				return false;
		}

		@Override
		public int hashCode() {
			return 0;
		}
	}

	class MyColor implements Serializable {
		int red, green, blue;
		private static final long serialVersionUID = -6552319171850636236L;

		public MyColor(int red, int green, int blue) {
			super();
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		public Color toColor() {
			return Color.rgb(red % 256, green % 256, blue % 256);
		}
	}
}
