package helper;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

public class DocHelper {

	public static void main(String... args) throws Exception {
		String basePak = "com.uvwxyz.intv.vo";
		Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader()
				.getResources(basePak.replace(".", "/"));
		while (dirs.hasMoreElements()) {
			URL url = dirs.nextElement();
			String protocol = url.getProtocol();
			if ("file".equals(protocol)) {
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				File dir = new File(filePath);
				File[] files = dir.listFiles(new FileFilter() {
					public boolean accept(File file) {
						return (file.getName().endsWith(".class"));
					}
				});
				for (File file : files) {
					String className = basePak + "."
							+ file.getName().replace(".class", "");
					System.out.println(className);

					Class<?> cls = Class.forName(className);
				}
			}
		}

	}

}
