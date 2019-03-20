public class BritishSheep extends Sheep  implements Cloneable {
	private int age;
	public BritishSheep(String name, int age) {
		super(name); this.age = age;
	}
	public Object clone() throws CloneNotSupportedException {
		return new BritishSheep(name,age);
    }
	public String toString(){ return "["+name+","+age+"]@"+hashCode(); }
	
	public static void main(String[] args) {
		BritishSheep holly = new BritishSheep("Holly",12);
		System.out.println("holly"+holly.toString());	
		try{
			BritishSheep golly = (BritishSheep)holly.clone();
			System.out.println("holly"+holly.toString());	
			System.out.println("gooly"+golly.toString());
			System.out.println(holly.equals(golly));
		}catch(CloneNotSupportedException e){
			System.out.println(":-(");
		}
	}	

}
