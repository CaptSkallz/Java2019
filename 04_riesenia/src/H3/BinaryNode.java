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
    boolean find(String x) {
        if(x == null) return false;

        if(x.equals(key)){
            return true;
        }
        if(x.compareTo(key) < 0) {
            if (left == null) return false;
            return left.find(x);
        }
        else {
            if(right ==  null) return false;
            return right.find(x);
        }
    }
    public BinaryNode insert (String x) {

        if(x == null) return this;

        if(x.equals(key)){
            return this;
        }
        if(x.compareTo(key) < 0) {
            if (left == null) {
                return new BinaryNode(new BinaryNode(x),key,right);
            }
            return new BinaryNode(left.insert(x),key,right);
        }
        else {
            if(right ==  null) return new BinaryNode(left,key,new BinaryNode(x));
            return new BinaryNode(left, key,right.insert(x));
        }
    	// cvicenie 4
    }
    void delete(String key) {
      	// cvicenie 4
    }
    public String toString(){
        return "("+((left == null)?"NULL":left)+
                ", key: "+ key + " ," +
                ((right == null)?"NULL":right) + ")";  // cvicenie 4
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
