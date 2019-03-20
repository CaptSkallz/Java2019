public class Pole3D {
	private Hruska[][][] mojePole;

	public Pole3D(Hruska[][][] tvojePole) {
		//mojePole = tvojePole;
		mojePole = tvojePole.clone();
		for (int i = 0; i < tvojePole.length; i++) {
			mojePole[i] = tvojePole[i].clone();
			for (int j = 0; j < tvojePole[i].length; j++) {
				mojePole[i][j] = tvojePole[i][j].clone();
				for (int k = 0; k < tvojePole[i][j].length; k++) {
					//mojePole[i][j][k] = (Hruska)(tvojePole[i][j][k].clone());
					mojePole[i][j][k] = (Hruska)(tvojePole[i][j][k].copy());
				}
			}
		}
	}

}
