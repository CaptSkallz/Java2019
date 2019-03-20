
public class SheepWithAge implements Cloneable {
		private int age;
		private Sheep sheep;
		public SheepWithAge(String name, int age) {
			sheep = new Sheep(name); this.age = age;
		}
		public Object clone() throws CloneNotSupportedException {
			/*
			sheep = (Sheep)sheep.clone();
			return (SheepWithAge)super.clone();
			*/
			SheepWithAge newSheep = (SheepWithAge)super.clone();
			newSheep.sheep = (Sheep)newSheep.sheep.clone();
			return newSheep;
			/**/
	    }
		public String toString(){ return "["+sheep+","+age+"]@"+hashCode(); }
		
		public static void main(String[] args) {
			SheepWithAge holly = new SheepWithAge("Holly",12);
			System.out.println("holly"+holly.toString());	
			try{
				SheepWithAge golly = (SheepWithAge)holly.clone();
				System.out.println("holly"+holly.toString());
				System.out.println("golly"+golly.toString());
				System.out.println(holly.equals(golly));
			}catch(CloneNotSupportedException e){
				System.out.println(":-(");
			}
		}	

	}

