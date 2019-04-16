package PB;

import java.util.ArrayList;
import java.util.List;

public class Prvocisla {
	public static boolean isPrime(long n) {
		if (n == 1) return false;
		if (n == 2) return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i*i <= n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
	public static List<Long> nprimes(int m) {
		List<Long> res = new ArrayList<Long>();
		res.add(2L);
		for (long n = 3; ; n++) {
			for(Long p : res) {
				if (p*p > n) {
					res.add(n);
					if (res.size() >= m)
						return res;
					else
						break;
				} else if (n % p == 0)
					break;
			}
		}
	}
	public static void main(String[] args) {
		for(int i = 2; i < 1000; i++)
			if (isPrime(i))
				System.out.println(i);
		System.out.println(nprimes(10));

		for(int i = 1; i < 10; i++) {
			Long sucin = nprimes(i).stream().reduce(1L,(a,b)->a*b)+1L;
			if (!isPrime(sucin)) {
				System.out.println(i + " ... " + sucin);
			}
		}
		// mersennovo zlozene	
		for(long p = 3;; p++) {
			if (isPrime(p))
				if (!isPrime((1<<p)-1)) {
					System.out.printf("%d ... %d\n", p , (1<<p)-1);
					break;
				}
		}
	}
}
