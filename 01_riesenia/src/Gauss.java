public class Gauss {

	public static long sucet(long from, long to) {
		return (from+to)*(to-from+1)/2;
	}
	
	public static void main(String[] args) {
		System.out.println(sucet(1,100));
		System.out.println(sucet(1,100000));
		System.out.println(sucet(1,100000000));
	}
}
