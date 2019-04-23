package demos;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class NumberCanvas extends Canvas {
	int value_ = 0;
	String title_;
	Color c;

	public NumberCanvas(String title) {
		this(title, Color.CYAN);
	}

	public NumberCanvas(String title, Color c) {
		super();
		title_ = title;
		this.c = c;
		setWidth(250);
		setHeight(150);
	}

	public void setcolor(Color c) {
		paintCanvas();
	}

	public void setvalue(int newval) {
		value_ = newval;
		paintCanvas();
	}

	public void paintCanvas() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				GraphicsContext gc = getGraphicsContext2D();
				gc.setFill(c);
				gc.fillRect(0, 0, getWidth(), getHeight());
				gc.setStroke(Color.BLACK);

				int w = 150;
				int h = 15;
				int x = ((int) getWidth() - w) / 2;
				int y = h;
				
				gc.setFont(Font.font(java.awt.Font.MONOSPACED, FontWeight.BLACK, FontPosture.REGULAR, 24));
				
				String s1 = String.valueOf(value_);
				gc.strokeText(title_ + ": " + s1, x, y);
			}
		});
	}
}
