/**
 * priklad, ktory ilustruje variabilitu komentarov
 * @author PB
 * @version 2009
 * @since 1.0
 */
public class Komentare {
	/**
	 * <code>main</code> je metoda hlavneho programu
	 * <br>
	 * 
	 * @param args pole retazcov prikazoveho riadku
	 * @return <code>void</code> nevracia nic, je to procedura
	 * @exception no exceptions
	 */
  public static void main(String[] args) {
    double ucet;
    int pocetPiv = 5;
    ucet = pocetPiv * 1.0;  	// typicky komentar
    System.out.println("Platis = " + ucet);

    ucet = pocetPiv * /* 1.0 */ 1.30;  /* 1.0 je za desinku */
    System.out.println("Platis = " + ucet);
  }
}
