/**
 * binarne hodinky
 * @author PB
 */
import java.util.*;		// pouzivam triedu Calendar

public class BinWatch3 {
	
  static void riadok(int i, int h1, int h2, int m1, int m2, int s1, int s2) {
	  System.out.println(""+
			  ((h1 >> i)&1) +
			  ((h2 >> i)&1) + " " +
			  ((m1 >> i)&1) +
			  ((m2 >> i)&1) + " " +
			  ((s1 >> i)&1) +
			  ((s2 >> i)&1));
  }

  public static void main(String[] args) {
	  int second = 0;
	  for(;;) {
	    Calendar calendar = new GregorianCalendar();
        int hodina = calendar.get(Calendar.HOUR_OF_DAY);
	    int minuta = calendar.get(Calendar.MINUTE);
	    int sekunda = calendar.get(Calendar.SECOND);
	    if (second != sekunda) {
	    	for(int i=0; i<4; i++)
	    	  riadok(i,hodina/10,hodina%10,minuta/10,minuta%10,sekunda/10, sekunda%10);
	    	System.out.println("--------------------");	    	
	    	second = sekunda;
	    }
	  }
  }
}