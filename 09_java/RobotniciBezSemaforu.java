

import java.util.ArrayList;
import java.util.Random;

public class RobotniciBezSemaforu {
	final static int N = 3;  	 // pocet lopat
	final static int R = 10;	 // pocet robotnikov
	final static char[] lopatyL = { '(','[', '{', '<', '\\' };
	final static char[] lopatyR = { ')',']', '}', '>', '/'};
	static ArrayList<Integer> lopaty = new ArrayList<Integer>();
	
public synchronized Integer zoberLopatu() throws InterruptedException {
	while (lopaty.size() == 0)
		wait();
	return lopaty.remove(0);
}

public synchronized void polozLopatu(Integer lop) throws InterruptedException {
	lopaty.add(lop);
	notify();
}

	public static void main(String[] args) {
		System.out.println(verify ("([{][][}{][)(][}{][][}{][)(][}{][)(][}{][)(}{)(}{][)}]"));
		System.out.println(verify ("([{][][][)(}{)(][][][}{)()(}{)(][][)(}{}{][}{)(][}])"));
		System.out.println(verify ("([{][)(][}{)(][}{][}{)(}{)(][}{}{)(][}{)(]})"));
		
		
		RobotniciBezSemaforu cv = new RobotniciBezSemaforu();
		lopaty = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) 
			lopaty.add(i);
		for (int i = 0; i < R; i++) 
			new Sedmospac1(i, cv).start();
	}
	
	public static boolean verify(String s) {
		int counters[] = new int[N];
		for (int j = 0; j<s.length(); j++) { 
			switch (s.charAt(j)) { 
				case '(':
					counters[0]++; break;
				case '[':
					counters[1]++; break;
				case '{':
					counters[2]++; break;
				case '<':
					counters[3]++; break;
				case '\\':
					counters[4]++; break;
				case ')':
					counters[0]--; break;
				case ']':
					counters[1]--; break;
				case '}':
					counters[2]--; break;
				case '>':
					counters[3]--; break;
				case '/':
					counters[4]--; break;
			}
			for (int k = 0; k<N; k++) {
				if (counters[k] < 0 || counters[k] > 1)
					return false;
			}
		}
		for (int k = 0; k<N; k++) {
			if (counters[k] != 0)
				return false;
		}
		return true;
	}
}

class Sedmospac1 extends Thread {
	int id;
	RobotniciBezSemaforu cv;
	
	public Sedmospac1(int id, RobotniciBezSemaforu cv) {
		this.cv = cv;
		this.id = id;
	}

	Random rnd = new Random();

	public void run() {
		long suma = 0;
		long cakal = 0;
		long end = System.currentTimeMillis();
		while (suma < 1000) {
			try {
				Integer lopata = cv.zoberLopatu();
				long start = System.currentTimeMillis();
				cakal += start-end;
		//System.out.println("Robotnik:" + id + " pracujem, zbytocne som cakal :" + (start - end) + ", celkovo: " + cakal + " zobral lopatu " + lopata );
				System.out.print(RobotniciBezSemaforu.lopatyL[lopata]);
				int p = rnd.nextInt(1000);
				suma += p;
				sleep(p);
				cv.polozLopatu(lopata);
				System.out.print(RobotniciBezSemaforu.lopatyR[lopata]);
		//System.out.println("Robotnik:" + id + " nepracujem-spim, polozil lopatu " + lopata);
				sleep(rnd.nextInt(1000));
		//System.out.println("Robotnik:" + id + " nespim");
				end = System.currentTimeMillis();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.out.println("Robotnik:" + id + " koncim, zbytocne som cakal celkovo: " + cakal);

	}
}