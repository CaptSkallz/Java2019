package Pacman;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PacmanInPane extends Application {
    int width = 600;
    int height = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        MyPanel root = new MyPanel();
        root.setFocusTraversable(true);
        primaryStage.setTitle("Pacman");
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.paint();
        root.requestFocus();
        root.setOnKeyPressed(e -> {
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

    class MyPanel extends Pane {
        public MyPanel() {
            setPrefSize(width, height);
        }

        public void paint() {
            ObservableList<Node> children = getChildren();

            children.clear();
            Text text = new Text("Nejaky text");
            text.setX(100);
            text.setY(100);
            text.setFill(Color.RED);
            text.setFont(Font.font ("Verdana", 20));
            children.add(text);

            Rectangle rect = new Rectangle(200, 200, 30, 40);
            rect.setStroke(Color.BLUE);
            children.add(rect);
        }
    }
}