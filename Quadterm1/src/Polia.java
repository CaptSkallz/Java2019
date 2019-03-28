/**
 * @author DasaK
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polia {

    public static double[] klzavyPriemer(double[] pole, int N) {
    	if (pole == null) return null;
    	double[] out = new double[pole.length - N + 1];
    	for (int i = 0; i < pole.length - N + 1; i++) {
    		double sum = 0.0;
    		for (int j = 0; j < N; j++) {
    			sum += pole[i + j];
    		}
			out[i] = sum / N;
		}
    	return out;
        //return null;
    }

    public static int[] doRiadku(int[][] matica) {
    	if (matica == null) return null;
    	List<Integer> out = new ArrayList<>();
    	for (int[] r: matica) {
    		if (r != null) {
    			for (int x: r) {
    				out.add(x);
    			}
    		}
    	}
    	int[] out2 = new int[out.size()];
    	for (int i = 0; i < out.size(); i++) {
			out2[i] = out.get(i);
		}
        return out2;
    }

    public static int[][] zmenTvar(int[][] matica, int r, int s) {
    	int[] pom = doRiadku(matica);
    	int index = 0;
    	int[][] out = new int[r][s];
    	for (int i = 0; i < r; i++) {
			for (int j = 0; j < s; j++) {
				out[i][j] = pom[index];
				index++;
			}
		}
        return out;
    }

    public static int[][] vlozStlpec(int[][] matica, int stlpecIdx, int[] stlpec) {
    	int[][] out = new int[matica.length][matica[0].length + 1];
    	for (int i = 0; i < out.length; i++) {
			for (int j = 0; j < out[0].length; j++) {
				if (j < stlpecIdx) {
					out[i][j] = matica[i][j];
					continue;
				}
				if (j == stlpecIdx) {
					out[i][j] = stlpec[i];
					continue;
				}
				out[i][j] = matica[i][j - 1];
			}
		}
        return out;
        //return null;
    }

    public static void main(String[] args) {
        double[] priemer = klzavyPriemer(new double[]{1, 4, 5}, 2);
        System.out.println(Arrays.toString((priemer))); // [2.5, 4.5]
        
        double[] priemer2 = klzavyPriemer(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        System.out.println(Arrays.toString((priemer2)));

        int[] vRiadku = doRiadku(new int[][]{{1, 2}, null, {}, {0}});
        System.out.println(Arrays.toString(vRiadku)); // [1, 2, 0]

        int[][] zmenena = zmenTvar(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}}, 3, 4);
        for (int[] riadok : zmenena) System.out.println(Arrays.toString(riadok)); // [1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]

        int[][] vlozenyStlpec = Polia.vlozStlpec(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1, new int[]{-1, -2, -3});
        for (int[] riadok : vlozenyStlpec) System.out.println(Arrays.toString(riadok)); // [1, -1, 2, 3], [4, -2, 5, 6], [7, -3, 8, 9]
    }
}
