/**
 * najdite chybu a opravte ju
 * @author PB
 */
public class Chyba6 {
	public static void main(String[] args) {
		int a = 36;
		int b = 99;
		// tento program pocita NSD dvoch cisel a, b
		do {
			System.out.println(a + ", " + b);
			if (a>b) {
				a = a-b;
			} else {
				b = b-a;
			}
		} while (a == b);	
		System.out.println(a);
	}
}
