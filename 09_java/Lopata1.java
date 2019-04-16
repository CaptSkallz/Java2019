import java.util.Random;


public class Lopata1 {
	final static int N = 5;  // pocet lopat
	final static int R = 10;	 // pocet robotnikov
	private int pocetLopatNaZemi;
	
	public Lopata1(int pocetLopatNaZemi){
		this.pocetLopatNaZemi = pocetLopatNaZemi;
	}
	public static void main(String[] args) {
		Lopata1 lop = new Lopata1(N);
		for (int i = 0; i < R; i++){
			new Robotnik1(i, lop).start();
		}

	}
	public synchronized void zoberLopatu(){
		if (pocetLopatNaZemi==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pocetLopatNaZemi--;
	}
	public synchronized void polozLopatu(){
		pocetLopatNaZemi++;
		notify();
	}
}

class Robotnik1 extends Thread {
	private int id;
	private Lopata1 lopata;
	private int odrobene = 0;
	public Robotnik1(int id,Lopata1 lop){
		this.id = id;
		lopata = lop;
	}
	
	public void run(){
		Random rnd = new Random();
		long celkovyCas = 0;
		while (odrobene < 10000){
			System.out.println("Spi "+id);
			int cas = rnd.nextInt(1000);
			try {
				sleep(cas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long cakam = System.currentTimeMillis();
			System.out.println("Cakam na lopatu "+id);
			lopata.zoberLopatu();
			long cas2 = System.currentTimeMillis() - cakam;
			celkovyCas += cas2;
			System.out.println("Ide pracovat "+id+" cakal na lopatu "+cas2);	
			cas = rnd.nextInt(1000);
			odrobene += cas;
			try {
				sleep(cas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lopata.polozLopatu();
		}
		System.out.println("Celkovy cas cakania "+id+ " "+celkovyCas);
	}
}
