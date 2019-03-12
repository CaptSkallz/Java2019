
public class Sucet extends Polynom{
	@Override
	Polynom simplify() {
		if (p1 instanceof Konstanta) {
			if (p1.valueAt(null, null) == 0) {
				return p2.simplify();
			}
		}
		if (p2 instanceof Konstanta) {
			if (p2.valueAt(null, null) == 0) {
				return p1.simplify();
			}
		}
		
		if (p1.equals(p2)) {
			return new Sucin(p1, new Konstanta(2));
		}
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Sucet) {
			if(p1.equals(((Sucet)obj).p1) &&
				p2.equals(((Sucet)obj).p2)) {
				return true;
			}
		}
		return false;
	}
	

	private Polynom p1;
	private Polynom p2;
	@Override
	double valueAt(String[] vars, double[] values) {
		return p1.valueAt(vars, values) + p2.valueAt(vars, values);
	}

	@Override
	Polynom derive(String var) {
		return new Sucet(p1.derive(var), p2.derive(var));
	}
	
	@Override
	public String toString() {
		return "(" + p1 + " + " + p2 + ")";
	}

	public Sucet(Polynom p1, Polynom p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	
	
}
