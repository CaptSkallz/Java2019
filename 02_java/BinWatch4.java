/**
 * binarne hodinky
 * @author PB
 */
import java.util.*;		// pouzivam triedu Calendar

public class BinWatch4 {
	
  static void riadok(int h1, int h2, int m1, int m2, int s1, int s2) {
	  if (h1+h2+m1+m2+s1+s2 == 0)
		  return;
	  riadok(h1/2, h2/2, m1/2, m2/2, s1/2, s2/2);
	  System.out.println(""+
			  (h1&1) +
			  (h2&1) + " " +
			  (m1&1) +
			  (m2&1) + " " +
			  (s1&1) +
			  (s2&1));
  }
  public static void main(String[] args) {
	  int second = 0;
	  for(;;) {
	    Calendar calendar = new GregorianCalendar();
        int hodina = calendar.get(Calendar.HOUR_OF_DAY);
	    int minuta = calendar.get(Calendar.MINUTE);
	    int sekunda = calendar.get(Calendar.SECOND);
	    if (second != sekunda) {
	    	riadok(hodina/10,hodina%10,minuta/10,minuta%10,sekunda/10, sekunda%10);
	    	System.out.println("--------------------");	    	
	    	second = sekunda;
	    }
	  }
  }
}


