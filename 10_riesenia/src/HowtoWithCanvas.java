import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class HowtoWithCanvas extends Application {  
    // inicialna sirka a vyska aplikacie
    static final int sirka = 400;
    static final int vyska = 500;
    int tick = 0;       // tikadielko = pocitadlo tikov

    public static void main(String[] args) {
        launch(args);       // vytvori aplikaciu, spusti start()
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Komponenty: Stage primaryStage -> Scene scene -> Pane root -> Canvas can

        // Vytvorime a pospajame komponenty
        MyCanvas can = new MyCanvas();
        Pane root = new Pane(can);
        root.setFocusTraversable(true);
        primaryStage.setTitle("HowtoFx");               // nadpis okna
        Scene scene = new Scene(root, sirka, vyska);    // velkost
        primaryStage.setScene(scene);
        // Nastavenie vazieb medzi velkostou sceny a canvasu
        scene.widthProperty().addListener( ov -> { can.setWidth( scene.getWidth());  can.paint(); });
        scene.heightProperty().addListener(ov -> { can.setHeight(scene.getHeight()); can.paint(); });
        // Zobrazime okno a prvy krat vykreslime
        primaryStage.show();
        can.paint();
        // Takto vieme odchytit kliknutie mysou do Pane root
        root.setOnMouseClicked(ev->System.out.println(ev.getX() + "," + ev.getY()));
        // Vytvorime timeline ... alebo iny so sposob animacie (vid. prednaska 8)
        Timeline tl = new Timeline(new KeyFrame(new Duration(1000), e -> { tick++; can.paint(); }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();      // spustenie tikadielka
    }

    class MyCanvas extends Canvas {

        public MyCanvas() {
            setWidth(sirka);
            setHeight(vyska);
        }

        public void paint() {
            System.out.println("paint");
            GraphicsContext g = getGraphicsContext2D(); // Do tohoto budeme kreslit
            // zisti fakt aktualne rozmery
            double w = getWidth();
            double h = getHeight();
            // Zmazanie - nastavime bielu farbu a nakreslime obdlznik cez cely canvas
            g.setFill(Color.WHITE);
            g.fillRect(0,0,w,h);
            // Kruh - nastavime farbu okraja, vyplne, a nakreslime kruh (argumenty = x, y, width, height)
            g.setStroke(Color.BLACK);
            g.setFill(Color.RED);
            g.fillOval(w/4, h/4, w/2, h/2);
            // Zaobleny stvorec - obdobne
            g.setFill(Color.BLACK);
            g.fillRoundRect(w/2, h/2, w/4, h/4,10, 10);
            // Ciara - sirka, farba a kreslenie
            g.setLineWidth(15);
            g.setStroke(Color.YELLOWGREEN);
            g.strokeLine(-w/2, h/4, w/2, h/4);
            // Obrazok zo suboru - cesta, x, y, width, height
            g.drawImage(new Image("file:namornik.gif"),w/8, h/8, w/8, h/8);
            // Text
            g.setFill(Color.BLACK);
            g.fillText("Tick: "+ tick, w/10,h/10);
        }
    }
}
