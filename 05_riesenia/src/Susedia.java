/** excercise 5, playing with neighboring countries */

import java.util.*;

/** Contains a single method that answers all the questions of the exercise.
 * @author Pavel
 */
public class Susedia {

	/** Testing program
	 * @param args - program takes no arguments
	 */
	public static void main(String[] args) 
	{
		// load the input data
		String [][] neighbors = DataPreVas.susedia;
		//String [][] neighbors = DataPreVas.susediaCelejGalaxie;
		
		// sort the names in a TreeSet
		TreeSet<String> s = new TreeSet<String>();
		
		// remember the number of neighbors in HashMap
		HashMap<String,Integer> numNeighbors = new HashMap<String,Integer>();
		int max = 0;
		String maxCountry = "no countries in the list";
		
		// build the TreeSet and HashMap
		for (int i = 0; i < neighbors.length; i++)
		{
			// add both neighbors of this border to set of c ountries
			s.add(neighbors[i][0]);
			s.add(neighbors[i][1]);

			// count this border for both countries
			Integer count = numNeighbors.get(neighbors[i][0]);  	// retrieve the first
			if (count == null) count = new Integer(0);				// reset if not known yet
			numNeighbors.put(neighbors[i][0], new Integer(count + 1));  // and increment count
			if (count > max)
			{
				max = count + 1;				// update most neighboring, if needed
				maxCountry = neighbors[i][0];
			}

			count = numNeighbors.get(neighbors[i][1]);  	// retrieve the second
			if (count == null) count = new Integer(0);		// reset if not known yet
			numNeighbors.put(neighbors[i][1], new Integer(count + 1));  // and increment count
			if (count > max)
			{
				max = count + 1;				// update most neighboring, if needed
				maxCountry = neighbors[i][1];
			}
		}
		System.out.println("Map contains " + s.size() + " countries.");
		
		// print them in alphabetical order
		for (Iterator<String> j = s.iterator(); j.hasNext(); )
		{
			String nm = j.next();
			System.out.println(nm);
			
		}
		
		System.out.println("Maximum number of neighbors (" + max + ") has " + maxCountry);
		
		// Vector of continents
		Vector<TreeSet<String>> continents = new Vector<TreeSet<String>>();
		// first, each country forms its own continent
		for (Iterator<String> j = s.iterator(); j.hasNext(); )
		{
			String nm = j.next();
			TreeSet<String> newContinent = new TreeSet<String>();
			newContinent.add(nm);
			continents.add(newContinent);
		}
		
		// now process all borders and merge the continents of neighboring countries
		for (int i = 0; i < s.size(); i++)
		{
			int c1 = 0, c2 = 0;
			
			// find the continents for this particular border
			for (int j = 0; j < continents.size(); j++)
			{
				TreeSet<String> c = continents.elementAt(j); 
				if (c.contains(neighbors[i][0])) c1 = j;
				if (c.contains(neighbors[i][1])) c2 = j;
			}
			// if they are already part of the same continent, nothing to do
			if (c1 == c2) continue;
			// otherwise, merge both continents to c1, and drop the original c2
			continents.elementAt(c1).addAll(continents.elementAt(c2));
			continents.removeElementAt(c2);
		}
		System.out.println("The map contains " + continents.size() + " continents.");
		System.out.println("The largest continent:");
		max = 0; 
		TreeSet<String> largest = null;
		// find the continent with largest number of countries
		for (int i = 0; i < continents.size(); i++)
			if (max < continents.elementAt(i).size())
			{
				largest = continents.elementAt(i);
				max = largest.size();
			}
		// and print the countries it contains
		for (Iterator<String> j = largest.iterator(); j.hasNext(); )
			System.out.print(j.next() + ", ");
		System.out.println("done.");
	}
}
