public class sameJednotky {
    public static int vrat(int n) {
        int hladane = (int)Math.pow(10 , n);
        int predch = 0;
        int akt = 0;
        int i = 0;
        while (true) {
            predch = akt;
            akt = (int)Math.pow(2, i) - 1;
            if (akt > hladane) break;
            i++;
        }
        return predch;
    }
    public static void main(String[] args) {
        System.out.println(vrat(3));
    }
}
