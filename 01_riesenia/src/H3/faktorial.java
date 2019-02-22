public class faktorial {
    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return factorial(n-1) * n;
    }

    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        }

        return fib(n-1) + fib(n-2);
    }

    public static long fib2(int n) {
        if (n <= 1) {
            return 1;
        }

        int a = 1, b = 1;
        int c = 0;

        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i + "! = " + factorial(i));
//        }
        System.out.println(fib(5));
        System.out.println(fib2(5));
        System.out.println(fib(15));
        System.out.println(fib2(15));
    }

}
