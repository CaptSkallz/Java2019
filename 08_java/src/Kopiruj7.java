import java.io.*;

public class Kopiruj7 {
  public static void main(String[] args) throws IOException {
   // byte stream
    File frName = new File("a.txt");
    try (
    		FileInputStream fr = new FileInputStream(frName);
    		FileOutputStream fw = new FileOutputStream(new File("b.txt"))
    ) {
      int c;
      while ((c = fr.read()) != -1)
        fw.write(c);
    }    
  }
}
