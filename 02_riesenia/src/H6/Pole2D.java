public class Pole2D {

	public static int pocet(String[][] a) {
		int counter = 0;
		if (a == null) {
			return 0;
		}
			
			
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				for (int j = 0; j < a[i].length; j++) {

					if ("yes".equalsIgnoreCase(a[i][j]) || "no".equalsIgnoreCase(a[i][j])) {
						counter++;
					}

				}
			}
		}
		return counter;
	}

	public static void main(String[] args) {
		System.out.println(pocet(Polia.pole2D_3));

	}

}
