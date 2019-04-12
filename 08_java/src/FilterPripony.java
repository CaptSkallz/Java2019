import java.io.*;
class FilterPripony implements FilenameFilter {
  String maska;
  FilterPripony(String maska) {
     this.maska = maska;
  }
  public boolean accept(File dir, String name) {
    if (name.lastIndexOf(maska) > 0)
      return true;
    else
      return false;
  }
}
