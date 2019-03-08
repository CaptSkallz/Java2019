import java.util.Arrays;

public class Matica {
	double arr[][];
	public Matica(double[][] m) {
		//this.arr = m; zle riesenie
		arr=new double [m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				arr[i][j]=m[i][j];
			}
		}
		
	}

	@Override
	public String toString() {
		//return "Matica [arr=" + Arrays.toString(arr) + "]";
		String result = "";
		result += "{";
		for (int i = 0; i < arr.length; i++) {
			result += "{";
			for (int j = 0; j < arr[i].length; j++) {
				result += arr[i][j] + " ";
				
			}
			result += "}";
		}
		result += "}";
		return result;
	}
	
	
	public Matica(int size) {
		//this.arr = m; zle riesenie
		arr=new double [size][size];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j]=(i==j)?1:0;
			}
		}
		
	}
	
	public Matica plus(Matica m) {
		double[][] newarr = new double[m.arr.length][m.arr[0].length];
		for (int i = 0; i < newarr.length; i++) {
			for (int j = 0; j < newarr[i].length; j++) {
				newarr[i][j] = arr[i][j] + m.arr[i][j];
			}
		}
		return new Matica(newarr);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matica m = new Matica(new double[][] {{1,2},{3,4}});
		Matica m2 = new Matica(new double[][] {{5,6},{7,8}});
		System.out.println(m);
		Matica arr1 = new Matica(4);
		System.out.println(arr1);
		System.out.println(m.plus(m2));
	}

}
