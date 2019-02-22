/**
 * priklad citania hodnot typu int, String z konzoly za pouzitia
 * modulu Imput.java
 * @author PB
 * @verstion 2009
 */
public class Vstup {

  public static void main(String[] args) {
	Input in = new Input();
	System.out.println("Vase meno:");
	final String meno = in.nextLine();
	System.out.println("meno:" + meno);
	System.out.println("Vas vek:");
	final int vek = in.nextInt();
	System.out.println("vek:" + vek);
	int suma = 0;
	while (in.hasNextInt())
		suma += in.nextInt();
	System.out.println("sucet:"+suma);	
	in.close();
  }
}
