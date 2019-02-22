public class Komutativnost {

	public static boolean loop() {	
		for(;;);
	}
	/*
	public static void main(String[] args) {
		System.out.println(false && (7/0 > 0)); // false
		//System.out.println((7/0 > 0) && false); // div by zero

		System.out.println(true || (7/0 > 0)); // true
		//System.out.println((7/0 > 0) || true); // div by zero
		
		System.out.println(false && loop()); // false
		//System.out.println(loop() && false); // zacykli sa

		System.out.println(true || loop()); // true
		//System.out.println(loop() || true); // zacykli sa
	}
	*/
	public static void main(String[] args) {
		//System.out.println(false & (7/0 > 0)); // div by zero
		//System.out.println((7/0 > 0) & false); // div by zero

		//System.out.println(true | (7/0 > 0)); // div by zero
		//System.out.println((7/0 > 0) | true); // div by zero
		
		//System.out.println(false & loop()); // zacykli sa
		//System.out.println(loop() & false); // zacykli sa

		//System.out.println(true | loop()); // zacykli sa
		//System.out.println(loop() | true); // zacykli sa
		int a = 7, b = 0;
		System.out.println( a == 0 | b == 0 );
		System.out.println( (a | b) == 0 );
	}	
}
