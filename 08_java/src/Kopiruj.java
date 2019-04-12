import java.io.*;

public class Kopiruj {
  public static void main(String[] args) throws IOException {
   // byte stream
    File frName = new File("a.txt");
	FileInputStream fr = new FileInputStream(frName);
	FileOutputStream fw = new FileOutputStream(new File("b.txt"));
	/*
    long dlzka = frName.length();
	for (long i = 0;  i < dlzka;  i++)
	  fw.write(fr.read());
	*/
	int c;
    while ((c = fr.read()) != -1)
        fw.write(c);
    
    fr.close();
    fw.close();
  }
}
