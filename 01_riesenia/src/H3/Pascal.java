public class Pascal {
    public static long najvacsie(int n) {
        int index = n / 2;
        return faktorial.factorial(n) / (faktorial.factorial(n - index) * faktorial.factorial(index));
    }
    public static void main(String[] args) {
        System.out.println(najvacsie(4));
    }
}
