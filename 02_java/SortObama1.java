
import static org.junit.Assert.assertEquals;

public class SortObama1 {

	public static void main(String[] args) {
		String alpha1 = "abcdefghijklmnopqrst";
		String alpha2 = new StringBuffer("abcdefghijklmnopqrst").reverse()
				.toString();
		String alpha3 = new StringBuffer("0123456789").reverse().toString();

		String alpha4 = "0123456789";
		String alpha5 = "01";

		String alpha7 = "10";
		System.out.println(compareTo(alpha1, "janko", "marienka"));
		System.out.println(compareTo(alpha1, "pat", "mat"));
		System.out.println(compareTo("01", "0", "00"));
		System.out.println(compareTo("01", "00", "01"));
		/*
		 * System.out.println(compareTo("01", "01", "1"));
		 * System.out.println(compareTo("01", "1", "10"));
		 * System.out.println(compareTo("01", "1", "11")); // v abecede "10"
		 * plati: "1" < "11" < "10" < "0" < "01" < "00"
		 * 
		 * System.out.println(compareTo("10", "1", "11"));
		 * System.out.println(compareTo("10", "11", "10"));
		 * System.out.println(compareTo("10", "10", "0"));
		 * System.out.println(compareTo("10", "0", "01"));
		 * System.out.println(compareTo("10", "01", "00"));
		 * System.out.println(compareTo(alpha2, "janko", "marienka"));
		 * System.out.println(compareTo(alpha2, "pat", "mat"));
		 */

	}

	public static int compareTo(String alphabet, String s1, String s2) {
		String[] abeceda = alphabet.split("");
		String[] slovo1 = s1.split("");
		String[] slovo2 = s2.split("");
		// System.out.println(abeceda.length);
		int vrat = 0;
		int len_1 = s1.length();
		int len_2 = s2.length();
		int len = Math.min(s1.length(), s2.length());
		String jedna, dva;
		int prvy = 0, druhy = 0;
		for (int i = 0; i < len; i++) {
			jedna = slovo1[i];
			dva = slovo2[i];
			for (int j = 0; j < abeceda.length; j++) {
				if (jedna.equals(abeceda[j]))
					prvy = j;
				if (dva.equals(abeceda[j]))
					druhy = j;
			}
			if (prvy > druhy) {
				// System.out.println("++++++++++++++++++++++");
				vrat = 1;
				break;
			}
			if (prvy < druhy) {
				// System.out.println("---------------------");
				vrat = -1;
				break;
			}
		}
		if (len_1 < len_2 && vrat == 0)
			return -1;
		else if (len_1 > len_2 && vrat == 0)
			return 1;
		else if (len_1 == len_2 && vrat == 0)
			return 0;
		else
			return vrat;
	}
}