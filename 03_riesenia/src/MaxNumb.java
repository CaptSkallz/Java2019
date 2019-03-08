/**
 * Cvicenie 3
 * @author PB
 */
public class MaxNumb {
	static long MaxNumbb(String s) {
		String p = s+"."; // zarazka
		long number = 0;
		long maxNumber = -1;
		for(int i=0; i<p.length(); i++) {
			if (Character.isDigit(p.charAt(i))) {
			  number *= 10; number += p.charAt(i)-'0';
			  if (number > maxNumber)
				  maxNumber = number;
			}
			else number = 0;
		}
		return maxNumber;
	}
	public static void main(String[] args) {
		System.out.println(MaxNumbb(args[0]));
	}
}
