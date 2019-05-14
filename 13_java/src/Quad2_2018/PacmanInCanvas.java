package Quad2_2018;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PacmanInCanvas extends Application {
	final int iconSize = 32;
	final char[][] map = Maps.map2;
    final int width = map.length * iconSize;
    final int height = map[0].length * iconSize;
    public static double waiting = 200.0;
    public static Timeline tl;
    Image up = new Image("up.gif");
	Image down = new Image("down.gif");
	Image left = new Image("left.gif");
	Image right = new Image("right.gif");
    
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        MyCanvas can = new MyCanvas(map);
        
        
        
        Pane root = new Pane(can);
        root.setFocusTraversable(true);
        primaryStage.setTitle("Pacman");
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        can.paint();
       
        can.requestFocus();
        can.setOnKeyPressed(e -> {
        	if (e.getCode() == KeyCode.UP) {
       			can.pacman = up;
        		can.smer = "UP";
        	} else if (e.getCode() == KeyCode.DOWN) {
        		can.pacman = down;
        		can.smer = "DOWN";
        		
        	} else if (e.getCode() == KeyCode.LEFT) {
        		can.pacman = left;
        		can.smer = "LEFT";
        		
        	} else if (e.getCode() == KeyCode.RIGHT) {
        		can.pacman = right;
        		can.smer = "RIGHT";
        		
        	}
        	
        });

        tl = new Timeline(new KeyFrame(new Duration(waiting), e -> {
        	can.paint();
        }));
        
        
        Timeline t2 = new Timeline(new KeyFrame(new Duration(5000), e -> {
        	can.addFruit();
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        t2.setCycleCount(Timeline.INDEFINITE);
        t2.play();
    }

    

    class MyCanvas extends Canvas {
    	public Image wall = new Image("wall.gif");
    	public Image pacman = new Image("pacman.gif");
    	public Image dot = new Image("dot.gif");
    	public Image cherry = new Image("cherry.gif");
    	public String smer = "NEUTRAL";
    	int PX;
    	int PY;
    	public Random rand = new Random();
    	
    	
    	public int stav = 0;
    	
    	char[][] map;
        public MyCanvas(char[][] map) {
            setWidth(width);
            setHeight(height);
            this.map = map;
            for (int i=0; i< map.length; i++) {
            	for (int j=0; j< map[i].length; j++) { 
            		if (map[i][j] == 'P') {
            			
            			PX = i;
            			PY = j;
            		}
            	}
            }
            
        }
        
        public void addFruit() {
        	while (true) {
        		int i= rand.nextInt(map.length);
        		int j = rand.nextInt(map[0].length);
        		if (map[i][j] == '.' || map[i][j] == ' ') {
        			map[i][j] = 'C';
        			return;
        		}
        	}
        }
        
        public void update() {
        	int newPx = -1;
        	int newPY = -1;
        	if (smer.compareTo("UP") == 0) {
        		newPx = PX;
        		newPY = PY-1;
        	} else if (smer.compareTo("DOWN") == 0) {
        		newPx = PX;
        		newPY = PY+1;
        	} else if (smer.compareTo("LEFT") == 0) {
        		newPx = PX-1;
        		newPY = PY;
        	} else if (smer.compareTo("RIGHT") == 0) {
        		newPx = PX+1;
        		newPY = PY;
        	}
        	if (newPx < 0 || newPx > map.length || newPY < 0 || newPY >map[0].length ) {
        		return;
        	}
        	if (map[newPx][newPY] == '#') {
        		return;
        	}
        	if (map[newPx][newPY] == '.' || map[newPx][newPY] == ' ' || map[newPx][newPY] == 'C') {
        		if (map[newPx][newPY] == '.') {
        			stav+=1;
        		} else if (map[newPx][newPY] == 'C') {
        			stav+=10;
        		}
        		if (map[newPx][newPY] != ' ') {
        			if (stav > 50) {
        				tl.stop();
                    	PacmanInCanvas.waiting = 200*Math.pow(99.0/100.0, stav);
                    	System.out.println(PacmanInCanvas.waiting);
                    	tl = new Timeline(new KeyFrame(new Duration(waiting), e -> {
                        	paint();
                        }));
                        tl.setCycleCount(Timeline.INDEFINITE);
                        tl.play();
                    	
            		}
        		}
        		map[PX][PY] = ' ';
        		PX = newPx;
        		PY = newPY;
        		map[PX][PY] = 'P';
        		
        	}
        	
        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();
            double w = getWidth();
            double h = getHeight();
            update();
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,w,h);
            for (int i=0; i< map.length; i++) {
            	for (int j=0; j< map[i].length; j++) {
            		double x = i*iconSize;
            		double y = j* iconSize;
            		if (map[i][j] == '#') {
            			gc.drawImage(wall, x, y);
            		} else if (map[i][j] == '.') {
            			gc.drawImage(dot, x, y);
            			
            		} else if (map[i][j] == 'P' ) {
            			gc.drawImage(pacman, x, y);
            			
            		} else if (map[i][j] == 'C') {
            			gc.drawImage(cherry, x, y);
            		}
            	}
            }
            gc.setFill(Color.RED);
            gc.setFont(Font.font ("Verdana", 15));
            gc.fillText("SCORE: " + stav, 20, 20);
            
            
        }
    }
}