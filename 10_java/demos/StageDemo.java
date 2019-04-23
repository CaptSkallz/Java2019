package demos;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageDemo extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			{
				Text text = new Text("Decorated");
		          text.setFill(Color.RED);
		          text.setFont(new Font(24));
		        Line line = new Line(2, 8, 104, 8);
		          line.setStroke(Color.BLUE);
		          line.setStrokeWidth(4);
		        Group group = new Group();
		          group.setEffect(new DropShadow());
		          group.getChildren().addAll(text, line);

		          FlowPane root = new FlowPane(group);

		          /*
		          BorderPane root = new BorderPane();
		          root.setTop(new Label("Introducing..."));
		          root.setCenter(group);
				*/
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("DECORATED");
				//primaryStage.show();
			}
			// --------------------------------------
			{
				Group root = new Group(new Button("Undecorated"));
				Scene scene = new Scene(root, 200, 200, Color.BLACK);
				Stage newStage = new Stage(StageStyle.UNDECORATED);
				newStage.setScene(scene);
				newStage.initModality(Modality.WINDOW_MODAL);
				newStage.setTitle("UNDECORATED");
				newStage.show();
			}
			{
				Group root = new Group(new Button("Decorated"));
				Scene scene = new Scene(root);
				Stage newStage = new Stage(StageStyle.DECORATED);
				newStage.setScene(scene);
				newStage.setTitle("UNDECORATED");
				newStage.show();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
