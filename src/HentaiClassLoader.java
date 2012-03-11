import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * HendaiClassLoader
 * @author tango
 */
public class HentaiClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		if (className.startsWith("java")) {
			return Class.forName(className);
		}
		if (className.endsWith(".txt")) {
			Class<?> clazz = defineClass(className);
			if (clazz != null) {
				return clazz;
			}
		}
		return super.loadClass(className);
	}

	protected Class<?> defineClass(String path) {
		Class<?> clazz;
		byte[] bytes = getBytes(path);
		clazz = defineClass("hentai.Main", bytes, 0, bytes.length);
		resolveClass(clazz);
		return clazz;
	}

	protected byte[] getBytes(String path) {
		byte[] bytes = null;

		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(new File(path)));
			byte[] buf = new byte[8192];
			StringBuilder sb = new StringBuilder();
            while (bis.read(buf, 0, buf.length) != -1) {
                sb.append(new String(buf));
            }
			String source = sb.toString().trim().toLowerCase();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int i = 0; i < source.length(); i += 2) {
				int b = Integer.parseInt(source.substring(i, i + 2), 16);
				baos.write(b);
			}
			bytes = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}
}
