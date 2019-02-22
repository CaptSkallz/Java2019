/**
 * pouzitie roznych operatorov
 * @author PB
 */
public class Operatory {
  public static void main(String[] args) {	
	int i, j, k;
	i = 1; j = 2; k = 3;
    if (i == 1  ||  ++j == 2)
      k = 4;
    System.out.println("i = "+ i +", j = "+ j +", k = "+ k);
    
    i = 1; j = 2; k = 3;
    if (i == 1  |  ++j == 2)
      k = 4;
    System.out.println("i = "+ i +", j = "+ j +", k = "+ k);
    
    i = 1; j = 2; k = 3;
    if (i == 2  &&  ++j == 3)
      k = 4;
    System.out.println("i = " + i + ", j = " + j + ", k = " + k);
    
    i = 1; j = 2; k = 3;
    if (i == 2  &  ++j == 3)
      k = 4;
    System.out.println("i = " + i + ", j = " + j + ", k = " + k);

    int a = 0;
    System.out.println(a == 0);
    System.out.println((a != 0) == false);
    System.out.println((a >= 0) && (a <= 0));
	System.out.println((a + a == a) || (a * a == a));
	System.out.println(!(a!=0));
	System.out.println((~a) == -1);
	System.out.println(a & (~a));
	System.out.println(a | (~a));
	System.out.println(a ^ (~a));
	System.out.println((a+1) << 2);
	System.out.println((a+1) >> 1);
	System.out.println((a-1) >> 4);
	System.out.println((a-1) >>> 4);
  }
}
