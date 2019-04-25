import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Labyrinth extends Application {
	public static void main(String[] args) {
		launch(args);
	}

    private Group group = new Group();
    private Camera camera = new PerspectiveCamera(true);
    private Scene scene = new Scene(group, 1280, 720, true);
    private PhongMaterial boxMaterial = new PhongMaterial(Color.LIGHTGREEN);
    private boolean mousePressed = false;
    private double lastMouseX;
    private float mouseSensitivity = 0.1f;

    private char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '.', '#', '.', '#'},
            {'#', '.', '#', '#', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '.', '#', '.', '#'},
            {'#', '#', '#', '#', '#'}
    };

    @Override
    public void start(Stage primaryStage) {
        scene.setFill(Color.LIGHTBLUE);
        scene.setCamera(camera);
        camera.setTranslateX(30);
        camera.setTranslateZ(30);
        camera.setFarClip(500);

        createFloor();
        loadMap();
        setEvents();

        primaryStage.setTitle("Labyrinth");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setEvents() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:
                    camera.translateYProperty().set(camera.getTranslateY() - 2);
                    break;
                case F:
                    camera.translateYProperty().set(camera.getTranslateY() + 2);
                    break;
                default:
                    cameraMovement(event);
                    break;
            }
        });

        scene.setOnMousePressed(event -> {
            mousePressed = true;
            lastMouseX = event.getSceneX();
        });

        scene.setOnMouseReleased(event -> mousePressed = false);

        scene.setOnMouseDragged(event -> {
            if (mousePressed) {
                camera.setRotationAxis(Rotate.Y_AXIS);
                camera.setRotate(camera.getRotate() + (event.getSceneX() - lastMouseX) * mouseSensitivity);
            }
            lastMouseX = event.getSceneX();
        });
    }

    private void loadMap() {
        int Z = 0;
        for (int i = map.length - 1; i >= 0; i--) {
            int X = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') {
                    createBox(X, Z);
                }
                X += 31;
            }
            Z += 31;
        }
    }

    private void createBox(int X, int Z) {
        Box box = new Box(30, 30,30);
        box.setTranslateX(X);
        box.setTranslateZ(Z);
        box.setMaterial(boxMaterial);
        group.getChildren().add(box);
    }

    private void createFloor() {
        Box box = new Box(1000,1,1000);
        box.setMaterial(new PhongMaterial(Color.WHEAT));
        box.setTranslateY(15);
        group.getChildren().add(box);
    }

    private void cameraMovement(KeyEvent keyEvent) {
        double dx = Math.sin(Math.toRadians(camera.getRotate())) * 5;
        double dz = Math.cos(Math.toRadians(camera.getRotate())) * 5;

        double oldZ = camera.getTranslateZ();
        double oldX = camera.getTranslateX();
        switch (keyEvent.getCode()) {
            case W:
                camera.translateZProperty().set(camera.getTranslateZ() + dz);
                camera.translateXProperty().set(camera.getTranslateX() + dx);
                break;
            case S:
                camera.translateZProperty().set(camera.getTranslateZ() - dz);
                camera.translateXProperty().set(camera.getTranslateX() - dx);
                break;
        }

        if (checkCollisions()) {
            camera.translateZProperty().set(oldZ);
            camera.translateXProperty().set(oldX);
        }
    }

    private boolean checkCollisions() {
        for (Node n: group.getChildren()) {
            if (n instanceof Box) {
                Box b = (Box) n;
                if (b.getBoundsInParent().intersects(camera.getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }

}