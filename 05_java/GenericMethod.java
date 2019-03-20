public class GenericMethod {

	public static <T> String genMethod(T value) {
		System.out.println(value.getClass());
		return value.toString();
	}
	public static <E> void printArray(E[] p) {
		   for ( E elem : p )                           
			   System.out.print( elem +",");
		   System.out.println();
	}
	
	public static <T extends Number> T genMethod2(T value) {
		System.out.println(value.getClass());
		return value;
	}
	public static <E extends Shape> void printArray(E sh) {
		sh.paint();
	}
	abstract class Shape {
		abstract void paint();
	}
	class Rectangle extends Shape {
		void paint() {}
	}
	class Oval extends Shape {
		void paint() {}
	}
	
	public static void main(String[] args) {
		System.out.println(genMethod(1));
		System.out.println(genMethod("wow"));
		Integer[] p = {1,2,3};
		System.out.println(genMethod(p));
		printArray(p);
		Double[] r = {1.1,2.2,3.3};
		System.out.println(genMethod(r));
		printArray(r);
		//--------
		System.out.println(genMethod2(1));
		//System.out.println(genMethod2("wow"));
		System.out.println(genMethod2(Math.PI));	
	}
}
