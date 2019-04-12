import java.io.*;

class FilterVelkosti implements FilenameFilter {
  int velkost;
  FilterVelkosti(int velkost) {
    this.velkost = velkost;
  }
  public boolean accept(File dir, String name) {
    File f = new File(dir, name);
    if (f.length() > velkost)
      return true;
    else
      return false;
  }
}

