import java.io.*;

class Password {
	
	public static void main(String[] args) {
		Console con = System.console();
		if (con != null) {
			String name = con.readLine("meno:");
			char[] passwd = con.readPassword("heslo:");
			System.out.println(name + "/" + new String(passwd));
		} else {
			System.err.println("no console");
		}
	}
}
