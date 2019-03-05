public class Postupnosti {

	public static void main(String[] args) {
		AritmetickaPostupnost p = new AritmetickaPostupnost(4);
		p.printPostupnost(10);
		AritmetickaPostupnost r = new AritmetickaPostupnost(13, 10);
		r.printPostupnost(10);
		GeometrickaPostupnost q = new GeometrickaPostupnost(1, 2);
		q.printPostupnost(10);
		FibonaccihoPostupnost f = new FibonaccihoPostupnost(0, 1);
		f.printPostupnost(10);
	}
}
