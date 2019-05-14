package Pacman;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pacman extends Application {
	char[][]mapa = Maps.map1;
	Image pLeft, pRight, pUp, pDown, cherry, dot, wall;
	double width, height, iconSize;
	int dx, dy = 0;
	int x = 0;
	int y = 0;
	Playground pg;
	int score = 0;
	double sleep = 200;
	Timeline tl;
	Random rnd = new Random();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	nasiel:
        for(int i=0; i<mapa.length; i++)
            for(int j=0; j<mapa[i].length; j++) {
            	if (mapa[i][j] == 'P') {
            		x = j; y = i; break nasiel;
            	}
            }
    	pLeft = new Image("file:./left.gif");
    	pRight = new Image("file:./right.gif");
    	pUp = new Image("file:./up.gif");
    	pDown = new Image("file:./down.gif");
    	cherry = new Image("file:./cherry.gif");
    	dot = new Image("file:./dot.gif");
    	wall = new Image("file:./wall.gif");
    	iconSize = Math.max(pLeft.getWidth(),pLeft.getHeight());
    	height = mapa.length*iconSize;
    	width = mapa[0].length*iconSize;
        pg = new Playground();
        pg.setFocusTraversable(true);
        primaryStage.setTitle("Pacman");
        Scene scene = new Scene(pg);
        primaryStage.setScene(scene);
        primaryStage.show();

        pg.paint();
        pg.requestFocus();
        pg.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
            	dx = 0; dy = -1;
            } else if (e.getCode() == KeyCode.DOWN) {
            	dx = 0; dy = 1;
            } else if (e.getCode() == KeyCode.LEFT) {
            	dx = -1; dy = 0;
            } else if (e.getCode() == KeyCode.RIGHT) {
            	dx = 1; dy = 0;
            }	
        });

        tl = new Timeline(new KeyFrame(new Duration(sleep), e -> {
            tick();
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        
        Timeline tl1 = new Timeline(new KeyFrame(new Duration(5000), e -> {
        	for(;;) {
                int i=rnd.nextInt(mapa.length);
                int j=rnd.nextInt(mapa[i].length);
                if (mapa[i][j] == ' ') { 
                	mapa[i][j] = 'C';
                	return;
                }
        	}
        }));
        tl1.setCycleCount(Timeline.INDEFINITE);
        tl1.play();

        pg.paint();
    }

	private void tick() {
		int xx = x+dx;
		int yy = y+dy;
		if (mapa[yy][xx] == '#')
			return;
		else if (mapa[yy][xx] == '.') { 
			score += 1;
			mapa[yy][xx] = ' ';
		} else if (mapa[yy][xx] == 'C') { 
			score += 10;
			mapa[yy][xx] = ' ';
		}
		x = xx;
		y = yy;
		pg.paint();
	}

    class Playground extends Pane {
        public Playground() {
            setPrefWidth(width);
            setPrefHeight(height);
        }

        public void paint() {
            ObservableList<Node> children = getChildren();
            children.clear();
            Rectangle rect = new Rectangle(0,0, width, height);
            rect.setFill(Color.BLACK);
            children.add(rect);
            for(int i=0; i < mapa.length; i++)
                for(int j=0; j<mapa[i].length; j++) {
                	ImageView iv = null;
                	if (i == y && j == x) {
                   		if (dx == 0 && dy == 1)
                        	iv = new ImageView(pDown);
                		else if (dx == 0 && dy == -1)
                        	iv = new ImageView(pUp);
                		else if (dx == -1 && dy == 0)
                        	iv = new ImageView(pLeft);
                		else if (dx == 1 && dy == 0)
                        	iv = new ImageView(pRight);
                		else
                			iv = new ImageView(pRight);
                	} else 
                	if (mapa[i][j] == '#') {
                    	iv = new ImageView(wall);
                	} else if (mapa[i][j] == '.') {
                    	iv = new ImageView(dot);
                	} else if (mapa[i][j] == 'C') {
                    	iv = new ImageView(cherry);
                	}
                	if (iv != null) {
	                	iv.setX(j*iconSize);
	                	iv.setY(i*iconSize);
	                	children.add(iv);
                	}
                }
            Text text = new Text("Score: "+ score);
            text.setX(100);
            text.setY(100);
            text.setFill(Color.RED);
            text.setFont(Font.font ("Verdana", 20));
            children.add(text);
            if (score > 50) {
            	if (tl != null) {
            		tl.stop();
            		sleep = 200.0 * Math.pow((999.0/1000), score);
            		System.out.println("speedup " + sleep);
                    tl = new Timeline(new KeyFrame(new Duration(sleep), e -> {
                        tick();
                    }));
                    tl.setCycleCount(Timeline.INDEFINITE);
                    tl.play();
           	}
            }
        }
    }
}