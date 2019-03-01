import java.util.Random;

public class Pole2D {
    public static int pocet(String[][] a){
        if (a == null) {
            return 0;
        }
        int c = 0;
        for (int i = 0; i < a.length; i++){
            if (a[i] != null) {
                for (int j = 0; j < a[i].length; j++){
                    if (a[i][j] != null) {
                        String converted = a[i][j].toUpperCase();
                        if (converted.contains("YES") || converted.contains("NO")) {
                            c += 1;
                        }
                    }
                }
            }
        }
        return c;
    }

    static String nahodnyRetazec(){
        Random r = new Random();
        int cislo = r.nextInt(100);
        StringBuffer s = new StringBuffer();
        for(int i = 0; i < cislo; i++){
            char pismeno = (char)('A' + r.nextInt('Z'-'A'+1));
            s.append(pismeno);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        {
            String[][] pole = {{"yEs"}, {"No"}};
            System.out.println(pocet(pole));
        }
        {
            String[][] pole = {{"tyEss"}, {" No"}};
            System.out.println(pocet(pole));
        }
        {
            String[][] pole = null;
            System.out.println(pocet(pole));
        }
        {
            String[][] pole = {{"tyEssno"}, {" No"}};
            System.out.println(pocet(pole));
        }
        {
            String[][] pole = {{"tyEssno"}, null};
            System.out.println(pocet(pole));
        }
        System.out.println(nahodnyRetazec());
    }
}
