import java.util.Random;

public class Pole2D {

	public static int pocet(String[][] a) {
		int pocet = 0;
		if (a != null) {
			for(int i=0; i<a.length; i++) {
				if (a[i] != null)
					for(int j=0; j<a[i].length; j++) {
						if (a[i][j] != null) {
							if (a[i][j].equalsIgnoreCase("yes") || a[i][j].equalsIgnoreCase("no"))
								pocet++;
						}
					}
			}
		}
		return pocet;
	}
	static Random rnd = new Random();
	
	public static String nahodnyRetazec() {
		StringBuffer vysl = new StringBuffer();
		for (int i = 0; i < rnd.nextInt(32); i++) {
			vysl.append((char)(rnd.nextInt((int)'z' - (int)'a') + (int)'a'));
		}
		//System.out.println(vysl.toString());		
		return vysl.toString();
	}
	public static String[] nahodnePole1D() {
		String[] vysl = new String[rnd.nextInt(1000)];
		for (int i = 0; i < vysl.length; i++) {
			vysl[i] = nahodnyRetazec();
		}
		return vysl;
	}
	public static String[][] nahodnePole2D() {
		String[][] vysl = new String[rnd.nextInt(1000)][];
		for (int i = 0; i < vysl.length; i++) {
			vysl[i] = nahodnePole1D();
		}
		return vysl;
	}
	
	public static void main(String[] args) {
		do {
			System.out.print('.');
		} while (pocet(nahodnePole2D()) > 0);
		System.out.print("mam ho");
	}
}
