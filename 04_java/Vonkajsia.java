
/**
 * toto je vonkajsia trieda, ktora obsahuje niekolko vnorenych tried
 */
public class Vonkajsia {
	public int a = 1;
	public static int stat = 2;
	
	public Vonkajsia() {
		System.out.println("Vytvaram: "+getClass().getName());
	}
	public class Vnutorna {
		public int b = a+stat;
		public Vnutorna() {
			System.out.println("Vytvaram: "+getClass().getName());
		}
	}
	private class PrivatnaVnutorna {
		public int c = a+stat;
		public PrivatnaVnutorna() {
			System.out.println("Vytvaram: "+getClass().getName());
		}		
	}
	private class ProtectedVnutorna {
		public int d = a+stat;
		public ProtectedVnutorna() {
			System.out.println("Vytvaram: "+getClass().getName());
		}				
	}
	class NicVnutorna {
		public int e = a+stat;
		public NicVnutorna() {
			System.out.println("Vytvaram: "+getClass().getName());
		}
	}
	public static class StatickaVnutorna {
		public int e =  stat;
		public StatickaVnutorna() {
			System.out.println("Vytvaram: "+getClass().getName());
		}
	}
}
