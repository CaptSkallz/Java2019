import java.util.*;

public class EnumSetDemo {

	public static void main(String[] args) {
		EnumSet<Farba> es = EnumSet.of(Farba.Gula, Farba.Zelen);
		es.add(Farba.Srdce);
		for(Farba f : es)
			System.out.println(f);

		EnumSet<Farba1> es1 = EnumSet.of(Farba1.Gula, Farba1.Zelen);
		es1.add(Farba1.Srdce);
		for(Farba1 f : es1)
			System.out.println(f);
		
	}
}