public class PodVonkajsou extends Vonkajsia {
	
		/*
	   Vonkajsia vonk = new Vonkajsia();
	   
	   // Vnutorna vnut1 = new Vnutorna();				//  nepozna Vnutornu triedu
	   // vnutorna bez vonkajsej neexistuje // Vonkajsia.Vnutorna vnut2 = new Vonkajsia.Vnutorna();
	   Vonkajsia.Vnutorna vnut3 = vonk.new Vnutorna();
	   // alebo
	   Vonkajsia.Vnutorna vnut4 = new Vonkajsia().new Vnutorna();
	   
	   static Vonkajsia.StatickaVnutorna staticVnut1 = new Vonkajsia.StatickaVnutorna();   
	   Vonkajsia.StatickaVnutorna staticVnut2 = new Vonkajsia.StatickaVnutorna();
	   // Vonkajsia.StatickaVnutorna staticVnut3 = new Vonkajsia().new StatickaVnutorna();
	  
	   //Vonkajsia.PrivatnaVnutorna privVnut = new Vonkajsia().new PrivatnaVnutorna(); // nepojde, lebo je privatna 
	   //Vonkajsia.ProtectedVnutorna protVnut = new Vonkajsia().new ProtectedVnutorna(); // nepojde, lebo je protected
	   //Vonkajsia.NicVnutorna nicVnut = new Vonkajsia.NicVnutorna();
	   
	   */
	   public PodVonkajsou() {
		   	System.out.println("PodVytvaram: "+getClass().getName());
	   }
	   
		public class PodVnutornou extends Vnutorna {
			   public PodVnutornou() {
					System.out.println("PodVytvaram: "+getClass().getName());
		   }
		}
		
		public static void main(String[] args) {
			PodVonkajsou vonk = new PodVonkajsou();
			System.out.println("...");
			PodVnutornou vnut = vonk.new PodVnutornou();
		}
}
