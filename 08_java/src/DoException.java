
public class DoException {
  public static void main(String[] args) {
	try { // kód produkujúci Ex1, Ex2 resp. Ex12
	  switch((int)(3*Math.random())) {
	    case 0: throw new Exception12();
	    case 1: throw new Exception1();
	    case 2: throw new Exception2();
	  }
	} catch (Exception1 e1) {
	    System.out.println("Exception 1"); 
	    return;
	} catch (Exception2 e2) {
	    System.out.println("Exception 2"); 
	    return;
	} catch (Exception12 e12) {
	    System.out.println("Exception 12");
	    return;
	} finally {
	    System.out.println("finally"); 
	}
    System.out.println("totally");
  }
}
