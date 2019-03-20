import java.io.Serializable;

/**
 * AVLNode je trieda implementujuca uzol AVL stromu
 * @param  url  an absolute URL giving the base location of the image
 * @param  name the location of the image, relative to the url argument
 * @return      the image at the specified URL
 * @see         Image
 */
public class AVLNode  implements Serializable { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
    AVLNode left, right;
    
    public AVLNode (int init) {
  	  x = init;
	  left = null;
	  right = null;
    }
    public int height () {
	  int l = 0;
	  int r = 0;
	  int max = 0;
	  if (left != null) l = left.height () + 1;
	  if (right != null) r = right.height () + 1;
	  if (l > r) max = l;
	  else max = r;
	  return max;
    }
    public int balance () {
	  int l = 0;
	  int r = 0;
	  if (left != null) l = left.height () + 1;
	  if (right != null) r = right.height () + 1;
	  return (r - l);
    }
    public AVLNode insert (int k) {
	  if (k < x) {
	    if (left == null) {
	 	  AVLNode t = new AVLNode (k);
		  left = t;
 	    } else
		  left = left.insert (k);
	  } else {
	    if (right == null) {
		  AVLNode t = new AVLNode (k);
		  right = t;
	    }  else 
		  right = right.insert (k);
	  }
	  if (balance () < -1) {  
	    if (k < left.x) 
		  return rotateRight ();
	    else {  
		  left = left.rotateLeft();
		  return rotateRight();
	    }
	  } else if (balance () > 1) {
	    if (k > right.x)  
		  return rotateLeft();
	    else { 
		  right = right.rotateRight();
		  return rotateLeft();
	    }
	  }
	  return this; 
    }
    public AVLNode rotateLeft () {
	  AVLNode tmp = right;
	  right = tmp.left;
	  tmp.left = this;
	  return tmp;
    }
    public AVLNode rotateRight () {
  	  AVLNode tmp = left;
	  left = tmp.right;
	  tmp.right = this;
	  return tmp;
    }
    public void print () {
	System.out.println ("<" + x + ":" + balance () + "> - <" + (left == null ? "x" : "" + left.x) + ">, <" + (right == null ? "x" : "" + right.x) + ">");
	if (left != null) left.print ();
	if (right != null) right.print ();
    }
}