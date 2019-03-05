public class Karta {
  Farba f;
  Hodnota h;
  
  public Karta(Farba _f, Hodnota _h) {
	  f = _f; h = _h;
  }
  public String toString() {
	  return h.toString()+f.toString();
  }
  public boolean bije(Karta k) {
	  if (f != k.f)
		 return false;
	  return h.bije(k.h);
  }
}
