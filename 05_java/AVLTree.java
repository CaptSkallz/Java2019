import java.io.*;
class AVLTree implements Serializable  {
	private static final long serialVersionUID = 1L;
	AVLNode root;

    public AVLTree () {
 	  root = null;
    }
    public AVLTree (int x) {
	  root = null;
	  insert (x);
    }
    public void insert (int x) {
	  if (root == null) root = new AVLNode (x);
	  else root = root.insert (x);
    }
    public void print () {
	  root.print ();
    }
    public static void main (String args[]) {
      AVLTree ss = new AVLTree(7);
      for(int i=0; i<11; i++)
        ss.insert((i*17+13)%313);
      ss.print();
      System.out.println("....");
      try {
    	  FileOutputStream out = new FileOutputStream("AVL");
    	  ObjectOutputStream fs = new ObjectOutputStream(out);
    	  fs.writeObject("avl");
    	  fs.writeObject(ss);
    	  fs.flush();
    	  fs.close();
      } catch (Exception e) {
    	  
      }
      try {
    	  FileInputStream in = new FileInputStream("AVL");
    	  ObjectInputStream is = new ObjectInputStream(in);
    	  String str = (String)is.readObject();
    	  System.out.println(str);
    	  AVLTree sss = (AVLTree)is.readObject();
    	  sss.print();
    	  is.close();
      } catch (Exception e) {
    	  System.out.println(e);  	  
      }
    }
}
