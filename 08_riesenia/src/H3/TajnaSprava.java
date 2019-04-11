package H3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TajnaSprava {
    public static void hladajSpravu(File momentalnyFolder){
        if(!momentalnyFolder.isDirectory()){
            try(
                    Scanner fileScanner=new Scanner(momentalnyFolder);
                    ){
                if(fileScanner.hasNext()){
                    if(fileScanner.nextLine().contains("TAJNA SPRAVA")){
                        System.out.println(momentalnyFolder.getAbsolutePath());
                    }
                }
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

        else {
            for (File subor : momentalnyFolder.listFiles()) {
                hladajSpravu(subor);
            }
        }
    }

    public static void main(String[] args) {
        File folder=new File("tajna-sprava");
        hladajSpravu(folder);
    }
}
