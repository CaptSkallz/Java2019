import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ScanInteger {

	public static void main(String[] args) {
		File f = new File("milion.txt");
		try {
			//BufferedOutputStream bos = new BufferedOutputStream(
			//		new FileOutputStream(f));
			PrintStream ps = new PrintStream(f);
			
			long beg = System.currentTimeMillis();
			long sum1 = 0;
			for (int i = 0; i < 10000000; i++) {
				sum1 += i;
				ps.println(i);
			}
			ps.close();
			long start = System.currentTimeMillis();
			System.out.println("write:"+(start-beg));

			

			BufferedReader br = new BufferedReader(new FileReader(f));
			long sum2 = 0;
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				Integer i = Integer.parseInt(line.trim());
				sum2 += i;
			}
			System.out.println(sum1 == sum2);
			long end = System.currentTimeMillis();
			System.out.println("readLine:"+(end-start));
			br.close();
			
			Scanner sc = new Scanner(new FileInputStream(f));
			long sum3 = 0;
			while (sc.hasNextInt())
				sum3 += sc.nextInt();
			System.out.println(sum1 == sum3);
			long end2 = System.currentTimeMillis();
			sc.close();
			System.out.println("ScannerRead:"+(end2-end));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
