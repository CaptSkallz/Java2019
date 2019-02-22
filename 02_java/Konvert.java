/**
 * priklady na konverziu ciselnych typov
 * @author PB
 */
public class Konvert {
  public static void main(String[] args) {	

	 short s = 300;
	 byte b;
	 b = (byte) s;
	 System.out.println("s = " + s + ", b = " + b);	// s = 300, b = 44
	 b = (byte) 255;
	 System.out.println("b = " + b);			// b = -1

	 byte bb = 126;
	 System.out.println(" bb = " + bb);			// bb = 126
	 bb += 3;
	 System.out.println("bb = " + bb);			// bb = -127
	 bb = -126;
	 bb += -5;
	 System.out.println("bb = " + bb);			// bb = 125
	  }
}
