package doc.resources;

public class BlaBla {
	int a;
	char b;
	double c;
	
	public BlaBla(int a, char b, double c) {
		//super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "BlaBla [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public char getB() {
		return b;
	}

	public void setB(char b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

}
