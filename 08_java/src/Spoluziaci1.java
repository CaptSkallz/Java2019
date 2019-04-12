/** exercise 5 - playing with the class DataForYou */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

/** This class only holds the static main method that works with arrays from DataForYou class.
 * @author Pavel
 *
 */
public class Spoluziaci1 {

	/** holds string that is sorted in Collections based on its length */
	private static class StringWithLength implements Comparable<StringWithLength>
	{
		public String elem;
		public int length;
		public StringWithLength(String s)
		{
			elem = s;
			length = s.length();
		}
		public int compareTo(StringWithLength other)
		{
			if (length > other.length) return 1;
			else if (length < other.length) return -1;
			else return 0;
		}
	}
	
	/**
	 * @param args - program takes no arguments
	 */
	public static void main(String[] args) {
		/*
		List<String> alls = new ArrayList<String>();
		BufferedReader fr;
		try {
			fr = new BufferedReader(new FileReader(
					//"DataForYou1.txt"
					"DataForYou2.txt"
					));
			for (;;) {
				String line = fr.readLine();
				if (line == null)
					break;
				String[] ss = line.trim().split(" ");
				List<String> names1 = (List<String>)Arrays.asList(ss);
				alls.addAll(names1);
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		String[] names = alls.toArray(new String[0]);
		
		*/
		
		List<String> alls = new ArrayList<String>();
		Scanner fr;
			try {
				fr = new Scanner(
						new FileInputStream( new File(
						//"DataForYou1.txt"
						"DataForYou2.txt"
								)));
			for (;;) {
				if (fr.hasNextLine())  {
					String line = fr.nextLine();
					String[] ss = line.trim().split(" ");
					List<String> names1 = (List<String>)Arrays.asList(ss);
					alls.addAll(names1);
				} else break;
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String[] names = alls.toArray(new String[0]);
		
		
		//String[] names = DataForYou.spoluziaci;     // local reference to the names array
		//String[] names = DataForYou.spoluziaciCelejSkoly; 
		
		// put all names to a HashMap as keys, the values will be the
		// frequencies of occurrence - we will keep incrementing occurrences while
		// inserting the values into the HashMap
		
		// create new HashMap
		HashMap<String,Integer> n = new HashMap<String,Integer>();
		// remember the most frequent name in this string
		String mostFrequent = "no names in the list";
		// frequency of the most frequent name
		int max = 0;
		// iterate through the whole names array and insert each to Hashtable
		for (int i = 0; i < names.length; i++)
		{
			Integer count = n.get(names[i]);  // retrieve it
			if (count == null) count = new Integer(0);
			n.put(names[i], new Integer(count + 1));  // increment count
			// update the most frequent
			if (count > max) 
			{
				mostFrequent = names[i];
				max = count + 1;
			}
		}		
		// we will use TreeSet to sort the names alphabetically 
		TreeSet<String> s = new TreeSet<String>(n.keySet());
		// and we will use PriorityQueue to sort the names based on their length
		PriorityQueue<StringWithLength> q = new PriorityQueue<StringWithLength>(s.size());
		// print the size of the set - i.e. number of different names
		System.out.println("*** Number of different names: " +s.size());
		// print the set in alphabetical order (it is naturally ordered)
		System.out.println("*** Alphabetical list:");
		for (Iterator<String> j = s.iterator(); j.hasNext(); )
		{
			String nm = j.next();
			System.out.println(nm);
			// also keep adding all names to the priority queue, where the names
			// are sorted based on their length
			q.add(new StringWithLength(nm));
		}
		// print list of names sorted based on their length
		System.out.println("*** Names ordered based on their length:");
		while (!q.isEmpty()) System.out.println(q.remove().elem);
		// finally print the most frequent name as determined above
		System.out.println("*** The most frequent name: " + mostFrequent);
	}
}
