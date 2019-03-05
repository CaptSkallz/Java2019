public class Boxing {
  
	public static void unBoxed(int a) {
		a++;
		System.out.println(a);
	}
	
	public static void Boxed(MyInteger a) {
		a.val++;
	}
	
	public static void main(String[] args) {
		/* 
		int bb = 5;
		Integer cc = new Integer(15);
		
		bb = cc;	bb = cc.intValue();
		cc = bb;
        */
		
		
		int b = 5;
		MyInteger c = new MyInteger(15);
			
		
		unBoxed(b);
		Boxed(c);
		System.out.println(b);
		System.out.println(c);
	  }
}
