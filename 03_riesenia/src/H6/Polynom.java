
abstract class Polynom {
	abstract double valueAt(String[] vars, double[] values);
	abstract Polynom derive(String var);
	abstract Polynom simplify();
	
	public static void main(String[] args) {
		Polynom k = new Konstanta(2);
		System.out.println(k);
		
		Polynom p = new Premenna("x");
		System.out.println(p);
		
		System.out.println(p.valueAt(new String[] {"x"}, new double[] {12}));
		
		Polynom s = new Sucet(k, p);
		System.out.println(s);
		
		System.out.println(s.derive("x"));
		
		Polynom sks = new Sucin(s, s);
		System.out.println(sks);
		System.out.println(sks.derive("x"));
		
		System.out.println(sks.derive("x").simplify());
	}
}
