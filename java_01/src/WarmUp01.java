public class WarmUp01 {
	static int a;
	static String b;
	class Dog {}
	static Dog c;
	static char d;
	static float e;
	static boolean f;

	public static void main(String[] args) {
		//1) V ktorych z nasledujucich moznosti uvedena kontanta zodpoveda 
		// preddefinovanej hodnote daneho typu:
		if (a == 0) {
			System.out.println("1A");
		}
		if ("null".equals(b)) {
			System.out.println("1B");
		}
		if (c == null) {
			System.out.println("1C");
		}
		if (d == '\u0000') {
			System.out.println("1D");
		}
		if (e == 0.0f) {
			System.out.println("1E");
		}
		if (f == true) {
			System.out.println("1F");
		}
		// 2) Ktore z nasledujucich moznosti predstavuju korektnu deklaraciu 
		// premennej typu char:

		char c1 = 064770;
		System.out.println("2A " + c1);
		// toto nie je char konstanta: char c2 = 'face';
		char c3 = 0xbeef;
		System.out.println("2C " + c3);
		// chybaju apostrofy char c4 = \u0022;
		//						char c4 = '\u0022';
		// \i neviem, co to je... char c5 = '\iface';
		char c6 = '\uface';
		System.out.println("2F " + c6);
		
		// 3) Ktore z nasledujucich moznosti predstavuju korektnu deklaraciu premennej 
		// typu float:

		float f1 = -343;
		System.out.println("3A " + f1);
		// float f2 = 3.14;
		// float f2 = 3.14f; je dobre
			
		float f3 = 0x12345;
		System.out.println("3C " + f3);
			
		//float f4 = 42e7;
		// float f4 = 42e7f;
		
		//float f5 = 2001.0D;
		float f6 = 2.81F;
		System.out.println("3F " + f6);

		// 4) Ktore z nasledujucich moznosti predstavuju korektnu deklaraciu premennej 
		// typu String:

		String s1 = null;
		System.out.println("4A " + s1);
		// String s2 = 'null';
		// String s3 = (String) 'abc';
		// String s4 = (String) '\ufeed';
		//	5) Ktore z nasledujucich moznosti predstavuju korektnu deklaraciu premennej 
		// typu boolean:

		// boolean b1 = 0;
		// boolean b2 = 'false';
		boolean b3 = false;
		System.out.println("5C " + b3);
		// boolean b4 = Boolean.false();
		// boolean b5 = no;

		// 6) Numericky interval typu char je:

		// -128 to 127 char je v JAVE 16 bit
		// ok -(2^15) to (2^15) - 1 
		System.out.println("6B char je v JAVE 16 bit" );
		// zle, nemame unsigned 0 to 32767   
		// zle, nemame unsigned 0 to 65535
	}
}
