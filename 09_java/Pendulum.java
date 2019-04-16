import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final Group group = new Group();
        final Scene scene = new Scene(group, 600, 400, Color.WHITE);
        stage.setScene(scene);
        stage.setTitle("Pendulum Animation");
        stage.show();
        //Pendulum Line
        final Line pendulumHand = new Line(0, 175, 0, 0);
        pendulumHand.setTranslateX(225);
        pendulumHand.setTranslateY(50);

        //Pendulum Ball
        final Circle circle = new Circle(0, 0, 5);
        circle.setFill(Color.BLACK);

        final Rotate secondRotate = new Rotate(90);

        pendulumHand.getTransforms().add(secondRotate);
        //moves pendulum hand
        final Timeline secondTime = new Timeline(
                  new KeyFrame(
                            Duration.seconds(1.0),
                            new KeyValue(
                                      secondRotate.angleProperty(),
                                      -90, Interpolator.EASE_BOTH
                            )
                  )
        );

        secondTime.setAutoReverse(true);
        secondTime.setCycleCount(Timeline.INDEFINITE);
        //pendulum arc
        ArcTo arcTo = new ArcTo(100, 100, 0, 400, 50, true, false);

        //transforming pendulum arc to shape
        final Path arcPath = new Path();
        arcPath.getElements().addAll(new MoveTo(50, 50), arcTo);
        arcPath.setOpacity(0.1);

        //adding all into pathtransition
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(1.0));
        pathTransition.setPath(arcPath);
        pathTransition.setNode(circle);
        pathTransition
                  .setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);

        group.getChildren().add(arcPath);
        group.getChildren().add(circle);
        group.getChildren().add(pendulumHand);

        pathTransition.play();
        secondTime.play();

    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }
}