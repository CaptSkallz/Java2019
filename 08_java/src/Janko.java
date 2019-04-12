import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Janko {
	public static void main(String[] args) {
		try {
			ObjectInputStream is = 	new ObjectInputStream(new FileInputStream("marienka.ab"));
			Adresar a = (Adresar)is.readObject();
			is.close();
			System.out.println(a.ab);
		} catch (Exception E) {
			System.err.println(E.getMessage());
		}
		
	}

}
