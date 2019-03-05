import java.util.*;
public class Marias {
  static Karta[] sedmove = {
		  new Karta( Farba.Gula, Hodnota.sedma),
		  new Karta( Farba.Gula, Hodnota.osma),
		  new Karta( Farba.Gula, Hodnota.devinka),
		  new Karta( Farba.Gula, Hodnota.desinka),
		  new Karta( Farba.Gula, Hodnota.dolnik),
		  new Karta( Farba.Gula, Hodnota.hornik),
		  new Karta( Farba.Gula, Hodnota.kral),
		  new Karta( Farba.Gula, Hodnota.eso),

		  new Karta( Farba.Zelen, Hodnota.sedma),
		  new Karta( Farba.Zelen, Hodnota.osma),
		  new Karta( Farba.Zelen, Hodnota.devinka),
		  new Karta( Farba.Zelen, Hodnota.desinka),
		  new Karta( Farba.Zelen, Hodnota.dolnik),
		  new Karta( Farba.Zelen, Hodnota.hornik),
		  new Karta( Farba.Zelen, Hodnota.kral),
		  new Karta( Farba.Zelen, Hodnota.eso),
		  
		  new Karta( Farba.Srdce, Hodnota.sedma),
		  new Karta( Farba.Srdce, Hodnota.osma),
		  new Karta( Farba.Srdce, Hodnota.devinka),
		  new Karta( Farba.Srdce, Hodnota.desinka),
		  new Karta( Farba.Srdce, Hodnota.dolnik),
		  new Karta( Farba.Srdce, Hodnota.hornik),
		  new Karta( Farba.Srdce, Hodnota.kral),
		  new Karta( Farba.Srdce, Hodnota.eso),

		  new Karta( Farba.Zalud, Hodnota.sedma),
		  new Karta( Farba.Zalud, Hodnota.osma),
		  new Karta( Farba.Zalud, Hodnota.devinka),
		  new Karta( Farba.Zalud, Hodnota.desinka),
		  new Karta( Farba.Zalud, Hodnota.dolnik),
		  new Karta( Farba.Zalud, Hodnota.hornik),
		  new Karta( Farba.Zalud, Hodnota.kral),
		  new Karta( Farba.Zalud, Hodnota.eso) };
	
  public static void main(String[] args) {
//	  System.out.println(sedmove);
	  //for(Karta k:sedmove) System.out.println(k);
	  
	  Random rand = new Random();
	  for(int i=0; i<100; i++) {
		  int k = rand.nextInt(sedmove.length);
		  int l = rand.nextInt(sedmove.length);
		  Karta pom = sedmove[k];
		  sedmove[k] = sedmove[l];
		  sedmove[l] = pom;
	  }
	  for(Karta k:sedmove) System.out.println(k);
		  
	  
  }
}
