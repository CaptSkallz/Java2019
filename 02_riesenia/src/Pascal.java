
public class Pascal {
	public static int[][] triangle(int N) {
		int[][] vysl = new int[N][];
		for (int i = 0; i< N; i++) {
			vysl[i] = new int[i+1];
			if (i == 0)
				vysl[0][0] = 1;
			else {
				for(int j = 0; j <= i; j++) {
					if (j == 0 || j == i)
						vysl[i][0] = 1;
					else
						vysl[i][j] = vysl[i-1][j-1] + vysl[i-1][j];
				}
			}
		}
		return vysl;
	}
	public static void main(String[] args) {
		int N = 10;
		int[][] t = triangle(N); 
		for (int i = 0; i< N; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print(t[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
