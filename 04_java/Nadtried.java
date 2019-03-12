public class Nadtried {
    String[] pole = new String[100];
    int counter = 0;
    public void add(String s) { //pridaj 1
	    pole[counter++] = s;
    }
    public void addAll(String[] p) {
	    for(String s:p) // pridaj všetky
		 //add(s);
		 this.add(s);
    }
}
