/**
 * konverzie medzi typmi String, int, double, boolean  
 * @author PB
 */
public class KonverzieString {
	public static void main(String[] args) {

	int i1, i2;
	String s1, s2;
	  i1 = Integer.valueOf("123");	s1 = String.valueOf(123);
	  i2 = Integer.parseInt("123");	s2 = Integer.toString(123,10);
	  
		System.out.println(i1);
		System.out.println(i2);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(Integer.toBinaryString(31));
		System.out.println(Integer.toOctalString(15));
		System.out.println(Integer.toHexString(255));

		
		double d1, d2;
		String t1, t2;
		  d1 = Double.valueOf("3.1415");	t1 = String.valueOf(Math.PI);
		  d2 = Double.parseDouble("3.1415");t2 = Double.toString(Math.PI);
 		  System.out.println(d1);
		  System.out.println(d2);
		  System.out.println(t1);
		  System.out.println(t2);
		  
		  System.out.println(Boolean.valueOf("true"));
		  System.out.println(Boolean.parseBoolean("false"));
		  System.out.println(String.valueOf(true));
		  System.out.println(Boolean.toString(false));
		
	}

}
