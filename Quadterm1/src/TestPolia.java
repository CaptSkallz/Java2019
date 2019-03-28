import static org.junit.Assert.*;

public class TestPolia {

    @org.junit.Test
    public void testKlzavyPriemer() {
        double[] vstup;
        double[] pozadovanyVystup;
        double[] skutocnyVystup;
        // ================================================
        vstup = new double[]{1, 4, 5};
        pozadovanyVystup = new double[]{2.5, 4.5};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 2);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
        // ================================================
        vstup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pozadovanyVystup = new double[]{3, 4, 5, 6, 7, 8};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 5);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
        // ================================================
        vstup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pozadovanyVystup = new double[]{1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 2);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
        // ================================================
        vstup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pozadovanyVystup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 1);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
        // ================================================
        vstup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pozadovanyVystup = new double[]{5, 6};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 9);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
        // ================================================
        vstup = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pozadovanyVystup = new double[]{5.5};
        skutocnyVystup = Polia.klzavyPriemer(vstup, 10);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup, 0.0001);
    }

    @org.junit.Test
    public void testDoRiadku() {
        int[][] vstup;
        int[] pozadovanyVystup;
        int[] skutocnyVystup;
        // ================================================
        vstup = new int[][]{{1, 2}, null, {}, {0}};
        pozadovanyVystup = new int[]{1, 2, 0};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = new int[][]{{1, 2}, {3, 4, 5}, null, {6, 7, 8, 9}, {}, {10}};
        pozadovanyVystup = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = new int[][]{};
        pozadovanyVystup = new int[]{};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = null;
        pozadovanyVystup = null;
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = new int[][]{{}};
        pozadovanyVystup = new int[]{};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = new int[][]{null, {0}};
        pozadovanyVystup = new int[]{0};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        vstup = new int[][]{{-1}, null, {-2, -3}, null, {-4, -5, -6}, {}, null};
        pozadovanyVystup = new int[]{-1, -2, -3, -4, -5, -6};
        skutocnyVystup = Polia.doRiadku(vstup);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
    }

    @org.junit.Test
    public void testZmenTvar() {
        int[][] matica = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}};
        int[][] pozadovanyVystup;
        int[][] skutocnyVystup;
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}};
        skutocnyVystup = Polia.zmenTvar(matica, 1, 12);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}};
        skutocnyVystup = Polia.zmenTvar(matica, 2, 6);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        skutocnyVystup = Polia.zmenTvar(matica, 3, 4);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        skutocnyVystup = Polia.zmenTvar(matica, 4, 3);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}};
        skutocnyVystup = Polia.zmenTvar(matica, 6, 2);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}};
        skutocnyVystup = Polia.zmenTvar(matica, 12, 1);
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
    }

    @org.junit.Test
    public void testVlozStlpec() {
        int[][] vstup = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] pozadovanyVystup;
        int[][] skutocnyVystup;
        // ================================================
        pozadovanyVystup = new int[][]{{-1, 1, 2, 3}, {-2, 4, 5, 6}, {-3, 7, 8, 9}};
        skutocnyVystup = Polia.vlozStlpec(vstup, 0, new int[]{-1, -2, -3});
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, -1, 2, 3}, {4, -2, 5, 6}, {7, -3, 8, 9}};
        skutocnyVystup = Polia.vlozStlpec(vstup, 1, new int[]{-1, -2, -3});
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, -1, 3}, {4, 5, -2, 6}, {7, 8, -3, 9}};
        skutocnyVystup = Polia.vlozStlpec(vstup, 2, new int[]{-1, -2, -3});
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
        // ================================================
        pozadovanyVystup = new int[][]{{1, 2, 3, -1}, {4, 5, 6, -2}, {7, 8, 9, -3}};
        skutocnyVystup = Polia.vlozStlpec(vstup, 3, new int[]{-1, -2, -3});
        assertArrayEquals(pozadovanyVystup, skutocnyVystup);
    }
}