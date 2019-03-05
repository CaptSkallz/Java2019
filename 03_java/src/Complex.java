public class Complex implements Cloneable {
  private double real, imag;
  
  public Complex(double _real, double _imag) {
	  real = _real; imag = _imag;
  }
  public Complex() {
	  this(0,0);
  }
  @Override
  public String toString() {
	  return "["+real+ "+" +imag+"*i]";
  }
  // properties
  public double getReal() { return real; }
  public void setReal(double _real) { real = _real; }

  public double getImag() { return imag; }
  public void setImag(double imag) { this.imag = imag; }
  
  public double abs() {
	return Math.sqrt(real*real + imag*imag);  
  }
  /* nepravne pretazenie
  public int abs() {
		return 5;  
	  }
	  */
  public void add(Complex c) {
	real += c.real;
	imag += c.imag;
  }
  public void mult(Complex c) {
	 double _real = real*c.real-imag*c.imag;
	 double _imag = real*c.imag+imag*c.real;
	 real = _real;
	 imag = _imag;
  }
  public void mult(double r) {
		real *= r;
		imag *=r;
	  }
  /*
  public Complex mult(Complex c) {
  }
  */
  
  @Override
  public Complex clone() throws CloneNotSupportedException {
	    return (Complex)(super.clone());
  }
}
