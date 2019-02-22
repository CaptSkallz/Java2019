
public class Binarne {
	/*
	 * Napíšte program, ktorý pre zadané èíslo 
	 * vypíše poèet jednotiek v jeho binárnom zápise. 
	 * Napríklad, pre 10 vypíše 2.
	 */
	
	public static void bin(long cislo) {
		int pocet = 0;
		long tmp = cislo;
		while (tmp != 0) {
			if ((tmp & 1) == 1) {
				pocet++;
			}
			tmp = tmp >>> 1;
		}
		
		System.out.println(pocet);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bin(15);
		bin(14);
		bin(10);
		bin(-1);
	}

}
