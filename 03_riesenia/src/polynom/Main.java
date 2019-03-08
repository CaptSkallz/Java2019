package polynom;

public class Main {
	public static void main(String[] args) {
		Polynom p1 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Konstanta(1) ).simplify();
		System.out.println(p1 + "..." + p1.valueAt(new String[]{"x"}, new double[]{5})); // 26
		Polynom p2 = p1.derive("x").simplify(); // 2*x
		System.out.println(p2 + "..." +p2.valueAt(new String[]{"x"}, new double[]{3})); // 6		
	}
}
