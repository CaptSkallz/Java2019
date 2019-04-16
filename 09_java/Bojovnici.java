import java.applet.*;
import java.awt.*;
import java.util.*;

public class Bojovnici extends Applet {
  final int N = 100;
  Bojovnik[] bojovnik = new Bojovnik[N]; // pole vsetkych bojovnikov
  Color[] cols = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};
  
  public void init() {
	resize(300,300);  
	Random rnd = new Random();
	for(int i=0; i<N; i++)	// vytvorenie bojovnikov
  	  bojovnik[i] = new Bojovnik(this, 
  		 rnd.nextInt(300),rnd.nextInt(300),
  		 cols[rnd.nextInt(cols.length)]);
	for(int i=0; i<N; i++) {	// priradenie zabijaka a stitu
	  bojovnik[i].zabijak(bojovnik[(i+1)%N]);
	  bojovnik[i].stit(bojovnik[(i>0)?i-1:N-1]);
	}
	for(int i=0; i<N; i++)	// spustenie
	  bojovnik[i].start();
  }
  public void paint(Graphics g) {
	for(int i=0; i<N; i++) {
 	  //System.out.println(bojovnik[i].x + "," + bojovnik[i].y);
 	  g.setColor(bojovnik[i].col);
	  g.drawOval((int)Math.round(bojovnik[i].x),
			  (int)Math.round(bojovnik[i].y),3,3);
	}
  }
}
