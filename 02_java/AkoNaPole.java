import java.io.BufferedInputStream;

public class AkoNaPole {
	public static String zretaz(String[] a) {
		StringBuffer sb = new StringBuffer();
		if (a != null)
			for(int i = 0; i < a.length; i++) sb.append(a[i]);
		return sb.toString();
	}
	public static int spocitaj(String[] a) {
		int vysl = 0;
		if (a != null)
			for(int i = 0; i < a.length; i++) 
				if (a[i] != null)
					vysl += a[i].length();
		return vysl;
	}
	
	public static void main(String[] args) {
		zretaz(null);
		zretaz(new String[]{null});
		spocitaj(new String[]{null});
		spocitaj(new String[]{new String()});
	}
}
