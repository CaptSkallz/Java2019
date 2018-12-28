public class Vstup {

  public static void main(String[] args) {
    Input in = new Input();
		System.out.println("Vase meno:");
		final String meno = in.nextLine();
		System.out.println("meno:"+meno);	
		System.out.println("Vas vek:");
		final int vek = in.nextInt();
		System.out.println("vek:"+vek);	
		int suma = 0;
		while(in.hasNext())
		  suma += in.nextInt();
		System.out.println("sucet:"+suma);	
		in.close();
	}
}
