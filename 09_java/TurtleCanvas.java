import java.awt.*;

public class TurtleCanvas extends Canvas {
TurtlePanel sp;
double X, Y, dir;
int steps;

	public TurtleCanvas(TurtlePanel sp) {
		this.sp = sp;
	}
    public void Vlavo(float Uhol) {
	  dir -= Uhol;
	}
	public void Vpravo(float Uhol) {
	  dir += Uhol;
	}
	public void Dopredu(float Dlzka) {
	  double X1 = X + (float)(Dlzka*Math.cos(dir*2*Math.PI/360));
	  double Y1 = Y + (float)(Dlzka*Math.sin(dir*2*Math.PI/360));
	  sp.can.getGraphics().drawLine((int)X,(int)Y,(int)X1,(int)Y1);
	  X = X1; Y = Y1;
	}
    public void update(Graphics g) {
		X = sp.getSize().getHeight()/2;
		Y = sp.getSize().getWidth()/2;
		dir = 0;
    	int init = sp.init;
    	int delta = sp.delta;
    	int uhol = sp.uhol;
     	for(int i=0; i < steps; i++) {
   		  Dopredu(init);
		  init+=delta;
		  Vpravo(uhol);
     	}
     }
    public void paint(Graphics g) {
    	update(g);
    }
}
