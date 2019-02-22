/**
 * binarne hodinky
 * @author PB
 */
import java.util.*;		// pouzivam triedu Calendar

public class BinWatch5 {
	
  static void riadok(int h, int m, int s) {
	  if (h+m+s == 0)
		  return;
	  riadok(h/2, m/2, s/2);
	  System.out.println(""+
			  (h&1) + " " +
			  (m&1) + " " +
			  (s&1));
  }
  public static void main(String[] args) {
	  int second = 0;
	  for(;;) {
	    Calendar calendar = new GregorianCalendar();
        int hodina = calendar.get(Calendar.HOUR);
	    int minuta = calendar.get(Calendar.MINUTE);
	    int sekunda = calendar.get(Calendar.SECOND);
	    if (second != sekunda) {
	    	riadok(hodina,minuta,sekunda);
	    	System.out.println("--------------------");	    	
	    	second = sekunda;
	    }
	  }
  }
}