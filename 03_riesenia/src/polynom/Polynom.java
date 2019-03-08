package polynom;

abstract class Polynom {
	abstract Double valueAt(String[] vars, double[] values);
	abstract Polynom derive(String var);
	abstract Polynom simplify();
}
