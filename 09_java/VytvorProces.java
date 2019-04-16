import java.io.FileWriter;
import java.io.IOException;

public class VytvorProces {

	public static void main(String[] args) { //throws IOException {
		try {
		System.out.println(args[0]);
		final ProcessBuilder pb = new ProcessBuilder("java", "VytvorProces", "druhy");
		if (args[0].equals("prvy"))
			pb.start();
		FileWriter fw = new FileWriter(args[0]);
		while (true) {
			fw.write(args[0]);  
			fw.flush();
		}
		//fw.close();
		} catch (Exception e) {}
	}
}
