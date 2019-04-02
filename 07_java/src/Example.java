public class Example {

  @FunctionalInterface
  interface BinOp { double operation(double a, double b);  }
  
  // @FunctionalInterface
  interface ZleOp {  // toto nie je funkcionalny interface lebo obsahuje dve metódy
	  double operation1(double a, double b);  
	  double operation2(double a, double b);  
  	}

  public static void main(String args[]){
    BinOp plus = (a, b) -> a + b;
    System.out.println("3 + 4 = " + plus.operation(3, 4));
    
    
    
    //BinOp vector = (double a, double b) -> {return Math.sqrt(a*a + b*b); };
    BinOp vector = (a,b) -> Math.sqrt(a*a + b*b);
    System.out.println("vector(3,4) = "+vector.operation(3,4));

    System.out.println("vector(3,4) = "+ 
    		((BinOp)(a, b) -> Math.sqrt(a*a + b*b)).operation(3,4));
  
  }
}
