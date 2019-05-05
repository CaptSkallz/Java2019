/**
 * JavaFX Template for CheckerBoard-based games.
 * Board
 * @author Lukáš Gajdošech
 */
package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

@SuppressWarnings("Duplicates")

public class BasicBoard extends GridPane {
    BasicGame game;

    public BasicBoard(BasicGame game) {
        this.game = game;
        this.setStyle("-fx-background-color: Sienna; -fx-border-width: 4; -fx-border-color: Sienna;");
        for (int riadok = 0; riadok < game.SIZE; riadok++) {
            for (int stlpec = 0; stlpec < game.SIZE; stlpec++)
                this.add(new Tile(riadok, stlpec), stlpec, riadok);
        }
    }

    public void paint() {
        for (Node node : getChildren()) {
            if (node instanceof Tile)
                ((Tile) node).paint();
        }
    }

    class Tile extends Pane {
        int row, col;
        Rectangle r;
        Ellipse e;
        Arc a;

        public Tile(int row, int col) {
            this.row = row;
            this.col = col;
            this.setOnMousePressed(e -> {  //Hracov tah
                if (game.gs.plocha[row][col] == 0 && game.gs.inProgress) {
                    game.gs.plocha[row][col] = (game.gs.player) ? 1 : 2;
                    game.turn();
                }
            });
        }

        private void generateObjects() {
            this.setWidth(game.tileWidth);
            this.setHeight(game.tileHeight);
            r = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
            r.setStroke(Color.SADDLEBROWN);
            r.setFill(Color.rgb(255, 197, 91));
            e = new Ellipse(getWidth()/2, getHeight()/2, getWidth()/3,getHeight()/3 );
            a = new Arc(getWidth()/2, getHeight()/2, getWidth()/5, getHeight()/5, 70, 120);
            a.setType(ArcType.OPEN);
            a.setFill(null);
            a.setStrokeWidth(3);
        }

        public void paint() {
            generateObjects();
            getChildren().clear();
            getChildren().add(r);

            if (game.gs.plocha[row][col] == 1) {
                e.setFill(Color.BLACK);
                a.setStroke(Color.WHITE);
                getChildren().addAll(e, a);
            } else if (game.gs.plocha[row][col] == 2) {
                e.setFill(Color.WHITE);
                a.setStroke(Color.BLACK);
                getChildren().addAll(e, a);
            }
        }

    }
}
