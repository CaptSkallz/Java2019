/**
 * hladanie tanku
 * V obdÂûnikovej matici char[][] vojna sa nach·dza s˙perov· konfigur·cia hry n·morn· bitka. 
 * Znak '.' predstavuje more, znak 'X' predstavuje Ëasù s˙perovej v˝zbroje. 
 * Zistite, Ëi m· eöte dvojkrÌûnik. Hint: moûete pouûiù s˙bor (vojna.txt). 
 * @author PB
 */
import java.util.*;
public class NamornaBitka {
	final static int tankSize = 5;
	static  char[][] tank = {
		{ '.','O','O','O','.'},
		{ 'O','O','X','O','O'},
		{ 'O','X','X','X','O'},
		{ 'O','O','O','O','O'}
	};
	static	char[][] vojna = {
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','X','.','.','.','X','.','.','.','.','X','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','X','X','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','X','.','.','X','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','X','.','X','X','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','X','.','.','X','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','X','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','X','X','X','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','X','.','.'},
			{ '.','.','.','.','.','.','X','X','.','.','.','.','.','.','.','X','X','X','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','X','.'},
			{ '.','.','.','.','.','.','.','.','.','.','X','X','X','.','.','.','X','X','.'},
			{ '.','.','.','.','X','.','.','.','.','.','.','.','.','.','.','.','.','X','.'},
			{ '.','.','.','X','X','X','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','X','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','X','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','X','X','X'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
			{ '.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}
		};
	/**
	 * hladaj tank vodorovne od pozicie [i,j] v orientacii dy 
	 */
    static boolean isTankXY(char[][] v, int i, int j, int dy) {
    	for (int k=0; k < tank.length; k++)
        	for (int l=0; l < tank[k].length; l++) {
        		if (tank[k][l] == 'X' && v[i+k][j+dy*l] != 'X')
        			return false;
    			if (tank[k][l] == 'O' && v[i+k][j+dy*l] != '.')
    				return false;
        	}
    	return true;
    }
    /**
     * hladaj tank zvisle od pozicie [i,j] v orientacii dx
     */
    static boolean isTankYX(char[][] v, int i, int j, int dx) {
    	for (int k=0; k < tank.length; k++)
        	for (int l=0; l < tank[k].length; l++) {
        		if (tank[k][l] == 'X' && v[i+l][j+dx*k] != 'X')
        			return false;
    			if (tank[k][l] == 'O' && v[i+l][j+dx*k] != '.')
    				return false;
        	}
    	return true;
    }
    /**
     * hladaj tank od pozicie [i,j]
     */
    static boolean isTank(char[][] v, int i, int j) {
    	return 
    	isTankXY(v, i, j, 1) || //  isTankXY(v, i, j, -1) || -- tank je symetricky podla osi x
    	isTankYX(v, i, j, -1) || isTankYX(v, i, j, 1); 
    }
    static void vypis(char[][] v) {
		for(int i=0; i<v.length; i++) {
			for(int j=0; j<v[i].length; j++)
				System.out.print((char)(v[i][j]));
			System.out.println();
		}
    }
	public static void main(String[] args) {
		char[][] vojnaSOkrajom = new char[vojna.length+tankSize+tankSize][vojna[0].length+tankSize+tankSize];
		
		// vynulovanie matice 
		for(int i=0; i<vojnaSOkrajom.length; i++)
			Arrays.fill(vojnaSOkrajom[i],'.');
		
		// prekopirovanie povodnej matice do vacsej matice s nulovym okrajom 
		for(int i=0; i<vojna.length; i++)
		    System.arraycopy(vojna[i], 0, vojnaSOkrajom[i+tankSize], tankSize, vojna[i].length);

		// kontrolny vypis
		vypis(vojnaSOkrajom);
		
		// hladanie tanku
		for(int i=0; i<vojna.length; i++)
			for(int j=0; j<vojna[i].length; j++)
				if (isTank(vojnaSOkrajom, i+tankSize, j+tankSize))
					System.out.println("Tank na pozicii: "+i+","+j);
	}
}
