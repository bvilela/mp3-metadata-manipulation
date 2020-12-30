package br.com.bruno.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Utils {
	private static final Logger LOG = LogManager.getLogger(Utils.class);
	
	public static Properties getPropertiesByFullPath(String path) throws IOException {		
		FileInputStream fileInputStream = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fileInputStream);
		fileInputStream.close();
		return prop;
	}
	
	public static boolean getBooleanProperties(Properties prop, String nameProperty) {
		String aux = prop.getProperty(nameProperty);
		if (aux == null || aux.trim().isEmpty()) {
			return false;			
		}
		
		if (aux.equalsIgnoreCase("true") || aux.equalsIgnoreCase("false")) {
			return Boolean.valueOf(aux);
		} 
		else {
			LOG.error("Propriedade '" + nameProperty + "' deve tem valor: 'true' ou 'false'");
			System.exit(-1);
		}
		return false;
	}
	
}
