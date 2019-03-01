import static org.junit.Assert.*;

import org.junit.Test;

public class Pole2DTest {

	@Test
	public void testPocet() {
		if (Pole2D.pocet(Polia.pole2D_1) != 3) {
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testPocet2() {
		assertEquals(2, Pole2D.pocet(Polia.pole2D_5));

	}

}
