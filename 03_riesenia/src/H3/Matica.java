public class Matica {
    double[][] matica;

    public Matica(double[][] m) {
        matica = new double[m.length][];
        for (int r = 0; r < m.length; r++) {
            matica[r] = new double[m[r].length];
            for (int s = 0; s < m[r].length; s++) {
                matica[r][s] = m[r][s];
            }
        }
    }

    public Matica(int r, int s) {
        matica = new double[r][s];
    }

    public Matica(int size) {
        this(size, size);

        for (int r = 0; r < matica.length; r++) {
            matica[r][r] = 1;
        }

    }
    public Matica krat(Matica m){
        double[][] result = new double[matica.length][m.matica[0].length];
        for(int i = 0;i < result.length;i++){
            for(int j = 0; j < result[i].length;j++){
                for(int k = 0; k < this.matica[0].length;k++){
                    result[i][j] += matica[i][k]*m.matica[k][j];
                }
            }

        }
        Matica res = new Matica(result);
        return res;
    }



    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int r = 0; r < matica.length; r++) {
            for (int s = 0; s < matica[r].length; s++) {
                builder.append(matica[r][s]);
                builder.append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
