package Skuska3_2015;

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
	static HashSet<Light> lights = new HashSet<Light>();
	static String[] input = { "......", "./..\\.", "...\\..", "../...", "...\\..", "......" };
	/*
	static String[] input = 
		{ ".......",
		 "./././.", 
		 ".......", 
		 "./././.", 
		 ".......", 
		 "./././.", 
		  "......." 
		};
		*/
	final static int SIZE = input.length-2;
	static Box[][] boxes = new Box[SIZE + 2][SIZE + 2];
	
	Button btnLoad, btnSave, btnQuit, btnHideShow;
	static final int leftup = 0;
	static final int rightup = 1;
	static final int leftdown = 2;
	static final int rightdown = 3;
	static final int horiz = 4;
	static final int verti = 5;
	static boolean hidden = false;
	static GridPane gp;

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
				btnHideShow = new Button("Hide/Show"),
				btnQuit = new Button("Quit")
				);
		buttonPane.setSpacing(122);
		bp.setBottom(buttonPane);

		btnQuit.setOnAction(event -> System.exit(0));
		btnLoad.setOnAction(event -> {
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("Mirrors.cfg"));
				boxes = (Box[][]) is.readObject();
				try {
					@SuppressWarnings("unchecked")
					HashSet<Light> readObject = (HashSet<Light>) is.readObject();
					lights = readObject;
				} catch(Exception e) {
					e.printStackTrace();
				}
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
				fs = new ObjectOutputStream(new FileOutputStream("Mirrors.cfg"));
				fs.writeObject(boxes);
				fs.writeObject(lights);
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} );
		btnHideShow.setOnAction(event -> {
			Mirrors.hidden = !Mirrors.hidden;
			paintAll();
		} );
		primaryStage.setTitle("Mirrors");
		primaryStage.setScene(new Scene(bp));
		primaryStage.show();
		paintAll();

	}

	public static void main(final String[] args) {
		Application.launch(args);
	}

	public static void paintAll() {
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
}

class Box extends Canvas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int i, j;
	int status; // -1, 0, 1 mirrors, -2, 2 lights
	MyColor[] shapes = new MyColor[6];

	public Box(Mirrors ap, int i, int j) {
		this.i = i;
		this.j = j;
		if (Mirrors.input[i].charAt(j) == '/')
			status = 1;
		else if (Mirrors.input[i].charAt(j) == '\\')
			status = -1;
		else
			status = 0;
		setWidth(100);
		setHeight(100);
		setOnMouseClicked(e -> {
			if (i == 0 || i == Mirrors.SIZE + 1 || j == 0 || j == Mirrors.SIZE + 1) {
				if ((i == 0 || i == Mirrors.SIZE + 1) && (j == 0 || j == Mirrors.SIZE + 1))
					return;
				Light l = null;
				int red = 100 + 30 * i;
				int green = 100 + 30 * j;
				int blue = 0;
				if (i == 0) {
					l = new Light(i, j, 1, 0, red, green, blue);
					status = 2;
				}
				if (i == Mirrors.SIZE + 1) {
					l = new Light(i, j, -1, 0, red, green, blue);
					status = 2;
				}
				if (j == 0) {
					l = new Light(i, j, 0, 1, red, green, blue);
					status = -2;
				}
				if (j == Mirrors.SIZE + 1) {
					l = new Light(i, j, 0, -1, red, green, blue);
					status = -2;
				}
				if (Mirrors.lights.contains(l))
					Mirrors.lights.remove(l);
				else
					Mirrors.lights.add(l);
				// System.out.println(lights);
			} else {
				if (status == 0)
					status = 1;
				else if (status == 1)
					status = -1;
				else
					status = 0;
			}
			Mirrors.paintAll();
		} );
	}
	/*
	private Color composeColors(Color c1, Color c2) {
		if (c1 == null)
			return c2;
		if (c2 == null)
			return c1;
		return Color.rgb((int) (c1.getRed() + c2.getRed()), (int) (c1.getGreen() + c2.getGreen()),
				(int) (c1.getBlue() + c2.getBlue()));
	}
	*/
	private void composeColors(int i, int j, int shape, Light l) {
		if (l == null)
			return;
		if (Mirrors.boxes[i][j].shapes[shape] == null) {
			Mirrors.boxes[i][j].shapes[shape] = new MyColor(0, 0, 0);
		}
		Mirrors.boxes[i][j].shapes[shape].red += l.red;
		Mirrors.boxes[i][j].shapes[shape].green += l.green;
		Mirrors.boxes[i][j].shapes[shape].blue += l.blue;

	}

	public void paintBefore() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, getWidth(), getHeight());
		if (0 == i || i == Mirrors.SIZE + 1 || 0 == j || j == Mirrors.SIZE + 1) {
			gc.setFill(new Color(0.5, 0.5, 0.5, 1.0));
			gc.fillRect(0, 0, getWidth(), getHeight());
		}
		gc.setStroke(Color.WHITE);
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
		gc.setLineWidth(3);
		if (Mirrors.boxes[i][j].shapes[Mirrors.horiz] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.horiz].toColor());
			gc.strokeLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		}
		if (Mirrors.boxes[i][j].shapes[Mirrors.verti] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.verti].toColor());
			gc.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		}
		if (Mirrors.boxes[i][j].shapes[Mirrors.leftup] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.leftup].toColor());
			gc.strokeLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
			gc.strokeLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, 0);
		}
		if (Mirrors.boxes[i][j].shapes[Mirrors.rightdown] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.rightdown].toColor());
			gc.strokeLine(getWidth() / 2, getHeight(), getWidth() / 2, getHeight() / 2);
			gc.strokeLine(getWidth() / 2, getWidth() / 2, getWidth(), getHeight() / 2);
		}
		if (Mirrors.boxes[i][j].shapes[Mirrors.leftdown] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.leftdown].toColor());
			gc.strokeLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
			gc.strokeLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight());
		}
		if (Mirrors.boxes[i][j].shapes[Mirrors.rightup] != null) {
			gc.setStroke(Mirrors.boxes[i][j].shapes[Mirrors.rightup].toColor());
			gc.strokeLine(getWidth() / 2, 0, getWidth() / 2, getHeight() / 2);
			gc.strokeLine(getWidth() / 2, getWidth() / 2, getWidth(), getHeight() / 2);
		}
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
	}

	public void paint() {
		//GraphicsContext gc = getGraphicsContext2D();
		if (Mirrors.hidden)
			if (!(0 == i || i == Mirrors.SIZE + 1 || 0 == j || j == Mirrors.SIZE + 1)) 
				return;

		for (Light l : Mirrors.lights) {
			int x = l.x, y = l.y;
			int dx = l.dx, dy = l.dy;
			while (0 <= x && x <= Mirrors.SIZE + 1 && 0 <= y && y <= Mirrors.SIZE + 1) {
				int st = Mirrors.boxes[x][y].status;
				if (i == x && j == y) {
					if (st == 0 || st == -2 || st == 2) {
						if (dx == 0)
							composeColors(i, j, Mirrors.horiz, l);
						if (dy == 0)
							composeColors(i, j, Mirrors.verti, l);
					} else { // /
						if (st == 1) {
							if ((dx == 0 && dy > 0) || (dx > 0 && dy == 0)) {
								composeColors(i, j, Mirrors.leftup, l);
							} else {
								composeColors(i, j, Mirrors.rightdown, l);
							}
						} else if (st == -1) {
							if ((dx == 0 && dy > 0) || (dx < 0 && dy == 0)) {
								composeColors(i, j, Mirrors.leftdown, l);
							} else {
								composeColors(i, j, Mirrors.rightup, l);
							}
						}
					}
				}
				if (st == 1) { // /
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

