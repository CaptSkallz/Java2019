package LG;
import java.util.ArrayList;
import java.util.List;

public class Primes {
    private static boolean isPrime(long n) {
    	if (n == 1) return false;
        for (long i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static List<Long> primes(long n) {
        List<Long> res = new ArrayList<>();
        for (long i = 2; res.size() != n; i++) {
            if (isPrime(i))
                res.add(i);
        }
        return res;
    }

    private static void findN(long m) {
        List<Long> primes = primes(m);
        long product = 1;
        for (int i = 0; i < primes.size(); i++) {
            product *= primes.get(i);
            if (!isPrime(product + 1)) {
                System.out.println("N: " + (i + 1) + ", Product:" + product);
                return;
            }
        }
        findN(m * 2);
    }

    private static void findN() {
        findN(1000);
    }

    public static void mersenn(long m) {
        List<Long> primes = primes(m);
        for (int i = 0; i < primes.size(); i++) {
            long res = (long) Math.pow(2, primes.get(i));
            if (!isPrime(res - 1)) {
                System.out.println("p: " + primes.get(i));
                return;
            }
        }
        mersenn(m * 2);
    }

    public static void mersenn() {
        mersenn(1000);
    }

    public static void main(String[] args) {
        System.out.println(isPrime(331));
        System.out.println(primes(5));
        findN();
        System.out.println(isPrime(30031));
        mersenn();
        System.out.println(isPrime(1023));
    }
}
