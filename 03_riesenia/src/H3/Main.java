public class Main {

    public static void main(String[] args) {
/*
        double[][] maticaPole = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        Matica A = new Matica(maticaPole);
        double[][] maticaPole2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        System.out.println(A);

        Matica B = new Matica(3, 8);
        System.out.println(B);

//        Matica C = new Matica(3);
//        System.out.println(C);

        System.out.println(A.krat(B));
*/
        Konštanta x = new Konštanta(1);
        System.out.println(x);
        Polynom y = new Premenna("y");
        System.out.println(y);

        Polynom s = new Sucet(x,y);
        System.out.println(s);

        System.out.println("\n\n\n");

        Polynom der = s.derive("x");
        System.out.println(der);
    }
}
