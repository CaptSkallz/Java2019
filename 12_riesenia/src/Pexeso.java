import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pexeso extends Application {
	static final int SIZE = 8;				// parametre hry
	static final int POCETKARTICIEK = 39;	// parametre pikachus
	// ak mame 39 karticiek, tak dvojicami vieme pokryt 2x39 policok, najmacsi svtorec je teda 8x8.
	// alternativa, more pikachus

	Label lbPlayer = new Label();
	Label lbTime = new Label();
	Label lbScore = new Label();
	State state = new State();
	Playground playground;
	BorderPane root;
	Integer first_row = null;
	Integer first_col = null;
	boolean showhide = true;
	
	// vnorena trieda
	public class Playground extends GridPane {
		// toto nas zabilo, poucenie z krizoveho vyboja c.2
		//State state;

		// vnoren trieda
		public class Cart extends Pane {
			int row, col;

			public Cart(int row, int col) {
				this.row = row;
				this.col = col;
				this.setWidth(40);
				this.setHeight(40);
				this.setOnMousePressed(e -> {
					state.get(row, col).revert();
					if (first_row == null) { // prvy tah
						first_row = row;
						first_col = col;
					} else { // druhy tah
						Integer first_row_ = first_row;
						Integer first_col_ = first_col;
						if (state.get(first_row, first_col).id != state.get(row, col).id ) { 
							// omyl, ale treba chvilku pockat...
							Timeline minitikadielko = new Timeline(new KeyFrame(new Duration(1000), ee -> {
								state.get(first_row_, first_col_).revert();
								state.get(row, col).revert();
								playground.paint();
							}));
							minitikadielko.setCycleCount(1);
							minitikadielko.play();
							state.player = !state.player;	// ide dalsi
						} else {	// trafil som, tak idem znova
							if (state.player)
								state.scoreFirstPlayer++;
							else 
								state.scoreSecondPlayer++;
							playground.paint();  // lebo score
						}
						first_row = null;
						first_col = null;
					}
					paint();
				});
			}

			public void paint() {
				//System.out.println("preslim karticku " + row + "x" + col);
				double w = getWidth();
				double h = getHeight();
				getChildren().clear();
				State.CartObject karticka = state.get(row, col);
				if (karticka.visible) {
					/// poucenie z krizoveho vyvoja c.1
					/// brrr !!! never more ImageView obrazok = new
					/// ImageView("file:images/" + karticka.id + ".gif");
					// nechceme citat subor pri kazdom kresleni
					// ImageView obrazok = new ImageView(new Image("images/" + karticka.id + ".gif"));
					// urobime to v konstruktore
					ImageView obrazok = karticka.pikaImage;
					if (obrazok == null)
						obrazok = new ImageView(new Image("images/" + karticka.id + ".gif"));
					obrazok.setFitHeight(h);
					obrazok.setFitWidth(w);
					getChildren().add(obrazok);
				} else {
					Rectangle rectangle = new Rectangle(0, 0, w-1, h-1);
					rectangle.setFill(Color.GRAY);
					rectangle.setStroke(Color.BLACK);
					getChildren().add(rectangle);
				}
			}
		}

		public Playground() {
			// super vec, ale nepomohlo :)
			// setStyle("-fx-background-color: #FFFF00");
			setWidth(400);
			setHeight(400);

			for (int riadok = 0; riadok < Pexeso.SIZE; riadok++) {
				for (int stlpec = 0; stlpec < Pexeso.SIZE; stlpec++) {
					Cart karticka = new Cart(riadok, stlpec);
					this.add(karticka, stlpec, riadok);
					karticka.paint();
				}
			}
		}

		public void paint() {
			lbPlayer.setText("Hráč: " + (state.player?"Prvý":"Druhý"));						
			lbScore.setText("Score: " + state.scoreFirstPlayer + ":" + state.scoreSecondPlayer);
			for (Node node : getChildren()) {
				if (node instanceof Cart) {
					((Cart) node).paint();
				}
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		Timeline tikadielko = new Timeline(new KeyFrame(new Duration(1000), e -> {
			state.elapsedTime++;
			lbTime.setText("Čas: " + state.elapsedTime);
		}));
		tikadielko.setCycleCount(Timeline.INDEFINITE);
		tikadielko.play();
		
		// Buttons
		Button load = new Button("Load");
		Button save = new Button("Save");
		Button quit = new Button("Quit");
		Button show = new Button("Show");

		load.setOnAction(e -> {
			//System.out.println("load");
			state = State.load("savedGame");
			playground.paint();
		});

		save.setOnAction(e -> {
			//System.out.println("save");
			state.save("savedGame");
		});

		quit.setOnAction(e -> {
			//System.out.println("quit");
			// System.exit(0);
			Platform.exit();
		});
		show.setOnAction(e -> {
			//System.out.println("show");
			showhide = !showhide;
			show.setText(showhide?"Show":"Hide");
			state.showhide();
			playground.paint();
		});

		// top panel
		HBox topPanel = new HBox(lbPlayer, lbTime, lbScore);
		topPanel.setAlignment(Pos.CENTER);
		topPanel.setSpacing(40);
		root.setTop(topPanel);
		// bottom panel
		HBox bottomPanel = new HBox(load, save, show, quit);
		bottomPanel.setAlignment(Pos.CENTER);
		bottomPanel.setSpacing(20);
		root.setBottom(bottomPanel);
		// playground
		root.setCenter(playground = new Playground());
		
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
	int elapsedTime = 0;
	boolean player = true;
	int scoreFirstPlayer = 0;
	int scoreSecondPlayer = 0;
	CartObject[][] plocha = new CartObject[Pexeso.SIZE][Pexeso.SIZE];
	Random rand = new Random();
	
	// vnorena trieda
	public class CartObject implements Serializable {
		private static final long serialVersionUID = 911775039L;
		int id;
		boolean visible = false;
		transient /* znamena nechceme serializovat */ 
		     ImageView pikaImage;	// toto na cviku navrhoval Peter, len sme sa nerozumeli :)

		public CartObject(int id) {
			// new Random ma byt asi inde...
			// drobna chyba...
			// Random rand = new Random();
			// this.id = rand.nextInt(Pexeso.POCETKARTICIEK) + 1;
			this.id = id;
			pikaImage = new ImageView(new Image("images/" + id + ".gif"));
		}
		public void revert() {
			visible = !visible;
		}
	}
	public State() {
		// ako generovat dvojice, rovnaka technika ako pri miesani kariet...
		int[]all = new int[Pexeso.SIZE*Pexeso.SIZE];
		for(int i = 0; i < Pexeso.SIZE*Pexeso.SIZE; i++) 
			all[i] = 1+(i/2);	// dvakrat jeden pikachu
		for(int k = 0; k < 1000; k++) { // miesame
			int i = rand.nextInt(Pexeso.SIZE*Pexeso.SIZE);
			int j = rand.nextInt(Pexeso.SIZE*Pexeso.SIZE);
			int tmp = all[i];
			all[i] = all[j];
			all[j] = tmp;
		}
		int index = 0;
		for (int i = 0; i < Pexeso.SIZE; i++) {
			for (int j = 0; j < Pexeso.SIZE; j++) {
				plocha[i][j] = new CartObject(all[index++]);
			}
		}
	}
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
		for (int i = 0; i < Pexeso.SIZE; i++) {
			for (int j = 0; j < Pexeso.SIZE; j++) {
				plocha[i][j].revert();
			}
		}
	}
}
