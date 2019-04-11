
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TajnaSprava {

	public static void recdir(String path) {
		File fin = new File(path);
		if (fin.isDirectory()) {
			File[] files1 = fin.listFiles(); // bez filtre
			File[] files2 = fin.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getAbsolutePath().length() > 10;
				}
			});
			File[] files = fin.listFiles(pathname -> pathname.getAbsolutePath().length() > 10);
			
			for(File f : files) {
				if (f.isDirectory()) {
					recdir(f.getAbsolutePath());
				}
				else {
					StringBuffer sb = new StringBuffer();
					try ( FileReader fr = new FileReader(f);
						  BufferedReader br = new BufferedReader(fr)) {
						for (;;) {
							String line = br.readLine();
							if (line == null) break;
							sb.append(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (sb.indexOf("TAJNA SPRAVA") >= 0)
						System.out.println(f.getAbsolutePath());
					String pattern = "\\d{3}\\s\\d{3}\\s\\d{3}";
					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(sb.toString());
					if (m.find()) {
						System.out.println("cislo=" + 
								m.group(0) + " obsahuje subor " + f.getAbsolutePath() );
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		File f = new File("tajna-sprava");
		System.out.println("isDirectory = " + f.isDirectory());
		System.out.println("lastModif = " + f.lastModified());
		recdir(f.getAbsolutePath());
	}
}
