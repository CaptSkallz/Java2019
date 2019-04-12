import java.io.*;

public class RecDirList {
  static void rekVypis(String aktualnyAdr) {
    String[] mena;
    File aktDir = new File(aktualnyAdr);
    FilterAdresara FilterAdr = new FilterAdresara();
    mena = aktDir.list(FilterAdr);
    if (mena != null)
      for (int i = 0;  i < mena.length;  i++) {
	    String podadr = new String (aktualnyAdr + File.separator + mena[i]);
	    System.out.println(podadr);	
	    rekVypis(podadr);
  	  }
}
  public static void main(String[] args) {
    rekVypis("c:\\Program Files");
  }
}
