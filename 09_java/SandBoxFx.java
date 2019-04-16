import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SandBoxFx extends Application {
	private static final int STAR_COUNT = 20000;
	private final Random random = new Random();
	static long now = 0;
	static long thnow = 0;
	static int w = 400, h = 400;

	@Override
	public void start(final Stage primaryStage) {
		final Scene scene = new Scene(getAnimationPanel(), 400, 400, Color.BLACK);
		primaryStage.setTitle("Animation Timer");
		primaryStage.setScene(scene);
		primaryStage.show();

		Stage stage = new Stage();
		stage.setTitle("TimeLine");
		stage.setScene(new Scene(getTimeLinePanel(), 400, 400, Color.BLACK));
		stage.show();

		Stage thstage = new Stage();
		thstage.setTitle("Thread");
		thstage.setScene(new Scene(getThreadPanel(), 400, 400, Color.BLACK));
		thstage.show();
	}

	public Pane getAnimationPanel() {
		Rectangle[] nodes = new Rectangle[STAR_COUNT];
		double[] angles = new double[STAR_COUNT];
		long[] start = new long[STAR_COUNT];
		Pane p = new Pane();
		p.setPrefSize(w, h);
		for (int i = 0; i < STAR_COUNT; i++) {
			nodes[i] = new Rectangle(1, 1, Color.WHITE);
			angles[i] = 2.0 * Math.PI * random.nextDouble();
			start[i] = random.nextInt(2000000000);
			p.getChildren().add(i, nodes[i]);
		}
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long now) {
				//System.out.println("at now=" + now);
				final double centerW = 0.5 * w;
				final double centerH = 0.5 * h;
				final double radius = Math.sqrt(2) * Math.max(centerW, centerH);
				for (int i = 0; i < STAR_COUNT; i++) {
					final Node node = nodes[i];
					final double angle = angles[i];
					final long t = (now - start[i]) % 2000000000;
					final double d = t * radius / 2000000000.0;
					node.setTranslateX(Math.cos(angle) * d + centerW);
					node.setTranslateY(Math.sin(angle) * d + centerH);
				}
			}
		};
		at.start();
		return p;
	}

	public Pane getTimeLinePanel() {
		Rectangle[] nodes = new Rectangle[STAR_COUNT];
		double[] angles = new double[STAR_COUNT];
		long[] start = new long[STAR_COUNT];
		Pane p = new Pane();
		p.setPrefSize(w, h);
		for (int i = 0; i < STAR_COUNT; i++) {
			nodes[i] = new Rectangle(1, 1, Color.YELLOW);
			angles[i] = 2.0 * Math.PI * random.nextDouble();
			start[i] = random.nextInt(2000000000);
			p.getChildren().add(i, nodes[i]);
		}
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(50), e -> {
			//System.out.println("tl now=" + now);
			final double centerW = 0.5 * w;
			final double centerH = 0.5 * h;
			final double radius = Math.sqrt(2) * Math.max(centerW, centerH);
			for (int i = 0; i < STAR_COUNT; i++) {
				final Node node = nodes[i];
				final double angle = angles[i];
				SandBoxFx.now -= 400;
				final long t = (now - start[i]) % 2000000000;
				final double d = t * radius / 2000000000.0;
				node.setTranslateX(Math.cos(angle) * d + centerW);
				node.setTranslateY(Math.sin(angle) * d + centerH);
			}
		} ));
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
		return p;
	}

	public Pane getThreadPanel() {
		Rectangle[] nodes = new Rectangle[STAR_COUNT];
		double[] angles = new double[STAR_COUNT];
		long[] start = new long[STAR_COUNT];
		Pane p = new Pane();
		p.setPrefSize(w, h);
		for (int i = 0; i < STAR_COUNT; i++) {
			nodes[i] = new Rectangle(1, 1, Color.ORANGE);
			angles[i] = 2.0 * Math.PI * random.nextDouble();
			start[i] = random.nextInt(2000000000);
			p.getChildren().add(i, nodes[i]);
		}
		Thread th = new Thread() {
			public void run() {
				while (true) {
					//System.out.println("th now=" + thnow);
					final double centerW = 0.5 * w;
					final double centerH = 0.5 * h;
					final double radius = Math.sqrt(2) * Math.max(centerW, centerH);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							for (int i = 0; i < STAR_COUNT; i++) {
								final Node node = nodes[i];
								final double angle = angles[i];
								SandBoxFx.thnow -= 400;
								final long t = (thnow - start[i]) % 2000000000;
								final double d = t * radius / 2000000000.0;
								node.setTranslateX(Math.cos(angle) * d + centerW);
								node.setTranslateY(Math.sin(angle) * d + centerH);
							}
						}
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
		return p;
	}

	public static void main(String[] args) {
		launch(args);
	}
}