package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class MyButton extends Button {
	public MyButton(String text) {
		super(text);
		setMaxWidth(Double.MAX_VALUE);
		setMaxHeight(Double.MAX_VALUE);
		setStyle("-fx-border-color: blue; -fx-font: 24px 'Arial';");
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("stlačil si " + text);

			}
		});
		setOnAction(event -> {
			System.out.println("stlačil si " + text);
		} );
		setOnMouseClicked(event -> {
			System.out.println("klikol si " + text + ", " + event.getX() + ", " + event.getY());
		} );
		setOnMouseEntered(event -> {
			System.out.println("myš vošla " + text + ", " + event.getX() + ", " + event.getY());
		} );
		setOnKeyPressed(event -> {
			System.out.println("stlačil si " + text + ", " + event.getCode());
		} );
	}
}

public class Layouts extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// ------------------------------------------------
			{
				BorderPane root = new BorderPane();
				root.setTop(new MyButton("Som na vrchu"));
				root.setBottom(new MyButton("I'm in the bottom"));
				root.setRight(new MyButton("Ich bin recht"));
				root.setLeft(new MyButton("Я в левом"));
				root.setCenter(new MyButton("Je suis au milieu"));
				Scene scene = new Scene(root, 600, 400);
				primaryStage.setScene(scene);
				primaryStage.setTitle("BorderPane");
				primaryStage.show();
			}
			// ------------------------------------------------
			{
				FlowPane root = new FlowPane(new MyButton("Som prvý"), new MyButton("Som druhý"),
						new MyButton("Som tretí"));
				Scene scene = new Scene(root, 300, 400);
				Stage newStage = new Stage();
				newStage.setScene(scene);
				newStage.setTitle("FlowPane");
				newStage.show();
			}
			// ------------------------------------------------
			{
				GridPane root = new GridPane();
				for (int i = 0; i < 5; i++)
					for (int j = 0; j < 5; j++)
						root.add(new MyButton(i + "x" + j), j, i);
				root.setHgap(10);
				root.setVgap(10);
				Scene scene = new Scene(root, 400, 400);
				Stage newStage = new Stage();
				newStage.setScene(scene);
				newStage.setTitle("GridPane");
				newStage.show();
			}
			// ------------------------------------------------
			{
				HBox root = new HBox(new MyButton("Som prvý"), new MyButton("Som druhý"), new MyButton("Som tretí"));
				Scene scene = new Scene(root, 600, 200);
				Stage newStage3 = new Stage();
				newStage3.setScene(scene);
				newStage3.setTitle("HBox");
				newStage3.show();
			}
			// ------------------------------------------------
			{
				VBox root = new VBox(new MyButton("Som prvý"), new MyButton("Som druhý"), new MyButton("Som tretí"));
				Scene scene = new Scene(root, 200, 200);
				Stage newStage3 = new Stage();
				newStage3.setScene(scene);
				newStage3.setTitle("VBox");
				newStage3.show();
			}
			// ------------------------------------------------
			{
				Button btn1 = new Button("naozaj som prvý");
				btn1.setPrefSize(150, 150);
				btn1.setStyle("-fx-background-color: blue");

				Button btn2 = new Button("som druhý");
				btn2.setPrefSize(100, 100);
				btn2.setStyle("-fx-background-color: red");

				Button btn3 = new Button("tretí");
				btn3.setPrefSize(50, 50);
				btn3.setStyle("-fx-background-color: green");

				StackPane root = new StackPane(btn1, btn2, btn3);
				Scene scene = new Scene(root, 200, 200);
				Stage newStage = new Stage();
				newStage.setScene(scene);
				newStage.setTitle("StackPane");
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
