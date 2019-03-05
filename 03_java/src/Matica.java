public class Matica {
	public Matica(double[][] m) {
	}
	public Matica(int size) {
	}
	public Matica plus(Matica m) {
	  return null;
	}
	public Matica krat(Matica m) {
		return null;
	}
	public boolean idempotetna() {
		return false;
	}
	public Matica transponuje() {
		return null;
	}
	public double determinant() {
		return 0;
	}
	public static void main(String[] args) {
	    	System.out.println(new Matica (new double[][]{{-2, 3,-1}, {5, -1, 4}, {4, -8, 2}}).determinant());
    		System.out.println(new Matica (new double[][]{{-2, 3,-1}, {-2, 3,-1}, {5, -1, 4}, {4, -8, 2}}).determinant());
	}
}
