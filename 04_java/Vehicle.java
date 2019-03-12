class Vehicle implements Car, Bus {
	public void distance() {
		System.out.println("distance is " + distance);
	}

	public void speed() {
		System.out.println("car speed is " + Car.speed);
		System.out.println("bus speed is " + Bus.speed);
	}
	
	
	public static void main(String args[]) {
		System.out.println("Vehicle");
		Vehicle v1 = new Vehicle();
		v1.distance();
		v1.speed();
		
		{
			Car c1 = v1;	// this je Vehicle, takže bude aj Car, aj Bus
			Bus b1 = v1;
//			Bus b2 = c1;
			int xx = b1.speed;
			int yy = b1.distance;
			
			Car c2 = new Vehicle();
			int xxx = c2.speed;
//			int yyy = c2.distance;
			
			Vehicle v = new Vehicle();
			// int zxxxxx = v.speed;
			int xxxxx = ((Bus)v).speed;
			int xxxx = ((Car)v).speed;
			int yyyy = v.distance;
			
		}
	}
}
