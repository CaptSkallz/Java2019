import java.awt.*;
import java.applet.Applet;

public class Korytnacka extends Thread {
	Korytnacky ap;
	double X, Y, dir;
	int init, delta, uhol;

	public Korytnacka(Korytnacky ap, int initX, int initY, 
			int init, int delta, int uhol) {
		this.ap = ap;
		this.init = init; this.delta = delta; this.uhol = uhol;
		X = initX; Y = initY;
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
  	  ap.bufImage.getGraphics().drawLine((int)X,(int)Y,(int)X1,(int)Y1);
  	  X = X1; Y = Y1;
  	}
  	
	public void run() {
		dir = 0;
		for(int i=0;; i++) {
		  Dopredu(init);
		  init+=delta;
		  Vpravo(uhol);
		  try { sleep(3); } catch(Exception e) {}
	      if (i%10== 0 ) ap.repaint();
	     }
	}
}
