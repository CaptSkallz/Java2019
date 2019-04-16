import java.applet.*;
import java.awt.*;
import javax.swing.JPanel;

public class Timer extends Thread {
	int waitTime;
	JPanel panel;
	public Timer(int wt, JPanel jp) {
		waitTime = wt; panel = jp;
	}
	public void run() {
	  while (true) {
		try { sleep(waitTime); } catch (Exception E) {}
		panel.repaint();
	  }
	}
}
