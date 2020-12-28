package br.com.bruno.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Utils {
	public static Properties getPropertiesByFullPath(String path) throws IOException {		
		FileInputStream fileInputStream = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fileInputStream);
		fileInputStream.close();
		return prop;
	}
}
