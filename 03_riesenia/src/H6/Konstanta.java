
public class Konstanta extends Polynom {
	double k;
	
	public Konstanta(double k) {
		this.k = k;
	}
	
	@Override
	double valueAt(String[] vars, double[] values) {
		return k;
	}

	@Override
	Polynom derive(String var) {
		return new Konstanta(0);
	}
	
	@Override
	Polynom simplify() {
		return this;
	}

	@Override
	public String toString() {
		return Double.toString(k);
	}
}
