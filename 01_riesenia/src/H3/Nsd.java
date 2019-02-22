public class Nsd {
    public static int nsd(int a, int b){
        //System.out.println(a);
        //System.out.println(b);
        if (a == b){
            return a;
        }
        if (a==0 || b==0){
            return Math.max(a,b);
        }
        if (a > b){
            return nsd(a%b,b);
        }
        else{
            return nsd(b%a,a);
        }
    }

    public static void main(String[] args) {
        int vysl = nsd(24,8);
        System.out.println(vysl);
    }
}
