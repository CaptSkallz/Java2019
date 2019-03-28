import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestSet {

	@Test
	public void testInheritance() {
		StringSet a = new StringSet(20);
		Union uni = new Union(a, a);
		Intersection inter = new Intersection(a, uni);
		assertTrue("StringSet musi byt podtrieda AbstractStringSet.", a instanceof AbstractStringSet);
		assertTrue("Union musi byt podtrieda AbstractStringSet.", uni instanceof AbstractStringSet);
		assertTrue("Intersection musi byt podtrieda AbstractStringSet.", inter instanceof AbstractStringSet);
	}



	//// StringSet testy
	@Test
	public void testStringSet() {
		// Prazdna mnozina
		StringSet a = new StringSet(20);
		assertEquals("Velkost prazdnej mnoziny.", 0, a.size());
		assertUnorderedArrays("Obsah prazdnej mnoziny.", new String[]{}, a.getElements());
		// Jednoduche pridavanie
		String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		for(int i = 1; i <= 10; i++) {
			assertTrue("Pridavanie prvku ktory nie je v mnozine.", a.add(Integer.toString(i)));
		}
		assertEquals("Velkost mnoziny {1..10}.", 10, a.size());
		assertUnorderedArrays("Obsah mnoziny {1..10}.", expected, a.getElements());
		for(int i = -5; i < 15; i++) {
			assertEquals("Mnozina {1..10}.contains(\""+i+"\").", i > 0 && i <= 10, a.contains(Integer.toString(i)));
		}
		// Pridavanie tych istych prvkov
		for(int i = 10; i > 0; i--) {
			assertFalse("Pridavanie prvku ktory je v mnozine.", a.add(Integer.toString(i)));
		}
		assertEquals("Velkost mnoziny {1..10}, opakovane prvky.", 10, a.size());
		assertUnorderedArrays("Obsah mnoziny {1..10}, opakovane prvky.", expected, a.getElements());
		for(int i = -5; i < 15; i++) {
			assertEquals("Mnozina {1..10}.contains(\""+i+"\"), opakovane prvky.", i > 0 && i <= 10, a.contains(Integer.toString(i)));
		}
		// Upravovanie vrateneho pola
		String[] elems = a.getElements();
		elems[0] = "zmeneny prvok";
		assertUnorderedArrays("Obsah mnoziny {1..10}, upraveny vysledok funkcie getElements.", expected, a.getElements());
		// Pridavanie do plnej mnoziny
		for(int i = 101; i <= 110; i++) {
			assertTrue("Pridavanie prvku ktory nie je v mnozine.", a.add(Integer.toString(i)));
		}
		assertFalse("Pridavanie prvku do plnej mnoziny.", a.add("uz sa nezmestim"));
	}

	@Test
	public void testStringSetNull() {
		// Prazdna mnozina
		StringSet a = new StringSet(20);
		assertEquals("Velkost prazdnej mnoziny.", 0, a.size());
		assertUnorderedArrays("Obsah prazdnej mnoziny.", new String[]{}, a.getElements());
		// Pridavanie null
		String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", null};
		for(int i = 1; i <= 10; i++) {
			assertTrue("Pridavanie prvku ktory nie je v mnozine.", a.add(Integer.toString(i)));
		}
		assertTrue("Pridavanie prvku null ktory nie je v mnozine.", a.add(null));
		assertEquals("Velkost mnoziny {1..10, null}.", 11, a.size());
		assertUnorderedArrays("Obsah mnoziny {1..10, null}.", expected, a.getElements());
		for(int i = -5; i < 15; i++) {
			assertEquals("Mnozina {1..10}.contains(\""+i+"\").", i > 0 && i <= 10, a.contains(Integer.toString(i)));
		}
		assertTrue("Mnozina {1..10, null}.contains(null).", a.contains(null));
		assertFalse("Pridavanie prvku null ktory uz je v mnozine.", a.add(null));
	}



	//// Union testy
	@Test
	public void testUnion() {
		// Prazdne zjednotenie
		StringSet a = new StringSet(10);
		StringSet b = new StringSet(15);
		Union u = new Union(a, b);
		assertEquals("Velkost prazdneho zjednotenia.", 0, u.size());
		assertUnorderedArrays("Obsah prazdneho zjednotenia.", new String[]{}, u.getElements());
		// Naplnenie mnozin
		for(int i = 1; i <= 10; i++) {
			a.add(Integer.toString(i));
			b.add(Integer.toString(i * 2));
		}
		String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "14", "16", "18", "20"};
		assertEquals("Velkost zjednotenia {1..10}+{2,4,..,20}.", 15, u.size());
		assertUnorderedArrays("Obsah zjednotenia {1..10}+{2,4,..,20}.", expected, u.getElements());
		for(int i = -5; i < 25; i++) {
			assertEquals("Zjednotenie {1..10}+{2,4,..,20}.contains(\""+i+"\").",
					i > 0 && (i <= 10 || i <= 20 && i%2 == 0), u.contains(Integer.toString(i)));
		}
		// Upravovanie vrateneho pola
		String[] elems = u.getElements();
		elems[0] = "zmeneny prvok";
		assertUnorderedArrays("Obsah zjednotenia {1..10}+{2,4,..,20}, upraveny vysledok funkcie getElements.",
				expected, u.getElements());

		// Zjednotenie zjednoteni
		StringSet c = new StringSet(20);
		StringSet d = new StringSet(20);
		for(int i = 1; i <= 10; i++) {
			c.add(Integer.toString(100 + i));
			d.add(Integer.toString(100 + i * 2));
		}
		u = new Union(new Union(a, b), new Union(c, d));
		expected = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "14", "16", "18", "20",
					"101","102","103","104","105","106","107","108","109","110","112","114","116","118","120"};
		assertEquals("Velkost zjednotenia zjednoteni.", 30, u.size());
		assertUnorderedArrays("Obsah zjednotenia zjednoteni.", expected, u.getElements());
		for(int i = -5; i < 125; i++) {
			int j = i % 100;
			assertEquals("Zjednotenie zjednoteni .contains(\""+i+"\").",
					j > 0 && (j <= 10 || j <= 20 && j%2 == 0), u.contains(Integer.toString(i)));
		}
	}

	@Test
	public void testUnionNull() {
		// Zjednotenie zjednoteni
		StringSet a = new StringSet(10);
		StringSet b = new StringSet(15);
		StringSet c = new StringSet(20);
		StringSet d = new StringSet(20);
		for(int i = 1; i <= 10; i++) {
			a.add(Integer.toString(i));
			b.add(Integer.toString(i * 2));
			c.add(Integer.toString(100 + i));
			d.add(Integer.toString(100 + i * 2));
		}
		b.add(null);
		c.add(null);
		Union u = new Union(new Union(a, b), new Union(c, d));
		String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "14", "16", "18", "20",
					"101","102","103","104","105","106","107","108","109","110","112","114","116","118","120", null};
		assertEquals("Velkost zjednotenia zjednoteni.", 31, u.size());
		assertUnorderedArrays("Obsah zjednotenia zjednoteni.", expected, u.getElements());
		for(int i = -5; i < 125; i++) {
			int j = i % 100;
			assertEquals("Zjednotenie zjednoteni.contains(\""+i+"\").",
					j > 0 && (j <= 10 || j <= 20 && j%2 == 0), u.contains(Integer.toString(i)));
		}
		assertTrue("Zjednotenie zjednoteni .contains(null).", u.contains(null));
	}



	//// Intersection testy
	@Test
	public void testIntersection() {
		// Prazdny prienik
		StringSet a = new StringSet(20);
		StringSet b = new StringSet(30);
		Intersection it = new Intersection(a, b);
		assertEquals("Velkost prazdneho prieniku.", 0, it.size());
		assertUnorderedArrays("Obsah prazdneho prieniku.", new String[]{}, it.getElements());
		// Naplnenie mnozin
		for(int i = 1; i <= 20; i++) {
			a.add(Integer.toString(i));
			b.add(Integer.toString(i * 2));
		}
		String[] expected = {"2", "4", "6", "8", "10", "12", "14", "16", "18", "20"};
		assertEquals("Velkost prieniku {1..20}*{2,4,..,40}.", 10, it.size());
		assertUnorderedArrays("Obsah prieniku {1..20}*{2,4,..,40}.", expected, it.getElements());
		for(int i = -5; i < 25; i++) {
			assertEquals("Prienik {1..20}*{2,4,..,40}.contains(\""+i+"\").",
					i > 0 && i <= 20 && i%2 == 0, it.contains(Integer.toString(i)));
		}
		// Upravovanie vrateneho pola
		String[] elems = it.getElements();
		elems[0] = "zmeneny prvok";
		assertUnorderedArrays("Obsah prieniku {1..20}*{2,4,..,40}, upraveny vysledok funkcie getElements.",
				expected, it.getElements());

		// Prienik prienikov
		StringSet c = new StringSet(40);
		StringSet d = new StringSet(40);
		for(int i = 1; i <= 20; i++) {
			c.add(Integer.toString(i - 5));
			d.add(Integer.toString(i * 2 + 4));
		}
		it = new Intersection(new Intersection(a, b), new Intersection(c, d));
		expected = new String[]{"6", "8", "10", "12", "14"};
		assertEquals("Velkost prieniku prienikov.", 5, it.size());
		assertUnorderedArrays("Obsah prieniku prienikov.", expected, it.getElements());
		for(int i = -5; i < 50; i++) {
			assertEquals("Prienik prienikov .contains(\""+i+"\").",
					i > 5 && i <= 15 && i%2 == 0, it.contains(Integer.toString(i)));
		}
	}

	@Test
	public void testIntersectionNull() {
		// Zjednotenie zjednoteni
		StringSet a = new StringSet(40);
		StringSet b = new StringSet(40);
		StringSet c = new StringSet(40);
		StringSet d = new StringSet(40);
		for(int i = 1; i <= 20; i++) {
			a.add(Integer.toString(i));
			b.add(Integer.toString(i * 2));
			c.add(Integer.toString(i - 5));
			d.add(Integer.toString(i * 2 + 4));
		}
		for(StringSet s : new StringSet[] {a, b, c, d}) {
			s.add(null);
		}
		Intersection it = new Intersection(new Intersection(a, b), new Intersection(c, d));
		String[] expected = {"6", "8", "10", "12", "14", null};
		assertEquals("Velkost prieniku prienikov.", 6, it.size());
		assertUnorderedArrays("Obsah prieniku prienikov.", expected, it.getElements());
		for(int i = -5; i < 125; i++) {
			assertEquals("Prienik prienikov .contains(\""+i+"\").",
					i > 5 && i <= 15 && i%2 == 0, it.contains(Integer.toString(i)));
		}
		assertTrue("Prienik prienikov .contains(null).", it.contains(null));
	}



	//// Pomocna metoda
	private static void assertUnorderedArrays(String msg, String[] expected, String[] actual) {
		if(expected == null || actual == null) {
			if(expected != actual) {
				fail(msg + " Expected: " + Arrays.toString(expected) +", but was: " + Arrays.toString(actual));
			}
			return;
		}
		String[] e = Arrays.copyOf(expected, expected.length);
		String[] a = Arrays.copyOf(actual, actual.length);
		Comparator<String> comp = (o1, o2) -> {
				if(o1 == null) { return o2 == null ? 0 : -1; }
				if(o2 == null) { return 1; }
				return o1.compareTo(o2);
			};
		Arrays.sort(e, comp); Arrays.sort(a, comp);
		if(!Arrays.equals(e, a)) {
			fail(msg + " Expected: " + Arrays.toString(e) + ", but was: " + Arrays.toString(a) +
				 ". (Order of element in arrays is not considered.)");
		}
	}

}
