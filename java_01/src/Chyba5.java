/**
 * najdite chybu a opravte ju
 * @author PB
 */
public class Chyba5 {
	public static void main(String[] args) {
		int a = 18;
		int b = 12;
		// tento program pocita NSD dvoch cisel a, b
		while (a != b) { 
			System.out.println(a + ", " + b);
			if (a>b) {
				a = a-b;
			} else {
				b = b-a;
			}
		}
		System.out.println(a);
	}
}
