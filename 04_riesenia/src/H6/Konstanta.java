
public class Konstanta extends Polynom{
	private double hodnota; 
	@Override
	double valueAt(String[] vars, double[] values) {
		return hodnota;
	}

	@Override
	Polynom derive(String var) {
		Polynom res = new Konstanta(0);
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Konstanta) {
			if(hodnota == ((Konstanta)obj).hodnota) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "" + hodnota;
	}

	public Konstanta(double hodnota) {
		super();
		this.hodnota = hodnota;
	}

	@Override
	Polynom simplify() {
		// TODO Auto-generated method stub
		return this;
	}
	
	

}
