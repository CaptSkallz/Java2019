public class InterfaceExample {

	public static void main() {
		NadInterfaceExample nie = new NadInterfaceExample();
		int xx = nie.aa;
		PodInterfaceExample pie = new PodInterfaceExample();
		int yy = pie.aa + pie.b + pie.bb + pie.a;
		
		pie.addAll(null);	
		nie.addAll(null);	// nie

		NadInterface ni = new NadInterfaceExample();
		PodInterface pi = new PodInterfaceExample();

		NadInterface xxx1 = ni; 
		NadInterface xxx2 = pi;	
		PodInterface yyy1 = ni; // nie
		PodInterface yy2 = pi;	
		
		NadInterfaceExample xxx1 = nie; 
		NadInterfaceExample xxx2 = pie;	// nie
		PodInterfaceExample yyy1 = nie; // nie
		PodInterfaceExample yy2 = pie;	
			
		System.out.print(pie.b);
		System.out.print(pie.a); // nie
		System.out.print(nie.b); // nie	
		System.out.print(nie.a);
	}
}
