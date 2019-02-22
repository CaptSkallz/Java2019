public class Hviezdicky {
    public static void hviezdicky(int n)
    {
        int medzery = n - 1;
        int hviezdicky = 1;

        for (int i = 0; i < n; i++) {
            String riadok = "";
            for (int j = 0; j < medzery; j++) {
                riadok += " ";
            }
            for (int j = 0; j < hviezdicky; j++) {
                riadok += "*";
            }
            medzery--;
            hviezdicky += 2;
            System.out.println(riadok);
        }

    }

    public static void main(String[] args) {
        hviezdicky(5);
    }
}
