package H6;

import java.util.Random;

public class RiekaThread extends Thread{
	int vzdialenost;
	int sirka;
	
	public RiekaThread(String meno,int sirka) {
		super(meno);
		Random r = new Random();		
		this.sirka = sirka;
		vzdialenost = r.nextInt(sirka);		
	}
	
	@Override
	public void run() {		
		Random r = new Random();
		
		try {
			for (int i = 0; i < 100; i++) {
				vzdialenost += r.nextInt(3) - 1;
				if (vzdialenost < 0) {
					vzdialenost = 0;
				}
				if (vzdialenost >= sirka) {
					vzdialenost = sirka - 1;
				}
				
				//System.out.println(getName()+" "+vzdialenost);	
				sleep(r.nextInt(500) + 200);
			}
			
		} catch (InterruptedException e) {
			// sutok riek
		}
	
	}
		
}
