package H3;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class RucickyFxCvicenie extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ClockPaneCvicenie clock = new ClockPaneCvicenie();

        // Create a handler for animation
        EventHandler<ActionEvent> eventHandler = e -> {
            clock.setCurrentTime(); // Set a new clock time
        };

        // Create an animation for a running clock
        //Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            clock.setCurrentTime();
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation

        Scene scene = new Scene(clock);    // vytvor scenu

        // nastavena vazieb
        scene.widthProperty().addListener(ov -> clock.setW(scene.getWidth()));
        scene.heightProperty().addListener(ov -> clock.setH(scene.getHeight()));

        primaryStage.setTitle("Hodinky");    // pomenuj okno aplikacie, javisko
        primaryStage.setScene(scene);            // vloz scenu do hlavneho okna, na javisko
        primaryStage.show();                    // zobraz javisko
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPaneCvicenie extends Pane {
    private int hour;
    private int minute;
    private int second;

    // Clock pane's width and height
    private double w = 450, h = 450;

    public ClockPaneCvicenie() {
        setPrefSize(w, h);
        setCurrentTime();
    }

    public void setH(double h) {
        this.h = h;
        paintClock();
    }

    public void setW(double w) {
        this.w = w;
        paintClock();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        // nastav this.hour, minute, second podla aktualneho casu
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        paintClock(); // Repaint the clock
    }

    protected void paintClock() {
        double clockRadius = Math.min(w, h) * 0.7 / 2;
        double centerX = w / 2;
        double centerY = h / 2;
        getChildren().clear();

        // kresli cifernik
        double s = Math.min(w, h) / 2 * (2.0 / 3);
        Circle c = new Circle(centerX, centerY, s);
        c.setFill(Color.DEEPPINK);
        c.setStroke(Color.BLACK);
        getChildren().add(c);

        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        // kresli sekudnovku
        Line l = new Line(centerX, centerY,
                Math.cos(2 * Math.PI * second / 60) * (s - 10) + centerX,
                Math.sin(2 * Math.PI * second / 60) * (s - 10) + centerY);
        l.setStrokeWidth(5);
        getChildren().add(l);

        // kresli minutovku
        // dorobit

        // kresli hodinovku
        // dorobit

        // kresli ciselnik
        // dorobit

    }
}