public class Premenna extends Polynom{
    String prem = "";
    public Premenna(String p){
        prem = p;
    }
    public String toString(){
        return prem;
    }
    double valueAt(String[] vars, double[] values){
        return 0;
    }

    Polynom derive(String var){
        if(prem.equals(var)){
            return new Konštanta(1);
        }
        return new Premenna(prem);
    }

    Polynom simplify(){
        return null;
    }
}
