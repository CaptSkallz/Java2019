import java.util.Arrays;

public class Matica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Matica (new double[][]{{-2, 3,-1}, {5, -1, 4}, {4, -8, 2}}));
		Matica p = new Matica (new double[][]{{-2, 3,-1}, {5, -1, 4}, {4, -8, 2}}); 
		System.out.println(p.plus(p));
		System.out.println(p);
	}
	private double[][] pole;
	
	public Matica(double[][] m){
		pole = m;
		
	}
	
	public Matica(int r, int s) {
		pole = new double[r][s];
		
	}
	public Matica(int size) {
		this(size,size);
		for (int i=0;i<size;i++) {				
			pole[i][i]=1;			
		}
	}
	
	
	
	public String toString() {
		StringBuffer vysl = new StringBuffer();
		for (int i = 0; i< pole.length;i++) {
			vysl.append( Arrays.toString(pole[i])+'\n');
		}
		return vysl.toString();
	}
	
	public int getRiadky() {
		return pole.length;
		
	}
	
	public int getStlpce() {
		return pole[0].length;		
	}
	
	public Matica plus(Matica m) {
		if ((getRiadky() != m.getRiadky()) || (getStlpce() != m.getStlpce())) {
			return null;
		}
		
		Matica m3 = new Matica(getRiadky(), getStlpce());
		for (int i = 0; i < getRiadky(); i++) {
			for(int j = 0; j < getStlpce(); j++) {
				m3.pole[i][j] = pole[i][j] + m.pole[i][j];		
			}
		}
		
		return m3;
		
		
		
		
		
	}

}
