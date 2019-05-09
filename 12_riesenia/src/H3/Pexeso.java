package H3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Pexeso extends Application {
	static final int SIZE = 8;				// parametre hry
	// ak mame 39 karticiek, tak dvojicami vieme pokryt 2x39 policok, najvacsi svtorec je teda 8x8.

	State state = new State();
	Playground playground;
	BorderPane root;
	
	// vnorena trieda
	public class Playground extends GridPane {
		
		// zvolili sme riesenie cez Grid Pane
		public class Cart extends Pane { // vnorena trieda
			int row, col;
			public Cart(int row, int col) {
				this.row = row;
				this.col = col;
				this.setWidth(40);
				this.setHeight(40);
				this.setOnMousePressed(e -> {
					// doprogramuj logiku
				});
			}

			public void paint() {
				//System.out.println("preslim karticku " + row + "x" + col);
				double w = getWidth();
				double h = getHeight();
				getChildren().clear();
				// doprogramuj kreslenie karticky alebo sedeho stvorceka
			}
		}

		public Playground() {
			setWidth(400);
			setHeight(400);
			// vygeneruj SIZE x SIZE Cart-ticiek a pridaj do Grid Pane
		}

		public void paint() {
			// prekresli stavove informacie, hrac na tahu, score, ...
			
			// prekresli vsetky children v Pane...
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();	
		// Buttons
		Button load = new Button("Load");
		Button save = new Button("Save");
		Button quit = new Button("Quit");
		Button show = new Button("Show");

		load.setOnAction(e -> {
			System.out.println("load");
			// dorob logiku
		});

		save.setOnAction(e -> {
			System.out.println("save");
			// dorob logiku
		});

		quit.setOnAction(e -> {
			System.out.println("quit");
			Platform.exit();
		});
		show.setOnAction(e -> {
			System.out.println("show");
			// dorob logiku
		});

		root.setOnKeyPressed(event -> {
			System.out.println("key pressed");
		});

		// top panel
		HBox topPanel = new HBox();
		// vyrob top panel, pridaj tam labels a pridaj ho do root

		// bottom panel
		HBox bottomPanel = new HBox(load, save, show, quit);
		// vyrob bottom panel, pridaj tam buttons, a pridaj ho do root
		// playground
		root.setCenter(playground = new Playground());
		//-------------------
		primaryStage.setTitle("Pexeso");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		playground.paint();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
// -------------------POZOR, TOTO NEMOZE BYT VNORENA TRIEDA, lebo ...
// a nemoze byt tiez public
class State implements Serializable {
	private static final long serialVersionUID = 918972645L;
	CartObject[][] plocha = new CartObject[Pexeso.SIZE][Pexeso.SIZE];
	
	// vnorena trieda
	public class CartObject implements Serializable {
		private static final long serialVersionUID = 911775039L;
		int id;					// ID karticky, s obrazkom zo suboru images/"ID".gif
		boolean visible = false;		// otocena/ neotocena
		transient /* znamena nechceme serializovat */ ImageView pikaImage;	

		public CartObject(int id) {
			this.id = id;
			// nacitaj obrazok do pikaImage
		}
		public void revert() {
			// otoc karticku
		}
	}
	public State() {
		// ako generovat dvojice, rovnaka technika ako pri miesani kariet...
		Random rand = new Random();
		// generuj dvojice rovnakych cisel do pola
		int[]all = new int[Pexeso.SIZE*Pexeso.SIZE];
		for(int i = 0; i < Pexeso.SIZE*Pexeso.SIZE; i++) 
			all[i] = 1+(i/2);	// dvakrat jeden index, dvakrat jeden pikatchu
		// miesaj 1000x
		for(int k = 0; k < 1000; k++) { // miesame
			int i = rand.nextInt(Pexeso.SIZE*Pexeso.SIZE);
			int j = rand.nextInt(Pexeso.SIZE*Pexeso.SIZE);
			int tmp = all[i];
			all[i] = all[j];
			all[j] = tmp;
		}
		// vygeneruj SIZE x SIZE CartObjectov do pola plocha[i][j] podla indexov all[i][j]
	}
	// dobry getter nie je nikdy na zahodenie
	public CartObject get(int i, int j) {
		if (0 <= i && i < plocha.length && 0 <= j && j < plocha[i].length)
			return plocha[i][j];
		else
			return null;
	}
	// uloz konfig do suboru
	public void save(String filePath) {
		File f = new File(filePath);
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(f));
			os.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// precitaj konfig do suboru
	public static State load(String filePath) {
		File f = new File(filePath);
		ObjectInputStream os = null;
		State state = null;
		try {
			os = new ObjectInputStream(new FileInputStream(f));
			state = (State) os.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (state == null)
			System.out.println("stalo sa nieco zle !!!");
		return state;
	}
	public void showhide() {
		// otoc vsetky karticky
	}
}
