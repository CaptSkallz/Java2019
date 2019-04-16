

import java.util.LinkedList;
import java.util.Random;

public class Jama {

	LinkedList<Lopata> lopaty;

	public static void main(String[] args) {
		Jama j = new Jama(10, 4);
	}

	public Jama(int m, int n) {
		// vyrobime lopaty
		lopaty = new LinkedList<Lopata>();
		for (int i = 0; i < n; i++) {
			lopaty.add(new Lopata());
		}
		
		// vyrobime robotnikov
		for (int i = 0; i < m; i++) {
			new Thread(Integer.toString(i)) {
				private Lopata moja;
				public void run() {
					Random r = new Random();
					while (true) {
						// zistime stav lopat a ak sa da, prvu volnu zoberieme
						synchronized (lopaty) {
							if (lopaty.size() > 0) {
								moja = lopaty.removeFirst();
								System.out.println(this.getName() + " Zobrali sme si lopatu, pocet zv.:" + lopaty.size());
							} else {
								continue; // ziadna lopata nie je, musime skusat znova
							}
						}
						// kopeme jamu
						try {
							Thread.sleep(r.nextInt(1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}			
						// vraciame lopatu
						synchronized (lopaty) {
							lopaty.add(moja);
							moja=null;
							System.out.println(this.getName() + " Vraciame lopatu, pocet zv.:" + lopaty.size());
						}
						// oddychujeme
						try {
							Thread.sleep(r.nextInt(1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}

}
