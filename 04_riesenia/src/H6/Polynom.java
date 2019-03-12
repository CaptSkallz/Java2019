
public abstract class Polynom {
	abstract double valueAt(String[] vars, double[] values);
	abstract Polynom derive(String var);
	abstract Polynom simplify();
}
