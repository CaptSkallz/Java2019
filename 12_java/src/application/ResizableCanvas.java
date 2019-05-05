package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class ResizableCanvas extends Application {
	final int SIZE = 10;
    class Playground extends Canvas {
        public Playground() {
            widthProperty().addListener(evt -> paint());
            heightProperty().addListener(evt -> paint());
        }
 
        private void paint() {
            double width = getWidth();
            double height = getHeight();
 
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);
 
            gc.setStroke(Color.BLACK);
            for(int i = 0; i<SIZE; i++)
            	gc.strokeLine(0, i*height/SIZE, width, i*height/SIZE);
            for(int i = 0; i<SIZE; i++)
            	gc.strokeLine(i*width/SIZE, 0, i*width/SIZE, height);
        }
 /*
        @Override
        public boolean isResizable() {
            return true;
        }
 
        @Override
        public double prefWidth(double height) {
            return getWidth();
        }
 
        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
 */
    }
    @Override
    public void start(Stage stage) throws Exception {
        Playground canvas = new Playground();
        Pane p = new Pane(canvas);
        canvas.widthProperty().bind(p.widthProperty());
        canvas.heightProperty().bind(p.heightProperty());
        stage.setScene(new Scene(p, 400, 400));
        stage.setTitle("Resizable Canvas");
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}