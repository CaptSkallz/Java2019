package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Hypoteka extends Application {
	private TextField tfUrokovaMiera = new TextField();
	private TextField tfPocetRokov = new TextField();
	private TextField tfSuma = new TextField();
	private TextField tfMesacneSplatky = new TextField();
	private TextField tfSpolu = new TextField();
	private Button btVypocet = new Button("Vyhodnoť");

	private static double rocnyUrok;
	private static int pocetRokov;
	private static double suma;

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override 
	public void start(Stage primaryStage) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Úrok [%]:"), 0, 0);
		gridPane.add(tfUrokovaMiera, 1, 0);
		gridPane.add(new Label("Dĺžka [roky]:"), 0, 1);
		gridPane.add(tfPocetRokov, 1, 1);
		gridPane.add(new Label("Suma:"), 0, 2);
		gridPane.add(tfSuma, 1, 2);
		gridPane.add(new Label("Mesačne:"), 0, 3);
		gridPane.add(tfMesacneSplatky, 1, 3);
		gridPane.add(new Label("Spolu:"), 0, 4);
		gridPane.add(tfSpolu, 1, 4);
		gridPane.add(btVypocet, 1, 5);

		gridPane.setAlignment(Pos.CENTER);
		tfUrokovaMiera.setAlignment(Pos.BOTTOM_RIGHT);
		tfPocetRokov.setAlignment(Pos.BOTTOM_RIGHT);
		tfSuma.setAlignment(Pos.BOTTOM_RIGHT);
		tfMesacneSplatky.setAlignment(Pos.BOTTOM_RIGHT);
		tfSpolu.setAlignment(Pos.BOTTOM_RIGHT);
		tfMesacneSplatky.setEditable(false);
		tfSpolu.setEditable(false);
		
		btVypocet.setOnAction(e -> {
			rocnyUrok = Double.parseDouble(tfUrokovaMiera.getText());
			pocetRokov = Integer.parseInt(tfPocetRokov.getText());
			suma = Double.parseDouble(tfSuma.getText());
			tfMesacneSplatky.setText(String.format("%.2f", mesacneSplatky()));
			tfSpolu.setText(String.format("%.2f", getTotalPayment()));			
		});
		Scene scene = new Scene(gridPane, 300, 250);
		primaryStage.setTitle("Hypotéka"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}
	public static double mesacneSplatky() {
		double mesacnyUrok = rocnyUrok/12/100;
		return suma * mesacnyUrok/(1-(1/Math.pow(1+mesacnyUrok, pocetRokov*12)));
	}
	public static double getTotalPayment() {
		return mesacneSplatky() * pocetRokov * 12;
	}
}
