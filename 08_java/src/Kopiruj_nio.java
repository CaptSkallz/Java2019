import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class Kopiruj_nio {

	public static void main(String[] args) {
	    try {
	    	FileInputStream aFile = new FileInputStream(new File("a.txt"));
	    	FileChannel inChannel = aFile.getChannel();
	    	ByteBuffer bufIN = ByteBuffer.allocate(48);
	    	
	    	FileOutputStream bFile = new FileOutputStream(new File("b.txt"));
	    	FileChannel outChannel = bFile.getChannel();
	    	ByteBuffer bufOUT = ByteBuffer.allocate(48);
	    	
	    	int bytesRead = inChannel.read(bufIN); 
	    	bufOUT.clear();
	    	while (bytesRead != -1) {
	    	  bufIN.flip();  
	    	  while(bufIN.hasRemaining())
	    	      bufOUT.put(bufIN.get()); 
	    	  bufOUT.flip();
	    	  outChannel.write(bufOUT);
	    	  bufOUT.clear();
	    	  bufIN.clear();
	    	  bytesRead = inChannel.read(bufIN);
	    	}
	    	aFile.close();
	    	bFile.close();
	    	
	    } catch (Exception E) {
	    	E.printStackTrace();
	    }	
	}
}
