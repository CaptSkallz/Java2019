/**
 * konverzia ciselnych argumentov z prikazoveho riadku
 * @author PB
 */
public class MainArgInt {
   public static void main(String[] args) {
  	  for(int i = 0; i < args.length; i++) {
  		int n = Integer.parseInt(args[i]);
		System.out.println("args["+i+ "]=" + n);
  	  }
	}
}
