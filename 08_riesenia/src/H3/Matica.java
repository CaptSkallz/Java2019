package H3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Matica {

    public static void main(String[] args) {
        int velkost =0;
        try (
                Scanner scanner = new Scanner(new File("Matica.txt"));
                PrintWriter pw = new PrintWriter("output.txt");
        ){
            scanner.useLocale(Locale.ENGLISH);
            if(scanner.hasNextInt()) {
                velkost = scanner.nextInt();
            }
            else{
                throw new IllegalArgumentException("Velokst musi byt cislo");
            }
            double [][] pole = new double[velkost][velkost];
            int i=0;
            int j=0;
            while (scanner.hasNext()){
                //System.out.println("Hello");
                pole[i][j]=scanner.nextDouble();
                j++;
                if(j==velkost){
                    i++;
                    j=0;
                }


            }


            for(int r=0;r<velkost;r++){
                for(int s=0; s<velkost;s++ ){
                    pw.printf("%7.2f ",pole[s][r]);
                }
                pw.println();

            }






        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
