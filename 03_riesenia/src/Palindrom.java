/**
 * Cvicenie 3
 * @author PB
 */
public class Palindrom {
	public static boolean palindrom(String s) {
		//return new StringBuffer(s).reverse().toString().equals(s);
		// alebo klasicky ;-)
		int j=s.length()-1;
		for(int i=0; i<=j; i++,j--)
			if (s.charAt(i)!=s.charAt(j)) return false;
		return true;
	}
	public static void main(String[] args) {
		System.out.println(palindrom(args[0]));
	}
}
