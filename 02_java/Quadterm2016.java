

public class Quadterm2016 {
	
	public static void main(String[] args) {
		/*
		long start = System.currentTimeMillis();
		String s = "";
		for(int i = 0; i<1000000; i++)
			s += "a";
		System.out.println("elapsed time: " + (System.currentTimeMillis()-start)/1000);
		*/
		long start = System.currentTimeMillis();
		StringBuffer ss = new StringBuffer();
		for(int i = 0; i<1000000; i++)
			ss.append("a");
		String s = ss.toString();
		System.out.println("elapsed time: " + (System.currentTimeMillis()-start)/1000);
		
	}

}
