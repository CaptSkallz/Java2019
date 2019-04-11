package H6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Triedenie {

	public static void main(String[] args) {
		try(Scanner s = new Scanner(new File("vstup.txt"))){
			Set<Integer> mnoz = new TreeSet<>();
			while(s.hasNextInt()) {
				int p = s.nextInt();
				mnoz.add(p);
			}
			System.out.println(mnoz);
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

}
	}
