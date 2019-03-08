package polynom;

public class Konstanta extends Polynom {
	private double k;

	public Konstanta(double k) {
		super();
		this.k = k;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	@Override
	public String toString() {
		return ""+k;
	}

	@Override
	Double valueAt(String[] vars, double[] values) {
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
}
