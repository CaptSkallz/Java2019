import java.io.*;
public class PiskyStav implements Serializable {

	private static final long serialVersionUID = 1L;
	char piskvorky[][] = new char [10][10];	// reprezentacia hracej plochy
	boolean XNaTahu = true;					// stav hry
	int pocetXPiskvoriek = 0;				// dalsie informacie
	int pocetOPiskvoriek = 0;
	
	public static PiskyStav load(String fileName) throws Exception {
  	  	ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
  	  	PiskyStav ps = (PiskyStav)is.readObject();
  	  	is.close();
  	  	return ps;
	}
	public void save(String fileName) throws Exception {
    	ObjectOutputStream fs = new ObjectOutputStream(new FileOutputStream(fileName));
    	fs.writeObject(this); 
    	fs.close();
	}
	public String toString() {
		String s = "";
		for (int i=0; i<piskvorky.length; i++) {
			for (int j=0; j<piskvorky[i].length; j++)
				s += ((piskvorky[i][j]>0)?piskvorky[i][j]:".") + " ";
			s += "\n";
		}
		s += "na tahu je: " + ((XNaTahu)?"X":"O");
		return s;
	}
}
