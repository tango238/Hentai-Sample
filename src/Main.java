import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.run();
	}

	public void run() throws Exception {
		HentaiClassLoader loader = new HentaiClassLoader();
		Class<?> clazz = loader.loadClass(Constant.PROJECT_DIR + "tsuuhou.txt");
		Object newInstance = clazz.newInstance();
		Method method = clazz.getDeclaredMethod("call110");
		method.invoke(newInstance);
	}
	
}
