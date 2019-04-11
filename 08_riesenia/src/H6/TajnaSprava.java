package H6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TajnaSprava {
	public static void rek(File f) {
		if(f.isDirectory()) {
			File[] pole = f.listFiles(
					pathname -> (pathname.isDirectory() || pathname.length() <= 1000)
			);		
			for(File f1 : pole) {
				rek(f1);
			}
		}
		else {
			
			if(nachadza(f)) {
				System.out.println(f.getAbsolutePath());
			}
		}
	}
	
	public static boolean nachadza (File f) {
		try(Scanner s = new Scanner(f)){
			StringBuffer sb = new StringBuffer();
			while(s.hasNextLine()) {
				sb.append(s.nextLine());
			}
		//	if(sb.toString().contains("TAJNA SPRAVA")) {
			Pattern pattern = Pattern.compile("\\d{3}\\s\\d{3}\\s\\d{3}");
			Matcher m = pattern.matcher(sb.toString());
			if(m.find()) {
				//System.out.println(sb);
				System.out.println(m.group(0));
				return true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public static void main(String[] args) {
		rek(new File("tajna-sprava"));
	}
}
