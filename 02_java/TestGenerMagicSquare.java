import static org.junit.Assert.*;

import org.junit.Test;


public class TestGenerMagicSquare {

		  public static boolean isMagic(int[][] sq) {
		    if (!duplicates(sq)){
		      return false;      
		    }
		    int temp = 0, temp2 = 0, temp3 = 0, temp4 = 0, temp5 = 0;
		    for (int i = 0; i < sq.length; i++){
		      for (int j = 0; j< sq[i].length; j++){
		        if (i == 0) {
		          temp += sq[i][j];          
		        }
		        else {
		          temp2 += sq[i][j];
		        }
		        temp3 += sq[j][i];
		        if (i == j) {
		          temp4 += sq[i][j];
		        }
		        if (j == sq.length - 1 - i) {
		          temp5 += sq[i][j];
		        }
		      }
		      if (i > 0 && temp != temp2) {
		        return false;
		      }
		      if (temp != temp3) {
		        return false;
		      }
		      temp2 = 0;
		      temp3 = 0;
		    }
		    if (temp != temp4 || temp != temp5) {
		      return false;
		    }
		    
		    return true;
		  }
		  
		  public static boolean duplicates(int[][] sq){
		    int[] sq2 = new int[sq.length*sq.length];
		    int temp = 0;
		    for (int i = 0; i < sq.length; i++) {
		      for (int j = 0; j < sq[i].length; j++) {
		        sq2[temp] = sq[i][j];
		        temp++;
		      }
		    }
		    for (int i = 0; i < sq2.length; i++) {
		      for (int j = 0; j < sq2.length; j++) {
		        if (i!=j && sq2[i] == sq2[j]){
		          return false;          
		        }
		      }
		    }    
		    return true;    
		  }

		@Test
		public void test() {
          for(int i=3; i < 20; i+=2) {			
			int[][] p1 = MagicSquare.magic(i);
			System.out.println("Test "+i+"x"+i);
			if (isMagic(p1) == false) {
				printM(p1);
				fail("tuto maticu ste prehlasili za magicky stvorec a nie je");
			} else
				System.out.println("... ok");
          }
		}
		public static String printM(int[][] a) {
			String s ="";
			for(int[]r:a) {
				for(int e:r)
					s += e+", ";
				s += "\n";
			}
			return s;
		}		
}
