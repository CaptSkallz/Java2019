import java.util.*;

public class TreeSetDemo {

	public static void main(String[] args) {
     List<String> list = Arrays.asList(
		"jedna dva tri styri pat sest sedem osem devat".split(" "));

     TreeSet<String> sortedSet = new TreeSet<>(list);
	 System.out.println(sortedSet);	// [devat, dva, jedna, osem, pat, sedem, sest, styri, tri]
	 String low = sortedSet.first(), high = sortedSet.last(); // devat, tri
	 Iterator<String> it = sortedSet.iterator();
	 /* */
	 for (int i = 0; i <= 6; i++)
	   if (i == 3)
	     low = it.next();			// osem
	   else if (i == 6)
	     high = it.next();			// sest
	   else
	     it.next();
	     /* */
	  low = "osem";
	  high = "sest";
	  System.out.println(sortedSet.subSet(low, high));	// [osem, pat, sedem]
	  System.out.println(sortedSet.headSet(high)); // [devat, dva, jedna, osem, pat, sedem]
	  System.out.println(sortedSet.tailSet(low));  	// [osem, pat, sedem, sest, styri, tri]
	}	
}
