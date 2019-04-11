
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Triedenie {

	public static void main(String[] args) {
    	try ( Scanner sc = new Scanner(new File("vstup.txt")) ) {
	    	while (sc.hasNextInt()) {
	    		int n = sc.nextInt();
	    		System.out.println("n = " + n);
	    	}
    	} catch (FileNotFoundException e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    	//sc.close();

	}

}
