public enum Farba { 
	Gula("gula"), 
	Zalud("zelen"),
	Srdce("srdce"), 
	Zelen("zelen");
	
	private String meno;
	
	Farba(String s) {
		meno = s;
	}
	public String toString() {
		return meno;
	}
};
