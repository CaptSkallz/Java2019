import java.io.*;

public class Konvertuj {
  public static void main(String[] args) throws IOException {
	  
    File frJm = new File("a.txt");
    File fwJm = new File("a_utf8.txt");
    if (frJm.exists() == true) {
      FileReader fr = new FileReader(frJm);
      BufferedWriter fw = new BufferedWriter(
    		  			    new OutputStreamWriter(
    		  			       new FileOutputStream(fwJm),
    		  			    "UTF-8"));
      int c;
      System.out.println(fr.getEncoding());
      while ((c = fr.read()) != -1)
        fw.write(c);

      fr.close();
      fw.close();
    }
    // utf-8 to 1250   
    File file1 = new File("a_utf8.txt");
    File file2 = new File("a_cp1250.txt");

    InputStreamReader isr = 
    	new InputStreamReader(
			new FileInputStream(file1),
			"UTF-8");
    BufferedReader br = new BufferedReader(isr);
      
    BufferedWriter bw = new BufferedWriter(
    		  			    new OutputStreamWriter(
    		  			       new FileOutputStream(file2),
    		  			    "cp1250"));
      System.out.println(isr.getEncoding());
      for(;;) {
    	 String line = br.readLine();
    	 if (line == null) break;
    	 bw.write(line);
    	 bw.newLine();
      }
      br.close();
      bw.close();
    }   
 }
