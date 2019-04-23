package demos;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ThreadPanel extends BorderPane {
	Button run;
	Button pause;
	ScrollBar bar;
	DisplayThread thread;
	GraphicCanvas canvas;
	boolean hasSlider;

	public ThreadPanel(String title, Color c, boolean hasSlider) {
		super();
		this.hasSlider = hasSlider;
		setPrefSize(300, 300);
		FlowPane buttonPanel = new FlowPane();
		buttonPanel.getChildren().add(run = new Button("Run"));
		run.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (thread != null)
					thread.restartThread();
			}
		});
		buttonPanel.getChildren().add(pause = new Button("Pause"));

		pause.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (thread != null)
					thread.pauseThread();
			}
		});

		setBottom(buttonPanel);
		canvas = new GraphicCanvas(title, c);
		canvas.setColor(Color.RED);
		setTop(canvas);
		bar = new ScrollBar();
		bar.setMin(0);
		bar.setMax(60);
		bar.setValue(0);

		if (hasSlider)
			setCenter(bar);

		bar.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number old_val, Number new_val) {
				if (thread != null)
					thread.setSplit((int) new_val.doubleValue());
			}
		});
	}

	public static boolean rotate() throws InterruptedException {
		return DisplayThread.rotate();
	}

	public static void rotate(int degrees) throws InterruptedException {
		for (int i = 0; i < degrees; i += 6)
			DisplayThread.rotate();
	}

	public void start(Runnable r) {
		thread = new DisplayThread(canvas, r, 100,
				hasSlider ? (int) bar.getValue() : 0, true);
		thread.start();
	}

	public Thread start(Runnable r, boolean suspended) {
		thread = new DisplayThread(canvas, r, 100,
				hasSlider ? (int) bar.getValue() : 0, suspended);
		thread.start();
		return thread;
	}

	public void stop() {
		thread.interrupt();
	}
}

/********************************************************/

class DisplayThread extends Thread {

	GraphicCanvas display;
	boolean suspended = true;
	int angle = 0;
	int segStart = 9999;
	int segEnd = 9999;
	int rate;
	final static int step = 6;
	int barValue;
	Color segColor = Color.CYAN;
	String name;
	Runnable target;

	public DisplayThread(GraphicCanvas g, Runnable target, int rate, int split,
			boolean susp) {
		this.target = target;
		display = g;
		this.rate = rate;
		setSplit(split);
		suspended = susp;
		if (suspended)
			display.setColor(Color.RED);
		else
			display.setColor(Color.GREEN);
	}

	synchronized void waitIfSuspended() throws InterruptedException {
		while (suspended) {
			System.out.println("wait");
			wait();
		}
	}

	void pauseThread() {
		if (!suspended) {
			suspended = true;
			display.setColor(Color.RED);
		}
	}

	void restartThread() {
		if (suspended) {
			suspended = false;
			display.setColor(Color.GREEN);
			synchronized (this) {
				System.out.println("notify");
				notify();
			}
		}
	}

	static boolean rotate() throws InterruptedException {
		DisplayThread d = (DisplayThread) Thread.currentThread();
		synchronized (d) {
			d.waitIfSuspended();
			d.angle = (d.angle + step) % 360;
			d.display.setAngle(d.angle);
			Thread.sleep(d.rate);
			return (d.angle >= d.segStart && d.angle <= d.segEnd);
		}
	}

	synchronized void setSplit(int i) {
		barValue = i;
		segStart = (60 - i) * 3;
		segEnd = segStart + i * 6;
		display.setSegment(segStart, segEnd, segColor);
	}

	public void run() {
		try {
			waitIfSuspended();
			target.run();
			display.setColor(Color.RED); 
			display.setAngle(0);
		} catch (InterruptedException e) {
		}
	}
}

/********************************************************/

class GraphicCanvas extends Canvas {
	int angle_ = 0;
	String title_;
	Color arcColor_ = Color.BLUE;
	int segStart_ = 9999;
	int segEnd_ = 9999;
	Color segColor_ = Color.YELLOW;
	Color c;

	static int Cx = 30;
	static int Cy = 45;

	GraphicCanvas(String title, Color c) {
		super();
		title_ = title;
		setHeight(250);
		setWidth(295);
		arcColor_ = c;
	}

	public void setColor(Color c) {
		this.c = c;
		paintCanvas();
	}

	public void setAngle(int a) {
		angle_ = a;
		paintCanvas();
	}

	public void setSegment(int start, int end, Color c) {
		segStart_ = start;
		segEnd_ = end;
		segColor_ = c;
	}

	public synchronized void paintCanvas() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				GraphicsContext gc = getGraphicsContext2D();
				gc.setFill(c);
				gc.fillRect(0, 0, getWidth(), getHeight());
				gc.setStroke(Color.BLACK);
				gc.setFont(Font.font(java.awt.Font.MONOSPACED, FontWeight.BLACK, FontPosture.REGULAR, 24));
				int w = 150;
				int h = 20;
				int x = ((int) getWidth() - w) / 2;
				int y = h;
				//System.out.println(angle_ + ", " + segStart_ + "," + segEnd_);
				gc.strokeText(title_ + ": " + angle_, x, y);
				// Display the arc
				if (angle_ > 0) {
					Cx = (int) getWidth() / 2;
					Cy = (int) getHeight() / 2;
					double Sx = Cx * 0.85;
					double Sy = Cy * 0.85;
					Cx -= Sx / 2;
					Cy -= Sy / 2;
					if (angle_ < segStart_ || segStart_ == segEnd_) {
						gc.setFill(arcColor_);
						gc.fillArc(Cx, Cy, Sx, Sy, 0, angle_, ArcType.ROUND);
					} else if (angle_ >= segStart_ && angle_ < segEnd_) {
						gc.setFill(arcColor_);
						gc.fillArc(Cx, Cy, Sx, Sy, 0, segStart_, ArcType.ROUND);
						if (angle_ - segStart_ > 0) {
							gc.setFill(segColor_);
							gc.fillArc(Cx, Cy, Sx, Sy, segStart_, angle_
									- segStart_, ArcType.ROUND);
						}
					} else {
						gc.setFill(arcColor_);
						gc.fillArc(Cx, Cy, Sx, Sy, 0, segStart_, ArcType.ROUND);
						gc.setFill(segColor_);
						gc.fillArc(Cx, Cy, Sx, Sy, segStart_, segEnd_
								- segStart_, ArcType.ROUND);
						if (angle_ - segEnd_ > 0) {
							gc.setFill(arcColor_);
							gc.fillArc(Cx, Cy, Sx, Sy, segEnd_, angle_
									- segEnd_, ArcType.ROUND);
						}
					}
				}
			}
		});
	}
}
