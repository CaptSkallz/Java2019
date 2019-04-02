import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import LISTTestScoring.LISTTestScoring;

public class TestTrieda {

	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}

	
	Trieda PrvaA = new Trieda(new HashMap<>());
	Trieda DruhaB = new Trieda(new HashMap<>());
	Trieda TretiaC = new Trieda(new HashMap<>());
	Trieda OsmaF = new Trieda(new HashMap<>());
	List<Trieda> triedy = Arrays.asList(PrvaA, DruhaB, TretiaC, OsmaF);
	
	public TestTrieda() {
		super();
		{
		HashMap<Ziak, List<VelkonocneVajce>>tr = new HashMap<>();
		tr.put(new Ziak("Palko", 12), 
				  Arrays.asList(
					new VelkonocneVajce(Color.RED, 2),
					new VelkonocneVajce(Color.BLUE, 3)
				  ));
		tr.put(new Ziak("Ferko", 13), 
				  Arrays.asList(
					new VelkonocneVajce(Color.ORANGE, 1),
					new VelkonocneVajce(Color.YELLOW, 4)
				  ));
		tr.put(new Ziak("Danko", 13), 
				  Arrays.asList(
					new VelkonocneVajce(Color.ORANGE, 1),
					new VelkonocneVajce(Color.ORANGE, 2),
					new VelkonocneVajce(Color.YELLOW, 4),
					new VelkonocneVajce(Color.ORANGE, 8)
				  ));
		tr.put(new Ziak("Marienka", 9), null); 
		tr.put(new Ziak("Zuzka", 9), Arrays.asList()); 
		PrvaA = new Trieda(tr);
		}
		//-------------------------------------
		{
		HashMap<Ziak, List<VelkonocneVajce>>tr = new HashMap<>();
		tr.put(new Ziak("Palko", 12), 
				  Arrays.asList(
							new VelkonocneVajce(Color.RED, 2),
							new VelkonocneVajce(Color.RED, 2),
							new VelkonocneVajce(Color.RED, 2)
				  ));
		tr.put(new Ziak("Ferko", 13), 
				  Arrays.asList(
							new VelkonocneVajce(Color.RED, 2),
							new VelkonocneVajce(Color.RED, 2),
							new VelkonocneVajce(Color.RED, 2),
							new VelkonocneVajce(Color.RED, 2)
				  ));
		tr.put(new Ziak("Danko", 13), 
				  Arrays.asList(
							new VelkonocneVajce(Color.RED, 2)
				  ));
		DruhaB = new Trieda(tr);
		}

	}
	@Test
	public void testPrvaA() {
		Trieda trieda = PrvaA;
		
	      assertEquals("vsetky rozne vajcia:" + trieda, 6, trieda.vsetkyRozneVajcia().size());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	      
	      assertEquals("bez vajec:" + trieda, 2, trieda.bezVajec().size());
	      assertTrue("bez vajec:" + trieda, trieda.bezVajec().contains(new Ziak("Zuzka", 9) ));
	      assertTrue("bez vajec:" + trieda, trieda.bezVajec().contains(new Ziak("Marienka", 9) ));
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	      
	      assertEquals("najvacsi zberatel:" + trieda, new Ziak("Danko", 13) , trieda.najvacsiZberatel());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 

	      assertEquals("najvacsie vajce:" + trieda, new VelkonocneVajce(Color.ORANGE, 8) , 
	    		  trieda.najvacsieVajce());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 

	      assertEquals("najcastejsie vajce:" + trieda, new VelkonocneVajce(Color.ORANGE, 1) , 
	    		  trieda.najcastejsieVajce());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	}
	@Test
	public void testDruhaB() {
		Trieda trieda = DruhaB;
		
	      assertEquals("vsetky rozne vajcia:" + trieda, 1, trieda.vsetkyRozneVajcia().size());
	      assertTrue("vsetky rozne vajcia:" + trieda, trieda.vsetkyRozneVajcia().contains(new VelkonocneVajce(Color.RED, 2)));
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	      
	      assertEquals("bez vajec:" + trieda, 0, trieda.bezVajec().size());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	      
	      assertEquals("najvacsi zberatel:" + trieda, new Ziak("Ferko", 13) , trieda.najvacsiZberatel());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 

	      assertEquals("najvacsie vajce:" + trieda, new VelkonocneVajce(Color.RED, 2) , 
	    		  trieda.najvacsieVajce());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 

	      assertEquals("najcastejsie vajce:" + trieda, new VelkonocneVajce(Color.RED, 2) , 
	    		  trieda.najcastejsieVajce());
		  scoring.updateScore("lang:common_list_test_scoring_name", 4); 
	}
	
	@Test
	public void testDiabolic() {
		for(int pokus = 0; pokus < 12; pokus++) {
			  Random rnd = new Random();
			  HashMap<Ziak, List<VelkonocneVajce>> hm = new HashMap<>();
			  for(int i = 0; i < 1+rnd.nextInt(10); i++) {
				  ArrayList<VelkonocneVajce> al = new ArrayList<>();
		          for(int j = 0; j < 1+rnd.nextInt(10); j++)
		            al.add(VelkonocneVajce.getRandom());
		          hm.put(new Ziak("Ziak"+0, 15+rnd.nextInt(10)), al);
			  }
			  MojaTrieda mojatrieda = new MojaTrieda(hm);
			  Trieda trieda = new Trieda(hm);
			  assertEquals("vsetkyRozneVajcia " + trieda, 
					  							mojatrieda.vsetkyRozneVajcia().size(),
					  							trieda.vsetkyRozneVajcia().size());
			  scoring.updateScore("lang:common_list_test_scoring_name", 1); 
			  
			  assertEquals("bezVajec " + trieda, 
						mojatrieda.bezVajec().size(),
						trieda.bezVajec().size());
			  scoring.updateScore("lang:common_list_test_scoring_name", 1); 
			  
			  assertEquals("najvacsiZberatel " + trieda, 
						mojatrieda.najvacsiZberatel(),
						trieda.najvacsiZberatel());
			  scoring.updateScore("lang:common_list_test_scoring_name", 1); 
			  
			  assertEquals("najvacsieVajce " + trieda, 
						mojatrieda.najvacsieVajce(),
						trieda.najvacsieVajce());
			  scoring.updateScore("lang:common_list_test_scoring_name", 1); 
			  
			  assertEquals("najcastejsieVajce " + trieda, 
						mojatrieda.najcastejsieVajce(),
						trieda.najcastejsieVajce());
			  scoring.updateScore("lang:common_list_test_scoring_name", 1); 
		}
	}	
}
