
/**
 * priklad viditelnosti vonkajsej a vnutornej triedy
 */
public class MimoVonkajsej {
   public static void main(String[] args) {
	   Vonkajsia vonk = new Vonkajsia();
	   // Vnutorna vnut1 = new Vnutorna();				//  nepozna Vnutornu triedu
	   // Vonkajsia.Vnutorna vnut2 = new Vonkajsia.Vnutorna(); // vnutorna bez vonkajsej neexistuje
	   Vonkajsia.Vnutorna vnut3 = vonk.new Vnutorna();
	   Vonkajsia.Vnutorna vnut4 = new Vonkajsia().new Vnutorna();
	   
	   //static Vonkajsia.StatickaVnutorna staticVnut1 = new Vonkajsia.StatickaVnutorna();   
	   final Vonkajsia.StatickaVnutorna staticVnut1 = new Vonkajsia.StatickaVnutorna();   
	   Vonkajsia.StatickaVnutorna staticVnut2 = new Vonkajsia.StatickaVnutorna();
	   //Vonkajsia.StatickaVnutorna staticVnut3 = vonk.new StatickaVnutorna();
	   
	   //Vonkajsia.PrivatnaVnutorna privVnut = new Vonkajsia().new PrivatnaVnutorna(); // nepojde, lebo je privatna 
	   //Vonkajsia.ProtectedVnutorna protVnut = new Vonkajsia().new ProtectedVnutorna(); // nepojde, lebo je protected
	   //Vonkajsia.NicVnutorna nicVnut = new Vonkajsia().NicVnutorna();

   }
}
