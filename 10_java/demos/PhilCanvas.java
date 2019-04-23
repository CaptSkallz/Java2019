package demos;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Affine;

class PhilCanvas extends Canvas {
	static final int NUMPHILS = 5;
	static final int THINKING = 0;
	static final int HUNGRY = 1;
	static final int GOTRIGHT = 2;
	static final int EATING = 3;
	static final int GOTLEFT = 4;
	Image[] imgs = new Image[5];
	Affine[] philPlace = new Affine[NUMPHILS];

	int[] state = new int[NUMPHILS];

	double[] chopX = new double[NUMPHILS];
	double[] chopY = new double[NUMPHILS];
	boolean[] untable = new boolean[NUMPHILS];

	boolean frozen = false;

	public PhilCanvas() {
		super();
		setWidth(300);
		setHeight(320);

		imgs[0] = new Image("thinking.gif");
		imgs[1] = new Image("hungry.gif");
		imgs[2] = new Image("gotright.gif");
		imgs[3] = new Image("eating.gif");
		imgs[4] = new Image("gotleft.gif");

		double radius = 100.0;
		double philWidth = imgs[0].getWidth();
		double philHeight = imgs[0].getHeight();
		double radians;

		for (int i = 0; i < NUMPHILS; i++) {
			philPlace[i] = new Affine();
			radians = 360 * ((double) i / (double) NUMPHILS);
			philPlace[i].append(Affine.rotate(radians, getWidth()/2, getHeight()/2));
			philPlace[i].append(Affine.translate(0, -radius));
			philPlace[i].append(Affine.translate(-philWidth / 2, -philHeight / 2));
		}

		radius = 35;
		for (int i = 0; i < NUMPHILS; i++) {
			radians = (double) i * 2.0 * Math.PI / (double) NUMPHILS + Math.PI
					/ (double) NUMPHILS;
			chopX[i] = -Math.sin(radians) * radius - 5;
			chopY[i] = -Math.cos(radians) * radius - 5;
			untable[i] = true;
		}
	}
	public void paintCanvas() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GraphicsContext gc = getGraphicsContext2D();
				gc.setFill(Color.GRAY);
				gc.fillRect(0, 0, getWidth(), getHeight());
				for (int i = 0; i < NUMPHILS; i++) {
					philPaint(gc, i, getWidth()/2, getHeight()/2);
				}
				gc.setFill(Color.RED);
				gc.fillOval(getWidth()/2-45, getHeight()/2-45, 90, 90);
				gc.setFill(Color.BLACK);
				for (int i = 0; i < NUMPHILS; i++) {
					if (untable[i])
						gc.fillOval(getWidth()/2+(int) chopX[i], getHeight()/2+(int) chopY[i], 10, 10);
					// gc.strokeText(String.valueOf(i),(int)chopX[i],(int)chopY[i]);
				}
				if (deadlocked()) {
					gc.setStroke(Color.BLACK);
					gc.setFont(Font.font(java.awt.Font.MONOSPACED, FontWeight.BLACK, FontPosture.REGULAR, 24));
					gc.strokeText("DEADLOCKED", getWidth()/2-60, getHeight()/2);
				}
			}
		});
	}

	void philPaint(GraphicsContext gc, int i, double dx, double dy) {
		gc.save();
        gc.setTransform(philPlace[i]);
        gc.drawImage(imgs[state[i]], dx, dy);
		gc.restore();
	}

	synchronized void setPhil(int id, int s)
			throws java.lang.InterruptedException {
		while (frozen)
			wait();
		state[id] = s;
		paintCanvas();
	}

	synchronized void freeze() {
		frozen = true;
	}

	synchronized void thaw() {
		frozen = false;
		notifyAll();
	}

	synchronized void setFork(int id, boolean taken) {
		untable[id] = !taken;
	}

	boolean deadlocked() {
		int i = 0;
		while (i < NUMPHILS && state[i] == GOTRIGHT)
			++i;
		return i == NUMPHILS;
	}

}
