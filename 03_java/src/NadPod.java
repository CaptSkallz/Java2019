
public class NadPod {
  public static void main(String[] args) {
	  Nadtrieda x = new Nadtrieda();
	  x.setA(5);
	  Podtrieda y = new Podtrieda();
	  y.setA(6);
	  System.out.println(x.getA());
	  System.out.println(y.getA());
	  System.out.println(y.getSuperA());
	//  System.out.println(y.getSuperAA());
  }
}
