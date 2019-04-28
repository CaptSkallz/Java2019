package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SingleStageDemo extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button btn = new Button("Press me !");
			Pane root = new Pane(btn);
			// 1.moznost
			root.setStyle("-fx-background-color: #" + "FFFF00");
			// 2.moznost			
			Color c = Color.YELLOW;
			root.setStyle("-fx-background-color: #" + 
					Integer.toHexString((int)(256*256*255*c.getRed() + 
											      256*255*c.getGreen() + 
											  	      255*c.getBlue())) 
					);
			// 3.moznost			
			root.setBackground(
					new Background(
							new BackgroundFill(
										Color.YELLOW, null, null)
							)
					);
			Scene scene = new Scene(root,400,400, Color.ORANGE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Primary stage");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
