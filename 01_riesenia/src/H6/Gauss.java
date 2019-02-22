
public class Gauss {

	public static void main(String[] args) {
		System.out.println(sucet(2,5));
		System.out.println(sucet(1,100));
	}
	
	public static long sucet(long from, long to) {
		return (((from + to) * (to - from + 1))/2);
	}

}
