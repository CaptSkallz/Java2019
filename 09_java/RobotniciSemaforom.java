

import java.util.Random;
import java.util.concurrent.Semaphore;

public class RobotniciSemaforom {
	final static int N = 3;  	 // pocet lopat
	final static int R = 10;	 // pocet robotnikov

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(N,true);
		//Semaphore sem = new Semaphore(1, true);
		for (int i = 0; i < R; i++) 
			new Sedmospac(i, sem).start();
	}
}

class Sedmospac extends Thread {
	Semaphore sem;
	int id;

	public Sedmospac(int id, Semaphore sem) {
		this.sem = sem;
		this.id = id;
	}

	Random rnd = new Random();

	public void run() {
		long suma = 0;
		long cakal = 0;
		long end = System.currentTimeMillis();
		while (suma < 10000) {
			try {
				sem.acquire();
				long start = System.currentTimeMillis();
				cakal += start-end;
				System.out.println("" + id + "pracujem, zbytocne som cakal :" + (start - end) + ", celkovo: " + cakal);
				int p = rnd.nextInt(1000);
				suma += p;
				sleep(p);
				sem.release();
				//System.out.println("" + id + "nepracujem-spim");
				sleep(rnd.nextInt(1000));
				//System.out.println("" + id + "nespim");
				end = System.currentTimeMillis();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("" + id + "koncim, zbytocne som cakal celkovo: " + cakal);

	}
	
}