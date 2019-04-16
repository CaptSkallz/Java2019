import java.util.*;
import java.io.*;
class Receiver extends Thread {
  private PipedReader in;
  public Receiver(Sender sender) throws IOException {
    in = new PipedReader(sender.getPipedWriter());
  }
  public void run() {
    try {
      while(true) {
        System.out.println("Read: " + (char)in.read());
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
