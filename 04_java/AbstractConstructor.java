public abstract class AbstractConstructor {
	int value;
	private AbstractConstructor() {  // konstruktor, dokonca privatny v abstraktnej triede
		this(0);
	}
	public AbstractConstructor(int val) {  // expilicitne sa neda zavolat pomocou new
		value = val;
	}
	abstract public int lenAbyTuBoloNiecoAbstract();
}

class JejPodtrieda extends AbstractConstructor {
	public JejPodtrieda() {  // konstruktor v podtriede zavola konstruktor v abstraktnej 
		super(0);		// nadtriede, ktorz zavola privatny konstruktor
	}
	public JejPodtrieda(int val) {
		super(val);		// to iste este raz
	}
	public int lenAbyTuBoloNiecoAbstract() {
		return value;
	}
}