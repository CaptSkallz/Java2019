package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragDropDemo extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Drag And Drop");

		final DragPane source = new DragPane(Color.RED);
		source.setContent("source\n");
		final DragPane target = new DragPane(Color.BLUE);
		target.setContent("dest\n");

		source.setOnDragDetected(mouseEvent -> {
			System.out.println("onDragDetected");
			Dragboard db = source.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(source.getContent());
			db.setContent(content);
			mouseEvent.consume();
		} );

		target.setOnDragOver(mouseEvent -> {
				System.out.println("onDragOver");
				if (mouseEvent.getGestureSource() != target && mouseEvent.getDragboard().hasString()) {
					mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				mouseEvent.consume();
		});

		target.setOnDragEntered(dragEvent -> {
			System.out.println("onDragEntered");
			if (dragEvent.getGestureSource() != target && dragEvent.getDragboard().hasString()) {
				target.setColor(Color.GREEN);
			}
			dragEvent.consume();
		} );

		target.setOnDragExited(dragEvent -> {
			target.setColor(Color.GREEN);
			dragEvent.consume();
		} );

		target.setOnDragDropped(dragEvent -> {
			Dragboard db = dragEvent.getDragboard();
			boolean success = false;
			if (db.hasString()) {
				target.setContent(db.getString());
				success = true;
			}
			dragEvent.setDropCompleted(success);
			dragEvent.consume();
		} );

		source.setOnDragDone(dragEvent -> {
			System.out.println("onDragDone");
			if (dragEvent.getTransferMode() == TransferMode.MOVE) {
				source.setColor(Color.BLACK);
			}
			dragEvent.consume();
		} );

		HBox root = new HBox();
		root.getChildren().addAll(source, target);
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class DragPane extends Pane {
	Color c;
	String s = "";

	DragPane(Color c) {
		this.c = c;
		setWidth(200);
		setHeight(200);
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public String getContent() {
		return s;
	}

	public void setContent(String s) {
		this.s += s;
		paint();
	}

	public void paint() {
		getChildren().clear();
		Rectangle rec = new Rectangle(0, 0, getWidth(), getHeight());
		rec.setFill(c);
		rec.setStroke(Color.BLACK);
		Text t = new Text(50, 50, s);
		t.setFont(Font.font(24));
		getChildren().addAll(rec, t);
	}
}
