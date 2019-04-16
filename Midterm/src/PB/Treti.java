package PB;
import java.util.HashSet;

public class Treti {
	public static void main(String[] args) {
//		{
//			int[][] s = new int[][]{{123456789},null};
//			//int[][] s = new int[][]{{1234},{56789},null};
//			for(int i = 0; i < s.length; i++) {
//				for(int j = 0; j < s[i].length; j++) {
//					System.out.print(s[i][j]);
//				}
//			}
//		}
//		{
//			String s1 = "Java";
//			String s2 = new String(s1);
//			String[] a = new String[]{s1, null};
//			
//			System.out.println(s1 == s2);
//			System.out.println(s1.equals(s2));
//			System.out.println(a[0].equals(a[1]));
//			System.out.println(a[1].equals(a[0]));
//			
//		}
		{
			Integer[]   a = {1};
			Integer[][] b = {a,a};
			Integer[][] c = (Integer[][])b.clone();
			b[0][0] = 99;
			System.out.printf("%d %d %d %d\n", a[0], b[1][0], c[0][0], c[1][0]); 
			System.out.println(a[0] + b[1][0] + c[0][0] + c[1][0]); 
		}
		{
			Object[] finta = {new Pes(), new Macka()};
		}
		{
		     HashSet<Zajac> pp = new HashSet<>();
		     pp.add(new Zajac());
		     pp.add(new Zajac());
		     System.out.println(pp.size());
		}
	}
}
class Pes{}
class Macka{}
class Zajac {
	@Override
	public int hashCode() { return 1; }	
	@Override
	public boolean equals(Object o) { return true; }	
}

