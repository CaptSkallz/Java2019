package H3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(new File("DataForYou1.txt"));
                PrintWriter pw = new PrintWriter("output.txt");
                ){
            Set<String> mena = new TreeSet<>();
            while (scanner.hasNext())
                mena.add(scanner.next());

            //System.out.println(mena + " " + mena.size())
            for(String m : mena)
                pw.println(m);


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
