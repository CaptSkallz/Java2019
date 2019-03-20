public class Sheep implements Cloneable {
	protected String name;
	
	public Sheep(String name){ this.name = name; }
	
	public String toString(){ return "("+name+")@"+hashCode(); }
	
	public Object clone() throws CloneNotSupportedException {
		super.clone();
		return new Sheep(this.name); 
	} 
	
	public static void main(String[] args) {
		Sheep dolly = new Sheep("Dolly");
		System.out.println("dolly="+dolly.toString());	
		try{
			Sheep molly = (Sheep)dolly.clone();
			System.out.println("dolly="+dolly.toString());	
			System.out.println("molly="+molly.toString());
			System.out.println(dolly.equals(molly));
		}catch(CloneNotSupportedException e){
			System.out.println(":-(");
		}
	}
}
