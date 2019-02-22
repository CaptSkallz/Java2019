/**
 * prevod cisla do dvojkovej sustavy
 * @author PB
 */
public class Binary {
  
  private static String toBinary(int n) {
	  if (n > 0)
		  return toBinary(n/2)+(n % 2);
	  else
		  return "";
  }
	
  public static void main(String[] args) {	
	Input in = new Input();
	System.out.println("Zadaj N:");
	int N = in.nextInt();
	System.out.println("Binarne N:"+toBinary(N));
	in.close();
  } 
}
