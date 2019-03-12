public class C {
	A a = new A();	// vloená referencia (!) na objekt a typu A
	B b = new B();	// vloená referencia (!) na objekt b typu B

	public static void main(String[] args) {
		C c = new C();	// vytvorenı objekt obsahujúci a:A aj b:B
		c.a.doA();		// interné metódy A, B by mali by skryté v C
		c.b.doB();
	}
}

