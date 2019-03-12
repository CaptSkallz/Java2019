
public class Ccc {

	public static void main(String[] args) {
		Cc c = new Cc();		// vytvorený objekt obsahujúci a:A aj b:B
		c.doA();			// interné metódy A, B by sú skryté v C
		c.doB();	
	}

}
