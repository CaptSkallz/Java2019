import java.util.ArrayList;

final class BinaryNode {
    private final BinaryNode left;
    private final String key; 
    private final BinaryNode right;
    
    public BinaryNode(BinaryNode left, String key, BinaryNode right) {
		this.left = left;
		this.key = key;
		this.right = right;
	}
	public BinaryNode(String theKey) {
      key = theKey;
      left = right = null;
    }
    boolean find(String key) {
        if (key.equals(this.key)) {
        	return true;
        }
        if (key.compareTo(this.key) < 0) {
	        if (left != null && left.find(key)) {
	        	return true;
	        }
        }
        else {
        	if (right != null && right.find(key)) {
        		return true;
        	}
        }
        return false;
    	// cvicenie 4	
    }
    public BinaryNode insert (String k) {
    	// cvicenie 4
    	return null;
    }
    void delete(String key) {
      	// cvicenie 4
    }
    public String toString(){
      	return "";  // cvicenie 4
    }
    public String[] toArray() {
      	String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
      	// cvicenie 4
     	return p;
    }
    ArrayList<String> toList() {
      	ArrayList<String> al = new ArrayList<String>();
      	// cvicenie 4
      	return al;
      }
}
