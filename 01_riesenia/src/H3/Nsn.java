public class Nsn {
    public static int nsn(int a, int b){
        if (a>0 && b>0){
            return (Math.abs(a*b))/Nsd.nsd(a,b);
        }
        return 0;
    }
    public static void main(String[] args) {
        int vysl = nsn(5,7);
        System.out.println(vysl);
    }
}
