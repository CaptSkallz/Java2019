public class Sucet extends Polynom {
    Polynom x;
    Polynom y;

    public Sucet(Polynom x,Polynom y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return x.toString() + "+" + y.toString();
    }


    double valueAt(String[] vars, double[] values){
        return 0;
    }

    Polynom derive(String var){
        return new Sucet(
                x.derive(var),
                y.derive(var)
        );
    }

    Polynom simplify(){
        return null;
    }
}
