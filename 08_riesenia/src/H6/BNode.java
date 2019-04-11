package H6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BNode< E extends Comparable< E>> implements Serializable {
    E key;  			// hodnota vrchola
    BNode< E> right;		// pravý podstrom    
    BNode< E> left;		// ľavý podstrom
    public BNode(BNode< E> left, E key, BNode< E>right) {  // konštruktor  
    	this.left=left; this.key=key; this.right = right;
    } 
    
    public static void foo() {
    	
    }
    
    public static void main(String args[]) {
    	BNode<Integer> strom = new BNode<Integer>(
    			new BNode<Integer>(null, 1, null),
    			2,
    			new BNode<Integer>(null, 3, null));
    	try(ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("strom.out"))) {
			oos.writeObject(strom);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	try(ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("strom.out"))) {
    		BNode<Integer> strom1 = (BNode<Integer>)ois.readObject();
    		System.out.println(strom1.key);
    		System.out.println(strom1.left.key);
    		System.out.println(strom1.right.key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}