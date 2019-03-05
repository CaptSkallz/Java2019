abstract class Animal {
	abstract void sound();
	public static void main(String[] args) {
		Animal[] animals = { new Dog(), new Cat()};
		for(Animal a:animals)
			a.sound();
		for(Animal a:animals)
			if (a instanceof Dog)
				System.out.println("a je to pes");
			else 
				System.out.println("nie je to pes");
	}
}

class Dog extends Animal {
	public void sound() {
		System.out.println("haw-haw");
	}
}
class Cat extends Animal {
	public void sound() {
		System.out.println("mnaw-mnaw");
	}
}
