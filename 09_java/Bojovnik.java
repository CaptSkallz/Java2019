import java.awt.*;

public class Bojovnik extends Thread {
	public double  x,y; //public int x,y;
	public Color col;
	Bojovnik killer, defender;
	Bojovnici ap;
	
	public Bojovnik(Bojovnici ap, int x, int y, Color col) {
		this.x = x; this.y = y; this.col = col;
		this.ap = ap;
	}
	public void zabijak(Bojovnik killer) {
		this.killer = killer;
	}
	public void stit(Bojovnik defender) {
		this.defender = defender;
	}
	public void run() {
	  while (true) {
		  // suradnice bodu, kam sa treba postavit, aby
		  // defender bol v strede medzi mnou a killerom
		  double xx = 2*defender.x - killer.x;
		  double yy = 2*defender.y - killer.y;
		  // parameter renivosti (0-1)
		  double laziness = 0.1;
		  x = (xx-x)*laziness+x;
		  y = (yy-y)*laziness+y;
		  ap.repaint();
		  try { sleep(100); } catch(Exception e) {}
	  }
	}
}
