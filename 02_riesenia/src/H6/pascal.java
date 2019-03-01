public class pascal {
	public static int[][] triangle(int N){
		int[][] vysl = new int [N][];
		for(int i = 0; i < N; i++) {
			vysl[i] = new int [i+1]; 
			for(int j = 0; j < i+1; j++) {
				if(j == 0 || j == i) {
					vysl[i][j] = 1;
					continue;
				}
				vysl[i][j] = vysl[i-1][j-1] + vysl[i-1][j];
			}
		}
		return vysl;
	}
	
	public static int fib(int N) {
		int[] f = new int[N];
		for (int i = 0; i < f.length; i++) {
			if (i == 0 || i == 1) {
				f[i] = 1;
			}
			else {
				f[i] = f[i-1] + f[i/2+1];
			}
		}
		return f[N-1];
	}
	
	public static void main(String[] args) {
		System.out.println(fib(50));
	}

}
