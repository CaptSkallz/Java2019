import java.io.*;

public class DirList {

  public static void main(String[] args) {
    String menaAktDir = System.getProperty("user.dir");
    File aktDir = new File(menaAktDir);

    String[] mena = aktDir.list();
    if (mena != null)
    for (int i = 0;  i < mena.length;  i++)
      if (mena[i].indexOf(".java")>0)
        System.out.println(mena[i]);
    // ---
    File[] subory = aktDir.listFiles();
    if (subory != null)
    for (int i = 0;  i < subory.length;  i++)
      if (subory[i].length() > 1024)	
        System.out.println(subory[i].getName()+"\t"+subory[i].length());
    // ---
    FilterPripony FilterPr = new FilterPripony(".java");
    String[] mena1 = aktDir.list(FilterPr);
    if (mena1 != null)
    for (int i = 0;  i < mena1.length;  i++)
       System.out.println(mena1[i]);
    //-- JAVA8
    String[] mena2 = aktDir.list((dir, name) -> name.lastIndexOf(".java") > 0 );
    if (mena2 != null)
    for (int i = 0;  i < mena2.length;  i++)
       System.out.println(mena2[i]);
    // ---
    FilterVelkosti FilterVel = new FilterVelkosti(2048);
    File[] subory1 = aktDir.listFiles(FilterVel);
    if (subory1 != null)
    for (int i = 0;  i < subory1.length;  i++)
       System.out.println(subory1[i].getName()+"\t"+subory1[i].length());
    // --- JAVA 8
    File[] subory2 = aktDir.listFiles((dir, name) -> new File(dir, name).length() > 2048 );
    if (subory2 != null)
    for (int i = 0;  i < subory2.length;  i++)
       System.out.println(subory2[i].getName()+"\t"+subory2[i].length());
  }
}
