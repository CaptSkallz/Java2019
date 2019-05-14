package Delo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.util.Duration;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
 
import static javafx.scene.shape.StrokeLineJoin.ROUND;
 
public class Canon extends Application {
    static int w = 600;
    static int h = 600;
    static Delo delo = new Delo();
    static List<Gulicka> gule= new ArrayList();
    double rotation, x, y;
 
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        CanonPane pane = new CanonPane();
        pane.DrawUniverse();
        pane.DrawCanon(Math.PI/2,0,0);
 
        Timeline anim = new Timeline(new KeyFrame(Duration.millis(50), e->{
            List<Gulicka> rem = new ArrayList<>();
            //pane.DrawCanon(rotation,x,y);
            for (Gulicka g:gule) {
                g.update();
                if(g.explodingIn==0){
                    rem.add(g);
                }
            }
            List<Gulicka> remove = pane.DrawGulicky();
            pane.DrawCanon(rotation,x,y);
            //pane.DrawCanon();
            gule.removeAll(rem);
 
        }));
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.play();
        pane.setOnMouseMoved(e-> {rotation = -Math.toDegrees(Math.atan2(600,0)- Math.atan2(e.getY() - 600, e.getX() - 300));
                                    x = e.getX();
                                    y = e.getY();});
        pane.setOnMouseClicked(e -> gule.add(new Gulicka((e.getX()-300)/8, (e.getY()-600)/8)));
 
        Scene scene = new Scene(pane,w,h);
        stage.setTitle("Canon");
        stage.setScene(scene);
        stage.show();
 
    }
}
 
class CanonPane extends Pane{
 
    List<Circle> gulicky = new ArrayList<>();
    Line line;
    Rectangle canon;
    public void DrawCanon(double angle, double x, double y){
        getChildren().removeAll(canon,line);
        Rectangle r = new Rectangle(Canon.delo.x,Canon.delo.y,Canon.delo.width,Canon.delo.height);
        r.setFill(Canon.delo.rgb);
        r.setArcHeight(40);
        r.setArcWidth(40);
        r.setRotate(angle);
        Line l = new Line(Canon.w/2, Canon.h, x, y);
        canon = r;
        line = l;
        getChildren().addAll(l,r);
    }
 
    public void DrawUniverse(){
        for(int i = 0; i< 8; i++){
            Circle c = new Circle(0 + 80*i, 0,90);
            c.setFill(Color.DARKBLUE);
            getChildren().add(c);
        }
        for(int i = 0; i < 150; i++){
            Circle c = new Circle(new Random().nextInt(600),new Random().nextInt(80),new Random().nextInt(3)+1);
            c.setFill(Color.WHITE);
            getChildren().add(c);
        }
        Circle c = new Circle(20,20, 20);
        c.setFill(Color.MAGENTA);
        Line l = new Line(0, 30, 43, 15);
        l.setStroke(Color.PINK);
        l.setStrokeWidth(5);
        l.setStrokeLineCap(StrokeLineCap.ROUND);
        getChildren().addAll(c,l);
 
    }
 
    public List<Gulicka> DrawGulicky(){
        getChildren().removeAll(gulicky);
        List<Gulicka> remove = new ArrayList<>();
        for (Gulicka g:Canon.gule) {
            Circle c = new Circle(g.x,g.y,g.radius);
            if (g.explodingIn == 0){
                remove.add(g);
                c.setRadius(40);
                c.setFill(Color.RED);
                getChildren().add(c);
                gulicky.add(c);
                //List<Color> colors = new ArrayList<>(List.of(Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.PURPLE,Color.PINK));
                List<Color> colors = 
                		Arrays.asList(Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.PURPLE,Color.PINK);
                for (int i = 0; i < 6; i++){
                    Circle x = new Circle(g.x,g.y,40-5*(i+1));
                    x.setFill(colors.get(i));
                    getChildren().add(x);
                    gulicky.add(x);
                }
 
            }
            else {
                c.setFill(g.rgb);
                getChildren().add(c);
                gulicky.add(c);
            }
        }
        return remove;
    }
}
 
class Delo{
    int width = 50;
    int height = 120;
    int x,y;
    Color rgb = Color.ROYALBLUE;
    public Delo(){
        x = Canon.w/2 - width/2;
        y = Canon.h-height+50;
    }
}
 
class Gulicka{
    double speedx,speedy;
    double radius = 20;
    double x = Canon.w/2 -radius/2;
    double y = Canon.h;
    int explodingIn;
    boolean antigravity = false;
    Color rgb = Color.BLACK;
 
    public Gulicka(double spx, double spy){
        explodingIn = new Random().nextInt(60)+40;
        speedx = spx;
        speedy = spy;
    }
 
    public void update(){
        if(antigravity){ //správanie sa vo vesmíre ... "náhodne lietajú"
            y -= new Random().nextInt(4)-1;
            x -= new Random().nextInt(4)-1;
            explodingIn--;
            return;
        }
        if(y<80){
            antigravity = true; // ak sa dostane príliš vysoko dostane sa do "vesmíru"
        }
        x += speedx;
        y += speedy;
        speedx *= 0.9;
        speedy *= 0.9;
        if (this.x < radius || this.x > 600 - radius) this.speedx = -this.speedx;
        if (this.y > 600-radius) this.speedy = -this.speedy;
        if(y <= 600) this.speedy += 1;
        explodingIn--;
    }
}