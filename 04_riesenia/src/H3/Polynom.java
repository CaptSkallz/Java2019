public abstract class Polynom {

    public static void main(String[] args) {
        Premenna x = new Premenna("x");
        Premenna y = new Premenna("y");
        Konstanta k = new Konstanta(1);

        Sucet s1 = new Sucet(x,y);
        Sucet s2 = new Sucet(s1,new Sucin(x, y));
        Sucet s3 = new Sucet(new Sucin(x,x), new Konstanta(1));
        //Sucet s4 = new Sucet(new Sucet(x, x), x);
        Sucet s4 = new Sucet(x, new Sucet(x, x));

        System.out.println(s2.valueAt(new String[] {"x","y"}, new double[]{-2,6}));
        System.out.println(s4);
        System.out.println(s4.derive("x"));
        System.out.println(s4.derive("x").zjednodus());
    }


    abstract double valueAt(String[] vars, double[] values);
    abstract Polynom derive(String var);

    abstract Polynom simplify();
    public Polynom zjednodus() {
        Polynom n = this;
        Polynom p;
        do {
            p = n;
            n = p.simplify();

        } while(!p.toString().equals(n.toString()));
        return p;
    }


}
//-----------------------
class Premenna extends Polynom{

    String p;
    public Premenna(String s){
        p = s;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        for(int i = 0; i < vars.length; i++){
            if (vars[i].equals(p)) return values[i];
        }
        return 0;
    }

    @Override
    Polynom derive(String var) {
        if(this.p.equals(var)) {
            return new Konstanta(1);
        }
        return new Konstanta(0);
    }

    public String toString() {
        return p;
    }
    public Polynom simplify() {
        return this;
    }

}
//------------------
class Konstanta extends Polynom{

    double konst;
    public Konstanta(double k){
        konst = k;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        return konst;
    }

    @Override
    Polynom derive(String var) {
        return new Konstanta(0);
    }
    @Override
    public String toString() {
        return ""+konst;
    }
    public Polynom simplify() {
        return this;
    }
}
//------------------
class Sucet extends Polynom{

    Polynom p1 ,p2;
    public Sucet(Polynom a, Polynom b){
        p1 = a;
        p2 = b;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        return p1.valueAt(vars, values) + p2.valueAt(vars, values);
    }

    @Override
    Polynom derive(String var) {
        return new Sucet(p1.derive(var), p2.derive(var));
    }

    @Override
    public String toString() {
        return "(" + p1 + "+" + p2 + ")";
    }
    public Polynom simplify() {
        if(p1 instanceof Konstanta && p2 instanceof Konstanta)
            return new Konstanta(((Konstanta)p1).konst + ((Konstanta)p2).konst);

        if(p1 instanceof Konstanta && ((Konstanta)p1).konst == 0)
            return p2;
        if(p2 instanceof Konstanta && ((Konstanta)p2).konst == 0)
            return p1;

        return new Sucet(p1.simplify(), p2.simplify());
    }
}

class Sucin extends Polynom{

    Polynom p1 ,p2;
    public Sucin(Polynom a, Polynom b){
        p1 = a;
        p2 = b;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        return p1.valueAt(vars, values) * p2.valueAt(vars, values);
    }

    @Override
    Polynom derive(String var) {
        return new Sucet(new Sucin(p1.derive(var), p2), new Sucin(p2.derive(var), p1));
    }

    @Override
    public String toString() {
        return "(" + p1 + "*" + p2 + ")";
    }
    public Polynom simplify() {
        return this;
    }
}
