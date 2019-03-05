public class StaticVsClass {
  static int pocetInstancii = 0;	// staticka premenna
  final static int MAX = 10;		// staticka konstanta
  int indexInstancii;				// triedna/nestaticka premenna
  final int MIN = 7;				// triedna/nestaticka konstanta
  
  StaticVsClass() {
	  indexInstancii = ++pocetInstancii;
  }
  static int Rest() {				// staticka metoda
	  return MAX-pocetInstancii;
  }
  int getIndex() {					// nestaticka metoda
	  return indexInstancii;
  }
  public static void main(String[] args) {
	  int a = MAX + 			 // referencia statickej premennej
	  		StaticVsClass.MAX +  // uplna referencia statickej p.
	  		StaticVsClass.Rest();// referencia statickej metody
	  
/*
 	  int b = MIN +				 // nestaticka konstanta v statickom kontexte	
	  		indexInstancii +	 // nestaticka premenna v statickom kontexte
	  		getIndex();			 // nestaticka metoda v statickom kontexte
	  		*/
	  
	  StaticVsClass XXX = new StaticVsClass(); // objekt triedy StaticVsClass
	  int c = XXX.indexInstancii +// nestaticka premenna v nestatickom kontexte
	  		XXX.MIN +			 // nestaticka konstanta v nestatickom kontexte
	  		XXX.getIndex();		 // nestaticka metoda v nestatickom kontexte
	  
	  int d = XXX.MAX +			 // staticka konstanta v nestatickom kontexte
	  		XXX.pocetInstancii + // staticka premenn v nestatickom kontexte
	  		XXX.Rest();			 // staticka metoda v nestatickom kontexte

	  StaticVsClass YYY = new StaticVsClass(); // objekt triedy StaticVsClass
	  
	  System.out.println(XXX.getIndex());	// 1
	  System.out.println(YYY.getIndex());	// 2
	  
	  System.out.println(StaticVsClass.pocetInstancii);	// 2
	  System.out.println(XXX.pocetInstancii);	// 2
	  System.out.println(YYY.pocetInstancii);	// 2
	  XXX.pocetInstancii = 17;
	  StaticVsClass.pocetInstancii = 13;
	  System.out.println(StaticVsClass.pocetInstancii);	// 13
	  System.out.println(XXX.pocetInstancii);	// 13
	  System.out.println(YYY.pocetInstancii);	// 13
  }
}
