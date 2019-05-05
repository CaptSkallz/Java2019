/**
 * JavaFX Template for CheckerBoard-based games.
 * Main Application
 * @author Lukáš Gajdošech
 */

package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("Duplicates")

public class BasicGame extends Application {
    final int SIZE = 12;                                            //velkost sachovnice
    public double tileWidth = 50;                                   //default sirka policka (meni sa podla velkosti okna)
    public double tileHeight = 50;                                  //default vyska policka (meni sa podla velkosti okna)
    public GameState gs = new GameState(SIZE);                      //instancia herneho stavu
    private Label lbPlayer = new Label();
    private Label lbTime = new Label();
    private Button load = new Button("Load");
    private Button save = new Button("Save");
    private Button reset = new Button("Reset");
    private Button quit = new Button("Quit");
    private BorderPane root = new BorderPane();
    private BasicBoard board = new BasicBoard(this);          //herna plocha
    private Rectangle winTable = new Rectangle();                   //vitazne okienko
    private Text winText = new Text();                              //vitazny text

    @Override
    public void start(Stage primaryStage) {
        update();
        //styl vyherneho okienka
        winTable.xProperty().bind(root.widthProperty().divide(2).subtract(80));
        winTable.yProperty().bind(root.heightProperty().divide(2).subtract(40));
        winTable.setWidth(160);
        winTable.setHeight(80);
        winTable.setFill(Color.WHITE);
        winTable.setStroke(Color.BLACK);
        winText.xProperty().bind(root.widthProperty().divide(2).subtract(60));
        winText.yProperty().bind(root.heightProperty().divide(2).add(5));
        winText.setFont(new Font(20));
        //tykadielko casu
        Timeline cas = new Timeline(new KeyFrame(new Duration(1000), e -> {
            if (gs.inProgress) {
                gs.remainingTime--;
                if (gs.remainingTime == 0) {
                    gs.inProgress = false;
                    turn();
                } else
                    update();
            }
        }));
        cas.setCycleCount(Timeline.INDEFINITE);
        cas.play();
        //load
        load.setOnAction(e -> {
            gs = GameState.load("SavedGame");
            update();
            root.getChildren().removeAll(winTable, winText);
        });
        //save
        save.setOnAction(e -> gs.save("SavedGame"));
        //reset
        reset.setOnAction(event -> {
            gs = new GameState(SIZE);
            update();
            root.getChildren().removeAll(winTable, winText);
        });
        //quit
        quit.setOnAction(e -> {
            Platform.exit();
            gs.inProgress = false;
        });
        //vrchny info panel
        lbPlayer.setMinWidth(150);
        lbTime.setMinWidth(150);
        HBox topPanel = new HBox(lbPlayer, lbTime);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setSpacing(20);
        root.setTop(topPanel);
        //spodny button panel
        HBox bottomPanel = new HBox(load, save, reset, quit);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setSpacing(40);
        root.setBottom(bottomPanel);
        //center herny panel
        root.setCenter(board);
        //skalovatelnost
        board.widthProperty().addListener(ov -> {
            tileWidth = board.getWidth() / SIZE;
            board.paint();
        });
        board.heightProperty().addListener(ov -> {
            tileHeight = board.getHeight() / SIZE;
            board.paint();
        });
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void update() {
        board.paint();
        if (gs.remainingTime == 0)
            lbTime.setText("TIMEOUT");
        else
            lbTime.setText("Remaining Time: " + gs.remainingTime);
        if (gs.player)
            if (gs.inProgress)
                lbPlayer.setText("On Turn: Player 1");
            else {
                lbPlayer.setText("WINNER: Player 1");
                winText.setText("Player 1 wins!");
                root.getChildren().addAll(winTable, winText);
            }
        else
        if (gs.inProgress)
            lbPlayer.setText("On Turn: Player 2");
        else {
            lbPlayer.setText("WINNER: Player 2");
            winText.setText("Player 2 wins!");
            root.getChildren().addAll(winTable, winText);
        }
    }

    public void turn() { //novy tah, cize vyresetuj zostavajuci cas, prekresli plochu, zmen hraca na tahu
        if (gs.inProgress)
            gs.remainingTime = 10;
        gs.player = !gs.player;
        update();
    }
}
