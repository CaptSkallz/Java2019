public enum Farba1 { 
	Gula("gula"), 			// volanie konötruktora
	Zalud("zelen"),
	Srdce("srdce"), 
	Zelen("zelen");
	
	private String meno;		// lok·lna premenn·
	
	Farba1 (String s) {		// konötruktor
		meno = s;
	}
	public String toString() {	// reprezent·cia objektu ako reùazec
		return meno;
	}
};


