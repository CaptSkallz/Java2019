public class PocetJednotiek {
    public static int pocet(int n){
        int poc = 0;
        while (n != 0){
            if (n % 2 == 1){
                poc++;
            }
            n /= 2;
        }
        return poc;
    }

    public static void main(String[] args) {
        System.out.println(pocet(10));
    }
}
