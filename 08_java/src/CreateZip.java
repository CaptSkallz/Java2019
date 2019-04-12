import java.io.*;
import java.util.*;
import java.util.zip.*;
public class CreateZip {
  public static void main(String[] args) {
    try {
    	String[] filenames = {"file1", "file2"};
        String outFilename = "file.zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
        for (int i=0; i<filenames.length; i++) {
            FileInputStream in = new FileInputStream(filenames[i]);
            out.putNextEntry(new ZipEntry(filenames[i]));
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            out.closeEntry();
            in.close();
        }
        out.close();
    } catch (IOException e) {
    }
    try {
        ZipFile zf = new ZipFile("file.zip");
        ZipInputStream in = new ZipInputStream(new FileInputStream("file.zip"));
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
        	ZipEntry entry = (ZipEntry)entries.nextElement();
        	String zipEntryName = entry.getName();
            System.out.println(zipEntryName);

            //ZipEntry entry2 = 
            		in.getNextEntry();
            String outFilename = zipEntryName+".out";
            OutputStream out = new FileOutputStream(outFilename);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            out.close();
        }
        in.close();      
        zf.close();
    } catch (IOException e) {
    }    
}
}
