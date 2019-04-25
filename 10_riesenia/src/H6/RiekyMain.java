package H6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RiekyMain {	
	
	final static int N = 20; 
	
	public static void main(String[] args) {
		
		//RiekaThread rieka1 = new RiekaThread("prva", N);
		//rieka1.run();
		
		List<RiekaThread> rieky = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			RiekaThread rieka = new RiekaThread("rieka" + i, N);
			rieky.add(rieka);
		}
		
		for (RiekaThread rt : rieky) {
			rt.start();
		}
		
		while(true) {
			sutok(rieky);
			kresli(rieky);
			
			try {
				Thread.sleep(250);
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public static void kresli(List<RiekaThread> rieky) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			boolean r = false;
			for (RiekaThread rt : rieky) {
				if (rt.vzdialenost == i) {
					sb.append("0");
					r = true;
				}
			}
			if (!r) {
				sb.append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void sutok(List<RiekaThread> rieky) {
		Collections.sort(rieky,
				(r1,r2) -> Integer.compare(r1.vzdialenost, r2.vzdialenost)
				);
		int pred = -1;		
		for (Iterator<RiekaThread> it = rieky.iterator(); it.hasNext(); ) {
			RiekaThread rt = it.next();
			int aktual = rt.vzdialenost;
			if (aktual == pred) {
				rt.interrupt();
				it.remove();
			}
			pred = aktual;
		}
//		for (RiekaThread rt : rieky) {
//			for (RiekaThread rt2 : rieky) {
//				if (rt.vzdialenost == rt2.vzdialenost) {
//					
//				}
//			}
//		}
	}
}
