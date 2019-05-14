package application;
import javafx.animation.AnimationTimer;
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.KeyCode;
    import javafx.scene.layout.Pane;
    import javafx.scene.paint.Color;
    import javafx.scene.shape.Line;
    import javafx.stage.Stage;
     
    public class Zabky extends Application {
    	final double rychlostSimulacie = 10;	// ms
    	final double horizontalnyPosun = 1;		// pixel
    											// brvno sa posunie o 1 pixel za 10 ms
    	final double velkostZabky = 50;			// pixel
    	final double sirkaBrvna = 15;			// pixel
    	long lastTimeUpdated;
    	Brvno[][] brvna = {						// predpripravene brvna, nemusite generovat
    			{ new Brvno(10,80, true), new Brvno(200,90, true), new Brvno(400,80, true), new Brvno(600,90, true) },
    			{ new Brvno(10,120, false), new Brvno(250,110, false), new Brvno(470,80, false) },
    			{ new Brvno(25,60, true), new Brvno(220,40, true), new Brvno(430,50, true), new Brvno(620,60, true), new Brvno(720,40, true) },
    			{ new Brvno(10,80, false), new Brvno(400,50, false), new Brvno(600,80, false) },
    			{ new Brvno(30,80, true), new Brvno(170,30, true), new Brvno(330,40, true), new Brvno(500,60, true) },
    			{ new Brvno(20,60, false), new Brvno(220,40, false), new Brvno(400,40, false), new Brvno(500,50, false) }
    	};
    	Playground playground;
    	Zabka zabka;
    	int newGame = 0;
     
    	@Override
    	public void start(Stage primaryStage) {
    		try {
    			playground = new Playground();
    			AnimationTimer at = new AnimationTimer() {
    				@Override
    				public void handle(long now) {
    					//System.out.println("zabka sedi na " + zabka.x + ":" + zabka.y + " az " + zabka.x+velkostZabky + ":" + zabka.y + velkostZabky);
    					if (now/1000000 > lastTimeUpdated/1000000 + rychlostSimulacie) {
    						//System.out.println("jo");
    						playground.update();
    						playground.paintPlayground();
    					}
    				}
    			};
    			at.start();
    			Scene scene = new Scene(playground, 800, 600);
    			zabka = new Zabka();
    			scene.setOnKeyPressed(event -> {
    				if (event.getCode() == KeyCode.DOWN) {
    					zabka.skok(false);
    				}
    				else if (event.getCode() == KeyCode.UP){
    					zabka.skok(true);
    				}
    				for (int i = 0; i < brvna.length; i++) {
    					for (int j = 0; j < brvna[i].length; j++) {
    						Brvno b = brvna[i][j];
    //						System.out.println(Math.abs((b.x + b.dlzka/2) - (zabka.x + velkostZabky/2)));
    //						System.out.println(Math.abs((i+2)*75+10 - (zabka.y + velkostZabky)));
    //						Math.abs((b.x + b.dlzka/2) - (zabka.x + velkostZabky/2)) <= b.dlzka/2 
    //								&& Math.abs((i+2)*75+10 - (zabka.y + velkostZabky)) <= 20
    						if (b.x <= zabka.x + velkostZabky/2 && zabka.x + velkostZabky/2 <= b.x+b.dlzka 
    								&& ((i+2)*75 <= zabka.y && zabka.y <= (i+2)*75 + 20) ) {
    							zabka.sittingOnALog = true;
    							zabka.log = b;
    							System.out.println("nasiel som log");
    							break;
    						}
    					}
    				}
    			});
    			primaryStage.setScene(scene);
    			primaryStage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	//---------------------------------------------------	
    	public class Brvno {
    		double x;			// lavy koniec brvna
    		double dlzka;		// jeho dlzka
    		boolean doPrava;	// ide doprava/dolava
    		public Brvno(double x, double dlzka, boolean doPrava) {
    			this.x = x; this.dlzka = dlzka; this.doPrava = doPrava;
    		}
    //		public void paint(int riadok) {
    //			// ...
    //		}
    		public void update() {
    			if (doPrava) {
    				this.x += horizontalnyPosun;
    			}
    			else {
    				this.x -= horizontalnyPosun;
    			}
    		}
    	}
    	//---------------------------------------------------
    	public class Zabka {
    		public double x=400;
    		public double y=600;
    		boolean sittingOnALog = false;
    		Brvno log;
    // 		public void paint() {
    //			// ...
    //		}
    		public void update() {
    			if (sittingOnALog) {
    				if (log.doPrava) {
    					x += horizontalnyPosun;
    				}
    				else {
    					x -= horizontalnyPosun;
    				}
    			}
    		}
    		public void skok(boolean hore) {
    			if (hore) {
    				y -= 75;
    			}
    			else {
    				y += 75;
    			}
    		}
    	}
    	//---------------------------------------------------
    	public class Playground extends Pane {
    		
    		public void update(){
    			for (int i = 0; i < brvna.length; i++) {
    				for (int j = 0; j < brvna[i].length; j++) {
    					brvna[i][j].update();
    					//if (//zab)
    				}
    			}
    			zabka.update();
    		}
    		public void paintPlayground() {
    			getChildren().clear();
    			for (int i = 0; i < brvna.length; i++) {
    				for (int j = 0; j < brvna[i].length; j++) {
    					Brvno b = brvna[i][j];
    					Line lb = new Line(b.x, (i+2)*75, b.x + b.dlzka, (i+2)*75);
    					lb.setStrokeWidth(20);
    					lb.setStroke(Color.BLACK);
    					getChildren().add(lb);
    					//if (b.x)
    				}
    			}
    			Image img = new Image("zaba.png");
    			ImageView iv = new ImageView(img);
    			iv.setX(zabka.x-velkostZabky);
    			iv.setY(zabka.y-velkostZabky);
    			iv.setFitWidth(velkostZabky);
    	        iv.setPreserveRatio(true);
    			getChildren().add(iv);
    		}
    	}
    	public static void main(String[] args) {
    		launch(args);
    	}
    }