public class AritmetickaPostupnost extends Postupnost {
protected long delta;

    AritmetickaPostupnost(int _delta) {
	   delta = _delta; prvy = 0;	
	}
    AritmetickaPostupnost(int _prvy, int _delta) {
	   delta = _delta; prvy = _prvy;	
	}
	public long Dalsi() {
		aktualny += delta;
		return aktualny;
	}
}
