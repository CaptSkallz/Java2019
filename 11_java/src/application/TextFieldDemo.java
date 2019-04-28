package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button b1 = new Button("get selected text");
		Button b2 = new Button("clear text");
		TextField tf = new TextField();
		tf.setPrefWidth(100);
		TextArea ta = new TextArea();
		ta.setEditable(false);
		ta.setPrefWidth(100);
		b1.setOnAction(event -> {
			System.out.println("b1");
			ta.appendText(ta.getSelectedText());
		} );
		b2.setOnAction(event -> {
			System.out.println("b2");
			ta.clear();
		} );
		tf.setOnAction(event -> {
			ta.appendText(tf.getText() + "\n");
		} );
		VBox fp = new VBox(new Label("textfield:"), tf, new Label("textarea:"), ta, new FlowPane(b1, b2));
		Scene scene = new Scene(fp);
		//Scene scene = new Scene(new Group(fp));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
