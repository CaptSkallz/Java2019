public class Chyba8 {
  public static void main(String[] args) {
	  Input in = new Input();
	  System.out.println("How many pennies do you have?");
	  int pennies = in.nextInt();
	  System.out.println("How many nickels do you have?");
	  int nickels = in.nextInt();
	  System.out.println("How many dimes do you have?");
	  int dimes = in.nextInt();
	  System.out.println("How many quarters do you have?");
	  int quarters = in.nextInt();
	  
	  /* Calculate totals */
	  double total = (pennies * 0.01) + (nickels * 0.05) + (dimes * 0.10) + (quarters * 0.25);
	  System.out.println("Total value = " + total);
	  in.close();
  }
}
