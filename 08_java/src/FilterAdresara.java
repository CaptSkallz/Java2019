import java.io.*;

class FilterAdresara implements FilenameFilter {

  public boolean accept(File dir, String name) {
    File f = new File(dir, name);
    return f.isDirectory();
  }
}