public enum Hodnota { 
	sedma(7), 
	osma(8), 
	devinka(9), 
	desinka(10), 
	dolnik(11), 
	hornik(12), 
	kral(13),
	eso(14);
	
	private int h;
    Hodnota(int _h) {
    	h = _h;
	}
    public String toString() {
    	return String.valueOf(h);
    }
    public boolean bije(Hodnota k) {
    	return h < k.h;
    }
};
