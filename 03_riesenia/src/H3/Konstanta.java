package H3;
public class Konstanta extends Polynom {
    double konstanta = 0;

    public Konstanta(double k){
            konstanta = k;

    }
    double valueAt(String[] vars, double[] values){
        return 0;
    }

    Polynom derive(String var){
        return new Konštanta(0);
    }

    Polynom simplify(){
        return null;
    }
    public String toString(){
        return Double.toString(konstanta);
    }

}
