
public class Sucet extends Polynom {
	Polynom a;
	Polynom b;
	
	public Sucet(Polynom a, Polynom b) {
		this.a = a;
		this.b = b;
	}

	@Override
	double valueAt(String[] vars, double[] values) {
		double aValue = a.valueAt(vars, values);
		double bValue = b.valueAt(vars, values);
		
		return aValue + bValue;
	}

	@Override
	Polynom derive(String var) {
		return new Sucet(a.derive(var), b.derive(var));
	}
	
	@Override
	Polynom simplify() {
		System.out.println("simplify +");
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		a = a.simplify();
		b = b.simplify();
		
		if (a instanceof Konstanta && b instanceof Konstanta) {
			return new Konstanta(((Konstanta)a).k + ((Konstanta)b).k);
		}
		
		if (a instanceof Konstanta && ((Konstanta)a).k == 0) {
			return b;
		}
		
		if (b instanceof Konstanta && ((Konstanta)b).k == 0) {
			return a;
		}
		
		return this;
	}
	
	@Override
	public String toString() {
		return "(" + a.toString() + " + " + b.toString() + ")";
	}
}
