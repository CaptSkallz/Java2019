/**
 * binarne hodinky
 * @author PB
 *
 */
import java.util.*;		// pouzivam triedu Calendar

public class BinWatch2 {

  public static void main(String[] args) {
	  int second = 0;
	  for(;;) {												// loop forever
	    Calendar calendar = new GregorianCalendar();		// zistenie aktualneho casu
        int hodina = calendar.get(Calendar.HOUR_OF_DAY);	// hodina vo formate 0..24
	    int minuta = calendar.get(Calendar.MINUTE);			// minuta
	    int sekunda = calendar.get(Calendar.SECOND);		// sekunda
	    if (second != sekunda) {							// kedze nevieme pouzit Timer/Thread
	    	System.out.println(Integer.toBinaryString(hodina));
	    	System.out.println(Integer.toBinaryString(minuta));
	    	System.out.println(Integer.toBinaryString(sekunda));
	    	System.out.println("--------------------");	    	
	    	second = sekunda;								// urobime takyto CPU killer hack
	    }
	  }
  }
}


