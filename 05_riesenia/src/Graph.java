/** exercise 5, playing with a graph */

import java.util.*;

/** This class implements a graph (internally stored as lists of incident verteces for
 *  each vertex. The class contains methods for adding, and removing edges, testing
 *  whether the graph is connected and for determining the distance of two verteces.
 * @author Pavel
 */
public class Graph {

	/** Vector v will hold Vectors of integers - vertex numbers */
	Vector<Vector<Integer>> v;
	
	/** Construct a new graph from array representation (the array contains list
	 *  of incident verteces for all verteces 
	 * @param N - two-dimensional array, N[i] is the list of verteces incident with i
	 */
	public Graph(int[][] N)
	{
		// create internal representation
		v = new Vector<Vector<Integer>>(N.length);
		// copy all data
		for (int i = 0; i < N.length; i++)
		{
			Vector<Integer> w = new Vector<Integer>(N[i].length);
			for (int j = 0; j < N[i].length; j++)
			  w.add(N[i][j]);
			v.add(w);	
		}	
	}
	
	/** Predicate determines, if the specified edge is contained 
	 * in the graph - in that case it returns true. 
	 * @param a - first vertex
	 * @param b - second vertex */
	public boolean hasEdge(int a, int b)
	{
		return v.elementAt(a).contains(new Integer(b));	
	}
	
	/** Removes the specified edge from the graph. 
	 * @return if the edge was present, returns true, otherwise returns false 
	 * @param a - first vertex
	 * @param b - second vertex */
	boolean removeEdge(int a, int b)
	{
		return v.elementAt(a).remove(new Integer(b));
	}
	
	/** Ads a new edge to the graph 
	 * @return if the edge was already present, returns true, otherwise returns false 
	 * @param a - first vertex
	 * @param b - second vertex */
	boolean addEdge(int a, int b)
	{
		// Vector has opposite semantics of add() return value, therfore the !
		return !v.elementAt(a).add(new Integer(b));
	}
	
	/** Returns the number of incident edges for the specified vertex 
	 * 
	 * @param a - vertex to query
	 * @return - degree of the vertex a
	 */
	int getDegree(int a)
	{
		return v.elementAt(a).size();
	}
	
	/** Determine whether the graph is connected.
	 * 
	 * @return true, if the graph contains only a single connected component, otherwise false.
	 */
	boolean isConnected() 
	{
		// c will contain vector of components that will be merged eventually
		Vector<TreeSet<Integer>> c = new Vector<TreeSet<Integer>>();
		// create isolated components, each containing one vertex
		for (int i = 0; i < v.size(); i++) 
		{
			TreeSet<Integer> comp = new TreeSet<Integer>();
			comp.add(new Integer(i));
			c.add(comp);
		}
		
		// pass through all edges, and merge the corresponding components
		for (int i = 0; i < v.size(); i++)
		{
			// now process all incident vertexes for vertex i, and merge the components
			for (int j = 0; j < v.elementAt(i).size(); j++)
			{
				int c1 = 0, c2 = 0;
				
				// find the components for i and the incident vertex 
				for (int k = 0; k < c.size(); k++)
				{
					TreeSet<Integer> comp = c.elementAt(k); 
					if (comp.contains(i)) c1 = k;
					if (comp.contains(v.elementAt(i).elementAt(j))) c2 = k;
				}
				// if they are already part of the same component, nothing to do
				if (c1 == c2) continue;
				// otherwise, merge both components to c1, and drop the original c2
				c.elementAt(c1).addAll(c.elementAt(c2));
				c.removeElementAt(c2);
			}
		}
		
		// if only a single component remains, the graph is connected
		if (c.size() == 1) return true;
		else return false;
	}
	
	/** Compute the edge-distance from vertex a to vertex b.
	 *  (this is not the fastest algorithm, but easy to implement)
	 * 
	 * @param a - starting vertex
	 * @param b - terminating vertex
	 * @return number of edges that need to be traversed to reach vertex b from vertex a
	 */
	int distance(int a, int b)
	{
		// breadth-first search: we will eliminate the used edges, therfore we need a copy
		Vector<Vector<Integer>> w = (Vector<Vector<Integer>>) v.clone();
		// will queue the discovered verteces to a LinkedList
		LinkedList<Integer> reached = new LinkedList<Integer>();
		reached.add(a);
		// -1 indicates we have studies all vertexes in the distance d
		reached.add(-1);
		// we start in distance 0
		int d = 0;
		// propagate to bredth while it is possible
		while (!reached.isEmpty())
		{
			int e = reached.removeFirst();
			if (e == -1)   // -1 indicates we have investigated all in distance d, increase it 
			{
				reached.add(-1);
				d++;
				continue;
			}
			if (e == b) return d;				// target found
			reached.addAll(w.elementAt(e));		// add all neighbors to queue
			w.elementAt(e).clear();				// will not need these edges anymore
		}		
		// target vertex was not reached and queue is empty - return -1
		return -1;
	}
	
	/** Testing program.
	 * @param args - program takes no arguments
	 */
	public static void main(String[] args) 
	{
		Graph g = new Graph(DataPourVous.graph);
		System.out.println(g.hasEdge(1, 2));
		System.out.println(g.hasEdge(1, 6));
		System.out.println("Removing 1-6: " + g.removeEdge(1,6));
		System.out.println("Adding 1-6: " + g.addEdge(1,6));
		System.out.println("isConnected(): " + g.isConnected());
		System.out.println("Adding 1-5: " + g.addEdge(1,5));
		System.out.println("isConnected(): " + g.isConnected());
		System.out.println("distance(0,5): " + g.distance(0, 2));
	}

}
