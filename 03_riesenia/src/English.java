/**
 * Cvicenie 3
 * @author PB
 * ako sa ukazalo (vid posledny riadok), toto nie je korektne riesenie
 * ale da sa lahko opravit (povedal F.Nagy:-)
 */
public class English {
	
	static int English2Int(String number) {
		int n = 0;
		String[] slovicka = number.split(" ");
		for(int i=0; i<slovicka.length; i++) {
            if (slovicka[i].equals("one")) n += 1;
            else if (slovicka[i].equals("two")) n += 2;
            else if (slovicka[i].equals("three")) n += 3;
            else if (slovicka[i].equals("four")) n += 4;
            else if (slovicka[i].equals("five")) n += 5;
            else if (slovicka[i].equals("six")) n += 6;
            else if (slovicka[i].equals("seven")) n += 7;
            else if (slovicka[i].equals("eight")) n += 8;
            else if (slovicka[i].equals("nine")) n += 9;
            else if (slovicka[i].equals("ten")) n += 10;
            else if (slovicka[i].equals("eleven")) n += 11;
            else if (slovicka[i].equals("twelve")) n += 12;
            else if (slovicka[i].equals("thirteen")) n += 13;
            else if (slovicka[i].equals("fourteen")) n += 14;
            else if (slovicka[i].equals("fifteen")) n += 15;
            else if (slovicka[i].equals("sixteen")) n += 16;
            else if (slovicka[i].equals("seventeen")) n += 17;
            else if (slovicka[i].equals("eighteen")) n += 18;
            else if (slovicka[i].equals("nineteen")) n += 19;
            else if (slovicka[i].equals("twenty")) n += 20;
            else if (slovicka[i].equals("thirty")) n += 30;
            else if (slovicka[i].equals("forty")) n += 40;
            else if (slovicka[i].equals("fifty")) n += 50;
            else if (slovicka[i].equals("sixty")) n += 60;
            else if (slovicka[i].equals("seventy")) n += 70;
            else if (slovicka[i].equals("eighty")) n += 80;
            else if (slovicka[i].equals("ninety")) n += 90;    
            else if (slovicka[i].equals("hundred")) n *= 100;
            else if (slovicka[i].equals("thousand")) n *= 1000;
		}
		return n;
	}

	public static void main(String[] args) {
		System.out.println(English2Int("one thousand two"));
		System.out.println(English2Int("eight hundred twelve"));
		System.out.println(English2Int("zero"));
		System.out.println(English2Int("fifteen"));
		System.out.println(English2Int("sixty five"));
		System.out.println(English2Int("one hundred ten"));	
		System.out.println(English2Int("one thousand one hundred ten")); // chyba
	}
}
