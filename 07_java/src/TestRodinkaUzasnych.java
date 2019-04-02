import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestRodinkaUzasnych {
	@Test
	public void test_isParent() {
		assertTrue("isParent", RodinkaUzasnych.adam.isParent(RodinkaUzasnych.jozo));
		assertTrue("isParent", RodinkaUzasnych.adam.isParent(RodinkaUzasnych.sona));
		assertFalse("isParent", RodinkaUzasnych.adam.isParent(RodinkaUzasnych.eva));
		assertFalse("isParent", RodinkaUzasnych.adam.isParent(RodinkaUzasnych.filomena));
		assertFalse("isParent", RodinkaUzasnych.eva.isParent(RodinkaUzasnych.eva));
		assertTrue("isParent", RodinkaUzasnych.eva.isParent(RodinkaUzasnych.pavel));
		assertFalse("isParent", RodinkaUzasnych.eva.isParent(RodinkaUzasnych.filomena));
	}
	@Test
	public void test_isFather() {
		assertTrue("isFather", RodinkaUzasnych.adam.isFather(RodinkaUzasnych.jozo));
		assertTrue("isFather", RodinkaUzasnych.adam.isFather(RodinkaUzasnych.sona));
		assertFalse("isFather", RodinkaUzasnych.adam.isFather(RodinkaUzasnych.eva));
		assertFalse("isFather", RodinkaUzasnych.adam.isFather(RodinkaUzasnych.filomena));
		assertFalse("isFather", RodinkaUzasnych.eva.isFather(RodinkaUzasnych.eva));
		assertFalse("isFather", RodinkaUzasnych.eva.isFather(RodinkaUzasnych.pavel));
		assertFalse("isFather", RodinkaUzasnych.eva.isFather(RodinkaUzasnych.filomena));
	}
	@Test
	public void test_isMother() {
		assertFalse("isMother", RodinkaUzasnych.adam.isMother(RodinkaUzasnych.jozo));
		assertFalse("isMother", RodinkaUzasnych.adam.isMother(RodinkaUzasnych.sona));
		assertFalse("isMother", RodinkaUzasnych.adam.isMother(RodinkaUzasnych.eva));
		assertFalse("isMother", RodinkaUzasnych.adam.isMother(RodinkaUzasnych.filomena));
		assertFalse("isMother", RodinkaUzasnych.eva.isMother(RodinkaUzasnych.eva));
		assertTrue("isMother", RodinkaUzasnych.eva.isMother(RodinkaUzasnych.pavel));
		assertFalse("isMother", RodinkaUzasnych.eva.isMother(RodinkaUzasnych.filomena));
	}
	@Test
	public void test_isOrphan() {
		assertTrue("isOrphan", RodinkaUzasnych.adam.isOrphan());
		assertTrue("isOrphan", RodinkaUzasnych.eva.isOrphan());
		assertFalse("isOrphan", RodinkaUzasnych.filomena.isOrphan());
		assertFalse("isOrphan", RodinkaUzasnych.simona.isOrphan());
	}
	@Test
	public void fathersOfMyChild() {
		assertEquals("fathersOfMyChild", 0, RodinkaUzasnych.adam.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 1, RodinkaUzasnych.eva.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 0, RodinkaUzasnych.paella.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 0, RodinkaUzasnych.pavel.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 0, RodinkaUzasnych.jozo.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 1, RodinkaUzasnych.sona.fathersOfMyChild().size());
		assertEquals("fathersOfMyChild", 0, RodinkaUzasnych.fero.fathersOfMyChild().size());
		assertTrue("fathersOfMyChild", RodinkaUzasnych.simona.fathersOfMyChild().contains(RodinkaUzasnych.jozo));
	}
	@Test
	public void test_motherOfMyChild() {
		assertEquals("motherOfMyChild", 1, RodinkaUzasnych.adam.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.eva.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.paella.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.pavel.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 2, RodinkaUzasnych.jozo.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.sona.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.fero.mothersOfMyChild().size());
		assertEquals("motherOfMyChild", 0, RodinkaUzasnych.simona.mothersOfMyChild().size());
	}
	@Test
	public void test_isSib() {
		assertFalse("isSib", RodinkaUzasnych.adam.isSib(RodinkaUzasnych.jozo));
		assertFalse("isSib", RodinkaUzasnych.adam.isSib(RodinkaUzasnych.sona));
		assertFalse("isSib", RodinkaUzasnych.adam.isSib(RodinkaUzasnych.eva));
		assertFalse("isSib", RodinkaUzasnych.adam.isSib(RodinkaUzasnych.filomena));
		assertFalse("isSib", RodinkaUzasnych.eva.isSib(RodinkaUzasnych.eva));
		assertFalse("isSib", RodinkaUzasnych.eva.isSib(RodinkaUzasnych.pavel));
		assertFalse("isSib", RodinkaUzasnych.eva.isSib(RodinkaUzasnych.filomena));

		assertTrue("isSib", RodinkaUzasnych.sona.isSib(RodinkaUzasnych.jozo));
		assertTrue("isSib", RodinkaUzasnych.jozo.isSib(RodinkaUzasnych.sona));
		assertTrue("isSib", RodinkaUzasnych.sona.isSib(RodinkaUzasnych.paella));
		assertFalse("isSib", RodinkaUzasnych.simona.isSib(RodinkaUzasnych.filomena));
		assertTrue("isSib", RodinkaUzasnych.fero.isSib(RodinkaUzasnych.simona));
		assertTrue("isSib", RodinkaUzasnych.jozo.isSib(RodinkaUzasnych.paella));
		assertTrue("isSib", RodinkaUzasnych.fero.isSib(RodinkaUzasnych.filomena));
	}
	@Test
	public void test_isBrother() {
		assertFalse("isBrother", RodinkaUzasnych.adam.isBrother(RodinkaUzasnych.jozo));
		assertFalse("isBrother", RodinkaUzasnych.adam.isBrother(RodinkaUzasnych.sona));
		assertFalse("isBrother", RodinkaUzasnych.adam.isBrother(RodinkaUzasnych.eva));
		assertFalse("isBrother", RodinkaUzasnych.adam.isBrother(RodinkaUzasnych.filomena));
		assertFalse("isBrother", RodinkaUzasnych.eva.isBrother(RodinkaUzasnych.eva));
		assertFalse("isBrother", RodinkaUzasnych.eva.isBrother(RodinkaUzasnych.pavel));
		assertFalse("isBrother", RodinkaUzasnych.eva.isBrother(RodinkaUzasnych.filomena));

		assertFalse("isBrother", RodinkaUzasnych.sona.isBrother(RodinkaUzasnych.jozo));
		assertTrue("isBrother", RodinkaUzasnych.jozo.isBrother(RodinkaUzasnych.sona));
		assertFalse("isBrother", RodinkaUzasnych.sona.isBrother(RodinkaUzasnych.paella));
		assertFalse("isBrother", RodinkaUzasnych.simona.isBrother(RodinkaUzasnych.filomena));
		assertTrue("isBrother", RodinkaUzasnych.fero.isBrother(RodinkaUzasnych.simona));
		assertTrue("isBrother", RodinkaUzasnych.jozo.isBrother(RodinkaUzasnych.paella));
		assertTrue("isBrother", RodinkaUzasnych.fero.isBrother(RodinkaUzasnych.filomena));

	}
	@Test
	public void test_isSister() {
		assertFalse("isSister", RodinkaUzasnych.adam.isSister(RodinkaUzasnych.jozo));
		assertFalse("isSister", RodinkaUzasnych.adam.isSister(RodinkaUzasnych.sona));
		assertFalse("isSister", RodinkaUzasnych.adam.isSister(RodinkaUzasnych.eva));
		assertFalse("isSister", RodinkaUzasnych.adam.isSister(RodinkaUzasnych.filomena));
		assertFalse("isSister", RodinkaUzasnych.eva.isSister(RodinkaUzasnych.eva));
		assertFalse("isSister", RodinkaUzasnych.eva.isSister(RodinkaUzasnych.pavel));
		assertFalse("isSister", RodinkaUzasnych.eva.isSister(RodinkaUzasnych.filomena));

		assertTrue("isSister", RodinkaUzasnych.sona.isSister(RodinkaUzasnych.jozo));
		assertFalse("isSister", RodinkaUzasnych.jozo.isSister(RodinkaUzasnych.sona));
		assertTrue("isSister", RodinkaUzasnych.sona.isSister(RodinkaUzasnych.paella));
		assertFalse("isSister", RodinkaUzasnych.simona.isSister(RodinkaUzasnych.filomena));
		assertFalse("isSister", RodinkaUzasnych.fero.isSister(RodinkaUzasnych.simona));
		assertFalse("isSister", RodinkaUzasnych.jozo.isSister(RodinkaUzasnych.paella));
		assertFalse("isSister", RodinkaUzasnych.fero.isSister(RodinkaUzasnych.filomena));
	}
	@Test
	public void test_motherInLaw() {
		assertEquals("motherInLaw", 2, RodinkaUzasnych.jozo.motherInLaw().size());  // eva, sona
		assertEquals("motherInLaw", 1, RodinkaUzasnych.sona.motherInLaw().size());
		assertEquals("motherInLaw", 0, RodinkaUzasnych.paella.motherInLaw().size());
		assertEquals("motherInLaw", 1, RodinkaUzasnych.simona.motherInLaw().size());
		assertEquals("motherInLaw", 0, RodinkaUzasnych.fero.motherInLaw().size());
	}
	@Test
	public void test_Predecessors() {
		assertEquals("Predecessors", 2, RodinkaUzasnych.jozo.predecessors().size());  
		assertEquals("Predecessors", 2, RodinkaUzasnych.sona.predecessors().size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.paella.predecessors().size());
		assertEquals("Predecessors", 3, RodinkaUzasnych.simona.predecessors().size());
		assertEquals("Predecessors", 4, RodinkaUzasnych.fero.predecessors().size());
		assertEquals("Predecessors", 5, RodinkaUzasnych.filomena.predecessors().size());
	}
	@Test
	public void test_PredecessorsAt() {
		assertEquals("Predecessors", 2, RodinkaUzasnych.jozo.predecessors(1).size());  
		assertEquals("Predecessors", 2, RodinkaUzasnych.sona.predecessors(1).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.paella.predecessors(1).size());
		assertEquals("Predecessors", 1, RodinkaUzasnych.simona.predecessors(1).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.simona.predecessors(2).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.fero.predecessors(1).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.fero.predecessors(2).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.filomena.predecessors(1).size());
		assertEquals("Predecessors", 3, RodinkaUzasnych.filomena.predecessors(2).size());
		assertEquals("Predecessors", 2, RodinkaUzasnych.filomena.predecessors(3).size());
		assertEquals("Predecessors", 0, RodinkaUzasnych.filomena.predecessors(4).size());
	}
	
	/*
	  final static Person adam = new Person("Adam", Sex.Male, null);
	  final static Person eva  = new Person("Eva", Sex.Female, null);
	  // 1st gen
	  final static Person jozo = new Person("Jozo", Sex.Male, Arrays.asList(adam, eva));
	  final static Person sona = new Person("Sona", Sex.Female, Arrays.asList(adam, eva));
	  final static Person pavel = new Person("Pavel", Sex.Male, Arrays.asList(adam, eva));
	  final static Person paella = new Person("Paella", Sex.Female, Arrays.asList(adam, eva));
	  // 2nd gen
	  final static Person fero = new Person("Fero", Sex.Male, Arrays.asList(jozo, sona));
	  final static Person simona = new Person("Simona", Sex.Female, Arrays.asList(sona));
	  // 3rd gen
	  final static Person filomena = new Person("Filomena", Sex.Female, Arrays.asList(jozo, simona));  // incest: jozo so svojou dcerou simonou
	  public static final List<Person> people = Arrays.asList( adam, eva, jozo, fero, simona, filomena  );
*/
}
