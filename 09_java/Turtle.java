import java.awt.*;
import java.applet.Applet;

public class Turtle extends Applet {
    int sizeX = 500;
    int sizeY = 500;
    Image img;
    float X = sizeX/2;
    float Y = sizeY/2;
    float dir = 0;
    
public void Vlavo(float Uhol) {
    dir -= Uhol;
  }
public void Vpravo(float Uhol) {
    dir += Uhol;
}
public void Dopredu(float Dlzka) {
    float X1 = X + (float)(Dlzka*Math.cos(dir*2*Math.PI/360));
    float Y1 = Y + (float)(Dlzka*Math.sin(dir*2*Math.PI/360));
    img.getGraphics().drawLine((int)X,(int)Y,(int)X1,(int)Y1);
    X = X1; Y = Y1;
}
public void ZmenXY(float nX, float nY) {
  X = nX; Y = nY;
}

public void paint(Graphics g) {
	g.drawImage(img,1,1,Color.YELLOW,this);
}

public void init() {
	resize(sizeX,sizeY);
    img = createImage(sizeX,sizeY);
}

public void start() {
  Dopredu(100);  Vlavo(90);
  Dopredu(100);  Vlavo(90);
  Dopredu(100);  Vlavo(90);
  Dopredu(100);  Vlavo(90);
	  
  ZmenXY(100,100);
  for(int i=0; i<150; i++){
    Dopredu(150);
    Vlavo(179);	  
  }
  ZmenXY(250,250);

  for(int i=0; i<150; i++){
	    Dopredu(150);
	    Vlavo(91);	  
	  }
  
  repaint();
}
}

