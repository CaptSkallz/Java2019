
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BNode< E extends Comparable< E>> implements Serializable {
    BNode< E> left;		// ľavý podstrom
    E key;  			// hodnota vrchola
    BNode< E> right;		// pravý podstrom    
    public BNode(BNode< E> left, E key, BNode< E>right) {  // konštruktor  
    	this.left=left; this.key=key; this.right = right;
    } 
    
    public static void main(String args[]) {
    	BNode<Integer> avl = new BNode<>(null, 10, null);
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("out"));
    		 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("out"))) {
    		oos.writeObject(avl);
    		BNode<Integer> avl1 = (BNode<Integer>)ois.readObject();
    		System.out.println(avl1.key);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}