
public class Sucin extends Polynom{
	Polynom a;
	Polynom b;
	
	public Sucin(Polynom a, Polynom b) {
		this.a = a;
		this.b = b;
	}

	@Override
	double valueAt(String[] vars, double[] values) {
		double aValue = a.valueAt(vars, values);
		double bValue = b.valueAt(vars, values);
		
		return aValue * bValue;
	}

	@Override
	Polynom derive(String var) {
		Polynom d1 = new Sucin(a.derive(var), b);
		Polynom d2 = new Sucin(a, b.derive(var));
		return new Sucet(d1, d2);
	}
	
	@Override
	Polynom simplify() {
		System.out.println("simplify *");
		System.out.println(a);
		System.out.println(b);
		System.out.println();
		
		a = a.simplify();
		b = b.simplify();
		
		if (a instanceof Konstanta && b instanceof Konstanta) {
			return new Konstanta(((Konstanta)a).k * ((Konstanta)b).k);
		}
		
		return this;
	}
	
	@Override
	public String toString() {
		return "(" + a.toString() + " * " + b.toString() + ")";
	}
}
