/**
 * lexikograficke triedenie mien, pomocou boblesort :-)
 * Dve rovnako dlhé polia String[] meno, a String[] priezvisko obsahujú menoslov triedy tak, 
 * že meno[i] a priezvisko[i] patria tej istej osobe. Utrieïte tento menoslov lexikograficky. 
 * Hint: upravte nejaký algoritmus triedenia pre vaše potreby. Môžete poiži� súbor (trieda.txt)
 * @author PB
 */
public class Class {
		  static String studenti[][] = {
				  // sem si doplnte subor string konstant zo zadania
				  };
		  /**
		   * hlavny program obsahuje buble sort z prednasky, jemne upraveny
		   */
		  public static void main(String[] args) {
			  int MAX = studenti.length;
			  for (int i = 0; i < MAX ; i++) {
				     for (int j = MAX-1; j>i ; j--) {
				        if ((studenti[j-1][1].compareTo(studenti[j][1])>0) || 
				        		(studenti[j-1][1].equals(studenti[j][1]) && studenti[j-1][0].compareTo(studenti[j][0])>0)) {
				        	// j-1 musi ist z j
				        	// tak ich vymen
				           String[] temp = studenti[j];
				           studenti[j] = studenti[j-1];
				           studenti[j-1] = temp;
				         }
				     }
				  }
			  for (int i = 0; i < MAX ; i++) {
				  System.out.println(studenti[i][1] + " " + studenti[i][0]);
			  }
		  }
}
