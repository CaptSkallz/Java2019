package polynom;

public class Sucin extends Polynom {
	private Polynom p1, p2;

	public Sucin(Polynom p1, Polynom p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public Polynom getP1() {
		return p1;
	}

	public void setP1(Polynom p1) {
		this.p1 = p1;
	}

	public Polynom getP2() {
		return p2;
	}

	public void setP2(Polynom p2) {
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return "(" + p1 + "*" + p2 + ")";
	}

	@Override
	Double valueAt(String[] vars, double[] values) {
		return p1.valueAt(vars, values) * p2.valueAt(vars, values);
	}

	@Override
	Polynom derive(String var) {
		return new Sucet(
				new Sucin (p1.derive(var), p2), 
				new Sucin (p2.derive(var), p1));
	}
	@Override
	Polynom simplify() {
		Polynom p1s = p1.simplify();
		Polynom p2s = p2.simplify();
		if (p1s != null && p1s instanceof Konstanta && ((Konstanta)p1s).getK()== 1) {
			return p2s;
		} else if (p2s != null && p2s instanceof Konstanta && ((Konstanta)p2s).getK()== 1) {
			return p1s;
		} else  if (p1s != null && p1s instanceof Konstanta && p2s != null && p2s instanceof Konstanta) {
			return new Konstanta(((Konstanta)p1s).getK() * ((Konstanta)p2s).getK());
		} 
		return new Sucin(p1s,p2s);
	}	
	
}
