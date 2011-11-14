import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ClassReader {
	public static void main(String[] args) throws Exception {
		String f = Constant.PROJECT_DIR + "bin/hentai/Main.class";

		StringBuilder sb = new StringBuilder();

		System.setOut(new PrintStream(new File(Constant.PROJECT_DIR + "tsuuhou.txt")));

		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(new File(f)));

			int c;
			while ((c = input.read()) != -1) {
				String hex = Integer.toHexString(c).toUpperCase();
				if (hex.length() == 1) {
					hex = "0" + hex;
				}
				sb.append(hex);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sb.toString());
	}

}
