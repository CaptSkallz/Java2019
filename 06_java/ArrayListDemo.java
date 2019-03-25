import java.util.*;
public class ArrayListDemo {
  public static void main(String[] args) {
    String[] p = {"a","b","c","d"};
    ArrayList <String> s = new ArrayList<String>();
   
    for (String a : p)
      s.add(a);
    for (Iterator<String> it = s.iterator();it.hasNext(); )
  	  System.out.print(it.next());
    System.out.println();

	s.set(1,"99");	s.remove(2);

	for (ListIterator<String> it = 				
	     s.listIterator(s.size());it.hasPrevious(); )
  	  System.out.print(it.previous());
    System.out.println();
	HashSet<String> hs = new HashSet<String>();
	hs.add("A");	hs.add("B");
	s.addAll(2,hs);
	System.out.println(s);
  }
}
