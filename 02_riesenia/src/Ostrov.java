/**
 * velkost ostrova v stvorcovej matici
 * V obdåžnikovej matici int[][] m sa nachádzajú kladné èísla a nuly. 
 * Nájdite najväèšiu súvislú oblas tvorenú kladnými èíslami, prièom políèko [i,j] susedí s 
 * najviac štyrmi susednými políèkami: [i,j+1], [i,j-1], [i+1,j], [i-1,j] (ak sú v matici). 
 * Hint: k¾úèom može by rekurzívna procedúra, ktorá prelieza maticu z políèka 
 * [i,j] na [i,j+1], [i,j-1], [i+1,j], [i-1,j] (sme tam ešte neboli). 
 * Hint: Môžete použi súbor (ostrov.txt)  
 * @author PB
 */

public class Ostrov {
	static int[][] m = {
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,2,0,2,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0 },
		{ 0,0,0,0,1,2,2,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 },
		{ 0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1 },
		{ 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 }
	};
	/**
	 * rekurzivna procedura, ktora ak [i,j] ukazuje do pola, tak si toto policko
	 * oznaci a siri sa na vsetky susedne polia. Na pole, kde uz bola, sa nesiri.
	 */
	public static int velkostOstrova(int i, int j) {
	  if (i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] > 0) {	
	    m[i][j] = 0;
	    return 1+velkostOstrova(i+1,j)+velkostOstrova(i-1,j)+velkostOstrova(i,j+1)+velkostOstrova(i,j-1);
	  } else
		  return 0;
	}
	public static void main(String[] args) {
		int max = 0;
		for(int i=0; i<m.length; i++)
			for(int j=0; j<m[i].length; j++)
				if (m[i][j] > 0) {
					int velkost = velkostOstrova(i,j);
					if (max < velkost)
						max = velkost;
				}
		System.out.println(max);
	}
}
