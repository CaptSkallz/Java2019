package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BalloonKiller extends Application {
	ballpane bp;
	int width = 800;
	int height = 600;
	ArrayList<balon> balony = new ArrayList<balon>();
	int score;
	int poradie;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		bp = new ballpane();
		Random rnd  = new Random();
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			int r1 = rnd.nextInt(15)+20;
			int r2 = rnd.nextInt(25)+30;
			balon b = new balon(rnd.nextInt(width-r1*2)+r1, rnd.nextInt(height-r2*2)+r2, r1, r2,
					-rnd.nextDouble(),-rnd.nextDouble(), poradie);
			poradie++;
			balony.add(b);
			System.out.println("vzinkol balon " + balony.size());
			b.start();
		}));
		
		Timeline animation2 = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			for (balon b : balony) {
				b.update(width, height);
			}
			bp.print();
		}));
		bp.setOnMouseClicked(event -> {
			//System.out.println(event.getX() + " " + event.getY());
			double x = event.getX();
			double y = event.getY();
			for (int i = balony.size()-1; i >= 0 ; i--) {
				balon b = balony.get(i);
				if (Math.pow(((x-b.x)/b.r1),2) + Math.pow(((y-b.y)/b.r2),2) <= 1){
					balony.remove(b);
					score += b.colno;
					System.out.println("trafil si balon " + b.cislo + " ziskavas " + b.colno );
					break;
				}
			}
			
		});
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		animation2.setCycleCount(Timeline.INDEFINITE);
		animation2.play();

		Scene scene = new Scene(bp);


		scene.widthProperty().addListener(ov -> bp.setW(scene.getWidth()));
		scene.heightProperty().addListener(ov -> bp.setH(scene.getHeight()));

		primaryStage.setTitle("Balloon Killer");
		primaryStage.setScene(scene);
		primaryStage.show();

		}

	class ballpane extends Pane{
		double h = height;
		double w = width;

		public ballpane(){
			setPrefSize(w, h);
		}
		public void setH(double d) {
			this.h = d;
		}
		public void setW(double d) {
			this.w = d;
		}
		public void print(){
			getChildren().clear();
			for (balon b : balony) {
				Ellipse e = new Ellipse(b.x, b.y, b.r1, b.r2);
				e.setStroke(Color.BLACK);
				e.setFill(b.colors[b.colno]);
				getChildren().add(e);
				Label l = new Label(b.cislo + "");
				l.setLayoutX(b.x);
				l.setLayoutY(b.y);
				getChildren().add(l);
			}
			Label l = new Label(score + "");
			l.setLayoutX(50);
			l.setLayoutY(50);
			getChildren().add(l);
		}
	}
	class balon extends Thread{
		double x;
		double y;
		double r1;
		double r2;
		double dx;
		double dy;
		int cislo;
		final Color[] colors = { Color.WHITE, Color.BEIGE, Color.LIGHTYELLOW, Color.LEMONCHIFFON,Color.MOCCASIN, Color.NAVAJOWHITE, Color.PEACHPUFF, Color.SANDYBROWN, Color.YELLOW, Color.SALMON, Color.CORAL, Color.ORANGE, Color.DARKOLIVEGREEN, Color.TOMATO, Color.RED };
		Color col = colors[0];
		int colno = 0;
		boolean zije = true;
		
		public balon(double x, double y, double r1, double r2, double dx, double dy, int cislo){
			this.x = x;
			this.y = y;
			this.r1 = r1;
			this.r2 = r2;
			this.dx = dx;
			this.dy = dy;
			this.cislo = cislo;
		}
		public void update(int w, int h) {
			x += dx;
			y += dy;
			if (x < r1)
				dx = -dx;
			if (y < r2)
				dy = -dy;
			if (x > w-r1)
				dx = -dx;
			if (y > h-r2)
				dy = -dy;
			dy+= 0.01;
		}
		public void run() {
			while (zije) {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				colno++;
				if (colno == colors.length){
					zije = false;
					System.out.println("zmizol balon" + cislo);
					balony.remove(this);
				}
			}
		}
	}
}
