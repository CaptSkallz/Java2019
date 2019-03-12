/**
 * naivny backtracking pre Hru 15
 * @author PB
 */
import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
public class Hra15 {
	//int[][] gb = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,15,14,0}};  // toto sa neda riesit
	 int[][] gb = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,0}};
	
	public String toString() {
		String s = "";
		for(int[] row:gb) {
			for(int e:row)
				s+=e+" ";
			s+="\n";
		}
		return s;
	}
	Point findZero() {
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				if (gb[i][j] == 0)
					return new Point(i,j);
		return null;
	}
	public boolean up() {
		Point p = findZero();
		if (p.x == 0)
			return false;
		else {
			int tmp = gb[p.x][p.y]; gb[p.x][p.y] = gb[p.x-1][p.y]; gb[p.x-1][p.y] = tmp;
			return true;
		}				
	}
	public boolean down() {
		Point p = findZero();
		if (p.x == 3)
			return false;
		else {
			int tmp = gb[p.x][p.y]; gb[p.x][p.y] = gb[p.x+1][p.y]; gb[p.x+1][p.y] = tmp;
			return true;
		}				
	}
	public boolean left() {
		Point p = findZero();
		if (p.y == 0)
			return false;
		else {
			int tmp = gb[p.x][p.y]; gb[p.x][p.y] = gb[p.x][p.y-1]; gb[p.x][p.y-1] = tmp;
			return true;
		}				
	}
	public boolean right() {
		Point p = findZero();
		if (p.y == 3)
			return false;
		else {
			int tmp = gb[p.x][p.y]; gb[p.x][p.y] = gb[p.x][p.y+1]; gb[p.x][p.y+1] = tmp;
			return true;
		}				
	}
	boolean isFinal() {
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if (i==3 && j==3)
					return gb[3][3] == 0;
				if (gb[i][j]-1 != 4*i+j)
					return false;
			}
		return true;
	}
	public boolean findSolution(int depth, HashSet<String> visited) {
		 //System.out.println("visited="+visited+visited.size());
		 //System.out.println(visited.size());
			 if (depth == 0)
				 return false;
			 if (isFinal())
				 return true;
				 else if (!visited.contains(toString())) {
		 	     visited.add(toString());
				   if (left())
					   if (findSolution(depth-1,visited))
						   return true;
					   else
						   right();
				   if (right()) 
					   if (findSolution(depth-1,visited))
						   return true;
					   else
						   left();
				   if (up()) 
					   if (findSolution(depth-1,visited))
						   return true;
					   else
						   down();
				   if (down()) 
					   if (findSolution(depth-1,visited))
						   return true;
					   else
						   up();
		 		 }
		 	return false;
		  }		
		/**
		Hra 15 sa hrá na ploche 4x4 s 15timi oèíslovanımi kameòmi 1,2, 3, ... , 15. 
		Jedno políèko je vo¾né. Hra je o tom, e náhodnú poèiatoènú kombináciu máte usporiada do stavu 
		1,2,3, ..., 15 a v pravom dolnom rohu je vo¾né pole (budeme ho kódova ako 0). 
		Nie vdy sa Vám to podarí, šance sú tak 50:50. To preto, e pri korektnom posúvaní kameòov platí nasledujúci 
		invariant: 
		parita súètu inverzií a riadku, na ktorom sa nachádza vo¾né políèko, je stále rovnaká. 
		Inverzia v permutácii je, ak stojí väèšie èíslo pred menším, vo¾né políèko 0 neberieme pritom do 
		úvahy. Napríklad, ak máme kombináciu [[1,2,3,4],[5,6,7,8],[9,10,11,12],[15,14,13,0]], 
		t.j. permutáciu [1,2,3,4,5,6,7,8,9,10,11,12,15,14,13,0], 
		inverzie sú tri: 15-14, 15-13, 14-13. Èíslo riadku, na ktorom sa vo¾né políèko (0) nachádza je 4. 
		Take 3+4=7, t.j. parita súètu je nepárna. Tento invariant pre vıslednú konfiguraciu 
		[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,0]] je 0+4, t.j. pár. 
		Za predpokladu, e uveríte korektnosti tohoto invariantu, z toho vyplıva, e z prvej konfigurácie sa nedá 
		korektne prejs do druhej, teda hlavolam sa nedá vyrieši. 
		hra15 [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,0]] = True, 
		hra15 [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,0,15]] = True,
		hra15 [[1,2,3,4],[5,6,7,8],[9,10,0,12],[13,14,11,15]] = True, 
		hra15 [[1,2,3,4],[5,6,7,8],[9,12,0,10],[13,14,11,15]] = False.
		 */
	public boolean invariant() {
		Point p = findZero();
		int inverzia = p.x+1;
		System.out.println("riadok:"+inverzia);
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++) {
				if (gb[i][j] == 0)
					continue;
				for(int ii=i; ii<4; ii++)
					for(int jj=0; jj<4; jj++) {
						if (gb[ii][jj] == 0)
							continue;
						if (i == ii & jj <= j)
							continue;
						if (gb[i][j] > gb[ii][jj])
							inverzia++;
					}
			}
		System.out.println("invanriant:"+inverzia);
		return inverzia % 2 == 0;
	}
	public static void main(String[] args) {
		Hra15 b = new Hra15();
		Random rnd = new Random();
		for(int i=0; i<30; i++)	// shake it
			switch(rnd.nextInt(4)) {
				case 0: b.left(); //System.out.println("left\n"+b); 
				break;
				case 1: b.right(); //System.out.println("right\n"+b);
				break;
				case 2: b.up(); //System.out.println("up\n"+b); 
				break;
				case 3: b.down(); //System.out.println("down\n"+b); 
				break;
			}
		System.out.println(b);
		System.out.println(((b.invariant())?"da":"neda")+" sa to riesit");
		int steps = 0;		// solve it
		do {
			System.out.println("false\nsteps:"+steps);
			steps++;			// findSolution up to n steps
		} while (!(b.findSolution(steps,new HashSet<String>())));
		System.out.println("true");
	}
}
