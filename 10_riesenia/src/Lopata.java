import java.util.Random;
import java.util.concurrent.Semaphore;

public class Lopata {
	final static int N = 5;  // pocet lopat
	final static int R = 10;	 // pocet robotnikov

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(N,true);
		for (int i = 0; i < R; i++)
			new Robotnik(i, sem).start();
	}
}

class Robotnik extends Thread {
	private int id;
	private Semaphore sem;
	private int odrobene = 0;
	public Robotnik(int id, Semaphore sem){
		this.id = id;
		this.sem = sem;
		
	}
	public void run(){
		Random rnd = new Random();
		long celkovyCas = 0;
		while (odrobene < 10000){
			System.out.println("Spi "+id);
			try { 
				sleep(rnd.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long cakam = System.currentTimeMillis();
			try {
				System.out.println("Cakam na lopatu "+id);
				sem.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			long cas2 = System.currentTimeMillis() - cakam;
			celkovyCas += cas2;
			System.out.println("Ide pracovat "+id+" cakal na lopatu "+cas2);
			
			int cas = rnd.nextInt(1000);
			odrobene += cas;
			try {
				sleep(cas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sem.release();
			
		}
		System.out.println("Celkovy cas cakania "+id+ " "+celkovyCas);
	}
}
