
public class SleeperJoiner {
	public static void main(String[] args) throws InterruptedException {
	    Sleeper prvy = new Sleeper("Prvy", 1500);
	    Sleeper druhy = new Sleeper("Druhy", 1500);
	    Joiner treti = new Joiner("Treti", druhy);
	    Joiner stvrty = new Joiner("Stvrty", prvy);
	    prvy.interrupt();
	}
}
