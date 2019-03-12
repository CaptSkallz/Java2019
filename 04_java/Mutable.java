public class Mutable {
	private int x;
	public Mutable(int x) {
		super();
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return "Mutable [x=" + x + "]";
	}
	public static void main(String[] args) {
		Mutable obj1 = new Mutable(77);
		Mutable obj2 = obj1;
		System.out.println(obj1);
		System.out.println(obj2);
		obj1.setX(999);
		System.out.println(obj1);
		System.out.println(obj2);
	}

}
