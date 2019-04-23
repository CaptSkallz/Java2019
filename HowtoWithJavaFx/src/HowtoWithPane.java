import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HowtoWithPane extends Application {
    // inicialna sirka a vyska aplikacie
    static final int sirka = 400;
    static final int vyska = 500;
    // pocitadlo tikov
    int tick = 0;

    public static void main(String[] args) {
        launch(args);       // vytvori aplikaciu, spusti start()
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Komponenty: Stage primaryStage -> Scene scene -> MyPanel root -> stvorceky, kruzky, obrazky, ...

        // Vytvorime a pospajame komponenty
        MyPanel root = new MyPanel();
        primaryStage.setTitle("HowtoFx");               // nadpis okna
        Scene scene = new Scene(root, sirka, vyska);    // velkost
        primaryStage.setScene(scene);
        // Nastavenie vazieb medzi velkostou sceny a root panelom
        scene.widthProperty().addListener( ov -> { root.setPrefWidth( scene.getWidth());  root.paint(); });
        scene.heightProperty().addListener(ov -> { root.setPrefHeight(scene.getHeight()); root.paint(); });
        // Zobrazime okno a prvy krat vykreslime
        primaryStage.show();
        root.paint();
        // Takto vieme odchytit kliknutie mysou
        root.setOnMouseClicked(ev->System.out.println(ev.getX() + "," + ev.getY()));
        // Vytvorime timeline ... alebo iny so sposob animacie (vid. prednaska 8)
        Timeline tl = new Timeline(new KeyFrame(new Duration(1000), e -> { tick++; root.paint(); }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    class MyPanel extends Pane {

        public MyPanel() {
            setPrefSize(sirka, vyska);
        }

        public void paint() {
            System.out.println("paint");
            // Do panelu sa nekresli, panel ma deti (jednotlive kruzky,
            // stvorceky, atd), ktore pridavame a mazeme. Funkcia getChildren()
            // nam vrati List<> deti.

            // Aktualne rozmery
            double w = getWidth();
            double h = getHeight();
            // Zmazanie - odstranime vsetky deti.
            getChildren().clear();
            // Kruh
            Ellipse c = new Ellipse(w/2, h/2, w/4, h/4);            // vytvorime objekt (este nie je pridany do panelu)
            c.setOnMouseClicked(ev->System.out.println("circle"));  // takto vieme odchytit kliknutie mysou na tento konkretny kruh
            c.setStroke(Color.BLACK);   // farba okraja
            c.setFill(Color.RED);       // farba vyplne
            // (Zaobleny) stvorec
            Rectangle r = new Rectangle(w/2, h/2, w/4, h/4);
            r.setOnMouseClicked(ev->System.out.println("rectangle"));
            r.setArcHeight(10); // zaoblenie rohov
            r.setArcWidth(10);  // zaoblenie rohov
            // Ciara
            Line l = new Line(-w/2, h/4, w/2, h/4);
            l.setStrokeWidth(15);
            l.setStroke(Color.YELLOWGREEN);
            // Obrazok zo suboru (dva sposoby)
            //ImageView img = new ImageView(new File("namornik.gif").toURI().toString());
            ImageView img = new ImageView("file:namornik.gif");
            img.setX(w/8);
            img.setY(h/8);
            img.setFitWidth(w/8);
            img.setFitHeight(h/8);
            // Text
            Text t = new Text(w/10,h/10,"Tick: "+ tick);
            // Az tu pridavame vsetky deti do panelu - bez toho by sa nevykreslili.
            getChildren().addAll(c, r, l, img, t);
        }
    }
}
