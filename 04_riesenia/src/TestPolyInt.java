import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import LISTTestScoring.LISTTestScoring;

public class TestPolyInt {

	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}


	@Test
	public void testZoZadania() {
		PolyInt p1 = new PolyInt(new long[] { 1, 2, 3, -4, 1 });
		PolyInt p2 = new PolyInt(new long[] { 1, 0, 0, 2 });
		MyPolyInt mp1 = new MyPolyInt(new long[] { 1, 2, 3, -4, 1 });
		MyPolyInt mp2 = new MyPolyInt(new long[] { 1, 0, 0, 2 });

		assertEquals("server:" + mp1 + " v pre x=13: " + mp1.value(13) + 
				"\nstudent: " + p1 + " v pre x=13: " + p1.value(13),
			mp1.value(13), 
			p1.value(13));
		
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 10);
		System.out.println("+  10%");

		p1.add(p2);
		mp1.add(mp2);

		assertEquals("server:" + mp1 + " v pre x=13: " + mp1.value(13) + 
				"\nstudent: " + p1 + " v pre x=13: " + p1.value(13),
			mp1.value(13), 
			p1.value(13));
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 10);
		System.out.println("+  10%");

		p1.times(19);
		mp1.times(19);

		assertEquals("server:" + mp1 + " v pre x=13: " + mp1.value(13) + 
				"\nstudent: " + p1 + " v pre x=13: " + p1.value(13),
			mp1.value(13), 
			p1.value(13));
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 10);
		System.out.println("+  10%");

		p1.times(p2);
		mp1.times(mp2);

		assertEquals("server:" + mp1 + " v pre x=13: " + mp1.value(13) + 
				"\nstudent: " + p1 + " v pre x=13: " + p1.value(13),
			mp1.value(13), 
			p1.value(13));
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 10);
		System.out.println("+  10%");

		p1.derive();
		mp1.derive();

		assertEquals("server:" + mp1 + " v pre x=13: " + mp1.value(13) + 
				"\nstudent: " + p1 + " v pre x=13: " + p1.value(13),
			mp1.value(13), 
			p1.value(13));
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 10);
		System.out.println("+  10%");
	}
	
	@Test
	public void testZxVaznejsi() {
		PolyInt p1 = new PolyInt(new long[] { 1, 0, 1, -1, 1 });
		PolyInt p2 = new PolyInt(new long[] { 1, 0, 1, 0, 1, 1 });
		MyPolyInt mp1 = new MyPolyInt(new long[] { 1, 0, 1, -1, 1 });
		MyPolyInt mp2 = new MyPolyInt(new long[] { 1, 0, 1, 0, 1, 1 });
		for (int i = 0; i < 5; i++) {
			
			System.out.println("pred add");
			System.out.println(mp1 + ", exp:" + mp1.value(1));
			System.out.println(p1 + ", act: "+ p1.value(1));
			System.out.println(mp2 + ", exp:" + mp2.value(1));
			System.out.println(p2 + ", act: "+ p2.value(1));
			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
						"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
					mp1.value(1), 
					p1.value(1));

			p1.add(p2);
			mp1.add(mp2);

			System.out.println("po add");
			System.out.println(mp1 + ", exp:" + mp1.value(1));
			System.out.println(p1 + ", act: "+ p1.value(1));
			System.out.println(mp2 + ", exp:" + mp2.value(1));
			System.out.println(p2 + ", act: "+ p2.value(1));
			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
					"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
				mp1.value(1), 
				p1.value(1));

			p1.times(2);
			mp1.times(2);

			System.out.println("po times2");
			System.out.println(mp1 + ", exp:" + mp1.value(1));
			System.out.println(p1 + ", act: "+ p1.value(1));
			System.out.println(mp2 + ", exp:" + mp2.value(1));
			System.out.println(p2 + ", act: "+ p2.value(1));
			
			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
					"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
				mp1.value(1), 
				p1.value(1));

			System.out.println("pred times server " + p2);
			System.out.println("pred times student " + mp2);
			
			p1.times(p2);
			mp1.times(mp2);

			System.out.println("po times" + p2);
			System.out.println("po times" + mp2);
			System.out.println(mp1 + ", exp:" + mp1.value(1));
			System.out.println(p1 + ", act: "+ p1.value(1));
			System.out.println(mp2 + ", exp:" + mp2.value(1));
			System.out.println(p2 + ", act: "+ p2.value(1));
			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
					"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
				mp1.value(1), 
				p1.value(1));

			p1.derive();
			mp1.derive();

			System.out.println("po derive");
			System.out.println(mp1 + ", exp:" + mp1.value(1));
			System.out.println(p1 + ", act: "+ p1.value(1));
			System.out.println(mp2 + ", exp:" + mp2.value(1));
			System.out.println(p2 + ", act: "+ p2.value(1));
			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
					"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
				mp1.value(1), 
				p1.value(1));
		}
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 25);
		System.out.println("+  25%");
	}

	@Test
	public void testZxVaznejsi2() {
		PolyInt p1 = new PolyInt(new long[] { 1, 1 });
		PolyInt p2 = new PolyInt(new long[] { -1, 1 });
		MyPolyInt mp1 = new MyPolyInt(new long[] { 1, 1 });
		MyPolyInt mp2 = new MyPolyInt(new long[] { -1, 1 });
		//System.out.println(p1 + ", exp:" + mp1.value(1) + ", act: "+ p1.value(1));
		for (int i = 0; i < 20; i++) {
			assertEquals("" + p1 + " v pre x=1: ", mp1.value(1), p1.value(1));

			p1.times(p2);
			mp1.times(mp2);

			assertEquals("server:" + mp1 + " v pre x=1: " + mp1.value(1) + 
					"\nstudent: " + p1 + " v pre x=1: " + p1.value(1),
				mp1.value(1), 
				p1.value(1));
			//System.out.println(p1);
			//System.out.println(p1 + ", exp:" + mp1.value(1) + ", act: "+ p1.value(1));
		}
		TestPolyInt.scoring.updateScore("lang:common_list_test_scoring_name", 25);
		System.out.println("+  25%");
	}
}
