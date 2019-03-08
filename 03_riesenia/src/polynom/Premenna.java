package polynom;

public class Premenna extends Polynom {
	private String name;

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Premenna(String name) {
		super();
		this.name = name;
	}

	@Override
	Double valueAt(String[] vars, double[] values) {
		for(int i = 0; i<vars.length; i++) {
			if (name.equals(vars[i]))
				return values[i];
		}
		return Double.NaN;
	}

	@Override
	Polynom derive(String var) {
		if (var.equals(name))
			return new Konstanta(1);
		else
			return new Konstanta(0);
	}
	@Override
	Polynom simplify() {
		return this;
	}	
}
