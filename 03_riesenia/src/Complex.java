public class Complex {
	private double real, imag;

	public Complex(double real, double imag) {
		super(); 
		this.real = real; this.imag = imag;
	}
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public double getImag() {
		return imag;
	}
	public void setImag(double imag) {
		this.imag = imag;
	}
	@Override
	public String toString() {
		return "Complex [real=" + real + ", imag=" + imag + "]";
	}	  
}
