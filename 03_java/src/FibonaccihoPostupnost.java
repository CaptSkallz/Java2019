public class FibonaccihoPostupnost extends Postupnost {
  protected long predch;
  FibonaccihoPostupnost(long _prech, long _aktual) {
    predch = _prech; prvy = aktualny = _aktual;
  }
  public long Dalsi() {
	  long pom = aktualny;
	  aktualny += predch;
	  predch = pom;
	  return aktualny;
  }
}
