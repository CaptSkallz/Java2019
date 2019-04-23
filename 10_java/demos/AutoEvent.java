package demos;

import java.util.Hashtable;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AutoEvent extends Application {

	Hashtable<String, Node> h = new Hashtable<String, Node>();
	String[] event = { 
			"keyPressed", "keyReleased", "keyTyped", 
			"mouseClicked", "mouseEntered", 
			"mouseExited", "mouseDragged"};
	SmallPane b1 = new SmallPane(this, Color.BLUE), 
			b2 = new SmallPane(this, Color.RED);
		
	@Override
	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		bp.setCenter(gp);
		for (int i = 0; i < event.length; i++) {
			TextField t = new TextField();
			t.setPrefWidth(300);
			t.setEditable(false);
			gp.add(new Label(event[i]), 0, i);
			gp.add(t, 1, i);
			h.put(event[i], t);
		}
		bp.setRight(b1);
		bp.setLeft(b2);
		Scene scene = new Scene(bp, 600, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
		b1.paint();
		b2.paint();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class SmallPane extends Pane {
	AutoEvent parent;
	Color color;

	SmallPane(AutoEvent parent, Color color) {
		this.parent = parent;
		this.color = color;
		setPrefWidth(100);
		setFocusTraversable(true);
		setOnKeyPressed(event -> {
			TextField t = (TextField) parent.h.get("keyPressed");
			t.setText(event.getEventType() + ", keyCode="+ event.getCode());
			paint();
		} );
		setOnKeyReleased(event -> {
			TextField t = (TextField) parent.h.get("keyReleased");
			t.setText(event.getEventType() + ", keyCode="+ event.getCode());
			paint();
		} );
		setOnKeyTyped(event -> {
			TextField t = (TextField) parent.h.get("keyTyped");
			t.setText(event.getEventType() + ", keyCode="+ event.getCode());
			paint();
		} );
		setOnMouseClicked(event -> {
			TextField t = (TextField) parent.h.get("mouseClicked");
			t.setText(event.getEventType() + ", X="+ event.getX() + ", Y="+ event.getY());
			paint();
		} );
		setOnMouseEntered(event -> {
			TextField t = (TextField) parent.h.get("mouseEntered");
			t.setText(event.getEventType() + ", X="+ event.getX() + ", Y="+ event.getY());
			paint();
		} );
		setOnMouseExited(event -> {
			TextField t = (TextField) parent.h.get("mouseExited");
			t.setText(event.getEventType() + ", X="+ event.getX() + ", Y="+ event.getY());
			paint();
		} );
		setOnMouseDragged(event -> {
			TextField t = (TextField) parent.h.get("mouseDragged");
			t.setText(event.getEventType() + ", X="+ event.getX() + ", Y="+ event.getY());
			paint();
		} );

	}

	public void paint() {
		getChildren().clear();
		Rectangle rec = new Rectangle(0, 0, getWidth(), getHeight());
		rec.setFill(color);
		rec.setStroke(Color.BLACK);
		getChildren().add(rec);
	}
}
