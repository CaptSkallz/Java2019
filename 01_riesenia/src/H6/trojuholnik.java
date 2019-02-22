
public class trojuholnik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		troj(15);

	}
	public static void troj(int n) {
		final int max = n;
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < max-i; j++) {
				System.out.print(' ');
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}
}
