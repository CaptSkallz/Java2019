
public class Premenna extends Polynom {
	String meno;

	public Premenna(String s) {
		meno = s;
	}
	
	@Override
	double valueAt(String[] vars, double[] values) {
		for (int i = 0; i < vars.length; i++) {
			if (vars[i].equals(meno)) {
				return values[i];
			}
		}
		
		return -1;
	}
	
	@Override
	Polynom simplify() {
		return this;
	}

	@Override
	Polynom derive(String var) {
		return (meno.equals(var)) ? new Konstanta(1) : new Konstanta(0);
	}
	
	@Override
	public String toString() {
		return meno;
	}
}
