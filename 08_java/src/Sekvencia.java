import java.io.*;

public class Sekvencia {

  public static void main(String[] args) throws IOException {
/*	  
    String[] listFileNames = { "a.txt", "b.txt" };
    ListOfFiles mylist = new ListOfFiles(listFileNames);
    SequenceInputStream s = new SequenceInputStream(mylist);
    */
    try {
      FileInputStream Fa = new FileInputStream("a.txt");	
      FileInputStream Fb = new FileInputStream("b.txt");	
      SequenceInputStream s = new SequenceInputStream(Fa, Fb);
      int c;
      while ((c = s.read()) != -1)
        System.out.write(c);
      Fa.close();
      Fb.close();
      s.close();
    } catch(Exception e) { }
  }
}
