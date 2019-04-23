package demos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class TMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TenisCanvas tenis=new TenisCanvas();
			Pane pane = new Pane(tenis);
			Scene scene = new Scene(pane);
			scene.setOnKeyPressed(event ->  {
				if(event.getCode()==KeyCode.A) tenis.Lhore=true; 
	    		if(event.getCode()==KeyCode.Z) tenis.Ldole=true;
	    		if(event.getCode()==KeyCode.P) tenis.Phore=true;
	    		if(event.getCode()==KeyCode.L) tenis.Pdole=true;
			});
			scene.setOnKeyReleased(event -> {
				if(event.getCode()==KeyCode.A) tenis.Lhore=false; 
	    		if(event.getCode()==KeyCode.Z) tenis.Ldole=false;
	    		if(event.getCode()==KeyCode.P) tenis.Phore=false;
	    		if(event.getCode()==KeyCode.L) tenis.Pdole=false;
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
