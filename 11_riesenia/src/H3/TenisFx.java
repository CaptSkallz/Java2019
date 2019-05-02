package H3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class TenisFx extends Application {
    int width = 600;
    int height = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TenisCanvas tenis = new TenisCanvas();
        Pane pane = new Pane(tenis);
        Scene scene = new Scene(pane, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        tenis.paint();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(15), e -> {
            tenis.update();
            tenis.paint();
        }));

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

        tenis.setOnMouseMoved(x -> {
            tenis.updateMouse(x.getY());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    class TenisCanvas extends Canvas {
        int gulickaX;
        double dX;
        int gulickaY;
        double dY;
        double raketaY;
        int raketaX = 10;

        public TenisCanvas() {
            setWidth(width);
            setHeight(height);
            dX = new Random().nextFloat() * 10 - 5;
            gulickaX = width / 2;
            dY = new Random().nextFloat() * 10 - 5;
            gulickaY = height / 2;
        }

        public void update(){
            gulickaX += dX;
            gulickaY += dY;
        }

        public void updateMouse(double mouseY){
            raketaY = mouseY;
        }

        public void paint() {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, width, height);
            gc.setFill(Color.WHITE);
            gc.fillOval(gulickaX - 10, gulickaY - 10, 20, 20);
            gc.fillRect(raketaX - 5, raketaY - 25, 10, 50);
        }


    }
}
