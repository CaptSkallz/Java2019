import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    Timer timer;
    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(
		new RemindTask(), // spustí pri zvonení
		seconds*1000);
	}
    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Vstaaaaavaj !!");
            timer.cancel(); //Terminate the thread
        }
    }
    public static void main(String args[]) {
        new Reminder(5);
        System.out.println("Budik mas nastaveny");
    }
}
