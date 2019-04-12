import java.io.*;

public class Kopiruj2 {
  public static void main(String[] args)  {
    // byte stream
	try { 
	    FileInputStream fr = null;
	    FileOutputStream fw = null;
	    File frName = new File("a.txt");
	    try {
			fr = new FileInputStream(frName);
	    	fw = new FileOutputStream(new File("b.txt"));
	    	int c;
	    	while ((c = fr.read()) != -1)
	    		fw.write(c);
	    } catch (FileNotFoundException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } finally {
	    	if (fr != null) fr.close();
	    	if (fw != null) fw.close();
	    }
	} catch (IOException e) {
    	System.err.println(e.getMessage());
    	e.printStackTrace();		
	}
  }
}
