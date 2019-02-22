public class Quiz {
	static String s1;
	static String s2 = null;
	static String s3 = "";

	static String s4 = "java";
	static String s5 = new String("java");
	static String s6 = "ja" + "va";
	static String s7 = "ja";

	static String[] p1;
	static String[] p2 = null;
	static String[] p3 = new String[0];
	static String[] p4 = new String[] {};
	static String[] p5 = new String[] { "" };
	static String[] p6 = new String[] { null };

	public static void main(String[] args) {
		System.out.println(s1 == s2); // takto sa retazce NESMU porovnavat
		System.out.println(s1 == s3);

		// System.out.println(s1.length());
		// System.out.println(s2.length());
		System.out.println(s3.length());

		System.out.println(s4 == s5);
		System.out.println(s4 == s6);
		s7 += "va";
		System.out.println(s4 == s7);

		System.out.println(s4.equals(s5));
		System.out.println(s4.equals(s6));
		System.out.println(s4.equals(s7));
		// -----------------------------

		System.out.println(p1.length);	// toto zrejme padne na null
		// System.out.println(p2.length);
		System.out.println(p3.length);
		System.out.println(p4.length);
		System.out.println(p5.length);
		System.out.println(p5[0].length());
		System.out.println(p6.length);
		// System.out.println(p6[0].length());

	}

}
