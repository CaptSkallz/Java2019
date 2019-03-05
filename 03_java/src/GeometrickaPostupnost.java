public class GeometrickaPostupnost extends Postupnost {
	protected long quotient;

	GeometrickaPostupnost(int prvy, int quotient) {
		this.quotient = quotient; this.prvy = prvy;	
	}
	public long Dalsi() {
		aktualny *= quotient;
		return aktualny;
	}
}
