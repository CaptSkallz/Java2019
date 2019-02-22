import static org.junit.Assert.*;

import org.junit.Test;


public class FibonacciTest {

	@Test
	public void testFib() {
		assertEquals(Fibonacci.fib(0),1);
		assertEquals(Fibonacci.fib(1),1);
		assertEquals(Fibonacci.fib(2),2);
		assertEquals(Fibonacci.fib(3),3);
		assertEquals(Fibonacci.fib(4),5);
		assertEquals(Fibonacci.fib(5),8);
		assertEquals(Fibonacci.fib(6),13);
		assertEquals(Fibonacci.fib(7),21);
	}

}
