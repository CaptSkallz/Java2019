public class Gulicka extends Thread {
	Ball ap;
	public int x = (int)(Math.random()*100);
	public int y = (int)(Math.random()*100);
	public int dx = (int)(Math.random()*3);
	public int dy = (int)(Math.random()*3);
	
	public Gulicka(Ball ap) {
		this.ap = ap;
	}
	public void run() {
		while (true) {
			int sizeX = ap.sizeX;
			int sizeY = ap.sizeY;
			x += dx;
			y += dy;
			if (x >= sizeX) {
			  	x = sizeX - (x-sizeX); dx = -dx;
			} else if (y >= sizeY) {
			  	y = sizeY - (y-sizeY); dy = -dy;
			} else if (x < 0) {
			  	x = -x; dx = -dx;
			} else if (y < 0) {
			  	y = -y; dy = -dy;
			}
			ap.repaint();
		    try { 			 
			  sleep(14);
			} catch(Exception e) {}
		}
	}
}
