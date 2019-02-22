
public class Switch {

	public static void main(String[] args) {
		if (args.length == 0) return;
		switch(args[0]) {
		case "load":
			System.out.println("citaj");
			break;
		case "save": 
		case "saveAs":
			System.out.println("pis");
			break;
		default:
			System.out.println("ine");
		}
		
	}
}
