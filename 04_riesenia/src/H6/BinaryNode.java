package H6;

final class BinaryNode {
	BinaryNode left;
	String key;
	BinaryNode right;

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
		return false; // cvicenie 4
	}

	public BinaryNode insert(String k) {
		if (k.compareTo(this.key) > 0) {
			if (this.right == null) {
				this.right = new BinaryNode(k);
			} else {
				this.right.insert(k);
			}
		} else if (k.compareTo(this.key) < 0) {
			if (this.left == null) {
				this.left = new BinaryNode(k);
			} else {
				this.left.insert(k);
			}
		}
		return this;
	}

	BinaryNode delete(String key) {
		
		if (key.equals(this.key)) {
			if (this.right == null && this.left == null) {
				return null;
			}
			else if (this.left != null ) {
				this.key = this.left.key;
				this.left = this.left.delete(this.key);
				
			
			}
			else if (this.right != null){
				this.key = this.right.key;
				this.right = this.right.delete(this.key);
			}
		}
		else {
			if (key.compareTo(this.key) > 0) {
				if (this.right != null) {
					this.right = this.right.delete(key);
				}
			}
			else {
				if(this.left != null) {
					this.left = this.left.delete(key);
				}
				
			}
		}
		return this;
		// cvicenie 4
	}

	public String toString() {
		String result = this.key;
		if(this.left != null) {
			result+= "(" + this.left.toString() + ")";
		}
		else {
			result+= " null";
		}
		if(this.right != null) {
			result+= "(" + this.right.toString() + ")";
		}
		else {
			result+= " null";
		}
		
		
		return result; // cvicenie 4
	}

	public String[] toArray() {
		String[] p = new String[100]; // 100 asi nie je dobra konstanta :-)
		// cvicenie 4
		return p;
	}
}
