import java.util.*;
public class T {
public static void main(String[] args) {
	  TreeMap<Integer,String> tm = new TreeMap<Integer,String>();
	  int i=0;
	  for(String a:args) tm.put(i++, a);
	  System.out.println(tm);
	}
}
