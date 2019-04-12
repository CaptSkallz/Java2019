import java.util.Random;
public class Piskvorky {

	public static void main(String[] args) {
		PiskyStav hra = new PiskyStav();
		try {
			/* pri prvom spusteni odkomentuj
			   hra = new PiskyStav();
			  	hra.save("pisky.cfg");
			  */
			hra = PiskyStav.load("pisky.cfg");
			System.out.println(hra);
			Random rnd = new Random();
			hra.piskvorky[rnd.nextInt(10)][rnd.nextInt(10)] = hra.XNaTahu?'X':'O';
			hra.XNaTahu = !hra.XNaTahu;
			hra.save("pisky.cfg");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
