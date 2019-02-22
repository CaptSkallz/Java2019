/**
 * binarne hodinky
 * @author PB
 *
 */
import java.util.*;		// pouzivam triedu Calendar

public class BinWatch1 {

  public static void main(String[] args) {
	  Calendar calendar = new GregorianCalendar();
      int hodina = calendar.get(Calendar.HOUR_OF_DAY);
	  int minuta = calendar.get(Calendar.MINUTE);
	  int sekunda = calendar.get(Calendar.SECOND);
	  System.out.println(Integer.toBinaryString(hodina));
	  System.out.println(Integer.toBinaryString(minuta));
	  System.out.println(Integer.toBinaryString(sekunda));
  }
}
