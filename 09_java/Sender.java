import java.util.*;
import java.io.*;
class Sender extends Thread {
  private Random rand = new Random();
  private PipedWriter out = new PipedWriter();
  public PipedWriter getPipedWriter() { return out; }
  public void run() {
    while(true) {
      for(char c = 'A'; c <= 'z'; c++) {
        try {
          out.write(c);
          sleep(rand.nextInt(500));
        } catch(Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
