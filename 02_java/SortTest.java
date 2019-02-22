import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SortTest {
int testPole[];

	@Before public void testGeneruj() {
		testPole= Sort.generuj(100);
		if (testPole == null)
			fail("ziadne pole");
		assertNotNull("ziadne pole",testPole);
		assertEquals("velkost pola", testPole.length, 100);		
		assertTrue("velkost pola", testPole.length == 100);
	}
	
	@Test (timeout=400)
	public void testBubleSortuj() {
		Sort.bubleSortuj(testPole);
		for(int i=0; i+1<testPole.length; i++)
			if (testPole[i] >testPole[i+1])
				fail("neutriedene");
	}

}
