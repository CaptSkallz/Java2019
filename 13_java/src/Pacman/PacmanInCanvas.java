package Pacman;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration; 

public class PacmanInCanvas extends Application {
    int width = 600;
    int height = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        MyCanvas can = new MyCanvas();
        Pane root = new Pane(can);
        root.setFocusTraversable(true);
        primaryStage.setTitle("Pacman");
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        can.paint();
        can.requestFocus();
        can.setOnKeyPressed(e -> {
            System.out.printf("stlacil si: %s\n", e.getCode());
        });

        Timeline tl = new Timeline(new KeyFrame(new Duration(1000), e -> {
            tick();

        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    public void tick() {
        System.out.println("Tick");
    }

    class MyCanvas extends Canvas {
        public MyCanvas() {
            setWidth(width);
            setHeight(height);
        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.RED);
            gc.setFont(Font.font ("Verdana", 20));
            gc.fillText("Nejaky text", 100, 100);

            gc.setStroke(Color.BLUE);
            gc.strokeRect(200, 200, 30, 40);
        }
    }
}