/**
 * Cvicenie 3
 * @author PB
 */
public class MAxOcc {
	public static char MaxOcc(String s) {
		int chars[] = new int[65536];		// alebo len 256
		int maxOcc = 0;
		char maxChar = 0;
		for(int i=0; i<chars.length; i++) chars[i]=0;
		for(int i=0; i<s.length(); i++) {
			char elem = s.charAt(i);
			chars[elem]++;
			if (chars[elem] > maxOcc) {
				maxOcc = chars[elem];
				maxChar = elem;
			}
		}
		return maxChar;
	}
	
	public static void main(String[] args) {
		System.out.println(MaxOcc(args[0]));
	}
}
