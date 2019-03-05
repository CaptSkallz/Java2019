public class ComplexMain {

	public static void main(String[] args) throws CloneNotSupportedException {
	  Complex c1 = new Complex(1,0);   // 1
	  Complex c2 = new Complex(0,1);   // i
	  System.out.println(c1);
	  System.out.println(c2);	
	  
	  System.out.println(Math.sqrt(
			  c1.getReal()*c1.getReal() + c1.getImag()*c1.getImag()));
	  
	  System.out.println(c1.abs());
	  
	  
	  Complex c_clone;
	try {
		c_clone = c1.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		throw e;
	}
	  Complex c_copy = c1;
	  c1.add(c2);
	  c2.mult(c2);
	  System.out.println(c1);
	  System.out.println(c2);
	  System.out.println(c_copy);
	  System.out.println(c_clone);
	  
	}
}
