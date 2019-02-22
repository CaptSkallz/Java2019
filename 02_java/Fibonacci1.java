/**
 * dve moznosti, ako dostat ciselny parameter do programu
 * @author PB
 */
public class Fibonacci1 {

  public static void main(String[] args) {	
	Input in = new Input();
	System.out.println("Zadaj N:");
	int N = in.nextInt();
	//int N = Integer.parseInt(args[0]);
	long a = 1;
	long b = 0;
	while (N-- > 0) {
		System.out.println(b);
		a = a+b;
		b = a - b;
	}
	in.close();
  } 
}
