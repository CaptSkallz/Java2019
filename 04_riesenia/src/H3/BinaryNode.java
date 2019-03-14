package H3;

final class BinaryNode {
    private BinaryNode left;
    private String key; 
    private BinaryNode right;
    
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
        if(key.equals(x))
            return true;
        else{
            if(key.compareTo(x) > 0){//left
                if (left == null){
                    return false;
                }
                else {
                    return left.find(x);
                }
            }
            else{
                if(right == null){
                    return false;
                }
                else{
                    return right.find(x);
                }
            }
        }
    }

    void delete(String key) {
      	// cvicenie 4
    }
    public String toString(){
      	return "(" + ((left == null)? "." : left.toString())
                + ", " + key + ", "
                +((right == null)? "." : right.toString()) + ")";  // cvicenie 4
    }
    public String maximalny(){
        if(right==null)
            return this.key;
        else{
            return right.maximalny();
        }
    }
    public String[] toArray() {
      	String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
      	// cvicenie 4
     	return p;
    }
    public BinaryNode insert(String x) {
        if(key.equals(x)){
            return this;
        }
        if(key.compareTo(x) > 0){  // key > x
            if(left == null){
                left = new BinaryNode(x);
            } else {
                left = left.insert(x);
            }
        } else {
            if(right == null){
                right = new BinaryNode(x);
            } else {
                right = right.insert(x);
            }
        }
        return this;
    }
}
