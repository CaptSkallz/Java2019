import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import LISTTestScoring.LISTTestScoring;

public class TestGauss {
	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}

	@Test
	public void test() {
		assertEquals("1+..+100 = 5050", 5050, Gauss.sucet(1, 100)  );
		TestGauss.scoring.updateScore("lang:common_list_test_scoring_name",25);				
		assertEquals("1+..+100000 = 5050500005000000", 5000050000L, Gauss.sucet(1, 100000)  );
		TestGauss.scoring.updateScore("lang:common_list_test_scoring_name",35);				
		assertEquals("1+..+100000000 = 5000050000", 5000000050000000L, Gauss.sucet(1,100000000L)  );
		TestGauss.scoring.updateScore("lang:common_list_test_scoring_name",40);				
	}
}
